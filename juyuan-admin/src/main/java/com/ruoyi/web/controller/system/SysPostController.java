/*     */ package com.ruoyi.web.controller.system;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.system.domain.SysPost;
/*     */ import com.ruoyi.system.service.ISysPostService;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.security.access.prepost.PreAuthorize;
/*     */ import org.springframework.validation.annotation.Validated;
/*     */ import org.springframework.web.bind.annotation.DeleteMapping;
/*     */ import org.springframework.web.bind.annotation.GetMapping;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.PostMapping;
/*     */ import org.springframework.web.bind.annotation.PutMapping;
/*     */ import org.springframework.web.bind.annotation.RequestBody;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RestController;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @RestController
/*     */ @RequestMapping({"/system/post"})
/*     */ public class SysPostController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ISysPostService postService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:post:list')")
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(SysPost post) {
/*  44 */     startPage();
/*  45 */     List<SysPost> list = this.postService.selectPostList(post);
/*  46 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */   
/*     */   @Log(title = "岗位管理", businessType = BusinessType.EXPORT)
/*     */   @PreAuthorize("@ss.hasPermi('system:post:export')")
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, SysPost post) {
/*  54 */     List<SysPost> list = this.postService.selectPostList(post);
/*  55 */     ExcelUtil<SysPost> util = new ExcelUtil(SysPost.class);
/*  56 */     util.exportExcel(response, list, "岗位数据");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:post:query')")
/*     */   @GetMapping({"/{postId}"})
/*     */   public AjaxResult getInfo(@PathVariable Long postId) {
/*  66 */     return success(this.postService.selectPostById(postId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:post:add')")
/*     */   @Log(title = "岗位管理", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@Validated @RequestBody SysPost post) {
/*  77 */     if (!this.postService.checkPostNameUnique(post))
/*     */     {
/*  79 */       return error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
/*     */     }
/*  81 */     if (!this.postService.checkPostCodeUnique(post))
/*     */     {
/*  83 */       return error("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
/*     */     }
/*  85 */     post.setCreateBy(getUsername());
/*  86 */     return toAjax(this.postService.insertPost(post));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:post:edit')")
/*     */   @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@Validated @RequestBody SysPost post) {
/*  97 */     if (!this.postService.checkPostNameUnique(post))
/*     */     {
/*  99 */       return error("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
/*     */     }
/* 101 */     if (!this.postService.checkPostCodeUnique(post))
/*     */     {
/* 103 */       return error("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
/*     */     }
/* 105 */     post.setUpdateBy(getUsername());
/* 106 */     return toAjax(this.postService.updatePost(post));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:post:remove')")
/*     */   @Log(title = "岗位管理", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{postIds}"})
/*     */   public AjaxResult remove(@PathVariable Long[] postIds) {
/* 117 */     return toAjax(this.postService.deletePostByIds(postIds));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/optionselect"})
/*     */   public AjaxResult optionselect() {
/* 126 */     List<SysPost> posts = this.postService.selectPostAll();
/* 127 */     return success(posts);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysPostController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */