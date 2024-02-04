package com.ymitcloud.module.promotion.controller.admin.seckill.vo.config;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 秒杀时段分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SeckillConfigPageReqVO extends PageParam {


    /**
     * 秒杀时段名称
     */
    private String name;

    /**
     * 状态
     */

    private Integer status;

}
