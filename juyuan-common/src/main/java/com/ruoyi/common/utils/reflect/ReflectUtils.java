/*     */ package com.ruoyi.common.utils.reflect;
/*     */ 
/*     */ import com.ruoyi.common.core.text.Convert;
/*     */ import com.ruoyi.common.utils.DateUtils;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ import org.apache.poi.ss.usermodel.DateUtil;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
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
/*     */ public class ReflectUtils
/*     */ {
/*     */   private static final String SETTER_PREFIX = "set";
/*     */   private static final String GETTER_PREFIX = "get";
/*     */   private static final String CGLIB_CLASS_SEPARATOR = "$$";
/*  32 */   private static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <E> E invokeGetter(Object obj, String propertyName) {
/*  41 */     Object object = obj;
/*  42 */     for (String name : StringUtils.split(propertyName, ".")) {
/*     */       
/*  44 */       String getterMethodName = "get" + StringUtils.capitalize(name);
/*  45 */       object = invokeMethod(object, getterMethodName, new Class[0], new Object[0]);
/*     */     } 
/*  47 */     return (E)object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <E> void invokeSetter(Object obj, String propertyName, E value) {
/*  56 */     Object object = obj;
/*  57 */     String[] names = StringUtils.split(propertyName, ".");
/*  58 */     for (int i = 0; i < names.length; i++) {
/*     */       
/*  60 */       if (i < names.length - 1) {
/*     */         
/*  62 */         String getterMethodName = "get" + StringUtils.capitalize(names[i]);
/*  63 */         object = invokeMethod(object, getterMethodName, new Class[0], new Object[0]);
/*     */       }
/*     */       else {
/*     */         
/*  67 */         String setterMethodName = "set" + StringUtils.capitalize(names[i]);
/*  68 */         invokeMethodByName(object, setterMethodName, new Object[] { value });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <E> E getFieldValue(Object obj, String fieldName) {
/*  79 */     Field field = getAccessibleField(obj, fieldName);
/*  80 */     if (field == null) {
/*     */       
/*  82 */       logger.debug("在 [" + obj.getClass() + "] 中，没有找到 [" + fieldName + "] 字段 ");
/*  83 */       return null;
/*     */     } 
/*  85 */     E result = null;
/*     */     
/*     */     try {
/*  88 */       result = (E)field.get(obj);
/*     */     }
/*  90 */     catch (IllegalAccessException e) {
/*     */       
/*  92 */       logger.error("不可能抛出的异常{}", e.getMessage());
/*     */     } 
/*  94 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <E> void setFieldValue(Object obj, String fieldName, E value) {
/* 102 */     Field field = getAccessibleField(obj, fieldName);
/* 103 */     if (field == null) {
/*     */ 
/*     */       
/* 106 */       logger.debug("在 [" + obj.getClass() + "] 中，没有找到 [" + fieldName + "] 字段 ");
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 111 */       field.set(obj, value);
/*     */     }
/* 113 */     catch (IllegalAccessException e) {
/*     */       
/* 115 */       logger.error("不可能抛出的异常: {}", e.getMessage());
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
/*     */   public static <E> E invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object[] args) {
/* 128 */     if (obj == null || methodName == null)
/*     */     {
/* 130 */       return null;
/*     */     }
/* 132 */     Method method = getAccessibleMethod(obj, methodName, parameterTypes);
/* 133 */     if (method == null) {
/*     */       
/* 135 */       logger.debug("在 [" + obj.getClass() + "] 中，没有找到 [" + methodName + "] 方法 ");
/* 136 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/* 140 */       return (E)method.invoke(obj, args);
/*     */     }
/* 142 */     catch (Exception e) {
/*     */       
/* 144 */       String msg = "method: " + method + ", obj: " + obj + ", args: " + args + "";
/* 145 */       throw convertReflectionExceptionToUnchecked(msg, e);
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
/*     */   public static <E> E invokeMethodByName(Object obj, String methodName, Object[] args) {
/* 157 */     Method method = getAccessibleMethodByName(obj, methodName, args.length);
/* 158 */     if (method == null) {
/*     */ 
/*     */       
/* 161 */       logger.debug("在 [" + obj.getClass() + "] 中，没有找到 [" + methodName + "] 方法 ");
/* 162 */       return null;
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 167 */       Class<?>[] cs = method.getParameterTypes();
/* 168 */       for (int i = 0; i < cs.length; i++) {
/*     */         
/* 170 */         if (args[i] != null && !args[i].getClass().equals(cs[i]))
/*     */         {
/* 172 */           if (cs[i] == String.class) {
/*     */             
/* 174 */             args[i] = Convert.toStr(args[i]);
/* 175 */             if (StringUtils.endsWith((String)args[i], ".0"))
/*     */             {
/* 177 */               args[i] = StringUtils.substringBefore((String)args[i], ".0");
/*     */             }
/*     */           }
/* 180 */           else if (cs[i] == Integer.class) {
/*     */             
/* 182 */             args[i] = Convert.toInt(args[i]);
/*     */           }
/* 184 */           else if (cs[i] == Long.class) {
/*     */             
/* 186 */             args[i] = Convert.toLong(args[i]);
/*     */           }
/* 188 */           else if (cs[i] == Double.class) {
/*     */             
/* 190 */             args[i] = Convert.toDouble(args[i]);
/*     */           }
/* 192 */           else if (cs[i] == Float.class) {
/*     */             
/* 194 */             args[i] = Convert.toFloat(args[i]);
/*     */           }
/* 196 */           else if (cs[i] == Date.class) {
/*     */             
/* 198 */             if (args[i] instanceof String)
/*     */             {
/* 200 */               args[i] = DateUtils.parseDate(args[i]);
/*     */             }
/*     */             else
/*     */             {
/* 204 */               args[i] = DateUtil.getJavaDate(((Double)args[i]).doubleValue());
/*     */             }
/*     */           
/* 207 */           } else if (cs[i] == boolean.class || cs[i] == Boolean.class) {
/*     */             
/* 209 */             args[i] = Convert.toBool(args[i]);
/*     */           } 
/*     */         }
/*     */       } 
/* 213 */       return (E)method.invoke(obj, args);
/*     */     }
/* 215 */     catch (Exception e) {
/*     */       
/* 217 */       String msg = "method: " + method + ", obj: " + obj + ", args: " + args + "";
/* 218 */       throw convertReflectionExceptionToUnchecked(msg, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Field getAccessibleField(Object obj, String fieldName) {
/* 229 */     if (obj == null)
/*     */     {
/* 231 */       return null;
/*     */     }
/* 233 */     Validate.notBlank(fieldName, "fieldName can't be blank", new Object[0]);
/* 234 */     for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
/*     */ 
/*     */       
/*     */       try {
/* 238 */         Field field = superClass.getDeclaredField(fieldName);
/* 239 */         makeAccessible(field);
/* 240 */         return field;
/*     */       }
/* 242 */       catch (NoSuchFieldException e) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 247 */     return null;
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
/*     */   public static Method getAccessibleMethod(Object obj, String methodName, Class<?>... parameterTypes) {
/* 260 */     if (obj == null)
/*     */     {
/* 262 */       return null;
/*     */     }
/* 264 */     Validate.notBlank(methodName, "methodName can't be blank", new Object[0]);
/* 265 */     for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
/*     */ 
/*     */       
/*     */       try {
/* 269 */         Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
/* 270 */         makeAccessible(method);
/* 271 */         return method;
/*     */       }
/* 273 */       catch (NoSuchMethodException e) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 278 */     return null;
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
/*     */   public static Method getAccessibleMethodByName(Object obj, String methodName, int argsNum) {
/* 290 */     if (obj == null)
/*     */     {
/* 292 */       return null;
/*     */     }
/* 294 */     Validate.notBlank(methodName, "methodName can't be blank", new Object[0]);
/* 295 */     for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
/*     */       
/* 297 */       Method[] methods = searchType.getDeclaredMethods();
/* 298 */       for (Method method : methods) {
/*     */         
/* 300 */         if (method.getName().equals(methodName) && (method.getParameterTypes()).length == argsNum) {
/*     */           
/* 302 */           makeAccessible(method);
/* 303 */           return method;
/*     */         } 
/*     */       } 
/*     */     } 
/* 307 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void makeAccessible(Method method) {
/* 315 */     if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) && 
/* 316 */       !method.isAccessible())
/*     */     {
/* 318 */       method.setAccessible(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void makeAccessible(Field field) {
/* 327 */     if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || 
/* 328 */       Modifier.isFinal(field.getModifiers())) && !field.isAccessible())
/*     */     {
/* 330 */       field.setAccessible(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> Class<T> getClassGenricType(Class clazz) {
/* 341 */     return getClassGenricType(clazz, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class getClassGenricType(Class clazz, int index) {
/* 350 */     Type genType = clazz.getGenericSuperclass();
/*     */     
/* 352 */     if (!(genType instanceof ParameterizedType)) {
/*     */       
/* 354 */       logger.debug(clazz.getSimpleName() + "'s superclass not ParameterizedType");
/* 355 */       return Object.class;
/*     */     } 
/*     */     
/* 358 */     Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
/*     */     
/* 360 */     if (index >= params.length || index < 0) {
/*     */       
/* 362 */       logger.debug("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
/*     */       
/* 364 */       return Object.class;
/*     */     } 
/* 366 */     if (!(params[index] instanceof Class)) {
/*     */       
/* 368 */       logger.debug(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
/* 369 */       return Object.class;
/*     */     } 
/*     */     
/* 372 */     return (Class)params[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public static Class<?> getUserClass(Object instance) {
/* 377 */     if (instance == null)
/*     */     {
/* 379 */       throw new RuntimeException("Instance must not be null");
/*     */     }
/* 381 */     Class<?> clazz = instance.getClass();
/* 382 */     if (clazz != null && clazz.getName().contains("$$")) {
/*     */       
/* 384 */       Class<?> superClass = clazz.getSuperclass();
/* 385 */       if (superClass != null && !Object.class.equals(superClass))
/*     */       {
/* 387 */         return superClass;
/*     */       }
/*     */     } 
/* 390 */     return clazz;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RuntimeException convertReflectionExceptionToUnchecked(String msg, Exception e) {
/* 399 */     if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException || e instanceof NoSuchMethodException)
/*     */     {
/*     */       
/* 402 */       return new IllegalArgumentException(msg, e);
/*     */     }
/* 404 */     if (e instanceof InvocationTargetException)
/*     */     {
/* 406 */       return new RuntimeException(msg, ((InvocationTargetException)e).getTargetException());
/*     */     }
/* 408 */     return new RuntimeException(msg, e);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/reflect/ReflectUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */