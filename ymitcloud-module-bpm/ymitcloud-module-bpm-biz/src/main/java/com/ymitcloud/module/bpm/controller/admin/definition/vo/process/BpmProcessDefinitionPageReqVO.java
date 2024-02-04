package com.ymitcloud.module.bpm.controller.admin.definition.vo.process;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 流程定义分页 Request VO
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BpmProcessDefinitionPageReqVO extends PageParam {


    /**
     * 标识-精准匹配
     */

    private String key;

}
