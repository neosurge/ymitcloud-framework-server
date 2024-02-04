package com.ymitcloud.module.bpm.controller.admin.task.vo.instance;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 管理后台 - 流程实例的取消 Request VO
 */
@Data
public class BpmProcessInstanceCancelReqVO {

    /**
     * 流程实例的编号
     */
    @NotEmpty(message = "流程实例的编号不能为空")
    private String id;
    /**
     * 取消原因
     */

    @NotEmpty(message = "取消原因不能为空")
    private String reason;

}
