package com.ymitcloud.module.infra.controller.admin.demo.demo01.vo;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 示例联系人新增/修改 Request VO
 */
@Data
public class Demo01ContactSaveReqVO {

    /**
     * 编号
     */
    private Long id;

    /**
     * 名字
     */
    @NotEmpty(message = "名字不能为空")
    private String name;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    private Integer sex;

    /**
     * 出生年
     */
    @NotNull(message = "出生年不能为空")
    private LocalDateTime birthday;

    /**
     * 简介
     */
    @NotEmpty(message = "简介不能为空")
    private String description;

    /**
     * 头像
     */
    private String avatar;

}