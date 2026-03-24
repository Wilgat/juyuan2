/*    */ package com.ruoyi.common.core.domain;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonInclude;
/*    */ import com.ruoyi.common.core.domain.entity.SysDept;
/*    */ import com.ruoyi.common.core.domain.entity.SysMenu;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TreeSelect
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private String label;
/*    */   @JsonInclude(JsonInclude.Include.NON_EMPTY)
/*    */   private List<TreeSelect> children;
/*    */   
/*    */   public TreeSelect() {}
/*    */   
/*    */   public TreeSelect(SysDept dept) {
/* 36 */     this.id = dept.getDeptId();
/* 37 */     this.label = dept.getDeptName();
/* 38 */     this.children = (List<TreeSelect>)dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
/*    */   }
/*    */ 
/*    */   
/*    */   public TreeSelect(SysMenu menu) {
/* 43 */     this.id = menu.getMenuId();
/* 44 */     this.label = menu.getMenuName();
/* 45 */     this.children = (List<TreeSelect>)menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
/*    */   }
/*    */ 
/*    */   
/*    */   public Long getId() {
/* 50 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(Long id) {
/* 55 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLabel() {
/* 60 */     return this.label;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLabel(String label) {
/* 65 */     this.label = label;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<TreeSelect> getChildren() {
/* 70 */     return this.children;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setChildren(List<TreeSelect> children) {
/* 75 */     this.children = children;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/domain/TreeSelect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */