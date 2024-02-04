package com.ymitcloud.module.promotion.controller.admin.combination.vo.recrod;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.promotion.enums.bargain.BargainRecordStatusEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 拼团记录分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CombinationRecordReqPageVO extends PageParam {


    /**
     * 活动状态
     */
    @InEnum(BargainRecordStatusEnum.class)
    private Integer status;

    /**
     * 团长编号
     */
    private Long headId;

    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
