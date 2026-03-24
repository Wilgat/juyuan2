/*    */ package com.ruoyi.system.service.impl;
/*    */ 
/*    */ import com.ruoyi.system.service.ISysEmailService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.mail.SimpleMailMessage;
/*    */ import org.springframework.mail.javamail.JavaMailSender;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class SysEmailServiceImpl
/*    */   implements ISysEmailService
/*    */ {
/*    */   @Autowired
/*    */   private JavaMailSender javaMailSender;
/*    */   @Value("${spring.mail.username}")
/*    */   private String username;
/*    */   
/*    */   public void sendVerificationCode(String toEmail, String code) {
/* 21 */     SimpleMailMessage message = new SimpleMailMessage();
/* 22 */     message.setFrom(this.username);
/* 23 */     message.setTo(toEmail);
/* 24 */     message.setSubject("验证码通知");
/* 25 */     message.setText("您的验证码是：" + code + "，有效期为2分钟");
/* 26 */     this.javaMailSender.send(message);
/*    */   }
/*    */ 
/*    */   
/*    */   public void sendEmail(String[] toEmail, String title, String text) {
/* 31 */     SimpleMailMessage message = new SimpleMailMessage();
/* 32 */     message.setFrom(this.username);
/* 33 */     message.setTo(toEmail);
/* 34 */     message.setSubject(title);
/* 35 */     message.setText(text);
/* 36 */     this.javaMailSender.send(message);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysEmailServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */