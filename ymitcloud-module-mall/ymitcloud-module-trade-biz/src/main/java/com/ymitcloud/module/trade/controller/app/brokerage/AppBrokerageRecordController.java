package com.ymitcloud.module.trade.controller.app.brokerage;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.web.core.util.WebFrameworkUtils.getLoginUserId;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.record.AppBrokerageProductPriceRespVO;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.record.AppBrokerageRecordPageReqVO;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.record.AppBrokerageRecordRespVO;
import com.ymitcloud.module.trade.convert.brokerage.BrokerageRecordConvert;
import com.ymitcloud.module.trade.dal.dataobject.brokerage.BrokerageRecordDO;
import com.ymitcloud.module.trade.service.brokerage.BrokerageRecordService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 APP - 分销用户
 */

@RestController
@RequestMapping("/trade/brokerage-record")
@Validated
@Slf4j
public class AppBrokerageRecordController {
    @Resource
    private BrokerageRecordService brokerageRecordService;


    /**
     * 获得分销记录分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthenticated
    public CommonResult<PageResult<AppBrokerageRecordRespVO>> getBrokerageRecordPage(
            @Valid AppBrokerageRecordPageReqVO pageReqVO) {
        PageResult<BrokerageRecordDO> pageResult = brokerageRecordService
                .getBrokerageRecordPage(BrokerageRecordConvert.INSTANCE.convert(pageReqVO, getLoginUserId()));
        return success(BrokerageRecordConvert.INSTANCE.convertPage02(pageResult));
    }

    /**
     * 获得商品的分销金额
     * 
     * @param spuId
     * @return
     */
    @GetMapping("/get-product-brokerage-price")

    public CommonResult<AppBrokerageProductPriceRespVO> getProductBrokeragePrice(@RequestParam("spuId") Long spuId) {
        return success(brokerageRecordService.calculateProductBrokeragePrice(getLoginUserId(), spuId));
    }

}
