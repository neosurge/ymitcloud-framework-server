package com.ymitcloud.module.bpm.controller.admin.definition.vo.form;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 动态表单分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmFormPageReqVO extends PageParam {

    /**
     * 表单名称
     */

    private String name;

}
