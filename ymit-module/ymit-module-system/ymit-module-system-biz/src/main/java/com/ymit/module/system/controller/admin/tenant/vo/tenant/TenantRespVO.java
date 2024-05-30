package com.ymit.module.system.controller.admin.tenant.vo.tenant;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.system.enums.DictTypeConstants;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 租户 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class TenantRespVO {
    /**
     * 租户编号
     */
    @ExcelProperty("租户编号")
    private Long id;
    /**
     * 租户名
     */
    @ExcelProperty("租户名")
    private String name;
    /**
     * 联系人
     */
    @ExcelProperty("联系人")
    private String contactName;
    /**
     * 联系手机
     */
    @ExcelProperty("联系手机")
    private String contactMobile;
    /**
     * 租户状态
     */
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;
    /**
     * 绑定域名
     */
    private String website;
    /**
     * 租户套餐编号
     */
    private Long packageId;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
    /**
     * 账号数量
     */
    private Integer accountCount;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
