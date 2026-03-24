/*    */ package com.ruoyi.system.service.impl;
/*    */ 
/*    */ import com.ruoyi.system.domain.SysNotice;
/*    */ import com.ruoyi.system.mapper.SysNoticeMapper;
/*    */ import com.ruoyi.system.service.ISysNoticeService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
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
/*    */ public class SysNoticeServiceImpl
/*    */   implements ISysNoticeService
/*    */ {
/*    */   @Autowired
/*    */   private SysNoticeMapper noticeMapper;
/*    */   
/*    */   public SysNotice selectNoticeById(Long noticeId) {
/* 30 */     return this.noticeMapper.selectNoticeById(noticeId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<SysNotice> selectNoticeList(SysNotice notice) {
/* 42 */     return this.noticeMapper.selectNoticeList(notice);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int insertNotice(SysNotice notice) {
/* 54 */     return this.noticeMapper.insertNotice(notice);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int updateNotice(SysNotice notice) {
/* 66 */     return this.noticeMapper.updateNotice(notice);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int deleteNoticeById(Long noticeId) {
/* 78 */     return this.noticeMapper.deleteNoticeById(noticeId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int deleteNoticeByIds(Long[] noticeIds) {
/* 90 */     return this.noticeMapper.deleteNoticeByIds(noticeIds);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysNoticeServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */