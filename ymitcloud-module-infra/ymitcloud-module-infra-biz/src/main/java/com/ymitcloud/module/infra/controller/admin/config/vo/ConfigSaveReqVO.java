package com.ymitcloud.module.infra.controller.admin.config.vo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * 管理后台 - 参数配置创建/修改 Request VO
 */
@Data
public class ConfigSaveReqVO {

    /**
     * 参数配置序号
     */
    private Long id;

    /**
     * 参数分组
     */
    @NotEmpty(message = "参数分组不能为空")
    @Size(max = 50, message = "参数名称不能超过 50 个字符")
    private String category;

    /**
     * 参数名称
     */
    @NotBlank(message = "参数名称不能为空")
    @Size(max = 100, message = "参数名称不能超过 100 个字符")
    private String name;

    /**
     * 参数键名
     */
    @NotBlank(message = "参数键名长度不能为空")
    @Size(max = 100, message = "参数键名长度不能超过 100 个字符")
    private String key;
    /**
     * 参数键值
     */
    @NotBlank(message = "参数键值不能为空")
    @Size(max = 500, message = "参数键值长度不能超过 500 个字符")
    private String value;
    /**
     * 是否可见
     */
    @NotNull(message = "是否可见不能为空")
    private Boolean visible;
    /**
     * 备注
     */
    private String remark;

}
