package com.ymitcloud.module.rule.controller.admin.request;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BasePageRequest implements Serializable {
    /**
     * 页码，从 1 开始
     */
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码不能小于1")
    private Long page;

    /**
     * 每页条数
     */
    @NotNull(message = "每页条数不能为空")
    @Max(value = 500, message = "每页条数不能超过500")
    @Min(value = 1, message = "每页条数不能小于1")
    private Long pageSize;
}
