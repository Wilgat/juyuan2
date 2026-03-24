package com.ruoyi.common.utils.poi;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.UtilException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileTypeUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.file.ImageUtils;
import com.ruoyi.common.utils.reflect.ReflectUtils;


/*      */ public class ExcelUtil<T> {
/*   51 */   private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
/*      */   
/*      */   public static final String FORMULA_REGEX_STR = "=|-|\\+|@";
/*      */   
/*   55 */   public static final String[] FORMULA_STR = new String[] { "=", "-", "+", "@" };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   60 */   public Map<String, String> sysDictMap = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int sheetSize = 65536;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String sheetName;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Excel.Type type;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Workbook wb;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Sheet sheet;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Map<String, CellStyle> styles;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List<T> list;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List<Object[]> fields;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int rownum;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String title;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short maxHeight;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  120 */   private int subMergedLastRowNum = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  125 */   private int subMergedFirstRowNum = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Method subMethod;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List<Field> subFields;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  140 */   private Map<Integer, Double> statistics = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  145 */   private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("######0.00");
/*      */ 
/*      */ 
/*      */   
/*      */   public Class<T> clazz;
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] excludeFields;
/*      */ 
/*      */ 
/*      */   
/*      */   public ExcelUtil(Class<T> clazz) {
/*  158 */     this.clazz = clazz;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void hideColumn(String... fields) {
/*  168 */     this.excludeFields = fields;
/*      */   }
/*      */   
/*      */   public void init(List<T> list, String sheetName, String title, Excel.Type type) {
/*  172 */     if (list == null) {
/*  173 */       list = new ArrayList<>();
/*      */     }
/*  175 */     this.list = list;
/*  176 */     this.sheetName = sheetName;
/*  177 */     this.type = type;
/*  178 */     this.title = title;
/*  179 */     createExcelField();
/*  180 */     createWorkbook();
/*  181 */     createTitle();
/*  182 */     createSubHead();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void createTitle() {
/*  189 */     if (StringUtils.isNotEmpty(this.title)) {
/*  190 */       this.subMergedFirstRowNum++;
/*  191 */       this.subMergedLastRowNum++;
/*  192 */       int titleLastCol = this.fields.size() - 1;
/*  193 */       if (isSubList()) {
/*  194 */         titleLastCol = titleLastCol + this.subFields.size() - 1;
/*      */       }
/*  196 */       Row titleRow = this.sheet.createRow((this.rownum == 0) ? this.rownum++ : 0);
/*  197 */       titleRow.setHeightInPoints(30.0F);
/*  198 */       Cell titleCell = titleRow.createCell(0);
/*  199 */       titleCell.setCellStyle(this.styles.get("title"));
/*  200 */       titleCell.setCellValue(this.title);
/*  201 */       this.sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(), titleRow.getRowNum(), titleRow.getRowNum(), titleLastCol));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void createSubHead() {
/*  209 */     if (isSubList()) {
/*  210 */       this.subMergedFirstRowNum++;
/*  211 */       this.subMergedLastRowNum++;
/*  212 */       Row subRow = this.sheet.createRow(this.rownum);
/*  213 */       int excelNum = 0;
/*  214 */       for (Object[] objects : this.fields) {
/*  215 */         Excel attr = (Excel)objects[1];
/*  216 */         Cell headCell1 = subRow.createCell(excelNum);
/*  217 */         headCell1.setCellValue(attr.name());
/*  218 */         headCell1.setCellStyle(this.styles.get(StringUtils.format("header_{}_{}", new Object[] { attr.headerColor(), attr.headerBackgroundColor() })));
/*  219 */         excelNum++;
/*      */       } 
/*  221 */       int headFirstRow = excelNum - 1;
/*  222 */       int headLastRow = headFirstRow + this.subFields.size() - 1;
/*  223 */       if (headLastRow > headFirstRow) {
/*  224 */         this.sheet.addMergedRegion(new CellRangeAddress(this.rownum, this.rownum, headFirstRow, headLastRow));
/*      */       }
/*  226 */       this.rownum++;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<T> importExcel(InputStream is) {
/*  237 */     List<T> list = null;
/*      */     try {
/*  239 */       list = importExcel(is, 0);
/*  240 */     } catch (Exception e) {
/*  241 */       log.error("导入Excel异常{}", e.getMessage());
/*  242 */       throw new UtilException(e.getMessage());
/*      */     } finally {
/*  244 */       IOUtils.closeQuietly(is);
/*      */     } 
/*  246 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<T> importExcel(InputStream is, int titleNum) throws Exception {
/*  257 */     return importExcel("", is, titleNum);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<T> importExcel(String sheetName, InputStream is, int titleNum) throws Exception {
/*      */     Map<String, PictureData> pictures;
/*  269 */     this.type = Excel.Type.IMPORT;
/*  270 */     this.wb = WorkbookFactory.create(is);
/*  271 */     List<T> list = new ArrayList<>();
/*      */     
/*  273 */     Sheet sheet = StringUtils.isNotEmpty(sheetName) ? this.wb.getSheet(sheetName) : this.wb.getSheetAt(0);
/*  274 */     if (sheet == null) {
/*  275 */       throw new IOException("文件sheet不存在");
/*      */     }
/*  277 */     boolean isXSSFWorkbook = !(this.wb instanceof HSSFWorkbook);
/*      */     
/*  279 */     if (isXSSFWorkbook) {
/*  280 */       pictures = getSheetPictures07((XSSFSheet)sheet, (XSSFWorkbook)this.wb);
/*      */     } else {
/*  282 */       pictures = getSheetPictures03((HSSFSheet)sheet, (HSSFWorkbook)this.wb);
/*      */     } 
/*      */     
/*  285 */     int rows = sheet.getLastRowNum();
/*  286 */     if (rows > 0) {
/*      */       
/*  288 */       Map<String, Integer> cellMap = new HashMap<>();
/*      */       
/*  290 */       Row heard = sheet.getRow(titleNum);
/*  291 */       for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++) {
/*  292 */         Cell cell = heard.getCell(i);
/*  293 */         if (StringUtils.isNotNull(cell)) {
/*  294 */           String value = getCellValue(heard, i).toString();
/*  295 */           cellMap.put(value, Integer.valueOf(i));
/*      */         } else {
/*  297 */           cellMap.put(null, Integer.valueOf(i));
/*      */         } 
/*      */       } 
/*      */       
/*  301 */       List<Object[]> fields = getFields();
/*  302 */       Map<Integer, Object[]> fieldsMap = (Map)new HashMap<>();
/*  303 */       for (Object[] objects : fields) {
/*  304 */         Excel attr = (Excel)objects[1];
/*  305 */         Integer column = cellMap.get(attr.name());
/*  306 */         if (column != null) {
/*  307 */           fieldsMap.put(column, objects);
/*      */         }
/*      */       } 
/*  310 */       for (int j = titleNum + 1; j <= rows; j++) {
/*      */         
/*  312 */         Row row = sheet.getRow(j);
/*      */         
/*  314 */         if (!isRowEmpty(row)) {
/*      */ 
/*      */           
/*  317 */           T entity = null;
/*  318 */           for (Map.Entry<Integer, Object[]> entry : fieldsMap.entrySet()) {
/*  319 */             Object val = getCellValue(row, ((Integer)entry.getKey()).intValue());
/*      */ 
/*      */             
/*  322 */             entity = (entity == null) ? this.clazz.newInstance() : entity;
/*      */             
/*  324 */             Field field = (Field)((Object[])entry.getValue())[0];
/*  325 */             Excel attr = (Excel)((Object[])entry.getValue())[1];
/*      */             
/*  327 */             Class<?> fieldType = field.getType();
/*  328 */             if (String.class == fieldType) {
/*  329 */               String s = Convert.toStr(val);
/*  330 */               if (StringUtils.endsWith(s, ".0")) {
/*  331 */                 val = StringUtils.substringBefore(s, ".0");
/*      */               } else {
/*  333 */                 String dateFormat = ((Excel)field.<Excel>getAnnotation(Excel.class)).dateFormat();
/*  334 */                 if (StringUtils.isNotEmpty(dateFormat)) {
/*  335 */                   val = parseDateToStr(dateFormat, val);
/*      */                 } else {
/*  337 */                   val = Convert.toStr(val);
/*      */                 } 
/*      */               } 
/*  340 */             } else if ((int.class == fieldType || Integer.class == fieldType) && StringUtils.isNumeric(Convert.toStr(val))) {
/*  341 */               val = Convert.toInt(val);
/*  342 */             } else if ((long.class == fieldType || Long.class == fieldType) && StringUtils.isNumeric(Convert.toStr(val))) {
/*  343 */               val = Convert.toLong(val);
/*  344 */             } else if (double.class == fieldType || Double.class == fieldType) {
/*  345 */               val = Convert.toDouble(val);
/*  346 */             } else if (float.class == fieldType || Float.class == fieldType) {
/*  347 */               val = Convert.toFloat(val);
/*  348 */             } else if (BigDecimal.class == fieldType) {
/*  349 */               val = Convert.toBigDecimal(val);
/*  350 */             } else if (Date.class == fieldType) {
/*  351 */               if (val instanceof String) {
/*  352 */                 val = DateUtils.parseDate(val);
/*  353 */               } else if (val instanceof Double) {
/*  354 */                 val = DateUtil.getJavaDate(((Double)val).doubleValue());
/*      */               } 
/*  356 */             } else if (boolean.class == fieldType || Boolean.class == fieldType) {
/*  357 */               val = Convert.toBool(val, Boolean.valueOf(false));
/*      */             } 
/*  359 */             if (StringUtils.isNotNull(fieldType)) {
/*  360 */               String propertyName = field.getName();
/*  361 */               if (StringUtils.isNotEmpty(attr.targetAttr())) {
/*  362 */                 propertyName = field.getName() + "." + attr.targetAttr();
/*      */               }
/*  364 */               if (StringUtils.isNotEmpty(attr.readConverterExp())) {
/*  365 */                 val = reverseByExp(Convert.toStr(val), attr.readConverterExp(), attr.separator());
/*  366 */               } else if (StringUtils.isNotEmpty(attr.dictType())) {
/*  367 */                 val = reverseDictByExp(Convert.toStr(val), attr.dictType(), attr.separator());
/*  368 */               } else if (!attr.handler().equals(ExcelHandlerAdapter.class)) {
/*  369 */                 val = dataFormatHandlerAdapter(val, attr, null);
/*  370 */               } else if (Excel.ColumnType.IMAGE == attr.cellType() && StringUtils.isNotEmpty(pictures)) {
/*  371 */                 PictureData image = pictures.get(row.getRowNum() + "_" + entry.getKey());
/*  372 */                 if (image == null) {
/*  373 */                   val = "";
/*      */                 } else {
/*  375 */                   byte[] data = image.getData();
/*  376 */                   val = FileUtils.writeImportBytes(data);
/*      */                 } 
/*      */               } 
/*  379 */               ReflectUtils.invokeSetter(entity, propertyName, val);
/*      */             } 
/*      */           } 
/*  382 */           list.add(entity);
/*      */         } 
/*      */       } 
/*  385 */     }  return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AjaxResult exportExcel(List<T> list, String sheetName) {
/*  396 */     return exportExcel(list, sheetName, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AjaxResult exportExcel(List<T> list, String sheetName, String title) {
/*  408 */     init(list, sheetName, title, Excel.Type.EXPORT);
/*  409 */     return exportExcel();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void exportExcel(HttpServletResponse response, List<T> list, String sheetName) {
/*  421 */     exportExcel(response, list, sheetName, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void exportExcel(HttpServletResponse response, List<T> list, String sheetName, String title) {
/*  434 */     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
/*  435 */     response.setCharacterEncoding("utf-8");
/*  436 */     init(list, sheetName, title, Excel.Type.EXPORT);
/*  437 */     exportExcel(response);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AjaxResult importTemplateExcel(String sheetName) {
/*  447 */     return importTemplateExcel(sheetName, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AjaxResult importTemplateExcel(String sheetName, String title) {
/*  458 */     init(null, sheetName, title, Excel.Type.IMPORT);
/*  459 */     return exportExcel();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void importTemplateExcel(HttpServletResponse response, String sheetName) {
/*  469 */     importTemplateExcel(response, sheetName, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void importTemplateExcel(HttpServletResponse response, String sheetName, String title) {
/*  480 */     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
/*  481 */     response.setCharacterEncoding("utf-8");
/*  482 */     init(null, sheetName, title, Excel.Type.IMPORT);
/*  483 */     exportExcel(response);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void exportExcel(HttpServletResponse response) {
/*      */     try {
/*  493 */       writeSheet();
/*  494 */       this.wb.write((OutputStream)response.getOutputStream());
/*  495 */     } catch (Exception e) {
/*  496 */       log.error("导出Excel异常{}", e.getMessage());
/*      */     } finally {
/*  498 */       IOUtils.closeQuietly((Closeable)this.wb);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AjaxResult exportExcel() {
/*  508 */     OutputStream out = null;
/*      */     try {
/*  510 */       writeSheet();
/*  511 */       String filename = encodingFilename(this.sheetName);
/*  512 */       out = new FileOutputStream(getAbsoluteFile(filename));
/*  513 */       this.wb.write(out);
/*  514 */       return AjaxResult.success(filename);
/*  515 */     } catch (Exception e) {
/*  516 */       log.error("导出Excel异常{}", e.getMessage());
/*  517 */       throw new UtilException("导出Excel失败，请联系网站管理员！");
/*      */     } finally {
/*  519 */       IOUtils.closeQuietly((Closeable)this.wb);
/*  520 */       IOUtils.closeQuietly(out);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeSheet() {
/*  529 */     int sheetNo = Math.max(1, (int)Math.ceil(this.list.size() * 1.0D / 65536.0D));
/*  530 */     for (int index = 0; index < sheetNo; index++) {
/*  531 */       createSheet(sheetNo, index);
/*      */ 
/*      */       
/*  534 */       Row row = this.sheet.createRow(this.rownum);
/*  535 */       int column = 0;
/*      */       
/*  537 */       for (Object[] os : this.fields) {
/*  538 */         Field field = (Field)os[0];
/*  539 */         Excel excel = (Excel)os[1];
/*  540 */         if (Collection.class.isAssignableFrom(field.getType())) {
/*  541 */           for (Field subField : this.subFields) {
/*  542 */             Excel subExcel = subField.<Excel>getAnnotation(Excel.class);
/*  543 */             createHeadCell(subExcel, row, column++);
/*      */           }  continue;
/*      */         } 
/*  546 */         createHeadCell(excel, row, column++);
/*      */       } 
/*      */       
/*  549 */       if (Excel.Type.EXPORT.equals(this.type)) {
/*  550 */         fillExcelData(index, row);
/*  551 */         addStatisticsRow();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fillExcelData(int index, Row row) {
/*  564 */     int startNo = index * 65536;
/*  565 */     int endNo = Math.min(startNo + 65536, this.list.size());
/*  566 */     int rowNo = 1 + this.rownum - startNo;
/*  567 */     for (int i = startNo; i < endNo; i++) {
/*  568 */       rowNo = isSubList() ? ((i > 1) ? (rowNo + 1) : (rowNo + i)) : (i + 1 + this.rownum - startNo);
/*  569 */       row = this.sheet.createRow(rowNo);
/*      */       
/*  571 */       T vo = this.list.get(i);
/*  572 */       Collection<?> subList = null;
/*  573 */       if (isSubList()) {
/*  574 */         if (isSubListValue(vo)) {
/*  575 */           subList = getListCellValue(vo);
/*  576 */           this.subMergedLastRowNum += subList.size();
/*      */         } else {
/*  578 */           this.subMergedFirstRowNum++;
/*  579 */           this.subMergedLastRowNum++;
/*      */         } 
/*      */       }
/*  582 */       int column = 0;
/*  583 */       for (Object[] os : this.fields) {
/*  584 */         Field field = (Field)os[0];
/*  585 */         Excel excel = (Excel)os[1];
/*  586 */         if (Collection.class.isAssignableFrom(field.getType()) && StringUtils.isNotNull(subList)) {
/*  587 */           boolean subFirst = false;
/*  588 */           for (Object obj : subList) {
/*  589 */             if (subFirst) {
/*  590 */               rowNo++;
/*  591 */               row = this.sheet.createRow(rowNo);
/*      */             } 
/*  593 */             List<Field> subFields = FieldUtils.getFieldsListWithAnnotation(obj.getClass(), Excel.class);
/*  594 */             int subIndex = 0;
/*  595 */             for (Field subField : subFields) {
/*  596 */               if (subField.isAnnotationPresent((Class)Excel.class)) {
/*  597 */                 subField.setAccessible(true);
/*  598 */                 Excel attr = subField.<Excel>getAnnotation(Excel.class);
/*  599 */                 addCell(attr, row, (T)obj, subField, column + subIndex);
/*      */               } 
/*  601 */               subIndex++;
/*      */             } 
/*  603 */             subFirst = true;
/*      */           } 
/*  605 */           this.subMergedFirstRowNum += subList.size(); continue;
/*      */         } 
/*  607 */         addCell(excel, row, vo, field, column++);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Map<String, CellStyle> createStyles(Workbook wb) {
/*  621 */     Map<String, CellStyle> styles = new HashMap<>();
/*  622 */     CellStyle style = wb.createCellStyle();
/*  623 */     style.setAlignment(HorizontalAlignment.CENTER);
/*  624 */     style.setVerticalAlignment(VerticalAlignment.CENTER);
/*  625 */     Font titleFont = wb.createFont();
/*  626 */     titleFont.setFontName("Arial");
/*  627 */     titleFont.setFontHeightInPoints((short)16);
/*  628 */     titleFont.setBold(true);
/*  629 */     style.setFont(titleFont);
/*  630 */     styles.put("title", style);
/*      */     
/*  632 */     style = wb.createCellStyle();
/*  633 */     style.setAlignment(HorizontalAlignment.CENTER);
/*  634 */     style.setVerticalAlignment(VerticalAlignment.CENTER);
/*  635 */     style.setBorderRight(BorderStyle.THIN);
/*  636 */     style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
/*  637 */     style.setBorderLeft(BorderStyle.THIN);
/*  638 */     style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
/*  639 */     style.setBorderTop(BorderStyle.THIN);
/*  640 */     style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
/*  641 */     style.setBorderBottom(BorderStyle.THIN);
/*  642 */     style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
/*  643 */     Font dataFont = wb.createFont();
/*  644 */     dataFont.setFontName("Arial");
/*  645 */     dataFont.setFontHeightInPoints((short)10);
/*  646 */     style.setFont(dataFont);
/*  647 */     styles.put("data", style);
/*      */     
/*  649 */     style = wb.createCellStyle();
/*  650 */     style.setAlignment(HorizontalAlignment.CENTER);
/*  651 */     style.setVerticalAlignment(VerticalAlignment.CENTER);
/*  652 */     Font totalFont = wb.createFont();
/*  653 */     totalFont.setFontName("Arial");
/*  654 */     totalFont.setFontHeightInPoints((short)10);
/*  655 */     style.setFont(totalFont);
/*  656 */     styles.put("total", style);
/*      */     
/*  658 */     styles.putAll(annotationHeaderStyles(wb, styles));
/*      */     
/*  660 */     styles.putAll(annotationDataStyles(wb));
/*      */     
/*  662 */     return styles;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Map<String, CellStyle> annotationHeaderStyles(Workbook wb, Map<String, CellStyle> styles) {
/*  672 */     Map<String, CellStyle> headerStyles = new HashMap<>();
/*  673 */     for (Object[] os : this.fields) {
/*  674 */       Excel excel = (Excel)os[1];
/*  675 */       String key = StringUtils.format("header_{}_{}", new Object[] { excel.headerColor(), excel.headerBackgroundColor() });
/*  676 */       if (!headerStyles.containsKey(key)) {
/*  677 */         CellStyle style = wb.createCellStyle();
/*  678 */         style.cloneStyleFrom(styles.get("data"));
/*  679 */         style.setAlignment(HorizontalAlignment.CENTER);
/*  680 */         style.setVerticalAlignment(VerticalAlignment.CENTER);
/*  681 */         style.setFillForegroundColor((excel.headerBackgroundColor()).index);
/*  682 */         style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
/*  683 */         Font headerFont = wb.createFont();
/*  684 */         headerFont.setFontName("Arial");
/*  685 */         headerFont.setFontHeightInPoints((short)10);
/*  686 */         headerFont.setBold(true);
/*  687 */         headerFont.setColor((excel.headerColor()).index);
/*  688 */         style.setFont(headerFont);
/*  689 */         headerStyles.put(key, style);
/*      */       } 
/*      */     } 
/*  692 */     return headerStyles;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Map<String, CellStyle> annotationDataStyles(Workbook wb) {
/*  702 */     Map<String, CellStyle> styles = new HashMap<>();
/*  703 */     for (Object[] os : this.fields) {
/*  704 */       Excel excel = (Excel)os[1];
/*  705 */       String key = StringUtils.format("data_{}_{}_{}", new Object[] { excel.align(), excel.color(), excel.backgroundColor() });
/*  706 */       if (!styles.containsKey(key)) {
/*  707 */         CellStyle style = wb.createCellStyle();
/*  708 */         style.setAlignment(excel.align());
/*  709 */         style.setVerticalAlignment(VerticalAlignment.CENTER);
/*  710 */         style.setBorderRight(BorderStyle.THIN);
/*  711 */         style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
/*  712 */         style.setBorderLeft(BorderStyle.THIN);
/*  713 */         style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
/*  714 */         style.setBorderTop(BorderStyle.THIN);
/*  715 */         style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
/*  716 */         style.setBorderBottom(BorderStyle.THIN);
/*  717 */         style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
/*  718 */         style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
/*  719 */         style.setFillForegroundColor(excel.backgroundColor().getIndex());
/*  720 */         Font dataFont = wb.createFont();
/*  721 */         dataFont.setFontName("Arial");
/*  722 */         dataFont.setFontHeightInPoints((short)10);
/*  723 */         dataFont.setColor((excel.color()).index);
/*  724 */         style.setFont(dataFont);
/*  725 */         styles.put(key, style);
/*      */       } 
/*      */     } 
/*  728 */     return styles;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Cell createHeadCell(Excel attr, Row row, int column) {
/*  736 */     Cell cell = row.createCell(column);
/*      */     
/*  738 */     cell.setCellValue(attr.name());
/*  739 */     setDataValidation(attr, row, column);
/*  740 */     cell.setCellStyle(this.styles.get(StringUtils.format("header_{}_{}", new Object[] { attr.headerColor(), attr.headerBackgroundColor() })));
/*  741 */     if (isSubList()) {
/*      */       
/*  743 */       this.sheet.setDefaultColumnStyle(column, this.styles.get(StringUtils.format("data_{}_{}_{}", new Object[] { attr.align(), attr.color(), attr.backgroundColor() })));
/*  744 */       if (attr.needMerge()) {
/*  745 */         this.sheet.addMergedRegion(new CellRangeAddress(this.rownum - 1, this.rownum, column, column));
/*      */       }
/*      */     } 
/*  748 */     return cell;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCellVo(Object value, Excel attr, Cell cell) {
/*  759 */     if (Excel.ColumnType.STRING == attr.cellType()) {
/*  760 */       String cellValue = Convert.toStr(value);
/*      */       
/*  762 */       if (StringUtils.startsWithAny(cellValue, (CharSequence[])FORMULA_STR)) {
/*  763 */         cellValue = RegExUtils.replaceFirst(cellValue, "=|-|\\+|@", "\t$0");
/*      */       }
/*  765 */       if (value instanceof Collection && StringUtils.equals("[]", cellValue)) {
/*  766 */         cellValue = "";
/*      */       }
/*  768 */       cell.setCellValue(StringUtils.isNull(cellValue) ? attr.defaultValue() : (cellValue + attr.suffix()));
/*  769 */     } else if (Excel.ColumnType.NUMERIC == attr.cellType()) {
/*  770 */       if (StringUtils.isNotNull(value)) {
/*  771 */         cell.setCellValue(StringUtils.contains(Convert.toStr(value), ".") ? Convert.toDouble(value).doubleValue() : Convert.toInt(value).intValue());
/*      */       }
/*  773 */     } else if (Excel.ColumnType.IMAGE == attr.cellType()) {
/*  774 */       XSSFClientAnchor xSSFClientAnchor = new XSSFClientAnchor(0, 0, 0, 0, (short)cell.getColumnIndex(), cell.getRow().getRowNum(), (short)(cell.getColumnIndex() + 1), cell.getRow().getRowNum() + 1);
/*  775 */       String imagePath = Convert.toStr(value);
/*  776 */       if (StringUtils.isNotEmpty(imagePath)) {
/*  777 */         byte[] data = ImageUtils.getImage(imagePath);
/*  778 */         getDrawingPatriarch(cell.getSheet()).createPicture((ClientAnchor)xSSFClientAnchor, cell
/*  779 */             .getSheet().getWorkbook().addPicture(data, getImageType(data)));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Drawing<?> getDrawingPatriarch(Sheet sheet) {
/*  788 */     if (sheet.getDrawingPatriarch() == null) {
/*  789 */       sheet.createDrawingPatriarch();
/*      */     }
/*  791 */     return sheet.getDrawingPatriarch();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getImageType(byte[] value) {
/*  798 */     String type = FileTypeUtils.getFileExtendName(value);
/*  799 */     if ("JPG".equalsIgnoreCase(type))
/*  800 */       return 5; 
/*  801 */     if ("PNG".equalsIgnoreCase(type)) {
/*  802 */       return 6;
/*      */     }
/*  804 */     return 5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDataValidation(Excel attr, Row row, int column) {
/*  811 */     if (attr.name().indexOf("注：") >= 0) {
/*  812 */       this.sheet.setColumnWidth(column, 6000);
/*      */     } else {
/*      */       
/*  815 */       this.sheet.setColumnWidth(column, (int)((attr.width() + 0.72D) * 256.0D));
/*      */     } 
/*  817 */     if (StringUtils.isNotEmpty(attr.prompt()) || (attr.combo()).length > 0) {
/*  818 */       if ((attr.combo()).length > 15 || StringUtils.join((Object[])attr.combo()).length() > 255) {
/*      */         
/*  820 */         setXSSFValidationWithHidden(this.sheet, attr.combo(), attr.prompt(), 1, 100, column, column);
/*      */       } else {
/*      */         
/*  823 */         setPromptOrValidation(this.sheet, attr.combo(), attr.prompt(), 1, 100, column, column);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Cell addCell(Excel attr, Row row, T vo, Field field, int column) {
/*  832 */     Cell cell = null;
/*      */     
/*      */     try {
/*  835 */       row.setHeight(this.maxHeight);
/*      */       
/*  837 */       if (attr.isExport()) {
/*      */         
/*  839 */         cell = row.createCell(column);
/*  840 */         if (isSubListValue(vo) && getListCellValue(vo).size() > 1 && attr.needMerge()) {
/*  841 */           CellRangeAddress cellAddress = new CellRangeAddress(this.subMergedFirstRowNum, this.subMergedLastRowNum, column, column);
/*  842 */           this.sheet.addMergedRegion(cellAddress);
/*      */         } 
/*  844 */         cell.setCellStyle(this.styles.get(StringUtils.format("data_{}_{}_{}", new Object[] { attr.align(), attr.color(), attr.backgroundColor() })));
/*      */ 
/*      */         
/*  847 */         Object value = getTargetValue(vo, field, attr);
/*  848 */         String dateFormat = attr.dateFormat();
/*  849 */         String readConverterExp = attr.readConverterExp();
/*  850 */         String separator = attr.separator();
/*  851 */         String dictType = attr.dictType();
/*  852 */         if (StringUtils.isNotEmpty(dateFormat) && StringUtils.isNotNull(value)) {
/*  853 */           cell.setCellValue(parseDateToStr(dateFormat, value));
/*  854 */         } else if (StringUtils.isNotEmpty(readConverterExp) && StringUtils.isNotNull(value)) {
/*  855 */           cell.setCellValue(convertByExp(Convert.toStr(value), readConverterExp, separator));
/*  856 */         } else if (StringUtils.isNotEmpty(dictType) && StringUtils.isNotNull(value)) {
/*  857 */           if (!this.sysDictMap.containsKey(dictType + value)) {
/*  858 */             String lable = convertDictByExp(Convert.toStr(value), dictType, separator);
/*  859 */             this.sysDictMap.put(dictType + value, lable);
/*      */           } 
/*  861 */           cell.setCellValue(this.sysDictMap.get(dictType + value));
/*  862 */         } else if (value instanceof BigDecimal && -1 != attr.scale()) {
/*  863 */           cell.setCellValue(((BigDecimal)value).setScale(attr.scale(), attr.roundingMode()).doubleValue());
/*  864 */         } else if (!attr.handler().equals(ExcelHandlerAdapter.class)) {
/*  865 */           cell.setCellValue(dataFormatHandlerAdapter(value, attr, cell));
/*      */         } else {
/*      */           
/*  868 */           setCellVo(value, attr, cell);
/*      */         } 
/*  870 */         addStatisticsData(Integer.valueOf(column), Convert.toStr(value), attr);
/*      */       } 
/*  872 */     } catch (Exception e) {
/*  873 */       log.error("导出Excel失败{}", e);
/*      */     } 
/*  875 */     return cell;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPromptOrValidation(Sheet sheet, String[] textlist, String promptContent, int firstRow, int endRow, int firstCol, int endCol) {
/*  891 */     DataValidationHelper helper = sheet.getDataValidationHelper();
/*  892 */     DataValidationConstraint constraint = (textlist.length > 0) ? helper.createExplicitListConstraint(textlist) : helper.createCustomConstraint("DD1");
/*  893 */     CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
/*  894 */     DataValidation dataValidation = helper.createValidation(constraint, regions);
/*  895 */     if (StringUtils.isNotEmpty(promptContent)) {
/*      */       
/*  897 */       dataValidation.createPromptBox("", promptContent);
/*  898 */       dataValidation.setShowPromptBox(true);
/*      */     } 
/*      */     
/*  901 */     if (dataValidation instanceof org.apache.poi.xssf.usermodel.XSSFDataValidation) {
/*  902 */       dataValidation.setSuppressDropDownArrow(true);
/*  903 */       dataValidation.setShowErrorBox(true);
/*      */     } else {
/*  905 */       dataValidation.setSuppressDropDownArrow(false);
/*      */     } 
/*  907 */     sheet.addValidationData(dataValidation);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setXSSFValidationWithHidden(Sheet sheet, String[] textlist, String promptContent, int firstRow, int endRow, int firstCol, int endCol) {
/*  922 */     String hideSheetName = "combo_" + firstCol + "_" + endCol;
/*  923 */     Sheet hideSheet = this.wb.createSheet(hideSheetName);
/*  924 */     for (int i = 0; i < textlist.length; i++) {
/*  925 */       hideSheet.createRow(i).createCell(0).setCellValue(textlist[i]);
/*      */     }
/*      */     
/*  928 */     Name name = this.wb.createName();
/*  929 */     name.setNameName(hideSheetName + "_data");
/*  930 */     name.setRefersToFormula(hideSheetName + "!$A$1:$A$" + textlist.length);
/*  931 */     DataValidationHelper helper = sheet.getDataValidationHelper();
/*      */     
/*  933 */     DataValidationConstraint constraint = helper.createFormulaListConstraint(hideSheetName + "_data");
/*      */     
/*  935 */     CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
/*      */     
/*  937 */     DataValidation dataValidation = helper.createValidation(constraint, regions);
/*  938 */     if (StringUtils.isNotEmpty(promptContent)) {
/*      */       
/*  940 */       dataValidation.createPromptBox("", promptContent);
/*  941 */       dataValidation.setShowPromptBox(true);
/*      */     } 
/*      */     
/*  944 */     if (dataValidation instanceof org.apache.poi.xssf.usermodel.XSSFDataValidation) {
/*  945 */       dataValidation.setSuppressDropDownArrow(true);
/*  946 */       dataValidation.setShowErrorBox(true);
/*      */     } else {
/*  948 */       dataValidation.setSuppressDropDownArrow(false);
/*      */     } 
/*      */     
/*  951 */     sheet.addValidationData(dataValidation);
/*      */     
/*  953 */     this.wb.setSheetHidden(this.wb.getSheetIndex(hideSheet), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String convertByExp(String propertyValue, String converterExp, String separator) {
/*  965 */     StringBuilder propertyString = new StringBuilder();
/*  966 */     String[] convertSource = converterExp.split(",");
/*  967 */     for (String item : convertSource) {
/*  968 */       String[] itemArray = item.split("=");
/*  969 */       if (StringUtils.containsAny(propertyValue, separator)) {
/*  970 */         for (String value : propertyValue.split(separator)) {
/*  971 */           if (itemArray[0].equals(value)) {
/*  972 */             propertyString.append(itemArray[1] + separator);
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  977 */       } else if (itemArray[0].equals(propertyValue)) {
/*  978 */         return itemArray[1];
/*      */       } 
/*      */     } 
/*      */     
/*  982 */     return StringUtils.stripEnd(propertyString.toString(), separator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String reverseByExp(String propertyValue, String converterExp, String separator) {
/*  994 */     StringBuilder propertyString = new StringBuilder();
/*  995 */     String[] convertSource = converterExp.split(",");
/*  996 */     for (String item : convertSource) {
/*  997 */       String[] itemArray = item.split("=");
/*  998 */       if (StringUtils.containsAny(propertyValue, separator)) {
/*  999 */         for (String value : propertyValue.split(separator)) {
/* 1000 */           if (itemArray[1].equals(value)) {
/* 1001 */             propertyString.append(itemArray[0] + separator);
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/* 1006 */       } else if (itemArray[1].equals(propertyValue)) {
/* 1007 */         return itemArray[0];
/*      */       } 
/*      */     } 
/*      */     
/* 1011 */     return StringUtils.stripEnd(propertyString.toString(), separator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String convertDictByExp(String dictValue, String dictType, String separator) {
/* 1023 */     return DictUtils.getDictLabel(dictType, dictValue, separator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String reverseDictByExp(String dictLabel, String dictType, String separator) {
/* 1035 */     return DictUtils.getDictValue(dictType, dictLabel, separator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String dataFormatHandlerAdapter(Object value, Excel excel, Cell cell) {
/*      */     try {
/* 1047 */       Object instance = excel.handler().newInstance();
/* 1048 */       Method formatMethod = excel.handler().getMethod("format", new Class[] { Object.class, String[].class, Cell.class, Workbook.class });
/* 1049 */       value = formatMethod.invoke(instance, new Object[] { value, excel.args(), cell, this.wb });
/* 1050 */     } catch (Exception e) {
/* 1051 */       log.error("不能格式化数据 " + excel.handler(), e.getMessage());
/*      */     } 
/* 1053 */     return Convert.toStr(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addStatisticsData(Integer index, String text, Excel entity) {
/* 1060 */     if (entity != null && entity.isStatistics()) {
/* 1061 */       Double temp = Double.valueOf(0.0D);
/* 1062 */       if (!this.statistics.containsKey(index)) {
/* 1063 */         this.statistics.put(index, temp);
/*      */       }
/*      */       try {
/* 1066 */         temp = Double.valueOf(text);
/* 1067 */       } catch (NumberFormatException numberFormatException) {}
/*      */       
/* 1069 */       this.statistics.put(index, Double.valueOf(((Double)this.statistics.get(index)).doubleValue() + temp.doubleValue()));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addStatisticsRow() {
/* 1077 */     if (this.statistics.size() > 0) {
/* 1078 */       Row row = this.sheet.createRow(this.sheet.getLastRowNum() + 1);
/* 1079 */       Set<Integer> keys = this.statistics.keySet();
/* 1080 */       Cell cell = row.createCell(0);
/* 1081 */       cell.setCellStyle(this.styles.get("total"));
/* 1082 */       cell.setCellValue("合计");
/*      */       
/* 1084 */       for (Integer key : keys) {
/* 1085 */         cell = row.createCell(key.intValue());
/* 1086 */         cell.setCellStyle(this.styles.get("total"));
/* 1087 */         cell.setCellValue(DOUBLE_FORMAT.format(this.statistics.get(key)));
/*      */       } 
/* 1089 */       this.statistics.clear();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String encodingFilename(String filename) {
/* 1097 */     filename = UUID.randomUUID() + "_" + filename + ".xlsx";
/* 1098 */     return filename;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAbsoluteFile(String filename) {
/* 1107 */     String downloadPath = RuoYiConfig.getDownloadPath() + filename;
/* 1108 */     File desc = new File(downloadPath);
/* 1109 */     if (!desc.getParentFile().exists()) {
/* 1110 */       desc.getParentFile().mkdirs();
/*      */     }
/* 1112 */     return downloadPath;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object getTargetValue(T vo, Field field, Excel excel) throws Exception {
/* 1125 */     Object o = field.get(vo);
/* 1126 */     if (StringUtils.isNotEmpty(excel.targetAttr())) {
/* 1127 */       String target = excel.targetAttr();
/* 1128 */       if (target.contains(".")) {
/* 1129 */         String[] targets = target.split("[.]");
/* 1130 */         for (String name : targets) {
/* 1131 */           o = getValue(o, name);
/*      */         }
/*      */       } else {
/* 1134 */         o = getValue(o, target);
/*      */       } 
/*      */     } 
/* 1137 */     return o;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object getValue(Object o, String name) throws Exception {
/* 1149 */     if (StringUtils.isNotNull(o) && StringUtils.isNotEmpty(name)) {
/* 1150 */       Class<?> clazz = o.getClass();
/* 1151 */       Field field = clazz.getDeclaredField(name);
/* 1152 */       field.setAccessible(true);
/* 1153 */       o = field.get(o);
/*      */     } 
/* 1155 */     return o;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void createExcelField() {
/* 1162 */     this.fields = getFields();
/* 1163 */     this.fields = (List<Object[]>)this.fields.stream().sorted(Comparator.comparing(objects -> Integer.valueOf(((Excel)objects[1]).sort()))).collect(Collectors.toList());
/* 1164 */     this.maxHeight = getRowHeight();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Object[]> getFields() {
/* 1171 */     List<Object[]> fields = new ArrayList();
/* 1172 */     List<Field> tempFields = new ArrayList<>();
/* 1173 */     tempFields.addAll(Arrays.asList(this.clazz.getSuperclass().getDeclaredFields()));
/* 1174 */     tempFields.addAll(Arrays.asList(this.clazz.getDeclaredFields()));
/* 1175 */     for (Field field : tempFields) {
/* 1176 */       if (!ArrayUtils.contains((Object[])this.excludeFields, field.getName())) {
/*      */         
/* 1178 */         if (field.isAnnotationPresent((Class)Excel.class)) {
/* 1179 */           Excel attr = field.<Excel>getAnnotation(Excel.class);
/* 1180 */           if (attr != null && (attr.type() == Excel.Type.ALL || attr.type() == this.type)) {
/* 1181 */             field.setAccessible(true);
/* 1182 */             fields.add(new Object[] { field, attr });
/*      */           } 
/* 1184 */           if (Collection.class.isAssignableFrom(field.getType())) {
/* 1185 */             this.subMethod = getSubMethod(field.getName(), this.clazz);
/* 1186 */             ParameterizedType pt = (ParameterizedType)field.getGenericType();
/* 1187 */             Class<?> subClass = (Class)pt.getActualTypeArguments()[0];
/* 1188 */             this.subFields = FieldUtils.getFieldsListWithAnnotation(subClass, Excel.class);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1193 */         if (field.isAnnotationPresent((Class)Excels.class)) {
/* 1194 */           Excels attrs = field.<Excels>getAnnotation(Excels.class);
/* 1195 */           Excel[] excels = attrs.value();
/* 1196 */           for (Excel attr : excels) {
/* 1197 */             if (!ArrayUtils.contains((Object[])this.excludeFields, field.getName() + "." + attr.targetAttr()) && attr != null && (attr
/* 1198 */               .type() == Excel.Type.ALL || attr.type() == this.type)) {
/* 1199 */               field.setAccessible(true);
/* 1200 */               fields.add(new Object[] { field, attr });
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1206 */     return fields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getRowHeight() {
/* 1213 */     double maxHeight = 0.0D;
/* 1214 */     for (Object[] os : this.fields) {
/* 1215 */       Excel excel = (Excel)os[1];
/* 1216 */       maxHeight = Math.max(maxHeight, excel.height());
/*      */     } 
/* 1218 */     return (short)(int)(maxHeight * 20.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void createWorkbook() {
/* 1225 */     this.wb = (Workbook)new SXSSFWorkbook(500);
/* 1226 */     this.sheet = this.wb.createSheet();
/* 1227 */     this.wb.setSheetName(0, this.sheetName);
/* 1228 */     this.styles = createStyles(this.wb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void createSheet(int sheetNo, int index) {
/* 1239 */     if (sheetNo > 1 && index > 0) {
/* 1240 */       this.sheet = this.wb.createSheet();
/* 1241 */       createTitle();
/* 1242 */       this.wb.setSheetName(index, this.sheetName + index);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getCellValue(Row row, int column) {
/* 1254 */     if (row == null) {
/* 1255 */       return row;
/*      */     }
/* 1257 */     Object val = "";
/*      */     try {
/* 1259 */       Cell cell = row.getCell(column);
/* 1260 */       if (StringUtils.isNotNull(cell)) {
/* 1261 */         if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
/* 1262 */           val = Double.valueOf(cell.getNumericCellValue());
/* 1263 */           if (DateUtil.isCellDateFormatted(cell)) {
/* 1264 */             val = DateUtil.getJavaDate(((Double)val).doubleValue());
/*      */           }
/* 1266 */           else if (((Double)val).doubleValue() % 1.0D != 0.0D) {
/* 1267 */             val = new BigDecimal(val.toString());
/*      */           } else {
/* 1269 */             val = (new DecimalFormat("0")).format(val);
/*      */           }
/*      */         
/* 1272 */         } else if (cell.getCellType() == CellType.STRING) {
/* 1273 */           val = cell.getStringCellValue();
/* 1274 */         } else if (cell.getCellType() == CellType.BOOLEAN) {
/* 1275 */           val = Boolean.valueOf(cell.getBooleanCellValue());
/* 1276 */         } else if (cell.getCellType() == CellType.ERROR) {
/* 1277 */           val = Byte.valueOf(cell.getErrorCellValue());
/*      */         }
/*      */       
/*      */       }
/* 1281 */     } catch (Exception e) {
/* 1282 */       return val;
/*      */     } 
/* 1284 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isRowEmpty(Row row) {
/* 1294 */     if (row == null) {
/* 1295 */       return true;
/*      */     }
/* 1297 */     for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
/* 1298 */       Cell cell = row.getCell(i);
/* 1299 */       if (cell != null && cell.getCellType() != CellType.BLANK) {
/* 1300 */         return false;
/*      */       }
/*      */     } 
/* 1303 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<String, PictureData> getSheetPictures03(HSSFSheet sheet, HSSFWorkbook workbook) {
/* 1314 */     Map<String, PictureData> sheetIndexPicMap = new HashMap<>();
/* 1315 */     List<HSSFPictureData> pictures = workbook.getAllPictures();
/* 1316 */     if (!pictures.isEmpty()) {
/* 1317 */       for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
/* 1318 */         HSSFClientAnchor anchor = (HSSFClientAnchor)shape.getAnchor();
/* 1319 */         if (shape instanceof HSSFPicture) {
/* 1320 */           HSSFPicture pic = (HSSFPicture)shape;
/* 1321 */           int pictureIndex = pic.getPictureIndex() - 1;
/* 1322 */           HSSFPictureData picData = pictures.get(pictureIndex);
/* 1323 */           String picIndex = anchor.getRow1() + "_" + anchor.getCol1();
/* 1324 */           sheetIndexPicMap.put(picIndex, picData);
/*      */         } 
/*      */       } 
/* 1327 */       return sheetIndexPicMap;
/*      */     } 
/* 1329 */     return sheetIndexPicMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<String, PictureData> getSheetPictures07(XSSFSheet sheet, XSSFWorkbook workbook) {
/* 1341 */     Map<String, PictureData> sheetIndexPicMap = new HashMap<>();
/* 1342 */     for (POIXMLDocumentPart dr : sheet.getRelations()) {
/* 1343 */       if (dr instanceof XSSFDrawing) {
/* 1344 */         XSSFDrawing drawing = (XSSFDrawing)dr;
/* 1345 */         List<XSSFShape> shapes = drawing.getShapes();
/* 1346 */         for (XSSFShape shape : shapes) {
/* 1347 */           if (shape instanceof XSSFPicture) {
/* 1348 */             XSSFPicture pic = (XSSFPicture)shape;
/* 1349 */             XSSFClientAnchor anchor = pic.getPreferredSize();
/* 1350 */             CTMarker ctMarker = anchor.getFrom();
/* 1351 */             String picIndex = ctMarker.getRow() + "_" + ctMarker.getCol();
/* 1352 */             sheetIndexPicMap.put(picIndex, pic.getPictureData());
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1357 */     return sheetIndexPicMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String parseDateToStr(String dateFormat, Object val) {
/*      */     String str;
/* 1368 */     if (val == null) {
/* 1369 */       return "";
/*      */     }
/*      */     
/* 1372 */     if (val instanceof Date) {
/* 1373 */       str = DateUtils.parseDateToStr(dateFormat, (Date)val);
/* 1374 */     } else if (val instanceof LocalDateTime) {
/* 1375 */       str = DateUtils.parseDateToStr(dateFormat, DateUtils.toDate((LocalDateTime)val));
/* 1376 */     } else if (val instanceof LocalDate) {
/* 1377 */       str = DateUtils.parseDateToStr(dateFormat, DateUtils.toDate((LocalDate)val));
/*      */     } else {
/* 1379 */       str = val.toString();
/*      */     } 
/* 1381 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSubList() {
/* 1388 */     return (StringUtils.isNotNull(this.subFields) && this.subFields.size() > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSubListValue(T vo) {
/* 1395 */     return (StringUtils.isNotNull(this.subFields) && this.subFields.size() > 0 && StringUtils.isNotNull(getListCellValue(vo)) && getListCellValue(vo).size() > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<?> getListCellValue(Object obj) {
/*      */     Object value;
/*      */     try {
/* 1404 */       value = this.subMethod.invoke(obj, new Object[0]);
/* 1405 */     } catch (Exception e) {
/* 1406 */       return new ArrayList();
/*      */     } 
/* 1408 */     return (Collection)value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Method getSubMethod(String name, Class<?> pojoClass) {
/* 1419 */     StringBuffer getMethodName = new StringBuffer("get");
/* 1420 */     getMethodName.append(name.substring(0, 1).toUpperCase());
/* 1421 */     getMethodName.append(name.substring(1));
/* 1422 */     Method method = null;
/*      */     try {
/* 1424 */       method = pojoClass.getMethod(getMethodName.toString(), new Class[0]);
/* 1425 */     } catch (Exception e) {
/* 1426 */       log.error("获取对象异常{}", e.getMessage());
/*      */     } 
/* 1428 */     return method;
/*      */   }
/*      */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/poi/ExcelUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */