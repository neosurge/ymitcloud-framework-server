package com.ymitcloud.module.rule.controller.admin.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LogicFlowConvertElResponse  implements Serializable {
    /**
     * el表达式数据
     */
    private String elData;
}
