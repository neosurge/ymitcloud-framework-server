package com.ymitcloud.module.promotion.controller.admin.combination.vo.product;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 拼团商品 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CombinationProductRespVO extends CombinationProductBaseVO {


    /** 
     * 编号
     */
    private Long id;

    /** 
     * 创建时间
     */

    private LocalDateTime createTime;

}
