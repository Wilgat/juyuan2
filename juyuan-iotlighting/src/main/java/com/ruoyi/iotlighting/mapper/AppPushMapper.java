package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.TerAlert;
import java.util.List;

public interface AppPushMapper {
  List<String> selectAppPushCidList(TerAlert paramTerAlert);
  
  List<String> selectAdminPushCidList();
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/AppPushMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */