package com.ymitcloud.module.infra.controller.admin.demo.demo02.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 示例分类新增/修改 Request VO
 */
@Data
public class Demo02CategorySaveReqVO {

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
     * 父级编号
     */
    @NotNull(message = "父级编号不能为空")
    private Long parentId;

}