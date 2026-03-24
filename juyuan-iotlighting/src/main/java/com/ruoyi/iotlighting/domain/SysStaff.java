package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.List;

public class SysStaff extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "用户表id")
    private Long staffId;

    @Excel(name = "用户表id")
    private Long userId;

    @Excel(name = "员工编号")
    private String staffNumber;

    @Excel(name = "员工职位：lifeguard救生员，patrol 巡检人员")
    private String staffPosition;

    @Excel(name = "工牌号码")
    private String licenseNumber;

    @Excel(name = "用户密码")
    private String password;

    private String userName;
    private String nickName;

    private List<Long> roleKeyList;

    // ==================== Getters ====================

    public Long getStaffId() {
        return staffId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public String getStaffPosition() {
        return staffPosition;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public List<Long> getRoleKeyList() {
        return roleKeyList;
    }

    // ==================== Setters ====================

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public void setStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setRoleKeyList(List<Long> roleKeyList) {
        this.roleKeyList = roleKeyList;
    }

    // ==================== equals, hashCode, toString (optional but recommended) ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysStaff sysStaff = (SysStaff) o;

        if (staffId != null ? !staffId.equals(sysStaff.staffId) : sysStaff.staffId != null) return false;
        if (userId != null ? !userId.equals(sysStaff.userId) : sysStaff.userId != null) return false;
        if (staffNumber != null ? !staffNumber.equals(sysStaff.staffNumber) : sysStaff.staffNumber != null) return false;
        if (staffPosition != null ? !staffPosition.equals(sysStaff.staffPosition) : sysStaff.staffPosition != null) return false;
        if (licenseNumber != null ? !licenseNumber.equals(sysStaff.licenseNumber) : sysStaff.licenseNumber != null) return false;
        if (password != null ? !password.equals(sysStaff.password) : sysStaff.password != null) return false;
        if (userName != null ? !userName.equals(sysStaff.userName) : sysStaff.userName != null) return false;
        if (nickName != null ? !nickName.equals(sysStaff.nickName) : sysStaff.nickName != null) return false;
        return roleKeyList != null ? roleKeyList.equals(sysStaff.roleKeyList) : sysStaff.roleKeyList == null;
    }

    @Override
    public int hashCode() {
        int result = staffId != null ? staffId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (staffNumber != null ? staffNumber.hashCode() : 0);
        result = 31 * result + (staffPosition != null ? staffPosition.hashCode() : 0);
        result = 31 * result + (licenseNumber != null ? licenseNumber.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (roleKeyList != null ? roleKeyList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysStaff{" +
                "staffId=" + staffId +
                ", userId=" + userId +
                ", staffNumber='" + staffNumber + '\'' +
                ", staffPosition='" + staffPosition + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", roleKeyList=" + roleKeyList +
                '}';
    }
}