package com.ymit.module.system.controller.admin.sensitiveword.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 管理后台 - 敏感词创建/修改 Request VO
 */
@Data
public class SensitiveWordSaveVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 敏感词
     */
    @NotNull(message = "敏感词不能为空")
    private String name;
    /**
     * 标签
     */
    @NotNull(message = "标签不能为空")
    private List<String> tags;
    /**
     * 状态，参见 CommonStatusEnum 枚举类
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
    /**
     * 描述
     */
    private String description;

}
