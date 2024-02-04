package com.ymitcloud.module.crm.controller.admin.customer.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;


import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.framework.common.validation.Mobile;
import com.ymitcloud.framework.common.validation.Telephone;
import com.ymitcloud.module.crm.enums.customer.CrmCustomerLevelEnum;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * 客户 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 */
@Data
public class CrmCustomerBaseVO {


    /**
     * 客户名称
     */
    @NotEmpty(message = "客户名称不能为空")
    private String name;
    /**
     * 所属行业
     */
    private Integer industryId;

    /**
     * 客户等级
     */
    @InEnum(CrmCustomerLevelEnum.class)
    private Integer level;

    /**
     * 客户来源
     */
    private Integer source;

    /**
     * 手机
     */
    @Mobile
    private String mobile;
    /**
     * 电话
     */
    @Telephone
    private String telephone;
    /**
     * 网址
     */
    private String website;
    /**
     * QQ
     */
    @Size(max = 20, message = "QQ长度不能超过 20 个字符")
    private String qq;
    /**
     * wechat
     */
    @Size(max = 255, message = "微信长度不能超过 255 个字符")
    private String wechat;
    /**
     * email
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 255, message = "邮箱长度不能超过 255 个字符")
    private String email;
    /**
     * 客户描述
     */
    @Size(max = 4096, message = "客户描述长度不能超过 4096 个字符")
    private String description;
    /**
     * 备注
     */
    private String remark;
    /**
     * 地区编号
     */
    private Integer areaId;
    /**
     * 详细地址
     */
    private String detailAddress;
    /**
     * 下次联系时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime contactNextTime;

}
