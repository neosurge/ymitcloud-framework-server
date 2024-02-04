package com.ymitcloud.module.bpm.controller.admin.definition.vo.model;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 流程模型更新状态 Request VO
 */
@Data
public class BpmModelUpdateStateReqVO {
    /**
     * 编号
     */
    @NotNull(message = "编号不能为空")
    private String id;
    /**
     * 状态-见 SuspensionState 枚举
     */

    @NotNull(message = "状态不能为空")
    private Integer state;

}
