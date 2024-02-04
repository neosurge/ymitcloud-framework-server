package com.ymitcloud.module.report.controller.admin.goview.vo.data;




import lombok.Data;

import jakarta.validation.constraints.NotEmpty;


/** 管理后台 - GoView 使用 SQL 查询数据 Request VO */
@Data
public class GoViewDataGetBySqlReqVO {

    /** SQL 语句*/

    @NotEmpty(message = "SQL 语句不能为空")
    private String sql;

}
