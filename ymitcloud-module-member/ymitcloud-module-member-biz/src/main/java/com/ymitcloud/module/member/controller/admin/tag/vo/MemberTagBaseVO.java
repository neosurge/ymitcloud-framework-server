package com.ymitcloud.module.member.controller.admin.tag.vo;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 会员标签 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MemberTagBaseVO {

    /** 标签名称*/
    @NotNull(message = "标签名称不能为空")
    private String name;

}
