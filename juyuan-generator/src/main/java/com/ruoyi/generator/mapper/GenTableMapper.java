package com.ruoyi.generator.mapper;

import com.ruoyi.generator.domain.GenTable;
import java.util.List;

public interface GenTableMapper {
  List<GenTable> selectGenTableList(GenTable paramGenTable);
  
  List<GenTable> selectDbTableList(GenTable paramGenTable);
  
  List<GenTable> selectDbTableListByNames(String[] paramArrayOfString);
  
  List<GenTable> selectGenTableAll();
  
  GenTable selectGenTableById(Long paramLong);
  
  GenTable selectGenTableByName(String paramString);
  
  int insertGenTable(GenTable paramGenTable);
  
  int updateGenTable(GenTable paramGenTable);
  
  int deleteGenTableByIds(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/mapper/GenTableMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */