package com.ymitcloud.module.pay.controller.admin.demo;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.pay.api.notify.dto.PayTransferNotifyReqDTO;
import com.ymitcloud.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import com.ymitcloud.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferRespVO;
import com.ymitcloud.module.pay.convert.demo.PayDemoTransferConvert;
import com.ymitcloud.module.pay.dal.dataobject.demo.PayDemoTransferDO;
import com.ymitcloud.module.pay.service.demo.PayDemoTransferService;



import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;


/**
 * 管理后台 - 示例转账单
 */

@RestController
@RequestMapping("/pay/demo-transfer")
@Validated
public class PayDemoTransferController {
    @Resource
    private PayDemoTransferService demoTransferService;


    /**
     * 创建示例转账订单
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    public CommonResult<Long> createDemoTransfer(@Valid @RequestBody PayDemoTransferCreateReqVO createReqVO) {
        return success(demoTransferService.createDemoTransfer(createReqVO));
    }


    /**
     * 获得示例转账订单分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    public CommonResult<PageResult<PayDemoTransferRespVO>> getDemoTransferPage(@Valid PageParam pageVO) {
        PageResult<PayDemoTransferDO> pageResult = demoTransferService.getDemoTransferPage(pageVO);
        return success(PayDemoTransferConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 更新示例转账订单的转账状态
     * 
     * @param notifyReqDTO
     * @return
     */
    @PostMapping("/update-status")

    @PermitAll // 无需登录，安全由 PayDemoTransferService 内部校验实现
    @OperateLog(enable = false) // 禁用操作日志，因为没有操作人
    public CommonResult<Boolean> updateDemoTransferStatus(@RequestBody PayTransferNotifyReqDTO notifyReqDTO) {
        demoTransferService.updateDemoTransferStatus(Long.valueOf(notifyReqDTO.getMerchantTransferId()),
                notifyReqDTO.getPayTransferId());
        return success(true);
    }
}
