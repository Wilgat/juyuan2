package com.ruoyi.iotlighting.domain.protocol;

import lombok.Data;

@Data
public class ProtocolData {

    private String start = "<<";
    private int version;
    private int option;
    private String end = ">>";

    // No need for manual getters/setters/equals/hashCode/toString
    // Lombok @Data generates them all
}