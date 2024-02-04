package com.ymitcloud.module.report.controller.admin.goview.vo.project;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;



import lombok.*;

import jakarta.validation.constraints.*;


/** 管理后台 - GoView 项目更新 Request VO */
@Data
public class GoViewProjectUpdateReqVO {

    /** 编号 */
    @NotNull(message = "编号不能为空")
    private Long id;

    /** 项目名称 */
    private String name;

    /** 发布状态 */
    @InEnum(value = CommonStatusEnum.class, message = "发布状态必须是 {value}")
    private Integer status;

    /**
     * 报表内容
     */
    private String content;

    /** 预览图片 URL */
    private String picUrl;

    /** 项目备注 */

    private String remark;

}
