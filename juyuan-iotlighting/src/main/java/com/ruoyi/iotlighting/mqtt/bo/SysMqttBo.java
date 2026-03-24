package com.ruoyi.iotlighting.mqtt.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)   // important: includes fields from BaseEntity
public class SysMqttBo extends BaseEntity {

    private String topic;
    private String data;
    private Integer qos;
    private boolean retained = false;     // default value

    // No need for manual getters/setters, equals, hashCode, toString
}