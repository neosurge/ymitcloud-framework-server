package com.ymitcloud.module.member.controller.admin.signin.vo.config;


import lombok.*;
import java.time.LocalDateTime;

/** 管理后台 - 签到规则 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberSignInConfigRespVO extends MemberSignInConfigBaseVO {


    /** 自增主键 */
    private Integer id;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
