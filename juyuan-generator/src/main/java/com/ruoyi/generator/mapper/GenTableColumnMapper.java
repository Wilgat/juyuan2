package com.ruoyi.generator.mapper;

import com.ruoyi.generator.domain.GenTableColumn;
import java.util.List;

public interface GenTableColumnMapper {
  List<GenTableColumn> selectDbTableColumnsByName(String paramString);
  
  List<GenTableColumn> selectGenTableColumnListByTableId(Long paramLong);
  
  int insertGenTableColumn(GenTableColumn paramGenTableColumn);
  
  int updateGenTableColumn(GenTableColumn paramGenTableColumn);
  
  int deleteGenTableColumns(List<GenTableColumn> paramList);
  
  int deleteGenTableColumnByIds(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/mapper/GenTableColumnMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */