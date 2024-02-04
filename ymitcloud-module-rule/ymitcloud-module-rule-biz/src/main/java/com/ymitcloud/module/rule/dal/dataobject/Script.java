package com.ymitcloud.module.rule.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName( "lf_script")
@Getter
@Setter
public class Script extends BaseModel {
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 脚本id,脚本的唯一标识，但与数据记录id不同
     */
    private String scriptId;
    /**
     * 脚本名称
     */
    private String scriptName;
    /**
     * 脚本内容
     */
    private String scriptData;
    /**
     * 脚本类型
     */
    private String scriptType;
    /**
     * 脚本语言（groovy | qlexpress | js | python | lua | aviator | java）
     */
    private String scriptLanguage;

    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * 流程id
     */
    private String processId;
}
