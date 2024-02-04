package com.ymitcloud.module.rule.service.logicflow;

import lombok.Getter;

/**
 * LogicFlow的Node 自定义配置的属性key
 */
@Getter
public enum LogicFlowNodePropEnum {
    nodeId,
    nodeTag,
    nodeData,
    /**
     * 对应 ElNode.ElName
     * @see ElNode.ElNameEnum
     */
    nodeType,
    conditionNodeId,
    nodeAliasId;
}
