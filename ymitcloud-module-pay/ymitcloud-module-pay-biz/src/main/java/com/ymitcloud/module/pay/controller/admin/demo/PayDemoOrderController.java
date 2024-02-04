package com.ymitcloud.module.pay.controller.admin.demo;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.servlet.ServletUtils.getClientIP;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.validation.annotation.Validated;
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
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.pay.api.notify.dto.PayOrderNotifyReqDTO;
import com.ymitcloud.module.pay.api.notify.dto.PayRefundNotifyReqDTO;
import com.ymitcloud.module.pay.controller.admin.demo.vo.order.PayDemoOrderCreateReqVO;
import com.ymitcloud.module.pay.controller.admin.demo.vo.order.PayDemoOrderRespVO;
import com.ymitcloud.module.pay.convert.demo.PayDemoOrderConvert;
import com.ymitcloud.module.pay.dal.dataobject.demo.PayDemoOrderDO;
import com.ymitcloud.module.pay.service.demo.PayDemoOrderService;



import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;


/**
 * 管理后台 - 示例订单
 */

@RestController
@RequestMapping("/pay/demo-order")
@Validated
public class PayDemoOrderController {

    @Resource
    private PayDemoOrderService payDemoOrderService;


    /**
     * 创建示例订单
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    public CommonResult<Long> createDemoOrder(@Valid @RequestBody PayDemoOrderCreateReqVO createReqVO) {
        return success(payDemoOrderService.createDemoOrder(getLoginUserId(), createReqVO));
    }


    /**
     * 获得示例订单分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    public CommonResult<PageResult<PayDemoOrderRespVO>> getDemoOrderPage(@Valid PageParam pageVO) {
        PageResult<PayDemoOrderDO> pageResult = payDemoOrderService.getDemoOrderPage(pageVO);
        return success(PayDemoOrderConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 更新示例订单为已支付
     * 
     * @param notifyReqDTO
     * @return
     */
    @PostMapping("/update-paid")

    @PermitAll // 无需登录，安全由 PayDemoOrderService 内部校验实现
    @OperateLog(enable = false) // 禁用操作日志，因为没有操作人
    public CommonResult<Boolean> updateDemoOrderPaid(@RequestBody PayOrderNotifyReqDTO notifyReqDTO) {
        payDemoOrderService.updateDemoOrderPaid(Long.valueOf(notifyReqDTO.getMerchantOrderId()),
                notifyReqDTO.getPayOrderId());
        return success(true);
    }


    /**
     * 发起示例订单的退款
     * 
     * @param id 编号
     * @return
     */
    @PutMapping("/refund")

    public CommonResult<Boolean> refundDemoOrder(@RequestParam("id") Long id) {
        payDemoOrderService.refundDemoOrder(id, getClientIP());
        return success(true);
    }


    /**
     * 更新示例订单为已退款
     * 
     * @param notifyReqDTO
     * @return
     */
    @PostMapping("/update-refunded")

    @PermitAll // 无需登录，安全由 PayDemoOrderService 内部校验实现
    @OperateLog(enable = false) // 禁用操作日志，因为没有操作人
    public CommonResult<Boolean> updateDemoOrderRefunded(@RequestBody PayRefundNotifyReqDTO notifyReqDTO) {
        payDemoOrderService.updateDemoOrderRefunded(Long.valueOf(notifyReqDTO.getMerchantOrderId()),
                notifyReqDTO.getPayRefundId());
        return success(true);
    }

}
