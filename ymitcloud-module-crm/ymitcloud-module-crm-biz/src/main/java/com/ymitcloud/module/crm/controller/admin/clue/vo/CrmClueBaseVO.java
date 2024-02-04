package com.ymitcloud.module.crm.controller.admin.clue.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.validation.Mobile;
import com.ymitcloud.framework.common.validation.Telephone;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 线索 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CrmClueBaseVO {
    /**
     * 线索名称
     */
    @NotEmpty(message = "线索名称不能为空")
    private String name;
    /**
     * 客户 id
     */
    @NotNull(message = "客户不能为空")
    private Long customerId;
    /**
     * 下次联系时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime contactNextTime;
    /**
     * 电话
     */
    @Telephone
    private String telephone;
    /**
     * 手机号
     */
    @Mobile
    private String mobile;
    /**
     * 地址
     */
    private String address;
    /**
     * 最后跟进时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime contactLastTime;
    /**
     * 备注
     */

    private String remark;

}
