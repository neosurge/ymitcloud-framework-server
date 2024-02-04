package com.ymitcloud.module.promotion.controller.admin.combination.vo.recrod;

import com.ymitcloud.module.promotion.controller.admin.combination.vo.activity.CombinationActivityRespVO;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 拼团记录的分页项 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CombinationRecordPageItemRespVO extends CombinationRecordBaseVO {

    // ========== 活动相关 ==========

    private CombinationActivityRespVO activity;

}
