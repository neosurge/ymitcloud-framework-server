package com.ymitcloud.module.member.controller.admin.level.vo.level;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * 会员等级 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MemberLevelBaseVO {

    /** 等级名称*/
    @NotBlank(message = "等级名称不能为空")
    private String name;

    /** 升级经验*/
    @NotNull(message = "升级经验不能为空")
    @Positive(message = "升级经验必须大于 0")
    private Integer experience;

    /** 等级*/
    @NotNull(message = "等级不能为空")
    @Positive(message = "等级必须大于 0")
    private Integer level;

    /** 享受折扣*/
    @NotNull(message = "享受折扣不能为空")
    @Range(min = 0, max = 100, message = "享受折扣的范围为 0-100")
    private Integer discountPercent;

    /** 等级图标*/
    @URL(message = "等级图标必须是 URL 格式")
    private String icon;

    /** 等级背景图*/
    @URL(message = "等级背景图必须是 URL 格式")
    private String backgroundUrl;

    /** 状态*/
    @NotNull(message = "状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

}
