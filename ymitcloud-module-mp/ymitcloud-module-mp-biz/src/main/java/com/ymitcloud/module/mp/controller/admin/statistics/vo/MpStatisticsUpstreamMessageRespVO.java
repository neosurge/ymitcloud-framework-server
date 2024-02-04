package com.ymitcloud.module.mp.controller.admin.statistics.vo;




import lombok.Data;

import java.time.LocalDateTime;


/** 管理后台 - 某一天的粉丝增减数据 */
@Data
public class MpStatisticsUpstreamMessageRespVO {

    /** 日期*/
    private LocalDateTime refDate;

    /** 上行发送了（向公众号发送了）消息的粉丝数*/
    private Integer messageUser;

    /** 上行发送了消息的消息总数*/

    private Integer messageCount;

}
