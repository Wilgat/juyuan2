package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysDictType;
import java.util.List;

public interface ISysDictTypeService {
  List<SysDictType> selectDictTypeList(SysDictType paramSysDictType);
  
  List<SysDictType> selectDictTypeAll();
  
  List<SysDictData> selectDictDataByType(String paramString);
  
  SysDictType selectDictTypeById(Long paramLong);
  
  SysDictType selectDictTypeByType(String paramString);
  
  void deleteDictTypeByIds(Long[] paramArrayOfLong);
  
  void loadingDictCache();
  
  void clearDictCache();
  
  void resetDictCache();
  
  int insertDictType(SysDictType paramSysDictType);
  
  int updateDictType(SysDictType paramSysDictType);
  
  boolean checkDictTypeUnique(SysDictType paramSysDictType);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysDictTypeService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */