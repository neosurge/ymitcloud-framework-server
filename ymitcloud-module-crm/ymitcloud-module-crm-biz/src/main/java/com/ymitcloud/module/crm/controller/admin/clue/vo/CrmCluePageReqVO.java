package com.ymitcloud.module.crm.controller.admin.clue.vo;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 线索分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmCluePageReqVO extends PageParam {

    /**
     * 线索名称
     */
    private String name;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 手机号
     */

    private String mobile;

}
