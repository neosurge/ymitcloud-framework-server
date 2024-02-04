package com.ymitcloud.module.promotion.controller.admin.reward.vo;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 满减送活动分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RewardActivityPageReqVO extends PageParam {


    /** 
     * 活动标题
     */
    private String name;

    /** 
     * 活动状态
     */

    private Integer status;

}
