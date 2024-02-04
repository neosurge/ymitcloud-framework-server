package com.ymitcloud.module.promotion.controller.admin.bargain.vo.help;


import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 砍价助力
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BargainHelpRespVO extends BargainHelpBaseVO {


    /**
     * 砍价助力编号
     */
    private Long id;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    // ========== 用户相关 ==========


    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */

    private String avatar;

}
