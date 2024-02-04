package com.ymitcloud.module.promotion.controller.admin.bargain.vo.help;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 砍价助力分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BargainHelpPageReqVO extends PageParam {


    /** 
     * 砍价记录编号
     */

    private Long recordId;

}
