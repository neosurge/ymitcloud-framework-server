package com.ymitcloud.module.trade.controller.admin.brokerage.vo.user;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/**
 * 管理后台 - 分销用户分页 Request VO=
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BrokerageUserPageReqVO extends PageParam {


    /** 推广员编号 */
    private Long bindUserId;

    /** 推广资格 */
    private Boolean brokerageEnabled;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    /** 用户等级 */ // 注意，这了不是用户的会员等级，而是过滤推广的层级
    private Integer level;

    /**
     * 绑定时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bindUserTime;

}
