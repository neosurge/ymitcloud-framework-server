package com.ymitcloud.module.promotion.controller.admin.combination.vo.recrod;


import lombok.Data;

/** 
 * 管理后台 - 拼团记录信息统计 Response VO
 */
@Data
public class CombinationRecordSummaryVO {

    /** 
     * 所有拼团记录
     */
    private Long userCount;

    /** 
     * 成团记录
     */
    private Long successCount;

    /** 
     * 虚拟成团记录
     */

    private Long virtualGroupCount;

}
