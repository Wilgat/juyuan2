package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.BuildingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BuildingInfoService {
  BuildingInfo queryById(Long paramLong);
  
  Page<BuildingInfo> paginQuery(BuildingInfo paramBuildingInfo, PageRequest paramPageRequest);
  
  BuildingInfo insert(BuildingInfo paramBuildingInfo);
  
  BuildingInfo update(BuildingInfo paramBuildingInfo);
  
  boolean deleteById(Long paramLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/BuildingInfoService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */