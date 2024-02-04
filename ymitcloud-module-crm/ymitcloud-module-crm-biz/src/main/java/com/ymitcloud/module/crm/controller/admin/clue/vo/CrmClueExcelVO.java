package com.ymitcloud.module.crm.controller.admin.clue.vo;

import com.ymitcloud.module.infra.enums.DictTypeConstants;
import lombok.*;

import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ymitcloud.framework.excel.core.annotations.DictFormat;
import com.ymitcloud.framework.excel.core.convert.DictConvert;

/**
 * 线索 Excel VO
 *
 * @author Wanwan
 */
@Data
public class CrmClueExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty(value = "转化状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.BOOLEAN_STRING)
    private Boolean transformStatus;

    @ExcelProperty(value = "跟进状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.BOOLEAN_STRING)
    private Boolean followUpStatus;

    @ExcelProperty("线索名称")
    private String name;

    // TODO 这里需要导出成客户名称
    @ExcelProperty("客户id")
    private Long customerId;

    @ExcelProperty("下次联系时间")
    private LocalDateTime contactNextTime;

    @ExcelProperty("电话")
    private String telephone;

    @ExcelProperty("手机号")
    private String mobile;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("负责人的用户编号")
    private Long ownerUserId;

    @ExcelProperty("最后跟进时间")
    private LocalDateTime contactLastTime;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
