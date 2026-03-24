package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.TerAlert;
import com.ruoyi.iotlighting.domain.TerAlertSystem;

public interface IAppPushService {
  void pushMessage(TerAlert paramTerAlert);
  
  void pushSystemAlertMessage(TerAlertSystem paramTerAlertSystem);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IAppPushService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */