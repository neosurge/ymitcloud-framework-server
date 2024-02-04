package com.ymitcloud.module.crm.controller.admin.contact.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;
import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import com.ymitcloud.framework.common.validation.Mobile;
import com.ymitcloud.framework.common.validation.Telephone;
import com.ymitcloud.framework.excel.core.annotations.DictFormat;
import com.ymitcloud.framework.excel.core.convert.DictConvert;
import com.ymitcloud.module.infra.enums.DictTypeConstants;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// TODO zyna：要不按照新的，干掉这个 basevo，都放子类里
/**
 * CRM 联系人 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 */
@Data
@ExcelIgnoreUnannotated
public class CrmContactBaseVO {

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名", order = 1)
    @NotNull(message = "姓名不能为空")
    private String name;
    /**
     * 客户编号
     */
    private Long customerId;
    /**
     * 性别
     */
    @ExcelProperty(value = "性别", converter = DictConvert.class, order = 3)
    @DictFormat(com.ymitcloud.module.system.enums.DictTypeConstants.USER_SEX)
    private Integer sex;
    /**
     * 职位
     */
    @ExcelProperty(value = "职位", order = 3)
    private String post;

    /**
     * 是否关键决策人
     */
    @ExcelProperty(value = "是否关键决策人", converter = DictConvert.class, order = 3)
    @DictFormat(DictTypeConstants.BOOLEAN_STRING)
    private Boolean master;
    /**
     * 直属上级
     */
    private Long parentId;
    /**
     * 手机号
     */
    @Mobile
    @ExcelProperty(value = "手机号", order = 4)
    private String mobile;
    /**
     * 座机
     */
    @Telephone
    @ExcelProperty(value = "座机", order = 4)
    private String telephone;

    /**
     * QQ
     */
    @ExcelProperty(value = "QQ", order = 4)
    private Long qq;

    /**
     * 微信
     */
    @ExcelProperty(value = "微信", order = 4)
    private String wechat;

    /**
     * 电子邮箱
     */
    @Email
    @ExcelProperty(value = "邮箱", order = 4)
    private String email;

    /**
     * 地址
     */
    @ExcelProperty(value = "地址", order = 5)
    private String address;

    /**
     * 下次联系时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    @ExcelProperty(value = "下次联系时间", order = 6)
    private LocalDateTime nextTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注", order = 6)
    private String remark;

    /**
     * 最后跟进时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ExcelProperty(value = "最后跟进时间", order = 6)
    private LocalDateTime lastTime;

    /**
     * 负责人用户编号
     */
    @NotNull(message = "负责人不能为空")
    private Long ownerUserId;

    /**
     * 地区编号
     */

    private Integer areaId;

}
