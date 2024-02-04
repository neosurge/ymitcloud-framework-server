package com.ymitcloud.module.mp.controller.admin.statistics.vo;




import lombok.Data;

import java.time.LocalDateTime;


/** 管理后台 - 某一天的粉丝增减数据 */
@Data
public class MpStatisticsUserSummaryRespVO {

    /** 日期*/
    private LocalDateTime refDate;

    /** 粉丝来源*/
    private Integer userSource;

    /** 新关注的粉丝数量*/
    private Integer newUser;

    /** 取消关注的粉丝数量*/

    private Integer cancelUser;

}
