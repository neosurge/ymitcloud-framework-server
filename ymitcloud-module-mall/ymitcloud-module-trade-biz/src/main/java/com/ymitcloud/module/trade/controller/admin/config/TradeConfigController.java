package com.ymitcloud.module.trade.controller.admin.config;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.trade.controller.admin.config.vo.TradeConfigRespVO;
import com.ymitcloud.module.trade.controller.admin.config.vo.TradeConfigSaveReqVO;
import com.ymitcloud.module.trade.convert.config.TradeConfigConvert;
import com.ymitcloud.module.trade.dal.dataobject.config.TradeConfigDO;
import com.ymitcloud.module.trade.service.config.TradeConfigService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - 交易中心配置
 */

@RestController
@RequestMapping("/trade/config")
@Validated
public class TradeConfigController {

    @Resource
    private TradeConfigService tradeConfigService;


    @Value("${ymitcloud.tencent-lbs-key}")
    private String tencentLbsKey;

    /**
     * 更新交易中心配置
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/save")

    @PreAuthorize("@ss.hasPermission('trade:config:save')")
    public CommonResult<Boolean> updateConfig(@Valid @RequestBody TradeConfigSaveReqVO updateReqVO) {
        tradeConfigService.saveTradeConfig(updateReqVO);
        return success(true);
    }


    /**
     * 获得交易中心配置
     * 
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('trade:config:query')")
    public CommonResult<TradeConfigRespVO> getConfig() {
        TradeConfigDO config = tradeConfigService.getTradeConfig();
        TradeConfigRespVO configVO = TradeConfigConvert.INSTANCE.convert(config);
        if (configVO != null) {
            configVO.setTencentLbsKey(tencentLbsKey);
        }
        return success(configVO);
    }

}
