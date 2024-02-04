package com.ymitcloud.module.promotion.controller.admin.combination.vo.activity;


import java.time.LocalDateTime;
import java.util.List;

import com.ymitcloud.module.promotion.controller.admin.combination.vo.product.CombinationProductRespVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 
 * 管理后台 - 拼团活动 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CombinationActivityRespVO extends CombinationActivityBaseVO {


    /** 
     * 活动编号
     */
    private Long id;

    /** 
     * 创建时间
     */
    private LocalDateTime createTime;

    /** 
     * 开团人数
     */
    private Integer userSize;

    /** 
     * 拼团商品
     */

    private List<CombinationProductRespVO> products;

}
