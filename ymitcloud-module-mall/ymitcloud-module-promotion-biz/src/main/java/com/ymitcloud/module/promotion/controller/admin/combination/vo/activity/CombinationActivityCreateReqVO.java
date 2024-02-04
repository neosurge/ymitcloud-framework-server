package com.ymitcloud.module.promotion.controller.admin.combination.vo.activity;


import java.util.List;

import com.ymitcloud.module.promotion.controller.admin.combination.vo.product.CombinationProductBaseVO;

import jakarta.validation.Valid;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 拼团活动创建 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CombinationActivityCreateReqVO extends CombinationActivityBaseVO {


    /**
     * 拼团商品
     */

    @Valid
    private List<CombinationProductBaseVO> products;

}
