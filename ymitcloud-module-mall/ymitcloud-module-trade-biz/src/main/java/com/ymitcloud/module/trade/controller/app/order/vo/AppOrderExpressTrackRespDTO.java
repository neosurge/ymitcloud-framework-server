package com.ymitcloud.module.trade.controller.app.order.vo;




import lombok.Data;

import java.time.LocalDateTime;

/**
 * 快递查询的轨迹 Resp DTO
 *
 * @author jason
 */

/** 用户 App - 快递查询的轨迹 */
@Data
public class AppOrderExpressTrackRespDTO {

    /** 发生时间*/
    private LocalDateTime time;

    /** 快递状态*/

    private String content;

}
