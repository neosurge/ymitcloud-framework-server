package com.ymitcloud.module.rule.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName(value = "lf_component_param")
@Getter
@Setter
public class ComponentParam  extends BaseModel {
    /**
     * 参数名称
     */
    private String paramField;
    /**
     * 参数标签
     */
    private String paramLabel;

    /**
     * 参数说明
     */
    private String paramDesc;

    /**
     * 是否必须
     */
    private Boolean required;

    /**
     * 组件id
     */
    private String componentId;
}
