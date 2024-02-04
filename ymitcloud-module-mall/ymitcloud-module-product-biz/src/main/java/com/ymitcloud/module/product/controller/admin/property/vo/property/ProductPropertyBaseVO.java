package com.ymitcloud.module.product.controller.admin.property.vo.property;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 商品属性项 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProductPropertyBaseVO {
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 备注
     */

    private String remark;

}
