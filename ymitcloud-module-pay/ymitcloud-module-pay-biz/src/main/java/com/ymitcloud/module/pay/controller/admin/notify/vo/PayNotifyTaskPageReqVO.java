package com.ymitcloud.module.pay.controller.admin.notify.vo;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/** 管理后台 - 回调通知分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayNotifyTaskPageReqVO extends PageParam {


    /** 应用编号 */
    private Long appId;

    /** 通知类型 */
    private Integer type;

    /** 数据编号 */
    private Long dataId;

    /** 通知状态 */
    private Integer status;

    /** 商户订单编号 */
    private String merchantOrderId;

    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
