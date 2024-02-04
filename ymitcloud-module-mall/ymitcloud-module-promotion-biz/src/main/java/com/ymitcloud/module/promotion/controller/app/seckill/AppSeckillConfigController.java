package com.ymitcloud.module.promotion.controller.app.seckill;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.promotion.controller.app.seckill.vo.config.AppSeckillConfigRespVO;
import com.ymitcloud.module.promotion.convert.seckill.seckillconfig.SeckillConfigConvert;
import com.ymitcloud.module.promotion.dal.dataobject.seckill.SeckillConfigDO;
import com.ymitcloud.module.promotion.service.seckill.SeckillConfigService;


import jakarta.annotation.Resource;

/**
 * 用户 App - 秒杀时间段
 */

@RestController
@RequestMapping("/promotion/seckill-config")
@Validated
public class AppSeckillConfigController {
    @Resource
    private SeckillConfigService configService;


    /**
     * 获得秒杀时间段列表
     * 
     * @return
     */
    @GetMapping("/list")

    public CommonResult<List<AppSeckillConfigRespVO>> getSeckillConfigList() {
        List<SeckillConfigDO> list = configService.getSeckillConfigListByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(SeckillConfigConvert.INSTANCE.convertList2(list));
    }

}
