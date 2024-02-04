package com.ymitcloud.module.report.controller.admin.goview.vo.project;




import lombok.*;

import java.time.LocalDateTime;


/** 管理后台 - GoView 项目 */
@Data
public class GoViewProjectRespVO {

    /** 编号*/
    private Long id;

    /** 项目名称*/
    private String name;

    /** 发布状态*/
    private Integer status;

    /** 报表内容") // JSON 格式
    private String content;

    /** 预览图片 URL", example = "https://www.ymitcloud.com")
    private String picUrl;

    /** 项目备注", example = "你猜")
    private String remark;

    /** 创建人编号*/
    private String creator;

    /** 创建时间*/

    private LocalDateTime createTime;

}
