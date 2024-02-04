package com.ymitcloud.module.promotion.controller.admin.combination.vo.activity;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 拼团活动分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CombinationActivityPageReqVO extends PageParam {


    /** 
     * 拼团名称
     */
    private String name;

    /** 
     * 活动状态
     */

    private Integer status;

}
