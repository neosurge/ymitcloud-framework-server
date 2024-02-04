package com.ymitcloud.module.rule.service.logicflow;

import com.ymitcloud.module.rule.controller.admin.request.LogicFlowEdgeRequest;
import com.ymitcloud.module.rule.controller.admin.request.LogicFlowNodeRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * 转换上下文
 */
@Getter
@Setter
public class ParseContext {

    /**
     * 节点Id -> 节点的多个边
     * sourceId-> List<EdgeEntity>
     */
    Map<String, List<LogicFlowEdgeRequest>> sourceNodeToEdgesMap;
    Map<String, List<LogicFlowEdgeRequest>> targetNodeToEdgesMap;

    /**
     *  所有的节点
     */
    Map<String, LogicFlowNodeRequest> nodeMap;

    /**
     * 所有的when节点
     */
    Map<String, LogicFlowNodeRequest> whenNodeMap;


    /**
     * 最外层的when
     */
    Set<LogicFlowNodeRequest> outermostLayerWhen;



    //待转化的Map, 父when -> 子when节点List
    Map<String, Set<String>> whenWaitProcessNestingMap = new HashMap<>();

    //已转化好的whenCompleteElNodeMap
    //when 节点id -》 ELNode节点
    Map<String, List<ElNode>> whenCompleteElNodeMap = new HashMap<>();

    List<WhenGroupTree> whenGroupTreeList = new ArrayList<>();
}
