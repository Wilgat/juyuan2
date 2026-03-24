package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysMenu;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper {
  List<SysMenu> selectMenuList(SysMenu paramSysMenu);
  
  List<String> selectMenuPerms();
  
  List<SysMenu> selectMenuListByUserId(SysMenu paramSysMenu);
  
  List<String> selectMenuPermsByRoleId(Long paramLong);
  
  List<String> selectMenuPermsByUserId(Long paramLong);
  
  List<SysMenu> selectMenuTreeAll();
  
  List<SysMenu> selectMenuTreeByUserId(Long paramLong);
  
  List<Long> selectMenuListByRoleId(@Param("roleId") Long paramLong, @Param("menuCheckStrictly") boolean paramBoolean);
  
  SysMenu selectMenuById(Long paramLong);
  
  int hasChildByMenuId(Long paramLong);
  
  int insertMenu(SysMenu paramSysMenu);
  
  int updateMenu(SysMenu paramSysMenu);
  
  int deleteMenuById(Long paramLong);
  
  SysMenu checkMenuNameUnique(@Param("menuName") String paramString, @Param("parentId") Long paramLong);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/mapper/SysMenuMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */