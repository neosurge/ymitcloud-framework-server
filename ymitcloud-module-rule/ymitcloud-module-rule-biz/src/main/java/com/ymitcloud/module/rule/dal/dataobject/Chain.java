package com.ymitcloud.module.rule.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName( "lf_chain")
@Getter
@Setter
public class Chain extends BaseModel {
    /**
     * 应用名称
     */
    private String applicationName;

    /**
     *规则链名称
     */
    private  String chainName;

    /**
     * EL表达式内容
     */
    private String elData;

    /**
     * 是否启用
     */
    private Boolean enable;
}
