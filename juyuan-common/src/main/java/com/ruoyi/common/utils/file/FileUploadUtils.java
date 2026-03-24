/*     */ package com.ruoyi.common.utils.file;
/*     */ 
/*     */ import com.ruoyi.common.config.RuoYiConfig;
/*     */ import com.ruoyi.common.exception.file.FileNameLengthLimitExceededException;
/*     */ import com.ruoyi.common.exception.file.FileSizeLimitExceededException;
/*     */ import com.ruoyi.common.exception.file.InvalidExtensionException;
/*     */ import com.ruoyi.common.utils.DateUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.uuid.Seq;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.Objects;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.springframework.web.multipart.MultipartFile;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileUploadUtils
/*     */ {
/*     */   public static final long DEFAULT_MAX_SIZE = 52428800L;
/*     */   public static final int DEFAULT_FILE_NAME_LENGTH = 100;
/*  38 */   private static String defaultBaseDir = RuoYiConfig.getProfile();
/*     */ 
/*     */   
/*     */   public static void setDefaultBaseDir(String defaultBaseDir) {
/*  42 */     FileUploadUtils.defaultBaseDir = defaultBaseDir;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getDefaultBaseDir() {
/*  47 */     return defaultBaseDir;
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
/*     */   public static final String upload(MultipartFile file) throws IOException {
/*     */     try {
/*  61 */       return upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
/*     */     }
/*  63 */     catch (Exception e) {
/*     */       
/*  65 */       throw new IOException(e.getMessage(), e);
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
/*     */ 
/*     */   
/*     */   public static final String upload(String baseDir, MultipartFile file) throws IOException {
/*     */     try {
/*  81 */       return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
/*     */     }
/*  83 */     catch (Exception e) {
/*     */       
/*  85 */       throw new IOException(e.getMessage(), e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension) throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException, InvalidExtensionException {
/* 105 */     int fileNamelength = ((String)Objects.<String>requireNonNull(file.getOriginalFilename())).length();
/* 106 */     if (fileNamelength > 100)
/*     */     {
/* 108 */       throw new FileNameLengthLimitExceededException(100);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 113 */     String fileName = extractFilename(file);
/*     */     
/* 115 */     String absPath = getAbsoluteFile(baseDir, fileName).getAbsolutePath();
/* 116 */     file.transferTo(Paths.get(absPath, new String[0]));
/* 117 */     return getPathFileName(baseDir, fileName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String extractFilename(MultipartFile file) {
/* 125 */     return StringUtils.format("{}/{}_{}.{}", new Object[] { DateUtils.datePath(), 
/* 126 */           FilenameUtils.getBaseName(file.getOriginalFilename()), Seq.getId("UPLOAD"), getExtension(file) });
/*     */   }
/*     */ 
/*     */   
/*     */   public static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException {
/* 131 */     File desc = new File(uploadDir + File.separator + fileName);
/*     */     
/* 133 */     if (!desc.exists())
/*     */     {
/* 135 */       if (!desc.getParentFile().exists())
/*     */       {
/* 137 */         desc.getParentFile().mkdirs();
/*     */       }
/*     */     }
/* 140 */     return desc;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final String getPathFileName(String uploadDir, String fileName) throws IOException {
/* 145 */     int dirLastIndex = RuoYiConfig.getProfile().length() + 1;
/* 146 */     String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
/* 147 */     return "/profile/" + currentDir + "/" + fileName;
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
/*     */   public static final void assertAllowed(MultipartFile file, String[] allowedExtension) throws FileSizeLimitExceededException, InvalidExtensionException {
/* 161 */     long size = file.getSize();
/* 162 */     if (size > 52428800L)
/*     */     {
/* 164 */       throw new FileSizeLimitExceededException(50L);
/*     */     }
/*     */     
/* 167 */     String fileName = file.getOriginalFilename();
/* 168 */     String extension = getExtension(file);
/* 169 */     if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
/*     */       
/* 171 */       if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION)
/*     */       {
/* 173 */         throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension, fileName);
/*     */       }
/*     */       
/* 176 */       if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION)
/*     */       {
/* 178 */         throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension, fileName);
/*     */       }
/*     */       
/* 181 */       if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION)
/*     */       {
/* 183 */         throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension, fileName);
/*     */       }
/*     */       
/* 186 */       if (allowedExtension == MimeTypeUtils.VIDEO_EXTENSION)
/*     */       {
/* 188 */         throw new InvalidExtensionException.InvalidVideoExtensionException(allowedExtension, extension, fileName);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 193 */       throw new InvalidExtensionException(allowedExtension, extension, fileName);
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
/*     */   
/*     */   public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
/* 207 */     for (String str : allowedExtension) {
/*     */       
/* 209 */       if (str.equalsIgnoreCase(extension))
/*     */       {
/* 211 */         return true;
/*     */       }
/*     */     } 
/* 214 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String getExtension(MultipartFile file) {
/* 225 */     String extension = FilenameUtils.getExtension(file.getOriginalFilename());
/* 226 */     if (StringUtils.isEmpty(extension))
/*     */     {
/* 228 */       extension = MimeTypeUtils.getExtension(Objects.<String>requireNonNull(file.getContentType()));
/*     */     }
/* 230 */     return extension;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/file/FileUploadUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */