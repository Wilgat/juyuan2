package com.ruoyi.generator.service;

import com.ruoyi.generator.domain.GenTable;
import java.util.List;
import java.util.Map;

public interface IGenTableService {
  List<GenTable> selectGenTableList(GenTable paramGenTable);
  
  List<GenTable> selectDbTableList(GenTable paramGenTable);
  
  List<GenTable> selectDbTableListByNames(String[] paramArrayOfString);
  
  List<GenTable> selectGenTableAll();
  
  GenTable selectGenTableById(Long paramLong);
  
  void updateGenTable(GenTable paramGenTable);
  
  void deleteGenTableByIds(Long[] paramArrayOfLong);
  
  void importGenTable(List<GenTable> paramList);
  
  Map<String, String> previewCode(Long paramLong);
  
  byte[] downloadCode(String paramString);
  
  void generatorCode(String paramString);
  
  void synchDb(String paramString);
  
  byte[] downloadCode(String[] paramArrayOfString);
  
  void validateEdit(GenTable paramGenTable);
}


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/service/IGenTableService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */