package com.ymitcloud.module.member.controller.admin.user.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/** 管理后台 - 会员用户 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberUserRespVO extends MemberUserBaseVO {

    /** 编号*/
    private Long id;

    /** 注册 IP*/
    private String registerIp;

    /** 最后登录IP*/
    private String loginIp;

    /** 最后登录时间*/
    private LocalDateTime loginDate;

    /** 创建时间*/
    private LocalDateTime createTime;

    // ========== 其它信息 ==========

    /** 积分*/
    private Integer point;

    /** 总积分*/
    private Integer totalPoint;

    /** 会员标签*/
    private List<String> tagNames;

    /** 会员等级*/
    private String levelName;

    /** 用户分组 */
    private String groupName;

    /** 用户经验值*/
    private Integer experience;

}
