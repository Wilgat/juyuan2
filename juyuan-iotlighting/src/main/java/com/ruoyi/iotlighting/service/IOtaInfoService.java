package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.OtaInfo;
import java.util.List;

public interface IOtaInfoService {
  OtaInfo selectOtaInfoById(Long paramLong);
  
  List<OtaInfo> selectOtaInfoList(OtaInfo paramOtaInfo);
  
  int insertOtaInfo(OtaInfo paramOtaInfo);
  
  int updateOtaInfo(OtaInfo paramOtaInfo);
  
  int deleteOtaInfoByIds(Long[] paramArrayOfLong);
  
  int deleteOtaInfoById(Long paramLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IOtaInfoService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */