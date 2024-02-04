package com.ymitcloud.module.report.controller.admin.goview.vo.data;




import lombok.Data;

import java.util.List;
import java.util.Map;


/** 管理后台 - GoView 数据 */
@Data
public class GoViewDataRespVO {

    /** 数据维度*/
    private List<String> dimensions;

    /** 数据明细列表*/

    private List<Map<String, Object>> source;

}
