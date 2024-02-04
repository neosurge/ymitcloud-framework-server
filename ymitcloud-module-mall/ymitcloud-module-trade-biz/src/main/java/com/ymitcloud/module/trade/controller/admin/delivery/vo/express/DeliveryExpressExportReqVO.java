package com.ymitcloud.module.trade.controller.admin.delivery.vo.express;



import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/**
 * 管理后台 - 快递公司 Excel 导出 Request VO
 */
@Data
public class DeliveryExpressExportReqVO {

    /**
     * 快递公司编码
     */
    private String code;
    /**
     * 快递公司名称
     */
    private String name;

    /** 状态（0正常 1停用） */
    private Integer status;

    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
