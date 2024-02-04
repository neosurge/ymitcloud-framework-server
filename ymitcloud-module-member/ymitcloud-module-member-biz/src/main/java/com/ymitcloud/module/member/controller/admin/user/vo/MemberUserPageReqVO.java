package com.ymitcloud.module.member.controller.admin.user.vo;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 管理后台 - 会员用户分页 Request VO */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberUserPageReqVO extends PageParam {

    /** 手机号*/
    private String mobile;

    /** 用户昵称*/
    private String nickname;

    /** 
     * 最后登录时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] loginDate;

    /** 
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    /** 会员标签编号列表*/
    private List<Long> tagIds;

    /** 会员等级编号*/
    private Long levelId;

    /** 用户分组编号*/
    private Long groupId;

    // TODO 云码：注册用户类型；

    // TODO 云码：登录用户类型；

}
