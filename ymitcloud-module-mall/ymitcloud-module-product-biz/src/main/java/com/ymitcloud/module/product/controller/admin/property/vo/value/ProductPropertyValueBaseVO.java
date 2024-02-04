package com.ymitcloud.module.product.controller.admin.property.vo.value;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 属性值 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProductPropertyValueBaseVO {
    /**
     * 属性项的编号
     */
    @NotNull(message = "属性项的编号不能为空")
    private Long propertyId;
    /**
     * 名称
     */
    @NotEmpty(message = "名称名字不能为空")
    private String name;
    /**
     * 备注
     */

    private String remark;

}
