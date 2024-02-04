package com.ymitcloud.module.rule.dal.dataobject;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Getter;
import lombok.Setter;

/**
 * 流程
 */
@TableName(value = "lf_process", autoResultMap = true)
@Getter
@Setter
public class Process extends BaseModel {
    /**
     * 流程名称
     */
    private String processName;

    /**
     * 流程数据
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONObject processData;

    /**
     * 流程编排规则链id
     */
    private String chainId;
}
