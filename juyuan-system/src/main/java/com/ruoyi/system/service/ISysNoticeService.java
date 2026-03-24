package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysNotice;
import java.util.List;

public interface ISysNoticeService {
  SysNotice selectNoticeById(Long paramLong);
  
  List<SysNotice> selectNoticeList(SysNotice paramSysNotice);
  
  int insertNotice(SysNotice paramSysNotice);
  
  int updateNotice(SysNotice paramSysNotice);
  
  int deleteNoticeById(Long paramLong);
  
  int deleteNoticeByIds(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysNoticeService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */