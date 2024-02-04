package com.ymitcloud.module.system.controller.app.dict.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户 App - 字典数据信息 Response VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppDictDataRespVO {

    /**
     * 字典数据编号
     */
    private Long id;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典值
     */
    private String value;

    /**
     * 字典类型
     */
    private String dictType;

}
