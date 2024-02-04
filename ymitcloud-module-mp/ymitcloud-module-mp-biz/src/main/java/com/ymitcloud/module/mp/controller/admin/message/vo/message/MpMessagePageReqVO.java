package com.ymitcloud.module.mp.controller.admin.message.vo.message;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/** 管理后台 - 公众号消息分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MpMessagePageReqVO extends PageParam {


    /** 公众号账号的编号 */
    @NotNull(message = "公众号账号的编号不能为空")
    private Long accountId;

    /** 消息类型 参见 WxConsts.XmlMsgType 枚举 */
    private String type;

    /** 公众号粉丝标识 */
    private String openid;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    /**
     * 创建时间
     */

    private LocalDateTime[] createTime;

}
