package com.ruoyi.common.utils.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelHandlerAdapter {
  Object format(Object paramObject, String[] paramArrayOfString, Cell paramCell, Workbook paramWorkbook);
}

