package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.ruoyi.framework.config.MyBatisConfig;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        MyBatisConfig.class          // ← This excludes the problematic custom MyBatisConfig
})
@MapperScan(basePackages = {
        "com.ruoyi.**.mapper",           // scans framework mappers
        "com.ruoyi.iotlighting.**.mapper" // scans your custom OTA mappers in the JAR
})
public class RuoYiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  若依启动成功   ლ(´ڡ`ლ)ﾞ  \n .-------.       ____     __        \n |  _ _   \\      \\   \\   /  /    \n | ( ' )  |       \\  _. /  '       \n |(_ o _) /        _( )_ .'         \n | (_,_).' __  ___(_ o _)'          \n |  |\\ \\  |  ||   |(_,_)'         \n |  | \\ `'   /|   `-'  /           \n |  |  \\    /  \\      /           \n ''-'   `'-'    `-..-'              ");
    }
}