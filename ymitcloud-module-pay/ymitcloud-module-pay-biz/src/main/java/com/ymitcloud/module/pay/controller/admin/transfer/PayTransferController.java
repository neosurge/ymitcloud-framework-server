package com.ymitcloud.module.pay.controller.admin.transfer;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.servlet.ServletUtils.getClientIP;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.pay.controller.admin.transfer.vo.PayTransferCreateReqVO;
import com.ymitcloud.module.pay.controller.admin.transfer.vo.PayTransferCreateRespVO;
import com.ymitcloud.module.pay.controller.admin.transfer.vo.PayTransferPageItemRespVO;
import com.ymitcloud.module.pay.controller.admin.transfer.vo.PayTransferPageReqVO;
import com.ymitcloud.module.pay.controller.admin.transfer.vo.PayTransferRespVO;
import com.ymitcloud.module.pay.convert.transfer.PayTransferConvert;
import com.ymitcloud.module.pay.dal.dataobject.transfer.PayTransferDO;
import com.ymitcloud.module.pay.service.transfer.PayTransferService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 转账单
 */

@RestController
@RequestMapping("/pay/transfer")
@Validated
public class PayTransferController {

    @Resource
    private PayTransferService payTransferService;


    /**
     * 创建转账单，发起转账
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('pay:transfer:create')")
    public CommonResult<PayTransferCreateRespVO> createPayTransfer(@Valid @RequestBody PayTransferCreateReqVO reqVO) {
        PayTransferDO payTransfer = payTransferService.createTransfer(reqVO, getClientIP());
        return success(new PayTransferCreateRespVO().setId(payTransfer.getId()).setStatus(payTransfer.getStatus()));
    }


    /**
     * 获得转账订单
     * 
     * @param id
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('pay:transfer:query')")
    public CommonResult<PayTransferRespVO> getTransfer(@RequestParam("id") Long id) {
        return success(PayTransferConvert.INSTANCE.convert(payTransferService.getTransfer(id)));
    }


    /**
     * 获得转账订单分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('pay:transfer:query')")
    public CommonResult<PageResult<PayTransferPageItemRespVO>> getTransferPage(@Valid PayTransferPageReqVO pageVO) {
        PageResult<PayTransferDO> pageResult = payTransferService.getTransferPage(pageVO);
        return success(PayTransferConvert.INSTANCE.convertPage(pageResult));
    }
}
