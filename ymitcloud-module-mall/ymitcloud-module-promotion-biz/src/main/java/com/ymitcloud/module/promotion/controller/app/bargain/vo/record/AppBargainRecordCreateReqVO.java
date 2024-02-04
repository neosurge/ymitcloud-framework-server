package com.ymitcloud.module.promotion.controller.app.bargain.vo.record;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户 App - 砍价记录的创建 Request VO
 */
@Data
public class AppBargainRecordCreateReqVO {

    /**
     * 砍价活动编号
     */

    @NotNull(message = "砍价活动编号不能为空")
    private Long activityId;

}
