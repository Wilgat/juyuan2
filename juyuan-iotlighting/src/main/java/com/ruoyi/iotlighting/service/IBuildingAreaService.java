package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.BuildingArea;
import com.ruoyi.iotlighting.domain.BuildingAreaStaff;
import java.util.List;

public interface IBuildingAreaService {
  List<BuildingArea> selectBuildingAreaList(BuildingArea paramBuildingArea);
  
  List<BuildingAreaStaff> selectBuildingAreaStaffList(BuildingAreaStaff paramBuildingAreaStaff);
  
  List<BuildingAreaStaff> selectBuildingAreaStaffConfigList(BuildingAreaStaff paramBuildingAreaStaff);
  
  int updateAreaStaffConfig(Long paramLong, List<BuildingAreaStaff> paramList);
  
  List<BuildingArea> selectBuildingAreaListAll(BuildingArea paramBuildingArea);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IBuildingAreaService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */