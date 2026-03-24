package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.BuildingArea;
import com.ruoyi.iotlighting.domain.BuildingAreaStaff;
import java.util.List;

public interface BuildingAreaMapper {
  List<BuildingArea> selectBuildingAreaList(BuildingArea paramBuildingArea);
  
  int insertBuildingArea(BuildingArea paramBuildingArea);
  
  int updateBuildingArea(BuildingArea paramBuildingArea);
  
  int deleteBuildingAreaById(Long paramLong);
  
  List<BuildingAreaStaff> selectBuildingAreaStaffList(BuildingAreaStaff paramBuildingAreaStaff);
  
  List<BuildingAreaStaff> selectBuildingAreaStaffConfigList(BuildingAreaStaff paramBuildingAreaStaff);
  
  int deletBuildingAreaStaffConfigByAreaId(Long paramLong);
  
  int insertBuildingAreaStaffConfigBatch(List<BuildingAreaStaff> paramList);
  
  List<BuildingArea> selectBuildingAreaListAll(BuildingArea paramBuildingArea);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/BuildingAreaMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */