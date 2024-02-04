package com.ymitcloud.module.rule.controller.admin.request;

import java.io.Serializable;

import com.alibaba.fastjson2.JSONObject;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProcessSaveRequest implements Serializable {
   /**
    * 流程名称
    */
    @NotBlank(message = "流程名称不能为空")
    private String processName;

    /**
     * 流程数据
     */
    private JSONObject processData;
}
