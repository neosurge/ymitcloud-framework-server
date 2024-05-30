package com.ymit.module.system.controller.admin.dept.vo.post;

import com.ymit.framework.common.data.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理后台 - 岗位分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PostPageReqVO extends PageParam {
    /**
     * 岗位编码，模糊匹配
     */
    private String code;
    /**
     * 岗位名称，模糊匹配
     */
    private String name;
    /**
     * 展示状态，参见 CommonStatusEnum 枚举类
     */
    private Integer status;

}
