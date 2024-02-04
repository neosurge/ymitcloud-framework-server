package com.ymitcloud.module.promotion.controller.app.seckill.vo.activity;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 用户 App - 商品评价分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppSeckillActivityPageReqVO extends PageParam {


    /**
     * 秒杀配置编号
     */

    private Long configId;

}
