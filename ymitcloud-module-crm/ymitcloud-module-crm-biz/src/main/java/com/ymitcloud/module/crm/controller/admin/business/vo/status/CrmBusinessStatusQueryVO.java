package com.ymitcloud.module.crm.controller.admin.business.vo.status;


import java.util.Collection;

import lombok.Data;
import lombok.ToString;

/**
 * 管理后台 - 商机状态 Query VO
 */

@Data
@ToString(callSuper = true)
public class CrmBusinessStatusQueryVO {


    /**
     * 主键集合
     */
    private Collection<Long> idList;

    /**
     * 状态类型编号
     */

    private Long typeId;
}
