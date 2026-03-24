/*     */ package com.ruoyi.common.utils.bean;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ //import org.springframework.beans.BeanUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BeanUtils
/*     */   extends org.springframework.beans.BeanUtils
/*     */ {
/*     */   private static final int BEAN_METHOD_PROP_INDEX = 3;
/*  20 */   private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");
/*     */ 
/*     */   
/*  23 */   private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void copyBeanProp(Object dest, Object src) {
/*     */     try {
/*  35 */       copyProperties(src, dest);
/*     */     }
/*  37 */     catch (Exception e) {
/*     */       
/*  39 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Method> getSetterMethods(Object obj) {
/*  52 */     List<Method> setterMethods = new ArrayList<>();
/*     */ 
/*     */     
/*  55 */     Method[] methods = obj.getClass().getMethods();
/*     */ 
/*     */ 
/*     */     
/*  59 */     for (Method method : methods) {
/*     */       
/*  61 */       Matcher m = SET_PATTERN.matcher(method.getName());
/*  62 */       if (m.matches() && (method.getParameterTypes()).length == 1)
/*     */       {
/*  64 */         setterMethods.add(method);
/*     */       }
/*     */     } 
/*     */     
/*  68 */     return setterMethods;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Method> getGetterMethods(Object obj) {
/*  81 */     List<Method> getterMethods = new ArrayList<>();
/*     */     
/*  83 */     Method[] methods = obj.getClass().getMethods();
/*     */     
/*  85 */     for (Method method : methods) {
/*     */       
/*  87 */       Matcher m = GET_PATTERN.matcher(method.getName());
/*  88 */       if (m.matches() && (method.getParameterTypes()).length == 0)
/*     */       {
/*  90 */         getterMethods.add(method);
/*     */       }
/*     */     } 
/*     */     
/*  94 */     return getterMethods;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isMethodPropEquals(String m1, String m2) {
/* 108 */     return m1.substring(3).equals(m2.substring(3));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/bean/BeanUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */