/*     */ package com.ruoyi.web.controller.common;
/*     */ 
/*     */ import com.ruoyi.common.config.RuoYiConfig;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.utils.MessageUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.file.FileUploadUtils;
/*     */ import com.ruoyi.common.utils.file.FileUtils;
/*     */ import com.ruoyi.framework.config.ServerConfig;
/*     */ import com.ruoyi.system.domain.SysImgFile;
/*     */ import com.ruoyi.system.service.ISysFileService;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.http.HttpHeaders;
/*     */ import org.springframework.http.HttpStatus;
/*     */ import org.springframework.http.MediaType;
/*     */ import org.springframework.http.ResponseEntity;
/*     */ import org.springframework.util.MultiValueMap;
/*     */ import org.springframework.web.bind.annotation.GetMapping;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.PostMapping;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RestController;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ @RestController
/*     */ @RequestMapping({"/common"})
/*     */ public class CommonController
/*     */ {
/*  36 */   private static final Logger log = LoggerFactory.getLogger(CommonController.class);
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private ServerConfig serverConfig;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private ISysFileService sysFileService;
/*     */ 
/*     */   
/*     */   private static final String FILE_DELIMETER = ",";
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/download"})
/*     */   public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
/*     */     try {
/*  55 */       if (!FileUtils.checkAllowDownload(fileName)) {
/*  56 */         throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", new Object[] { fileName }));
/*     */       }
/*  58 */       String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
/*  59 */       String filePath = RuoYiConfig.getDownloadPath() + fileName;
/*     */       
/*  61 */       response.setContentType("application/octet-stream");
/*  62 */       FileUtils.setAttachmentResponseHeader(response, realFileName);
/*  63 */       FileUtils.writeBytes(filePath, (OutputStream)response.getOutputStream());
/*  64 */       if (delete.booleanValue()) {
/*  65 */         FileUtils.deleteFile(filePath);
/*     */       }
/*  67 */     } catch (Exception e) {
/*  68 */       log.error("下载文件失败", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PostMapping({"/upload"})
/*     */   public AjaxResult uploadFile(MultipartFile file) throws Exception {
/*     */     try {
/*  79 */       String filePath = RuoYiConfig.getUploadPath();
/*     */       
/*  81 */       SysImgFile sysFile = this.sysFileService.uploadFileToDb(file);
/*     */       
/*  83 */       String fileName = sysFile.getId().toString();
/*  84 */       String url = this.serverConfig.getUrl() + "/common/file/" + fileName;
/*  85 */       AjaxResult ajax = AjaxResult.success();
/*  86 */       ajax.put("url", url);
/*  87 */       ajax.put("fileName", fileName);
/*  88 */       ajax.put("newFileName", "/common/file/" + FileUtils.getName(fileName));
/*  89 */       ajax.put("originalFilename", file.getOriginalFilename());
/*  90 */       return ajax;
/*  91 */     } catch (Exception e) {
/*  92 */       return AjaxResult.error(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   @GetMapping({"/file/{fileId}"})
/*     */   public ResponseEntity<byte[]> getFile(@PathVariable("fileId") String fileId) {
/*  98 */     SysImgFile file = this.sysFileService.selectSysFileById(Long.valueOf(Long.parseLong(fileId)));
/*  99 */     HttpHeaders headers = new HttpHeaders();
/*     */     try {
/* 101 */       headers.setContentType(MediaType.IMAGE_PNG);
/* 102 */       headers.setContentDispositionFormData("attachment", new String("图标".getBytes("GBK"), "ISO8859-1"));
/* 103 */     } catch (Exception e) {
/* 104 */       e.printStackTrace();
/*     */     } 
/* 106 */     return new ResponseEntity(file.getData(), (MultiValueMap)headers, HttpStatus.OK);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PostMapping({"/uploads"})
/*     */   public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception {
/*     */     try {
/* 116 */       String filePath = RuoYiConfig.getUploadPath();
/* 117 */       List<String> urls = new ArrayList<>();
/* 118 */       List<String> fileNames = new ArrayList<>();
/* 119 */       List<String> newFileNames = new ArrayList<>();
/* 120 */       List<String> originalFilenames = new ArrayList<>();
/* 121 */       for (MultipartFile file : files) {
/*     */         
/* 123 */         String fileName = FileUploadUtils.upload(filePath, file);
/* 124 */         String url = this.serverConfig.getUrl() + fileName;
/* 125 */         urls.add(url);
/* 126 */         fileNames.add(fileName);
/* 127 */         newFileNames.add(FileUtils.getName(fileName));
/* 128 */         originalFilenames.add(file.getOriginalFilename());
/*     */       } 
/* 130 */       AjaxResult ajax = AjaxResult.success();
/* 131 */       ajax.put("urls", StringUtils.join(urls, ","));
/* 132 */       ajax.put("fileNames", StringUtils.join(fileNames, ","));
/* 133 */       ajax.put("newFileNames", StringUtils.join(newFileNames, ","));
/* 134 */       ajax.put("originalFilenames", StringUtils.join(originalFilenames, ","));
/* 135 */       return ajax;
/* 136 */     } catch (Exception e) {
/* 137 */       return AjaxResult.error(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/download/resource"})
/*     */   public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*     */     try {
/* 148 */       if (!FileUtils.checkAllowDownload(resource)) {
/* 149 */         throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", new Object[] { resource }));
/*     */       }
/*     */       
/* 152 */       String localPath = RuoYiConfig.getProfile();
/*     */       
/* 154 */       String downloadPath = localPath + StringUtils.substringAfter(resource, "/profile");
/*     */       
/* 156 */       String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
/* 157 */       response.setContentType("application/octet-stream");
/* 158 */       FileUtils.setAttachmentResponseHeader(response, downloadName);
/* 159 */       FileUtils.writeBytes(downloadPath, (OutputStream)response.getOutputStream());
/* 160 */     } catch (Exception e) {
/* 161 */       log.error("下载文件失败", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   @GetMapping({"/changeLanguages"})
/*     */   public AjaxResult changeLanguages(String lang) {
/* 167 */     return AjaxResult.success(MessageUtils.message("success", new Object[0]));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/common/CommonController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */