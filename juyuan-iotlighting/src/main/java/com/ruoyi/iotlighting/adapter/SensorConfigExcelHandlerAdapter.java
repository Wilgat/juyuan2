/*    */ package com.ruoyi.iotlighting.adapter;
/*    */ 
/*    */ import com.alibaba.fastjson2.JSON;
/*    */ import com.alibaba.fastjson2.JSONObject;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.common.utils.poi.ExcelHandlerAdapter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.poi.ss.usermodel.Cell;
/*    */ import org.apache.poi.ss.usermodel.Workbook;
/*    */ 
/*    */ 
/*    */ public class SensorConfigExcelHandlerAdapter
/*    */   implements ExcelHandlerAdapter
/*    */ {
/*    */   public Object format(Object value, String[] args, Cell cell, Workbook wb) {
/* 17 */     List<String> lightConfigArray = new ArrayList<>();
/* 18 */     JSONObject jsonObject = JSON.parseObject(value.toString());
/* 19 */     lightConfigArray.add(jsonObject.getJSONObject("day").getString("start"));
/* 20 */     lightConfigArray.add(jsonObject.getJSONObject("day").getString("end"));
/* 21 */     lightConfigArray.add(jsonObject.getJSONObject("day").getString("high"));
/* 22 */     lightConfigArray.add(jsonObject.getJSONObject("day").getString("wait"));
/* 23 */     lightConfigArray.add(jsonObject.getJSONObject("day").getString("low"));
/*    */     
/* 25 */     lightConfigArray.add(jsonObject.getJSONObject("night").getString("start"));
/* 26 */     lightConfigArray.add(jsonObject.getJSONObject("night").getString("end"));
/* 27 */     lightConfigArray.add(jsonObject.getJSONObject("night").getString("high"));
/* 28 */     lightConfigArray.add(jsonObject.getJSONObject("night").getString("wait"));
/* 29 */     lightConfigArray.add(jsonObject.getJSONObject("night").getString("low"));
/* 30 */     return StringUtils.join(lightConfigArray, ",");
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/adapter/SensorConfigExcelHandlerAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */