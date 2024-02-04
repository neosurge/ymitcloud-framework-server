package com.ymitcloud.module.trade.controller.admin.aftersale.vo.log;




import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


/** 管理后台 - 交易售后日志 */
@Data
public class AfterSaleLogRespVO {

    /** 编号*/
    private Long id;

    /** 用户编号*/
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /** 用户类型*/
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    /** 售后编号*/
    @NotNull(message = "售后编号不能为空")
    private Long afterSaleId;

    /** 售后状态（之前）", example = "2")
    private Integer beforeStatus;

    /** 售后状态（之后）*/
    @NotNull(message = "售后状态（之后）不能为空")
    private Integer afterStatus;

    /** 操作明细*/
    @NotNull(message = "操作明细不能为空")
    private String content;

    /** 创建时间*/

    private LocalDateTime createTime;

}
