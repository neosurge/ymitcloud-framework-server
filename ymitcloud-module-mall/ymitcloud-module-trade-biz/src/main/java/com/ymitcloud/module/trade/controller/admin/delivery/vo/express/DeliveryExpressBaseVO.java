package com.ymitcloud.module.trade.controller.admin.delivery.vo.express;




import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
* 快递公司 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class DeliveryExpressBaseVO {


    /** 快递公司编码*/
    @NotNull(message = "快递公司编码不能为空")
    private String code;

    /** 快递公司名称*/
    @NotNull(message = "快递公司名称不能为空")
    private String name;

    /** 快递公司logo")
    private String logo;

    /** 排序*/
    @NotNull(message = "排序不能为空")
    private Integer sort;

    /** 状态*/

    @NotNull(message = "状态不能为空")
    private Integer status;

}
