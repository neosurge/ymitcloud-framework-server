package com.ymit.module.system.controller.admin.area.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AreaRespSimpleVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4231545512188324034L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 区域名称
     */
    private String name;
    /**
     * 区域编码
     */
    private String code;
    /**
     * 父级区域ID
     */
    private Long parentId;
    /**
     * 层级
     */
    private Integer layer;
    /**
     * 层级包含的id节点
     */
    private String layerList;
    /**
     * 子节点数量
     */
    private Integer childrenCount;

}
