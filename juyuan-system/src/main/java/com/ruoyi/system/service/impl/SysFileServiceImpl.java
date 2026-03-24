/*    */ package com.ruoyi.system.service.impl;
/*    */ 
/*    */ import cn.hutool.core.util.IdUtil;
/*    */ import com.ruoyi.system.domain.SysImgFile;
/*    */ import com.ruoyi.system.mapper.SysFileMapper;
/*    */ import com.ruoyi.system.service.ISysFileService;
/*    */ import java.io.IOException;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.web.multipart.MultipartFile;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class SysFileServiceImpl
/*    */   implements ISysFileService
/*    */ {
/*    */   @Autowired
/*    */   private SysFileMapper sysFileMapper;
/*    */   
/*    */   public SysImgFile selectSysFileById(Long id) {
/* 28 */     return this.sysFileMapper.selectSysFileById(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public SysImgFile uploadFileToDb(MultipartFile file) {
/* 33 */     SysImgFile sysFile = new SysImgFile();
/* 34 */     sysFile.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/* 35 */     sysFile.setFileName(file.getOriginalFilename());
/*    */     try {
/* 37 */       sysFile.setData(file.getBytes());
/* 38 */       this.sysFileMapper.insertSysFile(sysFile);
/* 39 */     } catch (IOException e) {
/* 40 */       throw new RuntimeException(e);
/*    */     } 
/* 42 */     return sysFile;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysFileServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */