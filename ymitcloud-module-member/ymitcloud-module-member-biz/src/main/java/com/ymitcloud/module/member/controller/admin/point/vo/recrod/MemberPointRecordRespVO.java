package com.ymitcloud.module.member.controller.admin.point.vo.recrod;

import lombok.Data;

import java.time.LocalDateTime;

/** 管理后台 - 用户积分记录 */
@Data
public class MemberPointRecordRespVO {

    /** 自增主键*/
    private Long id;

    /** 用户编号*/
    private Long userId;

    /** 
     * 昵称
     */
    private String nickname;

    /** 业务编码*/
    private String bizId;

    /** 业务类型*/
    private Integer bizType;

    /** 积分标题*/
    private String title;

    /**
     *  积分描述
     */
    private String description;

    /** 积分*/
    private Integer point;

    /** 变动后的积分*/
    private Integer totalPoint;

    /** 发生时间*/
    private LocalDateTime createTime;

}
