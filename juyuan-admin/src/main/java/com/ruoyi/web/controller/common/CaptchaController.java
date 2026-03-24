/*     */ package com.ruoyi.web.controller.common;
/*     */ 
/*     */ import cn.hutool.core.util.RandomUtil;
/*     */ import com.google.code.kaptcha.Producer;
/*     */ import com.ruoyi.common.config.RuoYiConfig;
/*     */ import com.ruoyi.common.constant.Constants;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.redis.RedisCache;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.sign.Base64;
/*     */ import com.ruoyi.common.utils.uuid.IdUtils;
/*     */ import com.ruoyi.system.service.ISysConfigService;
/*     */ import com.ruoyi.system.service.ISysEmailService;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.annotation.Resource;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.util.FastByteArrayOutputStream;
/*     */ import org.springframework.web.bind.annotation.GetMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.bind.annotation.RestController;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @RestController
/*     */ public class CaptchaController
/*     */ {
/*  35 */   private static final Logger log = LoggerFactory.getLogger(CaptchaController.class);
/*     */ 
/*     */   
/*     */   @Resource(name = "captchaProducer")
/*     */   private Producer captchaProducer;
/*     */ 
/*     */   
/*     */   @Resource(name = "captchaProducerMath")
/*     */   private Producer captchaProducerMath;
/*     */   
/*     */   @Autowired
/*     */   private RedisCache redisCache;
/*     */   
/*     */   @Autowired
/*     */   private ISysConfigService configService;
/*     */   
/*     */   @Autowired
/*     */   private ISysEmailService sysEmailService;
/*     */ 
/*     */   
/*     */   @GetMapping({"/sendEmailCaptcha"})
/*     */   public AjaxResult sendEmailCaptcha(@RequestParam("email") String email) {
/*  57 */     if (!"true".equals(this.configService.selectConfigByKey("sys.account.registerUser"))) {
/*  58 */       return AjaxResult.error("当前系统没有开启注册功能！");
/*     */     }
/*     */     
/*  61 */     if (StringUtils.isEmpty(email)) {
/*  62 */       return AjaxResult.error("邮箱格式不正确");
/*     */     }
/*     */ 
/*     */     
/*  66 */     if (this.redisCache.getCacheObject("captcha_codes:" + email) != null) {
/*  67 */       return AjaxResult.error("验证码已发送，请稍后再试");
/*     */     }
/*     */ 
/*     */     
/*  71 */     String code = RandomUtil.randomNumbers(6);
/*  72 */     String uuid = IdUtils.simpleUUID();
/*  73 */     String verifyKey = "captcha_codes:" + uuid;
/*  74 */     log.info("发送邮件验证码：{}", code);
/*     */     try {
/*  76 */       this.sysEmailService.sendVerificationCode(email, code);
/*  77 */       this.redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
/*  78 */       return AjaxResult.success().put("uuid", uuid);
/*  79 */     } catch (Exception e) {
/*  80 */       return AjaxResult.error("邮件发送失败：" + e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   @GetMapping({"/captchaImage"})
/*     */   public AjaxResult getCode(HttpServletResponse response) throws IOException {
/*  86 */     AjaxResult ajax = AjaxResult.success();
/*  87 */     boolean captchaEnabled = this.configService.selectCaptchaEnabled();
/*  88 */     ajax.put("captchaEnabled", Boolean.valueOf(captchaEnabled));
/*  89 */     if (!captchaEnabled) {
/*  90 */       return ajax;
/*     */     }
/*     */ 
/*     */     
/*  94 */     String uuid = IdUtils.simpleUUID();
/*  95 */     String verifyKey = "captcha_codes:" + uuid;
/*     */     
/*  97 */     String capStr = null, code = null;
/*  98 */     BufferedImage image = null;
/*     */ 
/*     */     
/* 101 */     String captchaType = RuoYiConfig.getCaptchaType();
/* 102 */     if ("math".equals(captchaType)) {
/* 103 */       String capText = this.captchaProducerMath.createText();
/* 104 */       capStr = capText.substring(0, capText.lastIndexOf("@"));
/* 105 */       code = capText.substring(capText.lastIndexOf("@") + 1);
/* 106 */       image = this.captchaProducerMath.createImage(capStr);
/* 107 */     } else if ("char".equals(captchaType)) {
/* 108 */       capStr = code = this.captchaProducer.createText();
/* 109 */       image = this.captchaProducer.createImage(capStr);
/*     */     } 
/*     */     
/* 112 */     this.redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
/*     */     
/* 114 */     FastByteArrayOutputStream os = new FastByteArrayOutputStream();
/*     */     try {
/* 116 */       ImageIO.write(image, "jpg", (OutputStream)os);
/* 117 */     } catch (IOException e) {
/* 118 */       return AjaxResult.error(e.getMessage());
/*     */     } 
/*     */     
/* 121 */     ajax.put("uuid", uuid);
/* 122 */     ajax.put("img", Base64.encode(os.toByteArray()));
/* 123 */     return ajax;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/common/CaptchaController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */