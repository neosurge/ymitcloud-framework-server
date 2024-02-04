package com.ymitcloud.module.bpm.controller.admin.definition.vo.model;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 流程模型的创建 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmModelRespVO extends BpmModelBaseVO {

    /**
     * 编号
     */
    private String id;
    /**
     * BPMN XML
     */
    private String bpmnXml;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
