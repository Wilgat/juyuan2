/*    */ package com.ruoyi.web.controller.iotlighting;
/*    */ 
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.iotlighting.domain.Diagnosis;
/*    */ import com.ruoyi.iotlighting.service.IDiagnosisService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.access.prepost.PreAuthorize;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping({"/diagnosis"})
/*    */ public class DiagnosisController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private IDiagnosisService diagnosisService;
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:diagnosis:list')")
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(Diagnosis diagnosis) {
/* 33 */     startPage();
/* 34 */     List<Diagnosis> list = this.diagnosisService.selectDiagnosisList(diagnosis);
/* 35 */     return getDataTable(list);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/DiagnosisController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */