package com.ymitcloud.module.promotion.controller.app.coupon.vo.template;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.promotion.enums.common.PromotionProductScopeEnum;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 用户 App - 优惠劵模板分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppCouponTemplatePageReqVO extends PageParam {


    /** 
     * 商品范围
     */
    @InEnum(value = PromotionProductScopeEnum.class, message = "商品范围，必须是 {value}")
    private Integer productScope;

    /** 
     * 商品标号
     */

    private Long spuId;

}
