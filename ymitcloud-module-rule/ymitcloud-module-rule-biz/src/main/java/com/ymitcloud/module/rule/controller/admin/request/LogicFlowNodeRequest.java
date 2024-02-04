package com.ymitcloud.module.rule.controller.admin.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * 节点描述信息
 */
@Getter
@Setter
public class LogicFlowNodeRequest implements Serializable {
    /**
     * 节点 id
     */
    private String id;
    /**
     * 节点类型
     */
    private String type;
    /**
     * 节点中心 x 轴坐标
     */
    private Integer x;
    /**
     * 节点中心 y 轴坐标
     */
    private Integer y;
    /**
     * 节点文本
     */
    private Text text;
    /**
     * 节点中心 y 轴坐标
     */
    private Map<String, Object> properties;
    /**
     * 子节点id列表，只有组节点才有
     */
    private List<String> children;

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
