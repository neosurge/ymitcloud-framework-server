package com.ymitcloud.module.rule.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName(value = "lf_component")
@Getter
@Setter
public class Component extends BaseModel {
    /**
     * 组件名称
     */
    private String componentName;
    /**
     * 组件类型 ( normal |  script )
     */
    private String componentType;

    /**
     * 组件类别
     */
    private String componentCategory;

    /**
     * 组件说明
     */
    private String componentDesc;
}
