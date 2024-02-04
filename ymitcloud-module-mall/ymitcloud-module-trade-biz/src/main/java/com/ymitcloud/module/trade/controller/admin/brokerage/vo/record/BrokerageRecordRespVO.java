package com.ymitcloud.module.trade.controller.admin.brokerage.vo.record;



import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 管理后台 - 佣金记录 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BrokerageRecordRespVO extends BrokerageRecordBaseVO {


    /** 编号*/
    private Integer id;

    /** 创建时间*/

    private LocalDateTime createTime;


    // ========== 用户信息 ==========


    /** 用户头像*/
    private String userAvatar;
    /** 用户昵称*/

    private String userNickname;


    // ========== 来源用户信息 ==========


    /** 来源用户头像*/
    private String sourceUserAvatar;
    /** 来源用户昵称 */

    private String sourceUserNickname;
}
