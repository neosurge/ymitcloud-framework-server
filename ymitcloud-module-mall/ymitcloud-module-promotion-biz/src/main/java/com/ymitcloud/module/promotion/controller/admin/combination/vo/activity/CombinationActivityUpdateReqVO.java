package com.ymitcloud.module.promotion.controller.admin.combination.vo.activity;


import java.util.List;

import com.ymitcloud.module.promotion.controller.admin.combination.vo.product.CombinationProductBaseVO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 拼团活动更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CombinationActivityUpdateReqVO extends CombinationActivityBaseVO {


    /** 
     * 活动编号
     */
    @NotNull(message = "活动编号不能为空")
    private Long id;

    /** 
     * 拼团商品
     */

    @Valid
    private List<CombinationProductBaseVO> products;

}
