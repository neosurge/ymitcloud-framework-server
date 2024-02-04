package com.ymitcloud.module.crm.controller.admin.business.vo.type;


import java.util.Collection;

import lombok.Data;
import lombok.ToString;

/**
 * 管理后台 - 商机状态类型 Query VO
 */

@Data
@ToString(callSuper = true)
public class CrmBusinessStatusTypeQueryVO {


    /**
     * 主键集合
     */
    private Collection<Long> idList;
    /**
     * 状态
     */

    private Integer status;
}
