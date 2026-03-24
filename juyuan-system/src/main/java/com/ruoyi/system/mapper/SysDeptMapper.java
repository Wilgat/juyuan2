package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysDept;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDeptMapper {
  List<SysDept> selectDeptList(SysDept paramSysDept);
  
  List<Long> selectDeptListByRoleId(@Param("roleId") Long paramLong, @Param("deptCheckStrictly") boolean paramBoolean);
  
  SysDept selectDeptById(Long paramLong);
  
  List<SysDept> selectChildrenDeptById(Long paramLong);
  
  int selectNormalChildrenDeptById(Long paramLong);
  
  int hasChildByDeptId(Long paramLong);
  
  int checkDeptExistUser(Long paramLong);
  
  SysDept checkDeptNameUnique(@Param("deptName") String paramString, @Param("parentId") Long paramLong);
  
  int insertDept(SysDept paramSysDept);
  
  int updateDept(SysDept paramSysDept);
  
  void updateDeptStatusNormal(Long[] paramArrayOfLong);
  
  int updateDeptChildren(@Param("depts") List<SysDept> paramList);
  
  int deleteDeptById(Long paramLong);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/mapper/SysDeptMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */