package com.ymitcloud.module.statistics.controller.admin.member.vo;

import com.ymitcloud.module.statistics.controller.admin.common.vo.DataComparisonRespVO;


import lombok.Data;

/** 管理后台 - 会员分析 */
@Data
public class MemberAnalyseRespVO {

    /** 访客数量*/
    private Integer visitUserCount;

    /** 下单用户数量*/
    private Integer orderUserCount;

    /** 成交用户数量*/
    private Integer payUserCount;

    /** 客单价，单位：分*/
    private Integer atv;

    /** 对照数据*/

    private DataComparisonRespVO<MemberAnalyseDataRespVO> comparison;

}
