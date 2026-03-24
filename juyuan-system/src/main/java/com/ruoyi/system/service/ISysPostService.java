package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysPost;
import java.util.List;

public interface ISysPostService {
  List<SysPost> selectPostList(SysPost paramSysPost);
  
  List<SysPost> selectPostAll();
  
  SysPost selectPostById(Long paramLong);
  
  List<Long> selectPostListByUserId(Long paramLong);
  
  boolean checkPostNameUnique(SysPost paramSysPost);
  
  boolean checkPostCodeUnique(SysPost paramSysPost);
  
  int countUserPostById(Long paramLong);
  
  int deletePostById(Long paramLong);
  
  int deletePostByIds(Long[] paramArrayOfLong);
  
  int insertPost(SysPost paramSysPost);
  
  int updatePost(SysPost paramSysPost);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysPostService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */