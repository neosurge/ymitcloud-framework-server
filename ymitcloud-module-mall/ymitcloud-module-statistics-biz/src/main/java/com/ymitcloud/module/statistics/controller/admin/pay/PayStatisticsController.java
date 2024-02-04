package com.ymitcloud.module.statistics.controller.admin.pay;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.statistics.controller.admin.pay.vo.PaySummaryRespVO;
import com.ymitcloud.module.statistics.convert.pay.PayStatisticsConvert;
import com.ymitcloud.module.statistics.service.pay.PayWalletStatisticsService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
/**
 * 管理后台 - 支付统计
 */

@RestController
@RequestMapping("/statistics/pay")
@Validated
@Slf4j
public class PayStatisticsController {

    @Resource
    private PayWalletStatisticsService payWalletStatisticsService;


    /**
     * 获取充值金额
     * 
     * @return
     */
    @GetMapping("/summary")

    public CommonResult<PaySummaryRespVO> getWalletRechargePrice() {
        Integer rechargePrice = payWalletStatisticsService.getRechargePriceSummary();
        return success(PayStatisticsConvert.INSTANCE.convert(rechargePrice));
    }

}
