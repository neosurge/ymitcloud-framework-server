package com.ymit.module.system.controller.admin.area.vo;


import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.List;

/**
 * 管理后台 - 行政区划 Response VO
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ExcelIgnoreUnannotated
public class AreaRespTreeVO extends AreaRespVO {

    @Serial
    private static final long serialVersionUID = -1864423054683837674L;
    /***
     * 父节点名称
     */
    private String parentName;
    /**
     * 父节点
     */
    @JsonIgnore
    private AreaRespSimpleVO parent;
    /**
     * 父节点名称集合
     */
    private String parentNames;
    /**
     * 父节点集合
     */
    @JsonIgnore
    private List<AreaRespSimpleVO> parents;
    /**
     * 子集节点,仅只是backfill时此字段有值
     */
    private List<AreaRespTreeVO> children;
}