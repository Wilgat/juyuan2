package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.core.domain.entity.SysDept;
import java.util.List;

public interface ISysDeptService {
  List<SysDept> selectDeptList(SysDept paramSysDept);
  
  List<TreeSelect> selectDeptTreeList(SysDept paramSysDept);
  
  List<SysDept> buildDeptTree(List<SysDept> paramList);
  
  List<TreeSelect> buildDeptTreeSelect(List<SysDept> paramList);
  
  List<Long> selectDeptListByRoleId(Long paramLong);
  
  SysDept selectDeptById(Long paramLong);
  
  int selectNormalChildrenDeptById(Long paramLong);
  
  boolean hasChildByDeptId(Long paramLong);
  
  boolean checkDeptExistUser(Long paramLong);
  
  boolean checkDeptNameUnique(SysDept paramSysDept);
  
  void checkDeptDataScope(Long paramLong);
  
  int insertDept(SysDept paramSysDept);
  
  int updateDept(SysDept paramSysDept);
  
  int deleteDeptById(Long paramLong);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysDeptService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */