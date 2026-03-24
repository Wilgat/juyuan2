package com.ruoyi.system.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统图片/文件表 (custom entity - adjust comment as needed)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysImgFile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件内容（二进制）
     */
    private byte[] data;

    /**
     * 部门ID（租户/机构）
     */
    private Long deptId;
}