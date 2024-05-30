package com.ymit.module.system.controller.admin.dict.vo.data;

import com.ymit.framework.common.enums.CommonStatusEnum;
import com.ymit.framework.common.validation.InEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 管理后台 - 字典数据创建/修改 Request VO
 */
@Data
public class DictDataSaveReqVO {
    /**
     * 字典数据编号
     */
    private Long id;
    /**
     * 显示顺序不能为空
     */
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;
    /**
     * 字典标签
     */
    @NotBlank(message = "字典标签不能为空")
    @Size(max = 100, message = "字典标签长度不能超过100个字符")
    private String label;
    /**
     * 字典值
     */
    @NotBlank(message = "字典键值不能为空")
    @Size(max = 100, message = "字典键值长度不能超过100个字符")
    private String value;
    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @Size(max = 100, message = "字典类型长度不能超过100个字符")
    private String dictType;
    /**
     * 状态,见 CommonStatusEnum 枚举
     */
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;
    /**
     * 颜色类型,default、primary、success、info、warning、danger
     */
    private String colorType;

    /**
     * css 样式
     */
    private String cssClass;
    /**
     * 备注
     */
    private String remark;

}
