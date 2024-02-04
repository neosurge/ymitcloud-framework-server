package com.ymitcloud.module.trade.controller.app.delivery;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.trade.controller.app.delivery.vo.pickup.AppDeliveryPickUpStoreRespVO;
import com.ymitcloud.module.trade.convert.delivery.DeliveryPickUpStoreConvert;
import com.ymitcloud.module.trade.dal.dataobject.delivery.DeliveryPickUpStoreDO;
import com.ymitcloud.module.trade.service.delivery.DeliveryPickUpStoreService;


import jakarta.annotation.Resource;

/**
 * 用户 App - 自提门店
 */

@RestController
@RequestMapping("/trade/delivery/pick-up-store")
@Validated
public class AppDeliverPickUpStoreController {

    @Resource
    private DeliveryPickUpStoreService deliveryPickUpStoreService;


    /**
     * 获得自提门店列表
     * 
     * @param latitude  经度
     * @param longitude 纬度
     * @return
     */
    @GetMapping("/list")
    public CommonResult<List<AppDeliveryPickUpStoreRespVO>> getDeliveryPickUpStoreList(
            @RequestParam(value = "latitude", required = false) Double latitude,
            @RequestParam(value = "longitude", required = false) Double longitude) {
        List<DeliveryPickUpStoreDO> list = deliveryPickUpStoreService
                .getDeliveryPickUpStoreListByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(DeliveryPickUpStoreConvert.INSTANCE.convertList(list, latitude, longitude));
    }

    /**
     * 获得自提门店
     * 
     * @param id 门店编号
     * @return
     */

    @GetMapping("/get")

    public CommonResult<AppDeliveryPickUpStoreRespVO> getOrder(@RequestParam("id") Long id) {
        DeliveryPickUpStoreDO store = deliveryPickUpStoreService.getDeliveryPickUpStore(id);
        return success(DeliveryPickUpStoreConvert.INSTANCE.convert03(store));
    }

}
