package com.ymitcloud.module.member.controller.app.point.vo;




import lombok.Data;

import java.time.LocalDateTime;


/** 用户 App - 用户积分记录 */
@Data
public class AppMemberPointRecordRespVO {

    /** 自增主键*/
    private Long id;;

    /** 积分标题*/
    private String title;

    /** 积分描述", example = "你猜")
    private String description;

    /** 积分*/
    private Integer point;

    /** 发生时间*/

    private LocalDateTime createTime;

}
