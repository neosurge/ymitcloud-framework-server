package com.ymitcloud.module.member.controller.admin.tag.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/** 管理后台 - 会员标签 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberTagRespVO extends MemberTagBaseVO {

    /** 编号*/
    private Long id;

    /** 创建时间*/
    private LocalDateTime createTime;

}
