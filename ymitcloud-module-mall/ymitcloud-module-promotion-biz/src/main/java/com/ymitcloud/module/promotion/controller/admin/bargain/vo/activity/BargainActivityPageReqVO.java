package com.ymitcloud.module.promotion.controller.admin.bargain.vo.activity;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 砍价活动分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BargainActivityPageReqVO extends PageParam {


    /** 
     * 砍价名称
     */
    private String name;

    /** 
     * 活动状态
     */

    private Integer status;

}
