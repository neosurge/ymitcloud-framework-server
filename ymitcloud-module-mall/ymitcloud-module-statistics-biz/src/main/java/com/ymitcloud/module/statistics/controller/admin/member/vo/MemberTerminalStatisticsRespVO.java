package com.ymitcloud.module.statistics.controller.admin.member.vo;



import lombok.Data;

/** 管理后台 - 会员终端统计 */
@Data
public class MemberTerminalStatisticsRespVO {

    /** 终端*/
    private Integer terminal;

    // TODO @疯狂：要不 orderCreateUserCount 和 orderPayUserCount 貌似更统一一些；
    /** 会员数量*/

    private Integer userCount;

}
