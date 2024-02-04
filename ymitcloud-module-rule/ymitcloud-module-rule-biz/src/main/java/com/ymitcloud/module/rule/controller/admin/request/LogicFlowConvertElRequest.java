package com.ymitcloud.module.rule.controller.admin.request;


import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogicFlowConvertElRequest implements Serializable {
    /**
     * 节点描述信息列表
     */
    List<LogicFlowNodeRequest> nodes;
    /**
     * 边界描述信息列表
     */
    List<LogicFlowEdgeRequest> edges;
}
