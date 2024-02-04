package com.ymitcloud.module.statistics.controller.admin.member.vo;


import lombok.Data;

/** 管理后台 - 会员地区统计 */
@Data
public class MemberAreaStatisticsRespVO {

    /** 省份编号*/
    private Integer areaId;
    /** 省份名称*/
    private String areaName;

    /** 会员数量*/
    private Integer userCount;

    /** 下单的会员数量*/
    private Integer orderCreateUserCount;
    /** 支付订单的会员数量*/
    private Integer orderPayUserCount;

    /** 订单支付金额，单位：分*/

    private Integer orderPayPrice;

}
