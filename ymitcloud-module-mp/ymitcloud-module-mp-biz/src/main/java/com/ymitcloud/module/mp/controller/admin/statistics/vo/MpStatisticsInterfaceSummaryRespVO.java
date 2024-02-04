package com.ymitcloud.module.mp.controller.admin.statistics.vo;




import lombok.Data;

import java.time.LocalDateTime;


/** 管理后台 - 某一天的接口分析数据 */
@Data
public class MpStatisticsInterfaceSummaryRespVO {

    /** 日期*/
    private LocalDateTime refDate;

    /** 通过服务器配置地址获得消息后，被动回复粉丝消息的次数*/
    private Integer callbackCount;

    /** 上述动作的失败次数*/
    private Integer failCount;

    /** 总耗时，除以 callback_count 即为平均耗时*/
    private Integer totalTimeCost;

    /** 最大耗时*/

    private Integer maxTimeCost;

}
