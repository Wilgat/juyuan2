/*    */ package com.ruoyi.iotlighting.service.impl;
/*    */ 
/*    */ import cn.hutool.core.util.ObjectUtil;
/*    */ import com.ruoyi.common.annotation.DataScope;
/*    */ import com.ruoyi.iotlighting.domain.SysStaff;
/*    */ import com.ruoyi.iotlighting.mapper.SysStaffMapper;
/*    */ import com.ruoyi.iotlighting.service.ISysStaffService;
/*    */ import com.ruoyi.system.service.ISysUserService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
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
/*    */ 
/*    */ @Service
/*    */ public class SysStaffServiceImpl
/*    */   implements ISysStaffService
/*    */ {
/*    */   @Autowired
/*    */   private SysStaffMapper sysStaffMapper;
/*    */   @Autowired
/*    */   private ISysUserService sysUserService;
/*    */   
/*    */   @DataScope(deptAlias = "d")
/*    */   public SysStaff selectSysStaffByUserId(Long userId) {
/* 38 */     return this.sysStaffMapper.selectSysStaffByUserId(userId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @DataScope(deptAlias = "d")
/*    */   public List<SysStaff> selectSysStaffList(SysStaff sysStaff) {
/* 50 */     return this.sysStaffMapper.selectSysStaffList(sysStaff);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int insertSysStaff(SysStaff sysStaff) {
/* 61 */     return this.sysStaffMapper.insertSysStaff(sysStaff);
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
/*    */   @Transactional(rollbackFor = {Exception.class})
/*    */   public int updateSysStaff(SysStaff sysStaff) {
/* 74 */     List<Long> roleIdList = sysStaff.getRoleKeyList();
/* 75 */     Long[] roleIds = roleIdList.<Long>toArray(new Long[0]);
/* 76 */     this.sysUserService.insertUserAuth(sysStaff.getUserId(), roleIds);
/* 77 */     if (ObjectUtil.isEmpty(sysStaff.getStaffId())) {
/* 78 */       return this.sysStaffMapper.insertSysStaff(sysStaff);
/*    */     }
/* 80 */     return this.sysStaffMapper.updateSysStaff(sysStaff);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/SysStaffServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */