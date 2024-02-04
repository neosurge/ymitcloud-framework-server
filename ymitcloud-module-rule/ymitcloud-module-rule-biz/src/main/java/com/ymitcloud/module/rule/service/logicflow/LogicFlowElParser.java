package com.ymitcloud.module.rule.service.logicflow;

import com.ymitcloud.framework.common.exception.ServiceException;
import com.ymitcloud.module.rule.controller.admin.request.LogicFlowConvertElRequest;
import com.ymitcloud.module.rule.controller.admin.request.LogicFlowEdgeRequest;
import com.ymitcloud.module.rule.controller.admin.request.LogicFlowNodeRequest;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * LogicFlow格式转为el表达式
 */
@Getter
@Setter
public class LogicFlowElParser implements ElParser {
    /**
     * 待解析的LogicFlow数据
     */
    private LogicFlowConvertElRequest request;

    /**
     * 上下文
     */
    private ParseContext context;

    public LogicFlowElParser(LogicFlowConvertElRequest request) {
        this.request = request;
        this.context = new ParseContext();
    }

    @Override
    public ElNode extractElNode() {
        List<LogicFlowNodeRequest> nodes = request.getNodes();
        List<LogicFlowEdgeRequest> edges = request.getEdges();
        if (CollectionUtils.isEmpty(nodes) || CollectionUtils.isEmpty(edges)) {
            return ElNode.initThenNode();
        }
        //根节点
        String rootId = extractInitialNode(edges);
        //sourceId-> List<EdgeEntity>
        Map<String, List<LogicFlowEdgeRequest>> sourceNodeToEdgesMap = edges.stream().collect(Collectors.groupingBy(LogicFlowEdgeRequest::getSourceNodeId));
        Map<String, List<LogicFlowEdgeRequest>> targetNodeToEdgesMap = edges.stream().collect(Collectors.groupingBy(LogicFlowEdgeRequest::getTargetNodeId));
        //id -> NodeEntity
        Map<String, LogicFlowNodeRequest> nodeMap = nodes.stream().collect(Collectors.toMap(LogicFlowNodeRequest::getId, Function.identity()));

        //获取when节点
        Map<String, LogicFlowNodeRequest> whenNodeMap = nodes.stream()
                .filter(e -> ElNode.ElNameEnum.WHEN.name().equals(e.getProperties().get(LogicFlowNodePropEnum.nodeType.name())))
                .collect(Collectors.toMap(LogicFlowNodeRequest::getId, Function.identity()));

        context.setNodeMap(nodeMap);
        context.setWhenNodeMap(whenNodeMap);
        context.setSourceNodeToEdgesMap(sourceNodeToEdgesMap);
        context.setTargetNodeToEdgesMap(targetNodeToEdgesMap);

        //获取所有外层when
        Set<LogicFlowNodeRequest> outermostLayerWhen = this.getOutermostLayerWhen();
        context.setOutermostLayerWhen(outermostLayerWhen);

        //先准备好各个并行组的树节点
        prepareWhenGroupTree();

        //开始提取
        ElNode elNode = ElNode.initThenNode();
        elNode.setChild(doExtract(rootId));
        return elNode;
    }
    /**
     * 获取最外层的whenIds
     *
     * @return
     */
    private Set<LogicFlowNodeRequest> getOutermostLayerWhen() {
        Map<String, LogicFlowNodeRequest> whenNodeMap = context.getWhenNodeMap();

        Collection<LogicFlowNodeRequest> whens = whenNodeMap.values();
        if (CollectionUtils.isEmpty(whens)) {
            return null;
        }

        Set<String> allWhenChild = whens.stream()
                .map(LogicFlowNodeRequest::getChildren)
                .flatMap(List::stream)
                .collect(Collectors.toSet());

        return whens.stream().filter(e -> !allWhenChild.contains(e.getId())).collect(Collectors.toSet());
    }


    /**
     * 获取when中的根节点，可能包含when组的id
     *
     * @param whenLfNode when节点
     * @return
     */
    private Set<String> getRootIdInWhen(LogicFlowNodeRequest whenLfNode) {
        Map<String, LogicFlowNodeRequest> nodeMap = context.getNodeMap();
        Set<String> firstIds = getFirstIdsInWhen(whenLfNode);
        Set<String> rootIds = new HashSet<>(firstIds);
        //获取when中的when子节点
        Set<String> whenChildren = getChildrenWhenInWhen(nodeMap, whenLfNode);

        for (String whenId : whenChildren) {
            LogicFlowNodeRequest lfNode = nodeMap.get(whenId);
            Set<String> firstIds2 = getFirstIdsInWhen(lfNode);
            if (firstIds.containsAll(firstIds2)) {
                rootIds.removeAll(firstIds2);
                rootIds.add(whenId);
            }
        }
        return rootIds;

    }

    /**
     * 获取靠前的第一个节点
     *
     * @param whenLfNode
     * @return
     */
    private Set<String> getFirstIdsInWhen(LogicFlowNodeRequest whenLfNode) {
        Map<String, List<LogicFlowEdgeRequest>> sourceNodeToEdgesMap = context.getSourceNodeToEdgesMap();
        Map<String, List<LogicFlowEdgeRequest>> targetNodeToEdgesMap = context.getTargetNodeToEdgesMap();

        List<String> allChildNodeInWhen = getAllChildNodeInWhen(whenLfNode);
        Set<String> fatherIds = getFatherIds(targetNodeToEdgesMap, allChildNodeInWhen);

        //待确认的rootId节点
        Set<String> readyRootIds = new HashSet<>();

        for (String f : fatherIds) {
            List<LogicFlowEdgeRequest> lfEdges = sourceNodeToEdgesMap.get(f);
            if (CollectionUtils.isEmpty(lfEdges)) {
                continue;
            }
            Set<String> collect = lfEdges.stream()
                    .map(LogicFlowEdgeRequest::getTargetNodeId)
                    .filter(allChildNodeInWhen::contains)
                    .collect(Collectors.toSet());
            readyRootIds.addAll(collect);
        }
        return readyRootIds;
    }


    /**
     * 获取父亲id
     *
     * @param targetNodeToEdgesMap 目标节点来边缘Map
     * @param children             孩子们
     * @return {@link Set}<{@link String}>
     */
    private Set<String> getFatherIds(Map<String, List<LogicFlowEdgeRequest>> targetNodeToEdgesMap, List<String> children) {
        //获取他们所有的父节点
        Set<String> fatherIds = new HashSet<>();
        for (String c : children) {
            List<LogicFlowEdgeRequest> lfEdges = targetNodeToEdgesMap.get(c);
            if (CollectionUtils.isEmpty(lfEdges)) {
                continue;
            }
            Set<String> sourceIds = lfEdges.stream().map(LogicFlowEdgeRequest::getSourceNodeId).collect(Collectors.toSet());
            fatherIds.addAll(sourceIds);
        }
        //删除后，剩下就是他们的根
        children.forEach(fatherIds::remove);
        return fatherIds;
    }

    /**
     * 递归析构出所有when内的所有节点
     *
     * @param when
     * @return
     */
    private List<String> getAllChildNodeInWhen(LogicFlowNodeRequest when) {
        List<String> children = when.getChildren();
        Map<String, LogicFlowNodeRequest> nodeMap = context.getNodeMap();

        List<String> res = new ArrayList<>();
        for (String c : children) {
            LogicFlowNodeRequest lfNode1 = nodeMap.get(c);
            if (lfNode1 == null) {
                continue;
            }
            if (ElNode.ElNameEnum.WHEN.name().equals(lfNode1.getProperties().get(LogicFlowNodePropEnum.nodeType.name()))) {
                List<String> allChildNodeInWhen = getAllChildNodeInWhen(lfNode1);
                res.addAll(allChildNodeInWhen);
            } else {
                res.add(c);
            }
        }

        return res;
    }

    /**
     * 准备各个并行组的树节点
     */
    private void prepareWhenGroupTree() {

        //获取最外层的when
        Set<LogicFlowNodeRequest> outermostLayerWhen = context.getOutermostLayerWhen();
        if (CollectionUtils.isEmpty(outermostLayerWhen)) {
            return;
        }
        List<WhenGroupTree> list = new ArrayList<>();

        //遍历最外层的when
        for (LogicFlowNodeRequest when : outermostLayerWhen) {
            String whenId = when.getId();
            WhenGroupTree whenGroupTrees = new WhenGroupTree();
            whenGroupTrees.setWhenGroupNodeId(whenId);
            List<ElNode> ex = this.doWhenGroupTree(whenId);
            whenGroupTrees.setElNodeList(ex);
            list.add(whenGroupTrees);
        }
        List<WhenGroupTree> whenGroupTreesList = context.getWhenGroupTreeList();
        whenGroupTreesList.addAll(list);

    }

    /**
     * 获取 when下所有的子when
     *
     * @param nodeMap
     * @param when
     * @return
     */
    private Set<String> getChildrenWhenInWhen(Map<String, LogicFlowNodeRequest> nodeMap, LogicFlowNodeRequest when) {
        List<String> childrenInWhen = when.getChildren();
        //获取when中的when子节点
        return childrenInWhen.stream().filter(e -> {
            LogicFlowNodeRequest lfNode = nodeMap.get(e);
            String nodeType = getLfNodeProperty(lfNode, LogicFlowNodePropEnum.nodeType.name());
            return ElNode.ElNameEnum.WHEN.name().equals(nodeType);
        }).collect(Collectors.toSet());
    }

    /**
     * 递归执行提取when并行树
     *
     * @param whenId 当id
     * @return {@link List}<{@link ElNode}>
     */
    private List<ElNode> doWhenGroupTree(String whenId) {
        Map<String, LogicFlowNodeRequest> nodeMap = context.getNodeMap();
        LogicFlowNodeRequest whenNode = nodeMap.get(whenId);

        Set<String> whenChildren = getChildrenWhenInWhen(nodeMap, whenNode);

        if (!CollectionUtils.isEmpty(whenChildren)) {
            for (String wc : whenChildren) {
                List<ElNode> ex = doWhenGroupTree(wc);
                Map<String, List<ElNode>> whenCompleteElNodeMap = context.getWhenCompleteElNodeMap();
                whenCompleteElNodeMap.put(wc, ex);
            }
            // 使用已有的Complete 继续构建
        }

        //获取根节点，执行提取
        Set<String> rootIdsInWhen = getRootIdInWhen(whenNode);

        ElNode whenELNode = ElNode.initWhenNode();
        List<ElNode> whenChildElNode = new ArrayList<>();
        whenELNode.setChild(whenChildElNode);
        for (String rootId : rootIdsInWhen) {
            ElNode thenNode = ElNode.initThenNode();
            whenChildElNode.add(thenNode);
            List<ElNode> elNodes = doExtractWhen(rootId, whenId, whenNode.getChildren());
            thenNode.setChild(elNodes);
        }

        List<ElNode> res = new ArrayList<>();
        res.add(whenELNode);
        return res;
    }


    /**
     * 递归提取when上各并行树
     *
     * @param currentId
     * @param whenId
     * @param childrenInWhen
     * @return
     */
    private List<ElNode> doExtractWhen(String currentId, String whenId, List<String> childrenInWhen) {
        Map<String, List<LogicFlowEdgeRequest>> sourceNodeToEdgesMap = context.getSourceNodeToEdgesMap();
        Map<String, LogicFlowNodeRequest> nodeMap = context.getNodeMap();


        //结果
        List<ElNode> listNodes = new ArrayList<>();

        LogicFlowNodeRequest currentLfNode = nodeMap.get(currentId);
        String currentNodeType = getLfNodeProperty(currentLfNode, LogicFlowNodePropEnum.nodeType.name());
        if (currentLfNode == null) {
            return listNodes;
        }


        //连续处理多个只有一个子节点的节点
        //当当前节点并非叶子节点，并且子节点只有一个，并且当前节点在当前when的子节点中
        while (sourceNodeToEdgesMap.containsKey(currentId) && sourceNodeToEdgesMap.get(currentId).size() == 1
                && childrenInWhen.contains(currentId)) {

            LogicFlowNodeRequest node = nodeMap.get(currentId);
            String nodeType = getLfNodeProperty(node, LogicFlowNodePropEnum.nodeType.name());
            if (!ElNode.ElNameEnum.COMMON.name().equals(nodeType)) {
                //如果不是COMMON ,不继续处理
                break;
            }
            listNodes.add(initElNode(node));
            List<LogicFlowEdgeRequest> edgeEntities = sourceNodeToEdgesMap.get(currentId);
            if (!CollectionUtils.isEmpty(edgeEntities)) {
                //替换当前节点Id
                currentId = edgeEntities.get(0).getTargetNodeId();
            }
        }

        if (!ElNode.ElNameEnum.WHEN.name().equals(currentNodeType)) {
            if (!childrenInWhen.contains(currentId)) {
                //非本when中的节点不处理
                return listNodes;
            }
            //叶子节点处理：
            if (!sourceNodeToEdgesMap.containsKey(currentId)) {
                listNodes.add(initElNode(nodeMap.get(currentId)));
                return listNodes;
            }
        }

        // 子节点数大于1个的处理 或者 这种节点可能是IF/ SWITCH / WHEN

        //更新当前节点的信息
        currentLfNode = nodeMap.get(currentId);
        currentNodeType = getLfNodeProperty(currentLfNode, LogicFlowNodePropEnum.nodeType.name());

        //如果当前节点是IF 或Switch 的处理
        List<String> ifOrSwitchType = Arrays.asList(ElNode.ElNameEnum.IF.name(), ElNode.ElNameEnum.SWITCH.name());
        if (ifOrSwitchType.contains(currentNodeType)) {
            ElNode curNode = initElNode(currentLfNode);
            List<ElNode> children = new ArrayList<>();
            curNode.setChild(children);
            listNodes.add(curNode);
            //获取他的子节点的边
            List<LogicFlowEdgeRequest> edgeEntities = sourceNodeToEdgesMap.get(currentId);
            this.validateIfOrSwitchNode(currentNodeType, edgeEntities);
            for (LogicFlowEdgeRequest edge : edgeEntities) {
                //获取子节点
                String targetNodeId = edge.getTargetNodeId();
                LogicFlowNodeRequest lfNode = nodeMap.get(targetNodeId);
                String nodeAliasId = getLfNodeProperty(lfNode, LogicFlowNodePropEnum.nodeAliasId.name());
                ElNode thenNode = ElNode.initThenNode();
                thenNode.setAliasNodeId(nodeAliasId);
                thenNode.setChild(doExtractWhen(targetNodeId, whenId, childrenInWhen));
                children.add(thenNode);
            }
        }
        // 如果当前节点是common 而且是两个以上的子节点，考虑替换when来出来。
        if (ElNode.ElNameEnum.COMMON.name().equals(currentNodeType)
                && sourceNodeToEdgesMap.containsKey(currentId)
                && sourceNodeToEdgesMap.get(currentId).size() > 1) {
            //方案1.获取下一个节点的when，再递归获取上层when ，直到当前when之内为止
            //方案2.在这些when中递归寻找合适：childrenInWhen,直到找到包含其中一个
            List<LogicFlowEdgeRequest> lfEdges = sourceNodeToEdgesMap.get(currentId);
            Set<String> targetIds = lfEdges.stream().map(LogicFlowEdgeRequest::getTargetNodeId).collect(Collectors.toSet());

            //把当前节点加入结果集中
            ElNode curNode = initElNode(currentLfNode);
            listNodes.add(curNode);

            //当前的父when
            LogicFlowNodeRequest whenLfNode = nodeMap.get(whenId);
            Set<String> childrenWhenInWhen = getChildrenWhenInWhen(nodeMap, whenLfNode);
            if (!CollectionUtils.isEmpty(childrenWhenInWhen)) {
                String foundWhen = findWhenContainAnyOne(childrenWhenInWhen, targetIds);
                if (!StringUtils.isEmpty(foundWhen)) {
                    //更新当前遍历节点
                    currentId = foundWhen;
                    currentLfNode = nodeMap.get(currentId);
                    currentNodeType = getLfNodeProperty(currentLfNode, LogicFlowNodePropEnum.nodeType.name());
                    //继续执行下面代码
                }
            }
        }


        //如果当前节点是when类型的处理
        if (ElNode.ElNameEnum.WHEN.name().equals(currentNodeType)) {
            Map<String, List<ElNode>> whenCompleteElNodeMap = context.getWhenCompleteElNodeMap();
            List<ElNode> elNodes1 = whenCompleteElNodeMap.get(currentId);
            List<ElNode> res = new ArrayList<>(elNodes1);

            //递归析构出所有when内的所有节点
            List<String> allChildNodeInWhen = getAllChildNodeInWhen(currentLfNode);
            //继续执行when下一个节点
            String externalNodeIdOutSideWhen = doFindOutSideWhenNode(allChildNodeInWhen.get(0),
                    sourceNodeToEdgesMap, new HashSet<>(allChildNodeInWhen));

            LogicFlowNodeRequest lfNode = nodeMap.get(whenId);
            List<ElNode> elNodes = doExtractWhen(externalNodeIdOutSideWhen, whenId, lfNode.getChildren());
            if (!CollectionUtils.isEmpty(elNodes)) {
                res.addAll(elNodes);
            }

            listNodes.addAll(res);
            return listNodes;

        }

        return listNodes;
    }

    /**
     * 寻找一个whenId,包含任意一个指定的targetIds组件
     *
     * @param childrenInWhen
     * @param targetIds
     * @return
     */
    private String findWhenContainAnyOne(Set<String> childrenInWhen, Set<String> targetIds) {
        Map<String, LogicFlowNodeRequest> nodeMap = context.getNodeMap();
        for (String whenId : childrenInWhen) {
            LogicFlowNodeRequest lfNode = nodeMap.get(whenId);
            if (lfNode == null) {
                continue;
            }
            List<String> children = lfNode.getChildren();
            //先看当前范围内是否有包含任意一个targetIds
            for (String targetId : targetIds) {
                if (children.contains(targetId)) {
                    return whenId;
                }
            }

            //再递归看，下面子when是否包含
            Set<String> childrenWhenInWhen = getChildrenWhenInWhen(nodeMap, lfNode);
            if (!CollectionUtils.isEmpty(childrenWhenInWhen)) {
                String whenContainAnyOne = findWhenContainAnyOne(childrenWhenInWhen, targetIds);
                if (!StringUtils.isEmpty(whenContainAnyOne)) {
                    return whenContainAnyOne;
                }
            }

        }
        return null;
    }


    /**
     * 递归提取ELNode方法
     *
     * @param currentId
     * @return
     */
    private List<ElNode> doExtract(String currentId) {
        Map<String, List<LogicFlowEdgeRequest>> sourceNodeToEdgesMap = context.getSourceNodeToEdgesMap();
        Map<String, LogicFlowNodeRequest> nodeMap = context.getNodeMap();


        List<ElNode> listNodes = new ArrayList<>();
        LogicFlowNodeRequest currentLfNode = nodeMap.get(currentId);
        if (currentLfNode == null) {
            return listNodes;
        }

        List<String> ifOrSwitchType = Arrays.asList(ElNode.ElNameEnum.IF.name(), ElNode.ElNameEnum.SWITCH.name());

        //连续处理多个只有一个子节点的节点
        while (sourceNodeToEdgesMap.containsKey(currentId) && sourceNodeToEdgesMap.get(currentId).size() == 1) {
            LogicFlowNodeRequest node = nodeMap.get(currentId);
            String nodeType = getLfNodeProperty(node, LogicFlowNodePropEnum.nodeType.name());
            if (ifOrSwitchType.contains(nodeType)) {
                //如果是if 或 switch 不继续处理
                break;
            }

            listNodes.add(initElNode(node));
            List<LogicFlowEdgeRequest> edgeEntities = sourceNodeToEdgesMap.get(currentId);
            if (!CollectionUtils.isEmpty(edgeEntities)) {
                //有子节点
                currentId = edgeEntities.get(0).getTargetNodeId();
            }
        }

        //叶子节点处理：
        if (!sourceNodeToEdgesMap.containsKey(currentId)) {
            listNodes.add(initElNode(nodeMap.get(currentId)));
            return listNodes;
        }


        //更新当前节点
        currentLfNode = nodeMap.get(currentId);
        String currentNodeType = getLfNodeProperty(currentLfNode, LogicFlowNodePropEnum.nodeType.name());

        //这种节点可能是IF/ SWITCH
        if (ifOrSwitchType.contains(currentNodeType)) {
            ElNode curNode = initElNode(currentLfNode);
            List<ElNode> children = new ArrayList<>();
            curNode.setChild(children);
            listNodes.add(curNode);
            List<LogicFlowEdgeRequest> edgeEntities = sourceNodeToEdgesMap.get(currentId);
            this.validateIfOrSwitchNode(currentNodeType, edgeEntities);
            for (LogicFlowEdgeRequest edge : edgeEntities) {
                String targetNodeId = edge.getTargetNodeId();
                LogicFlowNodeRequest lfNode = nodeMap.get(targetNodeId);
                String nodeAliasId = getLfNodeProperty(lfNode, LogicFlowNodePropEnum.nodeAliasId.name());
                ElNode thenNode = ElNode.initThenNode();
                thenNode.setAliasNodeId(nodeAliasId);
                thenNode.setChild(doExtract(targetNodeId));
                children.add(thenNode);
            }
            return listNodes;
        }

        // 如果当前节点是common 而且是两个以上的子节点，考虑替换when来出来。
        if (ElNode.ElNameEnum.COMMON.name().equals(currentNodeType)
                && sourceNodeToEdgesMap.containsKey(currentId)
                && sourceNodeToEdgesMap.get(currentId).size() > 1) {


            List<LogicFlowEdgeRequest> lfEdges = sourceNodeToEdgesMap.get(currentId);
            Set<String> targetIds = lfEdges.stream().map(LogicFlowEdgeRequest::getTargetNodeId).collect(Collectors.toSet());

            //把当前节点加入结果集中
            ElNode curNode = initElNode(currentLfNode);
            listNodes.add(curNode);

            //获取所有最外层的when准备好的树，在这些when中递归寻找合适：childrenInWhen,直到找到包含其中一个
            Set<LogicFlowNodeRequest> outermostLayerWhen = context.getOutermostLayerWhen();
            Set<String> whenIds = outermostLayerWhen.stream().map(LogicFlowNodeRequest::getId).collect(Collectors.toSet());
            if (!CollectionUtils.isEmpty(whenIds)) {
                String foundWhen = findWhenContainAnyOne(whenIds, targetIds);
                if (!StringUtils.isEmpty(foundWhen)) {
                    //更新当前遍历节点
                    currentId = foundWhen;
                    currentLfNode = nodeMap.get(currentId);
                    currentNodeType = getLfNodeProperty(currentLfNode, LogicFlowNodePropEnum.nodeType.name());
                    //继续执行下面代码
                }
            }
        }

        //如果当前节点是when类型的处理
        if (ElNode.ElNameEnum.WHEN.name().equals(currentNodeType)) {
            List<WhenGroupTree> whenGroupTreesList = context.getWhenGroupTreeList();
            Map<String, WhenGroupTree> treesMap = whenGroupTreesList.stream()
                    .collect(Collectors.toMap(WhenGroupTree::getWhenGroupNodeId, Function.identity()));

            WhenGroupTree whenGroupTree1 = treesMap.get(currentId);
            if (whenGroupTree1 != null) {
                List<ElNode> elNodeList = whenGroupTree1.getElNodeList();
                listNodes.addAll(elNodeList);
                //递归析构出所有when内的所有节点
                List<String> allChildNodeInWhen = getAllChildNodeInWhen(currentLfNode);
                //继续执行when下一个节点
                String externalNodeIdOutSideWhen = doFindOutSideWhenNode(allChildNodeInWhen.get(0),
                        sourceNodeToEdgesMap, new HashSet<>(allChildNodeInWhen));

                List<ElNode> elNodes = doExtract(externalNodeIdOutSideWhen);
                if (!CollectionUtils.isEmpty(elNodes)) {
                    listNodes.addAll(elNodes);
                }
                return listNodes;

            }
        }


        return listNodes;
    }


    /**
     * 校验If 或Switch 组件, 如果IF组件中是否相反时调整一下
     *
     * @param propertyNodeType 属性节点类型
     * @param edgeEntities     边缘实体
     */
    private void validateIfOrSwitchNode(String propertyNodeType, List<LogicFlowEdgeRequest> edgeEntities) {
        if (CollectionUtils.isEmpty(edgeEntities)) {
            throw new ServiceException(99999,"判断组件IF或选择组件SWITCH的子节点必须大于等于1");
        }
        if (ElNode.ElNameEnum.IF.name().equals(propertyNodeType)) {
            if (edgeEntities.size() > 2) {
                throw new ServiceException(99999,"判断组件IF 的子节点必须小于等于2");
            }
            if (edgeEntities.size() == 1) {
                String txt = Optional.of(edgeEntities.get(0))
                        .map(LogicFlowEdgeRequest::getText)
                        .map(LogicFlowEdgeRequest.Text::getValue)
                        .orElse("");
                if (textIsFalse(txt)) {
                    throw new ServiceException(99999,"判断组件IF 的只有一个子节点时，不能为 ‘否’ ");
                }
            }
            if (edgeEntities.size() == 2) {
                String aText = Optional.of(edgeEntities.get(0))
                        .map(LogicFlowEdgeRequest::getText)
                        .map(LogicFlowEdgeRequest.Text::getValue)
                        .orElse("");
                String bText = Optional.of(edgeEntities.get(1))
                        .map(LogicFlowEdgeRequest::getText)
                        .map(LogicFlowEdgeRequest.Text::getValue)
                        .orElse("");

                boolean b = (this.textIsTrue(aText) && this.textIsTrue(bText)) ||
                        (this.textIsFalse(aText) && this.textIsFalse(bText));

                if (b) {
                    throw new ServiceException(99999,"判断组件IF 的两个子节点，不能同时为 ‘是’ 或 ‘否’ ");
                }
                if (this.textIsTrue(bText) || this.textIsFalse(aText)) {
                    // 交换两个元素的位置
                    Collections.swap(edgeEntities, 0, 1);
                }
            }
        }
    }

    /**
     * 判断文本是错误
     *
     * @param text 文本
     * @return boolean
     */
    private boolean textIsFalse(String text) {
        List<String> strings = Arrays.asList("否", "false", "False", "FALSE");
        return strings.contains(text);
    }

    /**
     * 判断文本是正确
     *
     * @param text 文本
     * @return boolean
     */
    private boolean textIsTrue(String text) {
        List<String> strings = Arrays.asList("是", "true", "True", "TRUE");
        return strings.contains(text);
    }

    /**
     * 找when的外部的第一个节点
     *
     * @param enterWhenSourceId    当输入源id
     * @param sourceNodeToEdgesMap 源节点来边缘Map
     * @param whenChildIds         when中所有孩子节点
     * @return {@link String}
     */
    private String doFindOutSideWhenNode(String enterWhenSourceId,
                                         Map<String, List<LogicFlowEdgeRequest>> sourceNodeToEdgesMap,
                                         Set<String> whenChildIds) {
        List<LogicFlowEdgeRequest> edgeEntities = sourceNodeToEdgesMap.get(enterWhenSourceId);
        //没边
        if (CollectionUtils.isEmpty(edgeEntities)) {
            return null;
        }
        for (LogicFlowEdgeRequest edge : edgeEntities) {
            String targetNodeId = edge.getTargetNodeId();
            //不在框框中，就返回
            if (!whenChildIds.contains(targetNodeId)) {
                return targetNodeId;
            } else {
                String s = doFindOutSideWhenNode(targetNodeId, sourceNodeToEdgesMap, whenChildIds);
                if (s == null) {
                    continue;
                } else {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * init el节点
     *
     * @param lfNode Lf节点
     * @return {@link ElNode}
     */
    private ElNode initElNode(LogicFlowNodeRequest lfNode) {
        if (lfNode == null) {
            return null;
        }
        String nodeId = getLfNodeProperty(lfNode, LogicFlowNodePropEnum.nodeId.name());
        String nodeTag = getLfNodeProperty(lfNode, LogicFlowNodePropEnum.nodeTag.name());
        String nodeData = getLfNodeProperty(lfNode, LogicFlowNodePropEnum.nodeData.name());
        String nodeType = getLfNodeProperty(lfNode, LogicFlowNodePropEnum.nodeType.name());
        String conditionNodeId = getLfNodeProperty(lfNode, LogicFlowNodePropEnum.conditionNodeId.name());

        ElNode node = new ElNode();
        node.setNodeId(nodeId);
        node.setTag(nodeTag);
        node.setData(nodeData);
        node.setName(nodeType);
        node.setConditionNodeId(conditionNodeId);
        if (ElNode.ElNameEnum.COMMON.name().equals(nodeType)) {
            node.setType(ElNode.ElTypeEnum.idType.name());
        } else {
            node.setType(ElNode.ElTypeEnum.elType.name());
        }
        return node;
    }


    /**
     * 获取Lf节点属性
     *
     * @param nodeMap      节点Map
     * @param propertyName 属性名字
     * @return {@link String}
     */
    private String getLfNodeProperty(LogicFlowNodeRequest nodeMap, String propertyName) {
        return Optional.ofNullable(nodeMap)
                .map(LogicFlowNodeRequest::getProperties)
                .map(e -> e.get(propertyName))
                .map(Object::toString)
                .orElse("");
    }


    /**
     * 提取Lf节点的根节点id
     *
     * @param edges 边缘
     * @return {@link String}
     */
    private String extractInitialNode(List<LogicFlowEdgeRequest> edges) {

        //获取初始化节点
        Set<String> targetNodeIds = edges.stream().map(LogicFlowEdgeRequest::getTargetNodeId).collect(Collectors.toSet());
        Set<String> sourceNodeIds = edges.stream().map(LogicFlowEdgeRequest::getSourceNodeId).collect(Collectors.toSet());
        if (sourceNodeIds.size() - targetNodeIds.size() > 1) {
            throw new ServiceException(99999,"只能有一个开始节点");
        }
        for (String sourceNodeId : sourceNodeIds) {
            if (!targetNodeIds.contains(sourceNodeId)) {
                return sourceNodeId;
            }
        }
        return null;
    }
}
