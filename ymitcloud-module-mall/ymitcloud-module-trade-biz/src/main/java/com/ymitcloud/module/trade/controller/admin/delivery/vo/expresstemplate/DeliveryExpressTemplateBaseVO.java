package com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate;




import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
* 快递运费模板 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class DeliveryExpressTemplateBaseVO {


    /** 模板名称*/
    @NotNull(message = "模板名称不能为空")
    private String name;

    /** 配送计费方式 1:按件 2:按重量 3:按体积*/
    @NotNull(message = "配送计费方式 1:按件 2:按重量 3:按体积不能为空")
    private Integer chargeMode;

    /** 排序*/

    @NotNull(message = "排序不能为空")
    private Integer sort;

}
