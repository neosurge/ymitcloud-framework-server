package com.ymitcloud.module.promotion.controller.app.combination.vo.record;


import java.util.List;

import lombok.Data;

/** 
 * 用户 App - 拼团记录的简要概括 */

@Data
public class AppCombinationRecordSummaryRespVO {

    /**
     * 加载 {@link #avatars} 的数量
     */
    public static final Integer AVATAR_COUNT = 7;


    /** 
     * 拼团用户数量*/
    private Long userCount;

    /**
     *  拼团用户头像列表*/

    private List<String> avatars;

}
