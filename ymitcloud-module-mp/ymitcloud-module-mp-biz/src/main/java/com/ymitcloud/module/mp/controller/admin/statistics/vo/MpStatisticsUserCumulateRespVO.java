package com.ymitcloud.module.mp.controller.admin.statistics.vo;




import lombok.Data;

import java.time.LocalDateTime;


/** 管理后台 - 某一天的消息发送概况数据 */
@Data
public class MpStatisticsUserCumulateRespVO {

    /** 日期*/
    private LocalDateTime refDate;

    /** 累计粉丝量*/

    private Integer cumulateUser;

}
