package com.ymitcloud.module.member.controller.admin.group.vo;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 用户分组 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MemberGroupBaseVO {

    /** 名称*/
    @NotNull(message = "名称不能为空")
    private String name;

    /** 备注*/
    private String remark;

    /** 状态*/
    @NotNull(message = "状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

}
