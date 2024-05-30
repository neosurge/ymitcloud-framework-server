package com.ymit.module.system.controller.admin.dict.vo.type;

import com.ymit.framework.common.data.PageParam;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymit.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 管理后台 - 字典类型分页列表 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictTypePageReqVO extends PageParam {
    /**
     * 字典类型名称，模糊匹配
     */
    private String name;

    /**
     * 字典类型，模糊匹配
     */
    @Size(max = 100, message = "字典类型类型长度不能超过100个字符")
    private String type;
    /**
     * 展示状态，参见 CommonStatusEnum 枚举类
     */
    private Integer status;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
