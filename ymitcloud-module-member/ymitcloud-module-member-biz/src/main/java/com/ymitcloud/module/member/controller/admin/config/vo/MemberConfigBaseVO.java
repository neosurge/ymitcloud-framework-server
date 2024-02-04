package com.ymitcloud.module.member.controller.admin.config.vo;




import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 会员配置 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MemberConfigBaseVO {


    /** 积分抵扣开关*/
    @NotNull(message = "积分抵扣开发不能为空")
    private Boolean pointTradeDeductEnable;

    /** 积分抵扣，单位：分*/
    @NotNull(message = "积分抵扣不能为空")
    private Integer pointTradeDeductUnitPrice;

    /** 积分抵扣最大值*/
    @NotNull(message = "积分抵扣最大值不能为空")
    private Integer pointTradeDeductMaxPrice;

    /** 1 元赠送多少分*/

    @NotNull(message = "1 元赠送积分不能为空")
    private Integer pointTradeGivePoint;

}
