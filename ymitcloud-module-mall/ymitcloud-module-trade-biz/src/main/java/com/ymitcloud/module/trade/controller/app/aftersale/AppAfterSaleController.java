package com.ymitcloud.module.trade.controller.app.aftersale;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.trade.controller.app.aftersale.vo.AppAfterSaleCreateReqVO;
import com.ymitcloud.module.trade.controller.app.aftersale.vo.AppAfterSaleDeliveryReqVO;
import com.ymitcloud.module.trade.controller.app.aftersale.vo.AppAfterSaleRespVO;
import com.ymitcloud.module.trade.convert.aftersale.AfterSaleConvert;
import com.ymitcloud.module.trade.service.aftersale.AfterSaleService;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 App - 交易售后
 */

@RestController
@RequestMapping("/trade/after-sale")
@Validated
@Slf4j
public class AppAfterSaleController {

    @Resource
    private AfterSaleService afterSaleService;


    /**
     * 获得售后分页
     * 
     * @param pageParam
     * @return
     */
    @GetMapping(value = "/page")
    public CommonResult<PageResult<AppAfterSaleRespVO>> getAfterSalePage(PageParam pageParam) {
        return success(AfterSaleConvert.INSTANCE
                .convertPage02(afterSaleService.getAfterSalePage(getLoginUserId(), pageParam)));
    }

    /**
     * 获得售后订单
     * 
     * @param id 售后编号
     * @return
     */
    @GetMapping(value = "/get")

    public CommonResult<AppAfterSaleRespVO> getAfterSale(@RequestParam("id") Long id) {
        return success(AfterSaleConvert.INSTANCE.convert(afterSaleService.getAfterSale(getLoginUserId(), id)));
    }


    /**
     * 获得进行中的售后订单数量
     * 
     * @return
     */
    @GetMapping(value = "/get-applying-count")

    public CommonResult<Long> getApplyingAfterSaleCount() {
        return success(afterSaleService.getApplyingAfterSaleCount(getLoginUserId()));
    }


    /**
     * 申请售后
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping(value = "/create")

    public CommonResult<Long> createAfterSale(@RequestBody AppAfterSaleCreateReqVO createReqVO) {
        return success(afterSaleService.createAfterSale(getLoginUserId(), createReqVO));
    }


    /**
     * 退回货物
     * 
     * @param deliveryReqVO
     * @return
     */
    @PutMapping(value = "/delivery")

    public CommonResult<Boolean> deliveryAfterSale(@RequestBody AppAfterSaleDeliveryReqVO deliveryReqVO) {
        afterSaleService.deliveryAfterSale(getLoginUserId(), deliveryReqVO);
        return success(true);
    }


    /**
     * 取消售后
     * 
     * @param id 售后编号
     * @return
     */
    @DeleteMapping(value = "/cancel")

    public CommonResult<Boolean> cancelAfterSale(@RequestParam("id") Long id) {
        afterSaleService.cancelAfterSale(getLoginUserId(), id);
        return success(true);
    }

}
