package com.ymitcloud.module.rule.controller.admin.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogicFlowEdgeRequest implements Serializable {
    /**
     * 边 id
     */
    private String id;
    /**
     * 边类型
     */
    private String type;
    /**
     * 开始节点 Id
     */
    private String sourceNodeId;
    /**
     * 结束节点 Id
     */
    private String targetNodeId;
    /**
     * 边的开始坐标
     */
    private LogicFlowPoint startPoint;
    /**
     * 边的结束坐标
     */
    private LogicFlowPoint endPoint;
    /**
     * 控制边的轨迹
     */
    private List<LogicFlowPoint> pointsList;
    /**
     * 边的自定义属性
     */
    private Map<String, Object> properties;
    /**
     * 边文本
     */
    private Text text;
    /**
     * 连线起点锚点 id
     */
    private String sourceAnchorId;
    /**
     * 连线终点锚点 id
     */
    private String targetAnchorId;

    @Getter
    @Setter
    public static class Text {
        /**
         * 文本内容
         */
        private String value;
        /**
         * 文本中心 x 轴坐标
         */
        private Integer x;
        /**
         * 文本中心 y 轴坐标
         */
        private Integer y;
    }
}
