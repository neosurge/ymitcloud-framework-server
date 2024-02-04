package com.ymitcloud.module.member.controller.admin.group.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/** 管理后台 - 用户分组 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberGroupRespVO extends MemberGroupBaseVO {

    /** 编号*/
    private Long id;

    /** 创建时间*/
    private LocalDateTime createTime;

}
