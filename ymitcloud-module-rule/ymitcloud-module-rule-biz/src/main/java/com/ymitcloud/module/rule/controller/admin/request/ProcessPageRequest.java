package com.ymitcloud.module.rule.controller.admin.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessPageRequest extends BasePageRequest{
    /**
     * 流程名称
     */
    private String processName;
}
