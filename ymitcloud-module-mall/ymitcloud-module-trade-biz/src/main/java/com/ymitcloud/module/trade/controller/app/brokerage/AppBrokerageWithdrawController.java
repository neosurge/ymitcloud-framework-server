package com.ymitcloud.module.trade.controller.app.brokerage;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.web.core.util.WebFrameworkUtils.getLoginUserId;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.withdraw.AppBrokerageWithdrawCreateReqVO;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.withdraw.AppBrokerageWithdrawPageReqVO;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.withdraw.AppBrokerageWithdrawRespVO;
import com.ymitcloud.module.trade.convert.brokerage.BrokerageWithdrawConvert;
import com.ymitcloud.module.trade.dal.dataobject.brokerage.BrokerageWithdrawDO;
import com.ymitcloud.module.trade.service.brokerage.BrokerageWithdrawService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 APP - 分销提现
 */

@RestController
@RequestMapping("/trade/brokerage-withdraw")
@Validated
@Slf4j
public class AppBrokerageWithdrawController {

    @Resource
    private BrokerageWithdrawService brokerageWithdrawService;


    /**
     * 获得分销提现分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthenticated
    public CommonResult<PageResult<AppBrokerageWithdrawRespVO>> getBrokerageWithdrawPage(
            AppBrokerageWithdrawPageReqVO pageReqVO) {
        PageResult<BrokerageWithdrawDO> pageResult = brokerageWithdrawService
                .getBrokerageWithdrawPage(BrokerageWithdrawConvert.INSTANCE.convert(pageReqVO, getLoginUserId()));
        return success(BrokerageWithdrawConvert.INSTANCE.convertPage03(pageResult));
    }

    /**
     * 创建分销提现
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthenticated
    public CommonResult<Long> createBrokerageWithdraw(@RequestBody @Valid AppBrokerageWithdrawCreateReqVO createReqVO) {
        return success(brokerageWithdrawService.createBrokerageWithdraw(getLoginUserId(), createReqVO));
    }

}
