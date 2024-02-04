package com.ymitcloud.module.statistics.controller.admin.common.vo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 管理后台 - 数据对照
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataComparisonRespVO<T> {


    /** 当前数据 */
    private T value;

    /** 参照数据 */

    private T reference;

}
