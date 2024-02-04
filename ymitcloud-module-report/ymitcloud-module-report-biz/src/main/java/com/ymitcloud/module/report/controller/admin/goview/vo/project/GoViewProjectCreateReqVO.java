package com.ymitcloud.module.report.controller.admin.goview.vo.project;

import lombok.*;


import jakarta.validation.constraints.*;

/** 管理后台 - GoView 项目创建 Request VO */
@Data
public class GoViewProjectCreateReqVO {

    /** 项目名称*/

    @NotEmpty(message = "项目名称不能为空")
    private String name;

}
