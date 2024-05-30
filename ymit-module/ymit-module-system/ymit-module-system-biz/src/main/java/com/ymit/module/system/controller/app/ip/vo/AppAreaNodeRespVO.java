package com.ymit.module.system.controller.app.ip.vo;

import lombok.Data;

import java.util.List;

/**
 * 用户 App - 地区节点 Response VO
 */
@Data
public class AppAreaNodeRespVO {

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
    private List<AppAreaNodeRespVO> children;

}
