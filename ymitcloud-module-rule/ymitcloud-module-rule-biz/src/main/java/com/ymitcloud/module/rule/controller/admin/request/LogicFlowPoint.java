package com.ymitcloud.module.rule.controller.admin.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogicFlowPoint implements Serializable {
    /**
     * x 轴坐标
     */
    private Integer x;

    /**
     * y 轴坐标
     */
    private Integer y;
}
