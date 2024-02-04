package com.ymitcloud.module.member.controller.app.level.vo.level;


import lombok.Data;

/** 用户 App - 会员等级 */
@Data
public class AppMemberLevelRespVO {

    /** 等级名称*/
    private String name;

    /** 等级*/
    private Integer level;

    /** 升级经验*/
    private Integer experience;

    /** 享受折扣*/
    private Integer discountPercent;

    /** 等级图标*/
    private String icon;

    /** 等级背景图 */
    private String backgroundUrl;

}
