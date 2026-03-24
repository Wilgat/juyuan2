package com.ruoyi.iotlighting.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.iotlighting.mapper.TerSensorStatusMapper;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysEmailService;

import java.io.File;                    // ← FIXED: this import was missing
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Monitoring scheduled tasks
 */
@Component("monitorTask")
public class MonitorTast {

    private static final Logger log = LoggerFactory.getLogger(MonitorTast.class);

    @Autowired
    private ISysEmailService sysEmailService;

    @Autowired
    private TerSensorStatusMapper terSensorStatusMapper;

    @Autowired
    private ISysConfigService sysConfigService;

    /**
     * Monitor iOS package expiration (remind 90 days + buffer)
     */
    public void monitorIosPackage() {
        String dateConfig = sysConfigService.selectConfigByKey("ios.package.date");
        String expiredConfig = sysConfigService.selectConfigByKey("ios.package.expired");

        DateTime dateTime = DateUtil.offsetDay(DateUtil.parse(dateConfig), 90);

        long daysLeft = DateUtil.between(dateTime, new Date(), DateUnit.DAY);
        if (daysLeft <= Integer.parseInt(expiredConfig)) {
            String emailConfig = sysConfigService.selectConfigByKey("email.alert.address");
            String[] emailArray = emailConfig.split(",");

            String text = "【Dr.sense】iOS 包还有 " + expiredConfig + " 天到期，请及时更新！";
            String title = "【Dr.sense】iOS 包到期提醒";

            sysEmailService.sendEmail(emailArray, title, text);
        }
    }

    /**
     * Monitor whether the latest sensor data update time exceeds the threshold
     * If timeout → send alert email + write flag file
     */
    public void monitorDataUpdate() {
        String dataConfig = sysConfigService.selectConfigByKey("data.update.expired");
        Date lastUpdateTime = terSensorStatusMapper.getTerLightStatusUpdateTime();

        log.info("告警配置：{} 分钟，数据最后更新时间：{}", dataConfig, lastUpdateTime);

        int updateFlag = 0;

        if (lastUpdateTime != null) {
            long millisElapsed = System.currentTimeMillis() - lastUpdateTime.getTime();
            long thresholdMillis = Integer.parseInt(dataConfig) * 60L * 1000L;

            if (millisElapsed > thresholdMillis) {
                String emailStr = sysConfigService.selectConfigByKey("email.alert.address");
                String[] emailArray = emailStr.split(",");

                String text = "【Dr.sense】数据更新时间超过 " + dataConfig + " 分钟，请及时处理！";
                String title = "【Dr.sense】数据更新异常提醒";

                sysEmailService.sendEmail(emailArray, title, text);
                updateFlag = 1;
            }
        }

        try {
            // Write 0 or 1 to flag file (used by other systems/scripts?)
            FileUtils.writeStringToFile(
                new File("/app/server/updateFlag"),
                String.valueOf(updateFlag),
                "UTF-8"
            );
        } catch (Exception e) {
            log.error("保存数据更新标记文件失败", e);
        }
    }
}