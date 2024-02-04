package com.ymitcloud.module.bpm.controller.admin.definition.vo.model;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;


/**
 * 管理后台 - 流程模型的导入 Request VO 相比流程模型的新建来说，只是多了一个 bpmnFile 文件
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmModeImportReqVO extends BpmModelCreateReqVO {


    /**
     * BPMN 文件
     */

    @NotNull(message = "BPMN 文件不能为空")
    private MultipartFile bpmnFile;

}
