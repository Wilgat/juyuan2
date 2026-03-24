/*     */ package com.ruoyi.system.service.impl;
/*     */ 
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.core.domain.entity.SysRole;
/*     */ import com.ruoyi.common.core.domain.entity.SysUser;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.bean.BeanValidators;
/*     */ import com.ruoyi.common.utils.spring.SpringUtils;
/*     */ import com.ruoyi.system.domain.SysPost;
/*     */ import com.ruoyi.system.domain.SysUserPost;
/*     */ import com.ruoyi.system.domain.SysUserRole;
/*     */ import com.ruoyi.system.mapper.SysPostMapper;
/*     */ import com.ruoyi.system.mapper.SysRoleMapper;
/*     */ import com.ruoyi.system.mapper.SysUserMapper;
/*     */ import com.ruoyi.system.mapper.SysUserPostMapper;
/*     */ import com.ruoyi.system.mapper.SysUserRoleMapper;
/*     */ import com.ruoyi.system.service.ISysConfigService;
/*     */ import com.ruoyi.system.service.ISysUserService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.validation.Validator;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import org.springframework.util.CollectionUtils;
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class SysUserServiceImpl
/*     */   implements ISysUserService
/*     */ {
/*  38 */   private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private SysUserMapper userMapper;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private SysRoleMapper roleMapper;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private SysPostMapper postMapper;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private SysUserRoleMapper userRoleMapper;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private SysUserPostMapper userPostMapper;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private ISysConfigService configService;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   protected Validator validator;
/*     */ 
/*     */   
/*     */   @DataScope(deptAlias = "d", userAlias = "u")
/*     */   public List<SysUser> selectUserList(SysUser user) {
/*  71 */     return this.userMapper.selectUserList(user);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @DataScope(deptAlias = "d", userAlias = "u")
/*     */   public List<SysUser> selectAllocatedList(SysUser user) {
/*  83 */     return this.userMapper.selectAllocatedList(user);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @DataScope(deptAlias = "d", userAlias = "u")
/*     */   public List<SysUser> selectUnallocatedList(SysUser user) {
/*  95 */     return this.userMapper.selectUnallocatedList(user);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SysUser selectUserByUserName(String userName) {
/* 106 */     return this.userMapper.selectUserByUserName(userName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SysUser selectUserById(Long userId) {
/* 117 */     return this.userMapper.selectUserById(userId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String selectUserRoleGroup(String userName) {
/* 128 */     List<SysRole> list = this.roleMapper.selectRolesByUserName(userName);
/* 129 */     if (CollectionUtils.isEmpty(list)) {
/* 130 */       return "";
/*     */     }
/* 132 */     return list.stream().map(SysRole::getRoleName).collect(Collectors.joining(","));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String selectUserPostGroup(String userName) {
/* 143 */     List<SysPost> list = this.postMapper.selectPostsByUserName(userName);
/* 144 */     if (CollectionUtils.isEmpty(list)) {
/* 145 */       return "";
/*     */     }
/* 147 */     return list.stream().map(SysPost::getPostName).collect(Collectors.joining(","));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkUserNameUnique(SysUser user) {
/* 158 */     Long userId = Long.valueOf(StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId().longValue());
/* 159 */     SysUser info = this.userMapper.checkUserNameUnique(user.getUserName());
/* 160 */     if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
/* 161 */       return false;
/*     */     }
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkPhoneUnique(SysUser user) {
/* 174 */     Long userId = Long.valueOf(StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId().longValue());
/* 175 */     SysUser info = this.userMapper.checkPhoneUnique(user.getPhonenumber());
/* 176 */     if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
/* 177 */       return false;
/*     */     }
/* 179 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkEmailUnique(SysUser user) {
/* 190 */     Long userId = Long.valueOf(StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId().longValue());
/* 191 */     SysUser info = this.userMapper.checkEmailUnique(user.getEmail());
/* 192 */     if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
/* 193 */       return false;
/*     */     }
/* 195 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkUserAllowed(SysUser user) {
/* 205 */     if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin()) {
/* 206 */       throw new ServiceException("不允许操作超级管理员用户");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkUserDataScope(Long userId) {
/* 217 */     if (!SysUser.isAdmin(SecurityUtils.getUserId())) {
/* 218 */       SysUser user = new SysUser();
/* 219 */       user.setUserId(userId);
/* 220 */       List<SysUser> users = ((SysUserServiceImpl)SpringUtils.getAopProxy(this)).selectUserList(user);
/* 221 */       if (StringUtils.isEmpty(users)) {
/* 222 */         throw new ServiceException("没有权限访问用户数据！");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public int insertUser(SysUser user) {
/* 237 */     int rows = this.userMapper.insertUser(user);
/*     */     
/* 239 */     insertUserPost(user);
/*     */     
/* 241 */     insertUserRole(user);
/* 242 */     return rows;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public boolean registerUser(SysUser user) {
/* 254 */     boolean success = (this.userMapper.insertUser(user) > 0);
/* 255 */     String newUserRole = this.configService.selectConfigByKey("user.new.role");
/* 256 */     if (success && StringUtils.isNotEmpty(newUserRole)) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 261 */       Long[] newUserRoleIds = (Long[])Arrays.<String>stream(newUserRole.split(",")).map(String::trim).filter(s -> !s.isEmpty()).map(Long::parseLong).toArray(x$0 -> new Long[x$0]);
/* 262 */       insertUserRole(user.getUserId(), newUserRoleIds);
/*     */     } 
/* 264 */     return success;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public int updateUser(SysUser user) {
/* 276 */     Long userId = user.getUserId();
/*     */     
/* 278 */     this.userRoleMapper.deleteUserRoleByUserId(userId);
/*     */     
/* 280 */     insertUserRole(user);
/*     */     
/* 282 */     this.userPostMapper.deleteUserPostByUserId(userId);
/*     */     
/* 284 */     insertUserPost(user);
/* 285 */     return this.userMapper.updateUser(user);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public void insertUserAuth(Long userId, Long[] roleIds) {
/* 297 */     this.userRoleMapper.deleteUserRoleByUserId(userId);
/* 298 */     insertUserRole(userId, roleIds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int updateUserStatus(SysUser user) {
/* 309 */     return this.userMapper.updateUser(user);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int updateUserProfile(SysUser user) {
/* 320 */     return this.userMapper.updateUser(user);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateUserAvatar(String userName, String avatar) {
/* 332 */     return (this.userMapper.updateUserAvatar(userName, avatar) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int resetPwd(SysUser user) {
/* 343 */     return this.userMapper.updateUser(user);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int resetUserPwd(String userName, String password) {
/* 355 */     return this.userMapper.resetUserPwd(userName, password);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertUserRole(SysUser user) {
/* 364 */     insertUserRole(user.getUserId(), user.getRoleIds());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertUserPost(SysUser user) {
/* 373 */     Long[] posts = user.getPostIds();
/* 374 */     if (StringUtils.isNotEmpty((Object[])posts)) {
/*     */       
/* 376 */       List<SysUserPost> list = new ArrayList<>(posts.length);
/* 377 */       for (Long postId : posts) {
/* 378 */         SysUserPost up = new SysUserPost();
/* 379 */         up.setUserId(user.getUserId());
/* 380 */         up.setPostId(postId);
/* 381 */         list.add(up);
/*     */       } 
/* 383 */       this.userPostMapper.batchUserPost(list);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertUserRole(Long userId, Long[] roleIds) {
/* 394 */     if (StringUtils.isNotEmpty((Object[])roleIds)) {
/*     */       
/* 396 */       List<SysUserRole> list = new ArrayList<>(roleIds.length);
/* 397 */       for (Long roleId : roleIds) {
/* 398 */         SysUserRole ur = new SysUserRole();
/* 399 */         ur.setUserId(userId);
/* 400 */         ur.setRoleId(roleId);
/* 401 */         list.add(ur);
/*     */       } 
/* 403 */       this.userRoleMapper.batchUserRole(list);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public int deleteUserById(Long userId) {
/* 417 */     this.userRoleMapper.deleteUserRoleByUserId(userId);
/*     */     
/* 419 */     this.userPostMapper.deleteUserPostByUserId(userId);
/* 420 */     return this.userMapper.deleteUserById(userId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public int deleteUserByIds(Long[] userIds) {
/* 432 */     for (Long userId : userIds) {
/* 433 */       checkUserAllowed(new SysUser(userId));
/* 434 */       checkUserDataScope(userId);
/*     */     } 
/*     */     
/* 437 */     this.userRoleMapper.deleteUserRole(userIds);
/*     */     
/* 439 */     this.userPostMapper.deleteUserPost(userIds);
/* 440 */     this.userMapper.deleteStaffByIds(userIds);
/* 441 */     return this.userMapper.deleteUserByIds(userIds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName) {
/* 454 */     if (StringUtils.isNull(userList) || userList.size() == 0) {
/* 455 */       throw new ServiceException("导入用户数据不能为空！");
/*     */     }
/* 457 */     int successNum = 0;
/* 458 */     int failureNum = 0;
/* 459 */     StringBuilder successMsg = new StringBuilder();
/* 460 */     StringBuilder failureMsg = new StringBuilder();
/* 461 */     String password = this.configService.selectConfigByKey("sys.user.initPassword");
/* 462 */     for (SysUser user : userList) {
/*     */       
/*     */       try {
/* 465 */         SysUser u = this.userMapper.selectUserByUserName(user.getUserName());
/* 466 */         if (StringUtils.isNull(u)) {
/* 467 */           BeanValidators.validateWithException(this.validator, user, new Class[0]);
/* 468 */           user.setPassword(SecurityUtils.encryptPassword(password));
/* 469 */           user.setCreateBy(operName);
/* 470 */           this.userMapper.insertUser(user);
/* 471 */           successNum++;
/* 472 */           successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 导入成功"); continue;
/* 473 */         }  if (isUpdateSupport.booleanValue()) {
/* 474 */           BeanValidators.validateWithException(this.validator, user, new Class[0]);
/* 475 */           checkUserAllowed(u);
/* 476 */           checkUserDataScope(u.getUserId());
/* 477 */           user.setUserId(u.getUserId());
/* 478 */           user.setUpdateBy(operName);
/* 479 */           this.userMapper.updateUser(user);
/* 480 */           successNum++;
/* 481 */           successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 更新成功"); continue;
/*     */         } 
/* 483 */         failureNum++;
/* 484 */         failureMsg.append("<br/>" + failureNum + "、账号 " + user.getUserName() + " 已存在");
/*     */       }
/* 486 */       catch (Exception e) {
/* 487 */         failureNum++;
/* 488 */         String msg = "<br/>" + failureNum + "、账号 " + user.getUserName() + " 导入失败：";
/* 489 */         failureMsg.append(msg + e.getMessage());
/* 490 */         log.error(msg, e);
/*     */       } 
/*     */     } 
/* 493 */     if (failureNum > 0) {
/* 494 */       failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
/* 495 */       throw new ServiceException(failureMsg.toString());
/*     */     } 
/* 497 */     successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
/*     */     
/* 499 */     return successMsg.toString();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysUserServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */