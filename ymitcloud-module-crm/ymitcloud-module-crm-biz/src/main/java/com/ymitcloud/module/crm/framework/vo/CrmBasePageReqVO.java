package com.ymitcloud.module.crm.framework.vo;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.crm.enums.common.CrmSceneEnum;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理后台 - CRM 分页 Base Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CrmBasePageReqVO extends PageParam {

    /**
     * 场景类型，为 null 时则表示全部
     */

    @InEnum(CrmSceneEnum.class)
    private Integer sceneType;
    /**
     * 是否为公海数据
     */

    private Boolean pool; // null 则表示为不是公海数据

}
