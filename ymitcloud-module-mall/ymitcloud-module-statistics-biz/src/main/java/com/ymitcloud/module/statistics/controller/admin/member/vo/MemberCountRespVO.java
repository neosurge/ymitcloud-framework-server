package com.ymitcloud.module.statistics.controller.admin.member.vo;


import lombok.Data;

/** 管理后台 - 会员数量统计 */
@Data
public class MemberCountRespVO {

    /** 用户访问量*/
    private Integer visitUserCount;

    /** 注册用户数量*/

    private Integer registerUserCount;

}
