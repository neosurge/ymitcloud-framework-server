package com.ymitcloud.module.promotion.controller.app.bargain.vo.record;


import java.util.List;

import lombok.Data;

/**
 * 用户 App - 砍价记录的简要概括
 */
@Data
public class AppBargainRecordSummaryRespVO {

    /**
     * 砍价用户数量
     */
    private Integer successUserCount;

    /**
     * 成功砍价的记录
     */ // 只返回最近的 7 个
    private List<Record> successList;

    /**
     * 成功砍价记录")
     */
    @Data
    public static class Record {

        /**
         * 用户昵称
         */
        private String nickname;

        /**
         * 用户头像
         */
        private String avatar;

        /**
         * 活动名称
         */

        private String activityName;

    }

}
