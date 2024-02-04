package com.ymitcloud.module.product.controller.admin.property.vo.property;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 属性项 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductPropertyRespVO extends ProductPropertyBaseVO {

    /**
     * 编号
     */
    private Long id;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
