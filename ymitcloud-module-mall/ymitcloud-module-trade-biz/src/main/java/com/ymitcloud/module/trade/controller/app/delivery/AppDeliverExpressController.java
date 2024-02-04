package com.ymitcloud.module.trade.controller.app.delivery;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.Comparator;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.trade.controller.app.delivery.vo.express.AppDeliveryExpressRespVO;
import com.ymitcloud.module.trade.convert.delivery.DeliveryExpressConvert;
import com.ymitcloud.module.trade.dal.dataobject.delivery.DeliveryExpressDO;
import com.ymitcloud.module.trade.service.delivery.DeliveryExpressService;


import jakarta.annotation.Resource;

/**
 * 用户 App - 快递公司
 */

@RestController
@RequestMapping("/trade/delivery/express")
@Validated
public class AppDeliverExpressController {

    @Resource
    private DeliveryExpressService deliveryExpressService;


    /**
     * 获得快递公司列表
     * 
     * @return
     */
    @GetMapping("/list")
    public CommonResult<List<AppDeliveryExpressRespVO>> getDeliveryExpressList() {
        List<DeliveryExpressDO> list = deliveryExpressService
                .getDeliveryExpressListByStatus(CommonStatusEnum.ENABLE.getStatus());

        list.sort(Comparator.comparing(DeliveryExpressDO::getSort));
        return success(DeliveryExpressConvert.INSTANCE.convertList03(list));
    }

}
