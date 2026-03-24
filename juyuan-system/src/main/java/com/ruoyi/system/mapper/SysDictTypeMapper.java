package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysDictType;
import java.util.List;

public interface SysDictTypeMapper {
  List<SysDictType> selectDictTypeList(SysDictType paramSysDictType);
  
  List<SysDictType> selectDictTypeAll();
  
  SysDictType selectDictTypeById(Long paramLong);
  
  SysDictType selectDictTypeByType(String paramString);
  
  int deleteDictTypeById(Long paramLong);
  
  int deleteDictTypeByIds(Long[] paramArrayOfLong);
  
  int insertDictType(SysDictType paramSysDictType);
  
  int updateDictType(SysDictType paramSysDictType);
  
  SysDictType checkDictTypeUnique(String paramString);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/mapper/SysDictTypeMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */