package com.ymitcloud.module.rule.service.logicflow;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * WHEN条件组树
 */
@Getter
@Setter
public class WhenGroupTree {
    /**
     * when Group 节点
     */
    String whenGroupNodeId;

    /**
     * when中有多棵树
     */
    List<ElNode> elNodeList;
}
