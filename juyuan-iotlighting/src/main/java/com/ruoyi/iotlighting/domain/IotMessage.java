package com.ruoyi.iotlighting.domain;

public class IotMessage {

    private String sn;
    private String data;
    private String type;
    private String gateway;

    // Getters
    public String getSn() {
        return sn;
    }

    public String getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public String getGateway() {
        return gateway;
    }

    // Setters
    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    // Optional: equals(), hashCode(), toString() for completeness
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IotMessage that = (IotMessage) o;

        if (sn != null ? !sn.equals(that.sn) : that.sn != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return gateway != null ? gateway.equals(that.gateway) : that.gateway == null;
    }

    @Override
    public int hashCode() {
        int result = sn != null ? sn.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (gateway != null ? gateway.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IotMessage{" +
                "sn='" + sn + '\'' +
                ", data='" + data + '\'' +
                ", type='" + type + '\'' +
                ", gateway='" + gateway + '\'' +
                '}';
    }
}