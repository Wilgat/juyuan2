package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.TerGateway;
import java.util.List;

public interface ITerGatewayService {
  TerGateway selectTerGatewayById(Long paramLong);
  
  List<TerGateway> selectTerGatewayList(TerGateway paramTerGateway);
  
  int insertTerGateway(TerGateway paramTerGateway);
  
  int updateTerGateway(TerGateway paramTerGateway);
  
  int deleteTerGatewayByIds(Long[] paramArrayOfLong);
  
  int deleteTerGatewayById(Long paramLong);
  
  void checkTerGateway(TerGateway paramTerGateway);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/ITerGatewayService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */