package com.ymitcloud.module.member.controller.app.user.vo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/** 用户 APP - 用户个人信息 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppMemberUserInfoRespVO {


    /** 用户昵称 */
    private String nickname;

    /** 用户头像 */
    private String avatar;

    /** 用户手机号 */
    private String mobile;

    /** 积分 */
    private Integer point;

    /** 经验值 */
    private Integer experience;

    /**
     * 用户等级
     */
    private Level level;

    /** 是否成为推广员 */
    private Boolean brokerageEnabled;

    /**
     * 用户 App - 会员等级
     */
    @Data
    public static class Level {

        /** 等级编号 */
        private Long id;

        /** 等级名称 */
        private String name;

        /** 等级 */
        private Integer level;

        /**
         * 等级图标
         */

        private String icon;

    }

}
