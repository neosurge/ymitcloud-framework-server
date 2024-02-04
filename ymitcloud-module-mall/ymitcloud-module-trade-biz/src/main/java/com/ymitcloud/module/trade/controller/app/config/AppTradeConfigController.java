package com.ymitcloud.module.trade.controller.app.config;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.trade.controller.app.config.vo.AppTradeConfigRespVO;
import com.ymitcloud.module.trade.convert.config.TradeConfigConvert;
import com.ymitcloud.module.trade.dal.dataobject.config.TradeConfigDO;
import com.ymitcloud.module.trade.service.config.TradeConfigService;

import cn.hutool.core.util.ObjUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 App - 交易配置
 */

@RestController
@RequestMapping("/trade/config")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AppTradeConfigController {

    @Resource
    private TradeConfigService tradeConfigService;

    @Value("${ymitcloud.tencent-lbs-key}")
    private String tencentLbsKey;


    /**
     * 获得交易配置
     * 
     * @return
     */
    @GetMapping("/get")

    public CommonResult<AppTradeConfigRespVO> getTradeConfig() {
        TradeConfigDO config = ObjUtil.defaultIfNull(tradeConfigService.getTradeConfig(), new TradeConfigDO());
        return success(TradeConfigConvert.INSTANCE.convert02(config).setTencentLbsKey(tencentLbsKey));
    }

}
