package com.ruoyi.generator.service;

import com.ruoyi.generator.domain.GenTableColumn;
import java.util.List;

public interface IGenTableColumnService {
  List<GenTableColumn> selectGenTableColumnListByTableId(Long paramLong);
  
  int insertGenTableColumn(GenTableColumn paramGenTableColumn);
  
  int updateGenTableColumn(GenTableColumn paramGenTableColumn);
  
  int deleteGenTableColumnByIds(String paramString);
}


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/service/IGenTableColumnService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */