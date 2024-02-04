package com.ymitcloud.module.promotion.controller.app.combination.vo.record;


import java.util.List;

import lombok.Data;

/**
 * 用户 App - 拼团记录详细
 */
@Data
public class AppCombinationRecordDetailRespVO {

    /**
     * 团长的拼团记录
     */
    private AppCombinationRecordRespVO headRecord;

    /**
     * 成员的拼团记录
     */
    private List<AppCombinationRecordRespVO> memberRecords;

    /**
     * 当前用户参团记录对应的订单编号
     */

    private Long orderId;

}
