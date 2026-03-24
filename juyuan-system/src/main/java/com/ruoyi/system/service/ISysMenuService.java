package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.system.domain.vo.RouterVo;
import java.util.List;
import java.util.Set;

public interface ISysMenuService {
  List<SysMenu> selectMenuList(Long paramLong);
  
  List<SysMenu> selectMenuList(SysMenu paramSysMenu, Long paramLong);
  
  Set<String> selectMenuPermsByUserId(Long paramLong);
  
  Set<String> selectMenuPermsByRoleId(Long paramLong);
  
  List<SysMenu> selectMenuTreeByUserId(Long paramLong);
  
  List<Long> selectMenuListByRoleId(Long paramLong);
  
  List<RouterVo> buildMenus(List<SysMenu> paramList);
  
  List<SysMenu> buildMenuTree(List<SysMenu> paramList);
  
  List<TreeSelect> buildMenuTreeSelect(List<SysMenu> paramList);
  
  SysMenu selectMenuById(Long paramLong);
  
  boolean hasChildByMenuId(Long paramLong);
  
  boolean checkMenuExistRole(Long paramLong);
  
  int insertMenu(SysMenu paramSysMenu);
  
  int updateMenu(SysMenu paramSysMenu);
  
  int deleteMenuById(Long paramLong);
  
  boolean checkMenuNameUnique(SysMenu paramSysMenu);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysMenuService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */