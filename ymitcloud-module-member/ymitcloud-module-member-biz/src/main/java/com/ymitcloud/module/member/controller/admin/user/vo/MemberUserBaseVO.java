package com.ymitcloud.module.member.controller.admin.user.vo;



import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

import java.time.LocalDateTime;
import java.util.List;


import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

import lombok.Data;


/**
 * 会员用户 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MemberUserBaseVO {


    /** 手机号*/
    @NotNull(message = "手机号不能为空")
    private String mobile;

    /** 状态*/
    @NotNull(message = "状态不能为空")
    private Byte status;

    /** 用户昵称*/
    @NotNull(message = "用户昵称不能为空")
    private String nickname;

    /** 头像*/
    @URL(message = "头像必须是 URL 格式")
    private String avatar;

    /** 用户昵称*/
    private String name;

    /** 用户性别*/
    private Byte sex;

    /** 所在地编号*/
    private Long areaId;

    /** 所在地全程*/
    private String areaName;

    /** 出生日期*/
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDateTime birthday;

    /** 会员备注*/
    private String mark;

    /** 会员标签*/
    private List<Long> tagIds;

    /** 会员等级编号*/
    private Long levelId;

    /** 用户分组编号 */

    private Long groupId;

}
