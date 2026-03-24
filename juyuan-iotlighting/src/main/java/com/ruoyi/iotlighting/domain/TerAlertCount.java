package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Arrays;

public class TerAlertCount extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sensorType;
    private int count;
    private String[] alertSensorType;

    // ==================== Getters ====================

    public String getSensorType() {
        return sensorType;
    }

    public int getCount() {
        return count;
    }

    public String[] getAlertSensorType() {
        return alertSensorType;
    }

    // ==================== Setters ====================

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setAlertSensorType(String[] alertSensorType) {
        this.alertSensorType = alertSensorType;
    }

    // ==================== equals / hashCode / toString ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerAlertCount that = (TerAlertCount) o;

        if (count != that.count) return false;
        if (sensorType != null ? !sensorType.equals(that.sensorType) : that.sensorType != null) return false;
        return Arrays.deepEquals(alertSensorType, that.alertSensorType);
    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + (sensorType != null ? sensorType.hashCode() : 0);
        result = 31 * result + Arrays.deepHashCode(alertSensorType);
        return result;
    }

    @Override
    public String toString() {
        return "TerAlertCount{" +
                "sensorType='" + sensorType + '\'' +
                ", count=" + count +
                ", alertSensorType=" + Arrays.toString(alertSensorType) +
                '}';
    }
}