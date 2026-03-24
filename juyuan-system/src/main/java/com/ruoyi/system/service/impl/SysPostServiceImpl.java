/*     */ package com.ruoyi.system.service.impl;
/*     */ 
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.system.domain.SysPost;
/*     */ import com.ruoyi.system.mapper.SysPostMapper;
/*     */ import com.ruoyi.system.mapper.SysUserPostMapper;
/*     */ import com.ruoyi.system.service.ISysPostService;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
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
/*     */ @Service
/*     */ public class SysPostServiceImpl
/*     */   implements ISysPostService
/*     */ {
/*     */   @Autowired
/*     */   private SysPostMapper postMapper;
/*     */   @Autowired
/*     */   private SysUserPostMapper userPostMapper;
/*     */   
/*     */   public List<SysPost> selectPostList(SysPost post) {
/*  37 */     return this.postMapper.selectPostList(post);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<SysPost> selectPostAll() {
/*  48 */     return this.postMapper.selectPostAll();
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
/*     */   public SysPost selectPostById(Long postId) {
/*  60 */     return this.postMapper.selectPostById(postId);
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
/*     */   public List<Long> selectPostListByUserId(Long userId) {
/*  72 */     return this.postMapper.selectPostListByUserId(userId);
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
/*     */   public boolean checkPostNameUnique(SysPost post) {
/*  84 */     Long postId = Long.valueOf(StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId().longValue());
/*  85 */     SysPost info = this.postMapper.checkPostNameUnique(post.getPostName());
/*  86 */     if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
/*     */     {
/*  88 */       return false;
/*     */     }
/*  90 */     return true;
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
/*     */   public boolean checkPostCodeUnique(SysPost post) {
/* 102 */     Long postId = Long.valueOf(StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId().longValue());
/* 103 */     SysPost info = this.postMapper.checkPostCodeUnique(post.getPostCode());
/* 104 */     if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
/*     */     {
/* 106 */       return false;
/*     */     }
/* 108 */     return true;
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
/*     */   public int countUserPostById(Long postId) {
/* 120 */     return this.userPostMapper.countUserPostById(postId);
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
/*     */   public int deletePostById(Long postId) {
/* 132 */     return this.postMapper.deletePostById(postId);
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
/*     */   public int deletePostByIds(Long[] postIds) {
/* 144 */     for (Long postId : postIds) {
/*     */       
/* 146 */       SysPost post = selectPostById(postId);
/* 147 */       if (countUserPostById(postId) > 0)
/*     */       {
/* 149 */         throw new ServiceException(String.format("%1$s已分配,不能删除", new Object[] { post.getPostName() }));
/*     */       }
/*     */     } 
/* 152 */     return this.postMapper.deletePostByIds(postIds);
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
/*     */   public int insertPost(SysPost post) {
/* 164 */     return this.postMapper.insertPost(post);
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
/*     */   public int updatePost(SysPost post) {
/* 176 */     return this.postMapper.updatePost(post);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysPostServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */