package com.ymitcloud.module.member.controller.admin.level.vo.experience;




import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 会员经验记录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MemberExperienceRecordBaseVO {


    /** 用户编号*/
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /** 业务编号*/
    @NotNull(message = "业务编号不能为空")
    private String bizId;

    /** 业务类型*/
    @NotNull(message = "业务类型不能为空")
    private Integer bizType;

    /** 标题*/
    @NotNull(message = "标题不能为空")
    private String title;

    /** 经验*/
    @NotNull(message = "经验不能为空")
    private Integer experience;

    /** 变更后的经验*/
    @NotNull(message = "变更后的经验不能为空")
    private Integer totalExperience;

    /** 描述*/

    @NotNull(message = "描述不能为空")
    private String description;

}
