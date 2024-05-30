package com.ymit.module.system.controller.admin.dict.vo.type;

import lombok.Data;

/**
 * 管理后台 - 字典类型精简信息 Response VO
 */
@Data
public class DictTypeSimpleRespVO {
    /**
     * 字典类型编号
     */
    private Long id;
    /**
     * 字典类型名称
     */
    private String name;
    /**
     * 字典类型
     */
    private String type;

}
