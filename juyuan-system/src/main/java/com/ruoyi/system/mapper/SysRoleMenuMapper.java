package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysRoleMenu;
import java.util.List;

public interface SysRoleMenuMapper {
  int checkMenuExistRole(Long paramLong);
  
  int deleteRoleMenuByRoleId(Long paramLong);
  
  int deleteRoleMenu(Long[] paramArrayOfLong);
  
  int batchRoleMenu(List<SysRoleMenu> paramList);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/mapper/SysRoleMenuMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */