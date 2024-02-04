package com.ymitcloud.module.promotion.controller.app.bargain.vo.help;


import java.time.LocalDateTime;

import lombok.Data;

/**
 * 用户 App - 砍价助力 Response VO
 */
@Data
public class AppBargainHelpRespVO {

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 助力用户的昵称
     */
    private String nickname;

    /**
     * 助力用户的头像
     */
    private String avatar;

    /**
     * 助力用户的砍价金额
     */
    private Integer reducePrice;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
