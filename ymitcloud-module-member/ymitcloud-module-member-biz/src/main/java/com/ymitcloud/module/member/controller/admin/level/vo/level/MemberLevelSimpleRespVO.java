package com.ymitcloud.module.member.controller.admin.level.vo.level;


import lombok.Data;
import lombok.ToString;

/** 管理后台 - 会员等级 */
@Data
@ToString(callSuper = true)
public class MemberLevelSimpleRespVO {

    /** 编号*/
    private Long id;

    /** 等级名称*/
    private String name;

    /** 等级图标 */
    private String icon;

}
