package com.ymitcloud.module.member.controller.admin.level.vo.record;




import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 会员等级记录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MemberLevelRecordBaseVO {


    /** 用户编号*/
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /** 等级编号*/
    @NotNull(message = "等级编号不能为空")
    private Long levelId;

    /** 会员等级*/
    @NotNull(message = "会员等级不能为空")
    private Integer level;

    /** 享受折扣*/
    @NotNull(message = "享受折扣不能为空")
    private Integer discountPercent;

    /** 升级经验*/
    @NotNull(message = "升级经验不能为空")
    private Integer experience;

    /** 会员此时的经验*/
    @NotNull(message = "会员此时的经验不能为空")
    private Integer userExperience;

    /** 备注*/
    @NotNull(message = "备注不能为空")
    private String remark;

    /** 描述*/

    @NotNull(message = "描述不能为空")
    private String description;

}
