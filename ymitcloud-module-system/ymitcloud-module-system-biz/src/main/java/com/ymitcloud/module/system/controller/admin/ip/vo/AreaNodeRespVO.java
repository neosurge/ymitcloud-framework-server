package com.ymitcloud.module.system.controller.admin.ip.vo;

import java.util.List;

import lombok.Data;

/**
 * 管理后台 - 地区节点 Response VO
 */
@Data
public class AreaNodeRespVO {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 子节点
     */
    private List<AreaNodeRespVO> children;

}
