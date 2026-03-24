/*     */ package com.ruoyi.common.utils.file;
/*     */ 
/*     */ import com.ruoyi.common.config.RuoYiConfig;
/*     */ import com.ruoyi.common.utils.DateUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.uuid.IdUtils;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLEncoder;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileUtils
/*     */ {
/*  29 */   public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeBytes(String filePath, OutputStream os) throws IOException {
/*  40 */     FileInputStream fis = null;
/*     */     
/*     */     try {
/*  43 */       File file = new File(filePath);
/*  44 */       if (!file.exists())
/*     */       {
/*  46 */         throw new FileNotFoundException(filePath);
/*     */       }
/*  48 */       fis = new FileInputStream(file);
/*  49 */       byte[] b = new byte[1024];
/*     */       int length;
/*  51 */       while ((length = fis.read(b)) > 0)
/*     */       {
/*  53 */         os.write(b, 0, length);
/*     */       }
/*     */     }
/*  56 */     catch (IOException e) {
/*     */       
/*  58 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/*  62 */       IOUtils.close(os);
/*  63 */       IOUtils.close(fis);
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
/*     */   public static String writeImportBytes(byte[] data) throws IOException {
/*  76 */     return writeBytes(data, RuoYiConfig.getImportPath());
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
/*     */   public static String writeBytes(byte[] data, String uploadDir) throws IOException {
/*  89 */     FileOutputStream fos = null;
/*  90 */     String pathName = "";
/*     */     
/*     */     try {
/*  93 */       String extension = getFileExtendName(data);
/*  94 */       pathName = DateUtils.datePath() + "/" + IdUtils.fastUUID() + "." + extension;
/*  95 */       File file = FileUploadUtils.getAbsoluteFile(uploadDir, pathName);
/*  96 */       fos = new FileOutputStream(file);
/*  97 */       fos.write(data);
/*     */     }
/*     */     finally {
/*     */       
/* 101 */       IOUtils.close(fos);
/*     */     } 
/* 103 */     return FileUploadUtils.getPathFileName(uploadDir, pathName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean deleteFile(String filePath) {
/* 114 */     boolean flag = false;
/* 115 */     File file = new File(filePath);
/*     */     
/* 117 */     if (file.isFile() && file.exists())
/*     */     {
/* 119 */       flag = file.delete();
/*     */     }
/* 121 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isValidFilename(String filename) {
/* 132 */     return filename.matches(FILENAME_PATTERN);
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
/*     */   public static boolean checkAllowDownload(String resource) {
/* 144 */     if (StringUtils.contains(resource, ".."))
/*     */     {
/* 146 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 150 */     if (ArrayUtils.contains((Object[])MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, FileTypeUtils.getFileType(resource)))
/*     */     {
/* 152 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 156 */     return false;
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
/*     */   public static String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
/* 168 */     String agent = request.getHeader("USER-AGENT");
/* 169 */     String filename = fileName;
/* 170 */     if (agent.contains("MSIE")) {
/*     */ 
/*     */       
/* 173 */       filename = URLEncoder.encode(filename, "utf-8");
/* 174 */       filename = filename.replace("+", " ");
/*     */     }
/* 176 */     else if (agent.contains("Firefox")) {
/*     */ 
/*     */       
/* 179 */       filename = new String(fileName.getBytes(), "ISO8859-1");
/*     */     }
/* 181 */     else if (agent.contains("Chrome")) {
/*     */ 
/*     */       
/* 184 */       filename = URLEncoder.encode(filename, "utf-8");
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 189 */       filename = URLEncoder.encode(filename, "utf-8");
/*     */     } 
/* 191 */     return filename;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) throws UnsupportedEncodingException {
/* 202 */     String percentEncodedFileName = percentEncode(realFileName);
/*     */     
/* 204 */     StringBuilder contentDispositionValue = new StringBuilder();
/* 205 */     contentDispositionValue.append("attachment; filename=")
/* 206 */       .append(percentEncodedFileName)
/* 207 */       .append(";")
/* 208 */       .append("filename*=")
/* 209 */       .append("utf-8''")
/* 210 */       .append(percentEncodedFileName);
/*     */     
/* 212 */     response.addHeader("Access-Control-Expose-Headers", "Content-Disposition,download-filename");
/* 213 */     response.setHeader("Content-disposition", contentDispositionValue.toString());
/* 214 */     response.setHeader("download-filename", percentEncodedFileName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String percentEncode(String s) throws UnsupportedEncodingException {
/* 225 */     String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
/* 226 */     return encode.replaceAll("\\+", "%20");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFileExtendName(byte[] photoByte) {
/* 237 */     String strFileExtendName = "jpg";
/* 238 */     if (photoByte[0] == 71 && photoByte[1] == 73 && photoByte[2] == 70 && photoByte[3] == 56 && (photoByte[4] == 55 || photoByte[4] == 57) && photoByte[5] == 97) {
/*     */ 
/*     */       
/* 241 */       strFileExtendName = "gif";
/*     */     }
/* 243 */     else if (photoByte[6] == 74 && photoByte[7] == 70 && photoByte[8] == 73 && photoByte[9] == 70) {
/*     */       
/* 245 */       strFileExtendName = "jpg";
/*     */     }
/* 247 */     else if (photoByte[0] == 66 && photoByte[1] == 77) {
/*     */       
/* 249 */       strFileExtendName = "bmp";
/*     */     }
/* 251 */     else if (photoByte[1] == 80 && photoByte[2] == 78 && photoByte[3] == 71) {
/*     */       
/* 253 */       strFileExtendName = "png";
/*     */     } 
/* 255 */     return strFileExtendName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getName(String fileName) {
/* 266 */     if (fileName == null)
/*     */     {
/* 268 */       return null;
/*     */     }
/* 270 */     int lastUnixPos = fileName.lastIndexOf('/');
/* 271 */     int lastWindowsPos = fileName.lastIndexOf('\\');
/* 272 */     int index = Math.max(lastUnixPos, lastWindowsPos);
/* 273 */     return fileName.substring(index + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getNameNotSuffix(String fileName) {
/* 284 */     if (fileName == null)
/*     */     {
/* 286 */       return null;
/*     */     }
/* 288 */     String baseName = FilenameUtils.getBaseName(fileName);
/* 289 */     return baseName;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/file/FileUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */