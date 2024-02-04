package com.ymitcloud.module.system.controller.admin.notice.vo;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 管理后台 - 通知公告信息 Response VO
 */
@Data
public class NoticeRespVO {

    /**
     * 通知公告序号
     */
    private Long id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告类型
     */
    private Integer type;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 状态，参见 CommonStatusEnum 枚举类
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
