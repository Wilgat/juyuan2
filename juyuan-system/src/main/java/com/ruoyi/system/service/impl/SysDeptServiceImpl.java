/*     */ package com.ruoyi.system.service.impl;
/*     */ 
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.core.domain.TreeSelect;
/*     */ import com.ruoyi.common.core.domain.entity.SysDept;
/*     */ import com.ruoyi.common.core.domain.entity.SysRole;
/*     */ import com.ruoyi.common.core.domain.entity.SysUser;
/*     */ import com.ruoyi.common.core.text.Convert;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.spring.SpringUtils;
/*     */ import com.ruoyi.system.mapper.SysDeptMapper;
/*     */ import com.ruoyi.system.mapper.SysRoleMapper;
/*     */ import com.ruoyi.system.service.ISysDeptService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collectors;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class SysDeptServiceImpl
/*     */   implements ISysDeptService
/*     */ {
/*     */   @Autowired
/*     */   private SysDeptMapper deptMapper;
/*     */   @Autowired
/*     */   private SysRoleMapper roleMapper;
/*     */   
/*     */   @DataScope(deptAlias = "d")
/*     */   public List<SysDept> selectDeptList(SysDept dept) {
/*  48 */     return this.deptMapper.selectDeptList(dept);
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
/*     */   public List<TreeSelect> selectDeptTreeList(SysDept dept) {
/*  60 */     List<SysDept> depts = ((SysDeptServiceImpl)SpringUtils.getAopProxy(this)).selectDeptList(dept);
/*  61 */     return buildDeptTreeSelect(depts);
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
/*     */   public List<SysDept> buildDeptTree(List<SysDept> depts) {
/*  73 */     List<SysDept> returnList = new ArrayList<>();
/*  74 */     List<Long> tempList = (List<Long>)depts.stream().map(SysDept::getDeptId).collect(Collectors.toList());
/*  75 */     for (SysDept dept : depts) {
/*     */ 
/*     */       
/*  78 */       if (!tempList.contains(dept.getParentId())) {
/*     */         
/*  80 */         recursionFn(depts, dept);
/*  81 */         returnList.add(dept);
/*     */       } 
/*     */     } 
/*  84 */     if (returnList.isEmpty())
/*     */     {
/*  86 */       returnList = depts;
/*     */     }
/*  88 */     return returnList;
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
/*     */   public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts) {
/* 100 */     List<SysDept> deptTrees = buildDeptTree(depts);
/* 101 */     return (List<TreeSelect>)deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
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
/*     */   public List<Long> selectDeptListByRoleId(Long roleId) {
/* 113 */     SysRole role = this.roleMapper.selectRoleById(roleId);
/* 114 */     return this.deptMapper.selectDeptListByRoleId(roleId, role.isDeptCheckStrictly());
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
/*     */   public SysDept selectDeptById(Long deptId) {
/* 126 */     return this.deptMapper.selectDeptById(deptId);
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
/*     */   public int selectNormalChildrenDeptById(Long deptId) {
/* 138 */     return this.deptMapper.selectNormalChildrenDeptById(deptId);
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
/*     */   public boolean hasChildByDeptId(Long deptId) {
/* 150 */     int result = this.deptMapper.hasChildByDeptId(deptId);
/* 151 */     return (result > 0);
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
/*     */   public boolean checkDeptExistUser(Long deptId) {
/* 163 */     int result = this.deptMapper.checkDeptExistUser(deptId);
/* 164 */     return (result > 0);
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
/*     */   public boolean checkDeptNameUnique(SysDept dept) {
/* 176 */     Long deptId = Long.valueOf(StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId().longValue());
/* 177 */     SysDept info = this.deptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());
/* 178 */     if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue())
/*     */     {
/* 180 */       return false;
/*     */     }
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkDeptDataScope(Long deptId) {
/* 193 */     if (!SysUser.isAdmin(SecurityUtils.getUserId())) {
/*     */       
/* 195 */       SysDept dept = new SysDept();
/* 196 */       dept.setDeptId(deptId);
/* 197 */       List<SysDept> depts = ((SysDeptServiceImpl)SpringUtils.getAopProxy(this)).selectDeptList(dept);
/* 198 */       if (StringUtils.isEmpty(depts))
/*     */       {
/* 200 */         throw new ServiceException("没有权限访问部门数据！");
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
/*     */   public int insertDept(SysDept dept) {
/* 214 */     SysDept info = this.deptMapper.selectDeptById(dept.getParentId());
/*     */     
/* 216 */     if (!"0".equals(info.getStatus()))
/*     */     {
/* 218 */       throw new ServiceException("部门停用，不允许新增");
/*     */     }
/* 220 */     dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
/* 221 */     return this.deptMapper.insertDept(dept);
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
/*     */   public int updateDept(SysDept dept) {
/* 233 */     SysDept newParentDept = this.deptMapper.selectDeptById(dept.getParentId());
/* 234 */     SysDept oldDept = this.deptMapper.selectDeptById(dept.getDeptId());
/* 235 */     if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept)) {
/*     */       
/* 237 */       String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
/* 238 */       String oldAncestors = oldDept.getAncestors();
/* 239 */       dept.setAncestors(newAncestors);
/* 240 */       updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
/*     */     } 
/* 242 */     int result = this.deptMapper.updateDept(dept);
/* 243 */     if ("0".equals(dept.getStatus()) && StringUtils.isNotEmpty(dept.getAncestors()) && 
/* 244 */       !StringUtils.equals("0", dept.getAncestors()))
/*     */     {
/*     */       
/* 247 */       updateParentDeptStatusNormal(dept);
/*     */     }
/* 249 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateParentDeptStatusNormal(SysDept dept) {
/* 259 */     String ancestors = dept.getAncestors();
/* 260 */     Long[] deptIds = Convert.toLongArray(ancestors);
/* 261 */     this.deptMapper.updateDeptStatusNormal(deptIds);
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
/*     */   public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors) {
/* 273 */     List<SysDept> children = this.deptMapper.selectChildrenDeptById(deptId);
/* 274 */     for (SysDept child : children)
/*     */     {
/* 276 */       child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
/*     */     }
/* 278 */     if (children.size() > 0)
/*     */     {
/* 280 */       this.deptMapper.updateDeptChildren(children);
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
/*     */   public int deleteDeptById(Long deptId) {
/* 293 */     return this.deptMapper.deleteDeptById(deptId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void recursionFn(List<SysDept> list, SysDept t) {
/* 302 */     List<SysDept> childList = getChildList(list, t);
/* 303 */     t.setChildren(childList);
/* 304 */     for (SysDept tChild : childList) {
/*     */       
/* 306 */       if (hasChild(list, tChild))
/*     */       {
/* 308 */         recursionFn(list, tChild);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<SysDept> getChildList(List<SysDept> list, SysDept t) {
/* 318 */     List<SysDept> tlist = new ArrayList<>();
/* 319 */     Iterator<SysDept> it = list.iterator();
/* 320 */     while (it.hasNext()) {
/*     */       
/* 322 */       SysDept n = it.next();
/* 323 */       if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getDeptId().longValue())
/*     */       {
/* 325 */         tlist.add(n);
/*     */       }
/*     */     } 
/* 328 */     return tlist;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasChild(List<SysDept> list, SysDept t) {
/* 336 */     return (getChildList(list, t).size() > 0);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysDeptServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */