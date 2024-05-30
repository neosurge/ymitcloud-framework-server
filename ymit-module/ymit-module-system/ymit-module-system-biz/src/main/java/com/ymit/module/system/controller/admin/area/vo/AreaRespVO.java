package com.ymit.module.system.controller.admin.area.vo;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理后台 - 行政区划 Response VO
 */

@Data
@ExcelIgnoreUnannotated
public class AreaRespVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6926673550588363571L;

    @ExcelIgnore
    private final String selfKind = "area";

    /**
     * 主键ID
     */
    @ExcelProperty("主键ID")
    private Long id;

    /**
     * 区域编码
     */
    @ExcelProperty("区域编码")
    private String code;

    /**
     * 区域名称
     */
    @ExcelProperty("区域名称")
    private String name;

    /**
     * 区域等级：1 : 省/直辖市、2 : 市、3 : 区/县、4 : 乡镇/街道、5 : 社区/村委会
     */
    @ExcelProperty(value = "区域等级：1 : 省/直辖市、2 : 市、3 : 区/县、4 : 乡镇/街道、5 : 社区/村委会", converter = DictConvert.class)
    @DictFormat("system_area_level")
    private Integer level;

    /**
     * 父级区域编码
     */
    @ExcelProperty("父级区域编码")
    private String parentCode;

    /**
     * 父级区域ID
     */
    @ExcelProperty("父级区域ID")
    private Long parentId;

    /**
     * 层级
     */
    @ExcelProperty("层级")
    private Integer layer;

    /**
     * 层级包含的id集合
     */
    @ExcelProperty("层级包含的id集合")
    private String layerList;

    /**
     * 子节点数量
     */
    @ExcelProperty("子节点数量")
    private Integer childrenCount;

    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

}