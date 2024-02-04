package com.ymitcloud.module.bpm.controller.admin.definition.vo.process;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 流程定义的分页的每一项 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmProcessDefinitionPageItemRespVO extends BpmProcessDefinitionRespVO {

    /**
     * 表单名字
     */
    private String formName;
    /**
     * 部署时间
     */

    private LocalDateTime deploymentTime;

}
