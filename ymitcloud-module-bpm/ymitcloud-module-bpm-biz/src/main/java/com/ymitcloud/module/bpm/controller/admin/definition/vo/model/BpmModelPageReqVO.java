package com.ymitcloud.module.bpm.controller.admin.definition.vo.model;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 流程模型分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmModelPageReqVO extends PageParam {


    /**
     * 标识-精准匹配
     */
    private String key;
    /**
     * 名字-模糊匹配
     */
    private String name;
    /**
     * 流程分类-参见 bpm_model_category 数据字典
     */

    private String category;

}
