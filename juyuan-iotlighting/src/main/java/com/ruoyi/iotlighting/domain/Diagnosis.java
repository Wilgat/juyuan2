package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

// NEW: missing imports for Jackson annotations
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class Diagnosis extends BaseEntity {
  private String upc;
  private String buildingName;
  private String block;
  private String floorName;
  private String zone;
  private String zoneSub;
  private String pointName;
  private String sensorType;
  private String terSn;
  private String dataStatus;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long buildingId;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long pointId;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long floorId;

  private String gateway;
  private String checkSensorType;
  private String hopCount;
  private String gatewayOnlineStatus;
  private String terOnlineStatus;
  private String terRssi;
  private String wifiSsid;
  private String gatewayRssi;
  private String channel;

  private Date gatewayDiagnosisUpdateTime;
  private Date terDiagnosisUpdateTime;
  private Date sensorDiagnosisUpdateTime;

  private String wifiType;

  // All setters (preserved exactly as in the decompiled file)
  public void setUpc(String upc) { this.upc = upc; }
  public void setBuildingName(String buildingName) { this.buildingName = buildingName; }
  public void setBlock(String block) { this.block = block; }
  public void setFloorName(String floorName) { this.floorName = floorName; }
  public void setZone(String zone) { this.zone = zone; }
  public void setZoneSub(String zoneSub) { this.zoneSub = zoneSub; }
  public void setPointName(String pointName) { this.pointName = pointName; }
  public void setSensorType(String sensorType) { this.sensorType = sensorType; }
  public void setTerSn(String terSn) { this.terSn = terSn; }
  public void setDataStatus(String dataStatus) { this.dataStatus = dataStatus; }
  public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
  public void setPointId(Long pointId) { this.pointId = pointId; }
  public void setFloorId(Long floorId) { this.floorId = floorId; }
  public void setGateway(String gateway) { this.gateway = gateway; }
  public void setCheckSensorType(String checkSensorType) { this.checkSensorType = checkSensorType; }
  public void setHopCount(String hopCount) { this.hopCount = hopCount; }
  public void setGatewayOnlineStatus(String gatewayOnlineStatus) { this.gatewayOnlineStatus = gatewayOnlineStatus; }
  public void setTerOnlineStatus(String terOnlineStatus) { this.terOnlineStatus = terOnlineStatus; }
  public void setTerRssi(String terRssi) { this.terRssi = terRssi; }
  public void setWifiSsid(String wifiSsid) { this.wifiSsid = wifiSsid; }
  public void setGatewayRssi(String gatewayRssi) { this.gatewayRssi = gatewayRssi; }
  public void setChannel(String channel) { this.channel = channel; }
  public void setGatewayDiagnosisUpdateTime(Date gatewayDiagnosisUpdateTime) { this.gatewayDiagnosisUpdateTime = gatewayDiagnosisUpdateTime; }
  public void setTerDiagnosisUpdateTime(Date terDiagnosisUpdateTime) { this.terDiagnosisUpdateTime = terDiagnosisUpdateTime; }
  public void setSensorDiagnosisUpdateTime(Date sensorDiagnosisUpdateTime) { this.sensorDiagnosisUpdateTime = sensorDiagnosisUpdateTime; }
  public void setWifiType(String wifiType) { this.wifiType = wifiType; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof Diagnosis)) return false;  
    Diagnosis other = (Diagnosis)o; 
    if (!other.canEqual(this)) return false;  

    Object this$buildingId = getBuildingId(), other$buildingId = other.getBuildingId(); 
    if ((this$buildingId == null) ? (other$buildingId != null) : !this$buildingId.equals(other$buildingId)) return false;  

    Object this$pointId = getPointId(), other$pointId = other.getPointId(); 
    if ((this$pointId == null) ? (other$pointId != null) : !this$pointId.equals(other$pointId)) return false;  

    Object this$floorId = getFloorId(), other$floorId = other.getFloorId(); 
    if ((this$floorId == null) ? (other$floorId != null) : !this$floorId.equals(other$floorId)) return false;  

    Object this$upc = getUpc(), other$upc = other.getUpc(); 
    if ((this$upc == null) ? (other$upc != null) : !this$upc.equals(other$upc)) return false;  

    Object this$buildingName = getBuildingName(), other$buildingName = other.getBuildingName(); 
    if ((this$buildingName == null) ? (other$buildingName != null) : !this$buildingName.equals(other$buildingName)) return false;  

    Object this$block = getBlock(), other$block = other.getBlock(); 
    if ((this$block == null) ? (other$block != null) : !this$block.equals(other$block)) return false;  

    Object this$floorName = getFloorName(), other$floorName = other.getFloorName(); 
    if ((this$floorName == null) ? (other$floorName != null) : !this$floorName.equals(other$floorName)) return false;  

    Object this$zone = getZone(), other$zone = other.getZone(); 
    if ((this$zone == null) ? (other$zone != null) : !this$zone.equals(other$zone)) return false;  

    Object this$zoneSub = getZoneSub(), other$zoneSub = other.getZoneSub(); 
    if ((this$zoneSub == null) ? (other$zoneSub != null) : !this$zoneSub.equals(other$zoneSub)) return false;  

    Object this$pointName = getPointName(), other$pointName = other.getPointName(); 
    if ((this$pointName == null) ? (other$pointName != null) : !this$pointName.equals(other$pointName)) return false;  

    Object this$sensorType = getSensorType(), other$sensorType = other.getSensorType(); 
    if ((this$sensorType == null) ? (other$sensorType != null) : !this$sensorType.equals(other$sensorType)) return false;  

    Object this$terSn = getTerSn(), other$terSn = other.getTerSn(); 
    if ((this$terSn == null) ? (other$terSn != null) : !this$terSn.equals(other$terSn)) return false;  

    Object this$dataStatus = getDataStatus(), other$dataStatus = other.getDataStatus(); 
    if ((this$dataStatus == null) ? (other$dataStatus != null) : !this$dataStatus.equals(other$dataStatus)) return false;  

    Object this$gateway = getGateway(), other$gateway = other.getGateway(); 
    if ((this$gateway == null) ? (other$gateway != null) : !this$gateway.equals(other$gateway)) return false;  

    Object this$checkSensorType = getCheckSensorType(), other$checkSensorType = other.getCheckSensorType(); 
    if ((this$checkSensorType == null) ? (other$checkSensorType != null) : !this$checkSensorType.equals(other$checkSensorType)) return false;  

    Object this$hopCount = getHopCount(), other$hopCount = other.getHopCount(); 
    if ((this$hopCount == null) ? (other$hopCount != null) : !this$hopCount.equals(other$hopCount)) return false;  

    Object this$gatewayOnlineStatus = getGatewayOnlineStatus(), other$gatewayOnlineStatus = other.getGatewayOnlineStatus(); 
    if ((this$gatewayOnlineStatus == null) ? (other$gatewayOnlineStatus != null) : !this$gatewayOnlineStatus.equals(other$gatewayOnlineStatus)) return false;  

    Object this$terOnlineStatus = getTerOnlineStatus(), other$terOnlineStatus = other.getTerOnlineStatus(); 
    if ((this$terOnlineStatus == null) ? (other$terOnlineStatus != null) : !this$terOnlineStatus.equals(other$terOnlineStatus)) return false;  

    Object this$terRssi = getTerRssi(), other$terRssi = other.getTerRssi(); 
    if ((this$terRssi == null) ? (other$terRssi != null) : !this$terRssi.equals(other$terRssi)) return false;  

    Object this$wifiSsid = getWifiSsid(), other$wifiSsid = other.getWifiSsid(); 
    if ((this$wifiSsid == null) ? (other$wifiSsid != null) : !this$wifiSsid.equals(other$wifiSsid)) return false;  

    Object this$gatewayRssi = getGatewayRssi(), other$gatewayRssi = other.getGatewayRssi(); 
    if ((this$gatewayRssi == null) ? (other$gatewayRssi != null) : !this$gatewayRssi.equals(other$gatewayRssi)) return false;  

    Object this$channel = getChannel(), other$channel = other.getChannel(); 
    if ((this$channel == null) ? (other$channel != null) : !this$channel.equals(other$channel)) return false;  

    Object this$gatewayDiagnosisUpdateTime = getGatewayDiagnosisUpdateTime(), other$gatewayDiagnosisUpdateTime = other.getGatewayDiagnosisUpdateTime(); 
    if ((this$gatewayDiagnosisUpdateTime == null) ? (other$gatewayDiagnosisUpdateTime != null) : !this$gatewayDiagnosisUpdateTime.equals(other$gatewayDiagnosisUpdateTime)) return false;  

    Object this$terDiagnosisUpdateTime = getTerDiagnosisUpdateTime(), other$terDiagnosisUpdateTime = other.getTerDiagnosisUpdateTime(); 
    if ((this$terDiagnosisUpdateTime == null) ? (other$terDiagnosisUpdateTime != null) : !this$terDiagnosisUpdateTime.equals(other$terDiagnosisUpdateTime)) return false;  

    Object this$sensorDiagnosisUpdateTime = getSensorDiagnosisUpdateTime(), other$sensorDiagnosisUpdateTime = other.getSensorDiagnosisUpdateTime(); 
    if ((this$sensorDiagnosisUpdateTime == null) ? (other$sensorDiagnosisUpdateTime != null) : !this$sensorDiagnosisUpdateTime.equals(other$sensorDiagnosisUpdateTime)) return false;  

    Object this$wifiType = getWifiType(), other$wifiType = other.getWifiType(); 
    return !((this$wifiType == null) ? (other$wifiType != null) : !this$wifiType.equals(other$wifiType)); 
  } 

  protected boolean canEqual(Object other) { 
    return other instanceof Diagnosis; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $buildingId = getBuildingId(); 
    result = result * 59 + (($buildingId == null) ? 43 : $buildingId.hashCode()); 
    Object $pointId = getPointId(); 
    result = result * 59 + (($pointId == null) ? 43 : $pointId.hashCode()); 
    Object $floorId = getFloorId(); 
    result = result * 59 + (($floorId == null) ? 43 : $floorId.hashCode()); 
    Object $upc = getUpc(); 
    result = result * 59 + (($upc == null) ? 43 : $upc.hashCode()); 
    Object $buildingName = getBuildingName(); 
    result = result * 59 + (($buildingName == null) ? 43 : $buildingName.hashCode()); 
    Object $block = getBlock(); 
    result = result * 59 + (($block == null) ? 43 : $block.hashCode()); 
    Object $floorName = getFloorName(); 
    result = result * 59 + (($floorName == null) ? 43 : $floorName.hashCode()); 
    Object $zone = getZone(); 
    result = result * 59 + (($zone == null) ? 43 : $zone.hashCode()); 
    Object $zoneSub = getZoneSub(); 
    result = result * 59 + (($zoneSub == null) ? 43 : $zoneSub.hashCode()); 
    Object $pointName = getPointName(); 
    result = result * 59 + (($pointName == null) ? 43 : $pointName.hashCode()); 
    Object $sensorType = getSensorType(); 
    result = result * 59 + (($sensorType == null) ? 43 : $sensorType.hashCode()); 
    Object $terSn = getTerSn(); 
    result = result * 59 + (($terSn == null) ? 43 : $terSn.hashCode()); 
    Object $dataStatus = getDataStatus(); 
    result = result * 59 + (($dataStatus == null) ? 43 : $dataStatus.hashCode()); 
    Object $gateway = getGateway(); 
    result = result * 59 + (($gateway == null) ? 43 : $gateway.hashCode()); 
    Object $checkSensorType = getCheckSensorType(); 
    result = result * 59 + (($checkSensorType == null) ? 43 : $checkSensorType.hashCode()); 
    Object $hopCount = getHopCount(); 
    result = result * 59 + (($hopCount == null) ? 43 : $hopCount.hashCode()); 
    Object $gatewayOnlineStatus = getGatewayOnlineStatus(); 
    result = result * 59 + (($gatewayOnlineStatus == null) ? 43 : $gatewayOnlineStatus.hashCode()); 
    Object $terOnlineStatus = getTerOnlineStatus(); 
    result = result * 59 + (($terOnlineStatus == null) ? 43 : $terOnlineStatus.hashCode()); 
    Object $terRssi = getTerRssi(); 
    result = result * 59 + (($terRssi == null) ? 43 : $terRssi.hashCode()); 
    Object $wifiSsid = getWifiSsid(); 
    result = result * 59 + (($wifiSsid == null) ? 43 : $wifiSsid.hashCode()); 
    Object $gatewayRssi = getGatewayRssi(); 
    result = result * 59 + (($gatewayRssi == null) ? 43 : $gatewayRssi.hashCode()); 
    Object $channel = getChannel(); 
    result = result * 59 + (($channel == null) ? 43 : $channel.hashCode()); 
    Object $gatewayDiagnosisUpdateTime = getGatewayDiagnosisUpdateTime(); 
    result = result * 59 + (($gatewayDiagnosisUpdateTime == null) ? 43 : $gatewayDiagnosisUpdateTime.hashCode()); 
    Object $terDiagnosisUpdateTime = getTerDiagnosisUpdateTime(); 
    result = result * 59 + (($terDiagnosisUpdateTime == null) ? 43 : $terDiagnosisUpdateTime.hashCode()); 
    Object $sensorDiagnosisUpdateTime = getSensorDiagnosisUpdateTime(); 
    result = result * 59 + (($sensorDiagnosisUpdateTime == null) ? 43 : $sensorDiagnosisUpdateTime.hashCode()); 
    Object $wifiType = getWifiType(); 
    return result * 59 + (($wifiType == null) ? 43 : $wifiType.hashCode()); 
  } 

  public String toString() { 
    return "Diagnosis(upc=" + getUpc() + ", buildingName=" + getBuildingName() + ", block=" + getBlock() + ", floorName=" + getFloorName() + ", zone=" + getZone() + ", zoneSub=" + getZoneSub() + ", pointName=" + getPointName() + ", sensorType=" + getSensorType() + ", terSn=" + getTerSn() + ", dataStatus=" + getDataStatus() + ", buildingId=" + getBuildingId() + ", pointId=" + getPointId() + ", floorId=" + getFloorId() + ", gateway=" + getGateway() + ", checkSensorType=" + getCheckSensorType() + ", hopCount=" + getHopCount() + ", gatewayOnlineStatus=" + getGatewayOnlineStatus() + ", terOnlineStatus=" + getTerOnlineStatus() + ", terRssi=" + getTerRssi() + ", wifiSsid=" + getWifiSsid() + ", gatewayRssi=" + getGatewayRssi() + ", channel=" + getChannel() + ", gatewayDiagnosisUpdateTime=" + getGatewayDiagnosisUpdateTime() + ", terDiagnosisUpdateTime=" + getTerDiagnosisUpdateTime() + ", sensorDiagnosisUpdateTime=" + getSensorDiagnosisUpdateTime() + ", wifiType=" + getWifiType() + ")"; 
  }

  // All getters (preserved exactly as in the decompiled file)
  public String getUpc() {
    return this.upc;
  }

  public String getBuildingName() {
    return this.buildingName;
  }

  public String getBlock() {
    return this.block;
  }

  public String getFloorName() {
    return this.floorName;
  }

  public String getZone() {
    return this.zone;
  }

  public String getZoneSub() {
    return this.zoneSub;
  }

  public String getPointName() {
    return this.pointName;
  }

  public String getSensorType() {
    return this.sensorType;
  }

  public String getTerSn() {
    return this.terSn;
  }

  public String getDataStatus() {
    return this.dataStatus;
  }

  public Long getBuildingId() {
    return this.buildingId;
  }

  public Long getPointId() {
    return this.pointId;
  }

  public Long getFloorId() {
    return this.floorId;
  }

  public String getGateway() {
    return this.gateway;
  }

  public String getCheckSensorType() {
    return this.checkSensorType;
  }

  public String getHopCount() {
    return this.hopCount;
  }

  public String getGatewayOnlineStatus() {
    return this.gatewayOnlineStatus;
  }

  public String getTerOnlineStatus() {
    return this.terOnlineStatus;
  }

  public String getTerRssi() {
    return this.terRssi;
  }

  public String getWifiSsid() {
    return this.wifiSsid;
  }

  public String getGatewayRssi() {
    return this.gatewayRssi;
  }

  public String getChannel() {
    return this.channel;
  }

  public Date getGatewayDiagnosisUpdateTime() {
    return this.gatewayDiagnosisUpdateTime;
  }

  public Date getTerDiagnosisUpdateTime() {
    return this.terDiagnosisUpdateTime;
  }

  public Date getSensorDiagnosisUpdateTime() {
    return this.sensorDiagnosisUpdateTime;
  }

  public String getWifiType() {
    return this.wifiType;
  }
}