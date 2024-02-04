package com.ymitcloud.module.member.controller.admin.config.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 管理后台 - 会员配置 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberConfigRespVO extends MemberConfigBaseVO {

    /** 自增主键*/
    private Long id;

}
