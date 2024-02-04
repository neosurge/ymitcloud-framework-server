package com.ymitcloud.module.trade.controller.admin.aftersale.vo;

import com.ymitcloud.module.trade.controller.admin.base.member.user.MemberUserRespVO;
import com.ymitcloud.module.trade.controller.admin.base.product.property.ProductPropertyValueDetailRespVO;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


/** 管理后台 - 交易售后分页的每一条记录 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AfterSaleRespPageItemVO extends AfterSaleBaseVO {


    /** 售后编号*/
    private Long id;

    /** 创建时间*/

    private LocalDateTime createTime;

    /**
     * 商品属性数组
     */
    private List<ProductPropertyValueDetailRespVO> properties;

    /**
     * 用户信息
     */
    private MemberUserRespVO user;

}
