package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysPost;
import java.util.List;

public interface SysPostMapper {
  List<SysPost> selectPostList(SysPost paramSysPost);
  
  List<SysPost> selectPostAll();
  
  SysPost selectPostById(Long paramLong);
  
  List<Long> selectPostListByUserId(Long paramLong);
  
  List<SysPost> selectPostsByUserName(String paramString);
  
  int deletePostById(Long paramLong);
  
  int deletePostByIds(Long[] paramArrayOfLong);
  
  int updatePost(SysPost paramSysPost);
  
  int insertPost(SysPost paramSysPost);
  
  SysPost checkPostNameUnique(String paramString);
  
  SysPost checkPostCodeUnique(String paramString);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/mapper/SysPostMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */