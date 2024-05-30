package com.ymit.module.system.controller.admin.dict.vo.data;

import com.ymit.framework.common.data.PageParam;
import com.ymit.framework.common.enums.CommonStatusEnum;
import com.ymit.framework.common.validation.InEnum;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理后台 - 字典类型分页列表 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictDataPageReqVO extends PageParam {
    /**
     * 字典标签
     */
    @Size(max = 100, message = "字典标签长度不能超过100个字符")
    private String label;
    /**
     * 字典类型，模糊匹配
     */
    @Size(max = 100, message = "字典类型类型长度不能超过100个字符")
    private String dictType;
    /**
     * 展示状态，参见 CommonStatusEnum 枚举类
     */
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
