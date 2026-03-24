package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.TerGateway;
import java.util.List;

public interface TerGatewayMapper {
  TerGateway selectTerGatewayById(Long paramLong);
  
  List<TerGateway> selectTerGatewayList(TerGateway paramTerGateway);
  
  int insertTerGateway(TerGateway paramTerGateway);
  
  int updateTerGateway(TerGateway paramTerGateway);
  
  int deleteTerGatewayById(Long paramLong);
  
  int deleteTerGatewayByIds(Long[] paramArrayOfLong);
  
  TerGateway selectTerGatewayBySn(String paramString);
  
  int selectTerByGatewayId(Long paramLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/TerGatewayMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */