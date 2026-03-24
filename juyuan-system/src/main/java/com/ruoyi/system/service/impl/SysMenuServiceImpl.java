/*     */ package com.ruoyi.system.service.impl;
/*     */ 
/*     */ import com.ruoyi.common.core.domain.TreeSelect;
/*     */ import com.ruoyi.common.core.domain.entity.SysMenu;
/*     */ import com.ruoyi.common.core.domain.entity.SysRole;
/*     */ import com.ruoyi.common.core.domain.entity.SysUser;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.system.domain.vo.MetaVo;
/*     */ import com.ruoyi.system.domain.vo.RouterVo;
/*     */ import com.ruoyi.system.mapper.SysMenuMapper;
/*     */ import com.ruoyi.system.mapper.SysRoleMapper;
/*     */ import com.ruoyi.system.mapper.SysRoleMenuMapper;
/*     */ import com.ruoyi.system.service.ISysMenuService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class SysMenuServiceImpl
/*     */   implements ISysMenuService
/*     */ {
/*     */   public static final String PREMISSION_STRING = "perms[\"{0}\"]";
/*     */   @Autowired
/*     */   private SysMenuMapper menuMapper;
/*     */   @Autowired
/*     */   private SysRoleMapper roleMapper;
/*     */   @Autowired
/*     */   private SysRoleMenuMapper roleMenuMapper;
/*     */   
/*     */   public List<SysMenu> selectMenuList(Long userId) {
/*  56 */     return selectMenuList(new SysMenu(), userId);
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
/*     */   public List<SysMenu> selectMenuList(SysMenu menu, Long userId) {
/*  68 */     List<SysMenu> menuList = null;
/*     */     
/*  70 */     if (SysUser.isAdmin(userId)) {
/*     */       
/*  72 */       menuList = this.menuMapper.selectMenuList(menu);
/*     */     }
/*     */     else {
/*     */       
/*  76 */       menu.getParams().put("userId", userId);
/*  77 */       menuList = this.menuMapper.selectMenuListByUserId(menu);
/*     */     } 
/*  79 */     return menuList;
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
/*     */   public Set<String> selectMenuPermsByUserId(Long userId) {
/*  91 */     List<String> perms = this.menuMapper.selectMenuPermsByUserId(userId);
/*  92 */     Set<String> permsSet = new HashSet<>();
/*  93 */     for (String perm : perms) {
/*     */       
/*  95 */       if (StringUtils.isNotEmpty(perm))
/*     */       {
/*  97 */         permsSet.addAll(Arrays.asList(perm.trim().split(",")));
/*     */       }
/*     */     } 
/* 100 */     return permsSet;
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
/*     */   public Set<String> selectMenuPermsByRoleId(Long roleId) {
/* 112 */     List<String> perms = this.menuMapper.selectMenuPermsByRoleId(roleId);
/* 113 */     Set<String> permsSet = new HashSet<>();
/* 114 */     for (String perm : perms) {
/*     */       
/* 116 */       if (StringUtils.isNotEmpty(perm))
/*     */       {
/* 118 */         permsSet.addAll(Arrays.asList(perm.trim().split(",")));
/*     */       }
/*     */     } 
/* 121 */     return permsSet;
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
/*     */   public List<SysMenu> selectMenuTreeByUserId(Long userId) {
/* 133 */     List<SysMenu> menus = null;
/* 134 */     if (SecurityUtils.isAdmin(userId)) {
/*     */       
/* 136 */       menus = this.menuMapper.selectMenuTreeAll();
/*     */     }
/*     */     else {
/*     */       
/* 140 */       menus = this.menuMapper.selectMenuTreeByUserId(userId);
/*     */     } 
/* 142 */     return getChildPerms(menus, 0);
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
/*     */   public List<Long> selectMenuListByRoleId(Long roleId) {
/* 154 */     SysRole role = this.roleMapper.selectRoleById(roleId);
/* 155 */     return this.menuMapper.selectMenuListByRoleId(roleId, role.isMenuCheckStrictly());
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
/*     */   public List<RouterVo> buildMenus(List<SysMenu> menus) {
/* 167 */     List<RouterVo> routers = new LinkedList<>();
/* 168 */     for (SysMenu menu : menus) {
/*     */       
/* 170 */       RouterVo router = new RouterVo();
/* 171 */       router.setHidden("1".equals(menu.getVisible()));
/* 172 */       router.setName(getRouteName(menu));
/* 173 */       router.setPath(getRouterPath(menu));
/* 174 */       router.setComponent(getComponent(menu));
/* 175 */       router.setQuery(menu.getQuery());
/* 176 */       router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
/* 177 */       List<SysMenu> cMenus = menu.getChildren();
/* 178 */       if (StringUtils.isNotEmpty(cMenus) && "M".equals(menu.getMenuType())) {
/*     */         
/* 180 */         router.setAlwaysShow(Boolean.valueOf(true));
/* 181 */         router.setRedirect("noRedirect");
/* 182 */         router.setChildren(buildMenus(cMenus));
/*     */       }
/* 184 */       else if (isMenuFrame(menu)) {
/*     */         
/* 186 */         router.setMeta(null);
/* 187 */         List<RouterVo> childrenList = new ArrayList<>();
/* 188 */         RouterVo children = new RouterVo();
/* 189 */         children.setPath(menu.getPath());
/* 190 */         children.setComponent(menu.getComponent());
/* 191 */         children.setName(StringUtils.capitalize(menu.getPath()));
/* 192 */         children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
/* 193 */         children.setQuery(menu.getQuery());
/* 194 */         childrenList.add(children);
/* 195 */         router.setChildren(childrenList);
/*     */       }
/* 197 */       else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
/*     */         
/* 199 */         router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
/* 200 */         router.setPath("/");
/* 201 */         List<RouterVo> childrenList = new ArrayList<>();
/* 202 */         RouterVo children = new RouterVo();
/* 203 */         String routerPath = innerLinkReplaceEach(menu.getPath());
/* 204 */         children.setPath(routerPath);
/* 205 */         children.setComponent("InnerLink");
/* 206 */         children.setName(StringUtils.capitalize(routerPath));
/* 207 */         children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
/* 208 */         childrenList.add(children);
/* 209 */         router.setChildren(childrenList);
/*     */       } 
/* 211 */       routers.add(router);
/*     */     } 
/* 213 */     return routers;
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
/*     */   public List<SysMenu> buildMenuTree(List<SysMenu> menus) {
/* 225 */     List<SysMenu> returnList = new ArrayList<>();
/* 226 */     List<Long> tempList = (List<Long>)menus.stream().map(SysMenu::getMenuId).collect(Collectors.toList());
/* 227 */     for (Iterator<SysMenu> iterator = menus.iterator(); iterator.hasNext(); ) {
/*     */       
/* 229 */       SysMenu menu = iterator.next();
/*     */       
/* 231 */       if (!tempList.contains(menu.getParentId())) {
/*     */         
/* 233 */         recursionFn(menus, menu);
/* 234 */         returnList.add(menu);
/*     */       } 
/*     */     } 
/* 237 */     if (returnList.isEmpty())
/*     */     {
/* 239 */       returnList = menus;
/*     */     }
/* 241 */     return returnList;
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
/*     */   public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus) {
/* 253 */     List<SysMenu> menuTrees = buildMenuTree(menus);
/* 254 */     return (List<TreeSelect>)menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
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
/*     */   public SysMenu selectMenuById(Long menuId) {
/* 266 */     return this.menuMapper.selectMenuById(menuId);
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
/*     */   public boolean hasChildByMenuId(Long menuId) {
/* 278 */     int result = this.menuMapper.hasChildByMenuId(menuId);
/* 279 */     return (result > 0);
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
/*     */   public boolean checkMenuExistRole(Long menuId) {
/* 291 */     int result = this.roleMenuMapper.checkMenuExistRole(menuId);
/* 292 */     return (result > 0);
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
/*     */   public int insertMenu(SysMenu menu) {
/* 304 */     return this.menuMapper.insertMenu(menu);
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
/*     */   public int updateMenu(SysMenu menu) {
/* 316 */     return this.menuMapper.updateMenu(menu);
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
/*     */   public int deleteMenuById(Long menuId) {
/* 328 */     return this.menuMapper.deleteMenuById(menuId);
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
/*     */   public boolean checkMenuNameUnique(SysMenu menu) {
/* 340 */     Long menuId = Long.valueOf(StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId().longValue());
/* 341 */     SysMenu info = this.menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
/* 342 */     if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue())
/*     */     {
/* 344 */       return false;
/*     */     }
/* 346 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRouteName(SysMenu menu) {
/* 357 */     String routerName = StringUtils.capitalize(menu.getPath());
/*     */     
/* 359 */     if (isMenuFrame(menu))
/*     */     {
/* 361 */       routerName = "";
/*     */     }
/* 363 */     return routerName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRouterPath(SysMenu menu) {
/* 374 */     String routerPath = menu.getPath();
/*     */     
/* 376 */     if (menu.getParentId().intValue() != 0 && isInnerLink(menu))
/*     */     {
/* 378 */       routerPath = innerLinkReplaceEach(routerPath);
/*     */     }
/*     */     
/* 381 */     if (0 == menu.getParentId().intValue() && "M".equals(menu.getMenuType()) && "1"
/* 382 */       .equals(menu.getIsFrame())) {
/*     */       
/* 384 */       routerPath = "/" + menu.getPath();
/*     */     
/*     */     }
/* 387 */     else if (isMenuFrame(menu)) {
/*     */       
/* 389 */       routerPath = "/";
/*     */     } 
/* 391 */     return routerPath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getComponent(SysMenu menu) {
/* 402 */     String component = "Layout";
/* 403 */     if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
/*     */       
/* 405 */       component = menu.getComponent();
/*     */     }
/* 407 */     else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
/*     */       
/* 409 */       component = "InnerLink";
/*     */     }
/* 411 */     else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
/*     */       
/* 413 */       component = "ParentView";
/*     */     } 
/* 415 */     return component;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMenuFrame(SysMenu menu) {
/* 426 */     return (menu.getParentId().intValue() == 0 && "C".equals(menu.getMenuType()) && menu
/* 427 */       .getIsFrame().equals("1"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInnerLink(SysMenu menu) {
/* 438 */     return (menu.getIsFrame().equals("1") && StringUtils.ishttp(menu.getPath()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isParentView(SysMenu menu) {
/* 449 */     return (menu.getParentId().intValue() != 0 && "M".equals(menu.getMenuType()));
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
/*     */   public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId) {
/* 461 */     List<SysMenu> returnList = new ArrayList<>();
/* 462 */     for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext(); ) {
/*     */       
/* 464 */       SysMenu t = iterator.next();
/*     */       
/* 466 */       if (t.getParentId().longValue() == parentId) {
/*     */         
/* 468 */         recursionFn(list, t);
/* 469 */         returnList.add(t);
/*     */       } 
/*     */     } 
/* 472 */     return returnList;
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
/*     */   private void recursionFn(List<SysMenu> list, SysMenu t) {
/* 484 */     List<SysMenu> childList = getChildList(list, t);
/* 485 */     t.setChildren(childList);
/* 486 */     for (SysMenu tChild : childList) {
/*     */       
/* 488 */       if (hasChild(list, tChild))
/*     */       {
/* 490 */         recursionFn(list, tChild);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
/* 500 */     List<SysMenu> tlist = new ArrayList<>();
/* 501 */     Iterator<SysMenu> it = list.iterator();
/* 502 */     while (it.hasNext()) {
/*     */       
/* 504 */       SysMenu n = it.next();
/* 505 */       if (n.getParentId().longValue() == t.getMenuId().longValue())
/*     */       {
/* 507 */         tlist.add(n);
/*     */       }
/*     */     } 
/* 510 */     return tlist;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasChild(List<SysMenu> list, SysMenu t) {
/* 518 */     return (getChildList(list, t).size() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String innerLinkReplaceEach(String path) {
/* 528 */     return StringUtils.replaceEach(path, new String[] { "http://", "https://", "www.", "." }, new String[] { "", "", "", "/" });
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysMenuServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */