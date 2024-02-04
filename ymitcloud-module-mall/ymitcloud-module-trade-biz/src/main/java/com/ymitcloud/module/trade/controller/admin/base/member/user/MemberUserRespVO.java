package com.ymitcloud.module.trade.controller.admin.base.member.user;



import lombok.Data;

/** 管理后台 - 会员用户 */
@Data
public class MemberUserRespVO {

    /** 用户 ID*/
    private Long id;

    /** 用户昵称*/
    private String nickname;

    /** 
     * 用户头像
     */

    private String avatar;

}
