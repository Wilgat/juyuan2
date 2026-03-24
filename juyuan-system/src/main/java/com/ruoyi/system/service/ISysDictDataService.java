package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysDictData;
import java.util.List;

public interface ISysDictDataService {
  List<SysDictData> selectDictDataList(SysDictData paramSysDictData);
  
  String selectDictLabel(String paramString1, String paramString2);
  
  SysDictData selectDictDataById(Long paramLong);
  
  void deleteDictDataByIds(Long[] paramArrayOfLong);
  
  int insertDictData(SysDictData paramSysDictData);
  
  int updateDictData(SysDictData paramSysDictData);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysDictDataService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */