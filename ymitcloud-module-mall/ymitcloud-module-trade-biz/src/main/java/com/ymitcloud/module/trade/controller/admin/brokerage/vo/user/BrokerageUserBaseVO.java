package com.ymitcloud.module.trade.controller.admin.brokerage.vo.user;




import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 分销用户 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BrokerageUserBaseVO {


    /** 推广员编号*/
    @NotNull(message = "推广员编号不能为空")
    private Long bindUserId;

    /** 推广员绑定时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime bindUserTime;

    /** 推广资格*/
    @NotNull(message = "推广资格不能为空")
    private Boolean brokerageEnabled;

    /** 成为分销员时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime brokerageTime;

    /** 可用佣金*/
    @NotNull(message = "可用佣金不能为空")
    private Integer price;

    /** 冻结佣金*/

    @NotNull(message = "冻结佣金不能为空")
    private Integer frozenPrice;

}
