package com.ymitcloud.module.bpm.controller.admin.definition.vo.process;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 流程定义列表 Request VO
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BpmProcessDefinitionListReqVO extends PageParam {


    /**
     * 中断状态-参见 SuspensionState 枚举
     */

    private Integer suspensionState;

}
