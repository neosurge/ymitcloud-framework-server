package com.ymitcloud.module.bpm.controller.admin.task.vo.task;


import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 管理后台 - 加签流程任务的 Request VO
 */
@Data
public class BpmTaskAddSignReqVO {
    /**
     * 需要加签的任务 ID
     */
    @NotEmpty(message = "任务编号不能为空")
    private String id;
    /**
     * 加签的用户 ID
     */
    @NotEmpty(message = "加签用户 ID 不能为空")
    private Set<Long> userIdList;
    /**
     * 加签类型，before 向前加签，after 向后加签
     */
    @NotEmpty(message = "加签类型不能为空")
    private String type;
    /**
     * 加签原因
     */

    @NotEmpty(message = "加签原因不能为空")
    private String reason;

}
