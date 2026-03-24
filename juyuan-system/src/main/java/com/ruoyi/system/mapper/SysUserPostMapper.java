package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysUserPost;
import java.util.List;

public interface SysUserPostMapper {
  int deleteUserPostByUserId(Long paramLong);
  
  int countUserPostById(Long paramLong);
  
  int deleteUserPost(Long[] paramArrayOfLong);
  
  int batchUserPost(List<SysUserPost> paramList);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/mapper/SysUserPostMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */