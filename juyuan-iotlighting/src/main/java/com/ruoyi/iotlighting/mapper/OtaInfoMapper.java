package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.OtaInfo;
import java.util.List;

public interface OtaInfoMapper {
  OtaInfo selectOtaInfoById(Long paramLong);
  
  List<OtaInfo> selectOtaInfoList(OtaInfo paramOtaInfo);
  
  int insertOtaInfo(OtaInfo paramOtaInfo);
  
  int updateOtaInfo(OtaInfo paramOtaInfo);
  
  int deleteOtaInfoById(Long paramLong);
  
  int deleteOtaInfoByIds(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/OtaInfoMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */