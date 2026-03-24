package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysImgFile;
import org.springframework.web.multipart.MultipartFile;

public interface ISysFileService {
  SysImgFile uploadFileToDb(MultipartFile paramMultipartFile);
  
  SysImgFile selectSysFileById(Long paramLong);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysFileService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */