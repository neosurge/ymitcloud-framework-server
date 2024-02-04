package com.ymitcloud.module.trade.controller.admin.delivery;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.Collection;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.pickup.DeliveryPickUpStoreCreateReqVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.pickup.DeliveryPickUpStorePageReqVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.pickup.DeliveryPickUpStoreRespVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.pickup.DeliveryPickUpStoreSimpleRespVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.pickup.DeliveryPickUpStoreUpdateReqVO;
import com.ymitcloud.module.trade.convert.delivery.DeliveryPickUpStoreConvert;
import com.ymitcloud.module.trade.dal.dataobject.delivery.DeliveryPickUpStoreDO;
import com.ymitcloud.module.trade.service.delivery.DeliveryPickUpStoreService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 自提门店
 */

@RestController
@RequestMapping("/trade/delivery/pick-up-store")
@Validated
public class DeliveryPickUpStoreController {

    @Resource
    private DeliveryPickUpStoreService deliveryPickUpStoreService;


    /**
     * 创建自提门店
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('trade:delivery:pick-up-store:create')")
    public CommonResult<Long> createDeliveryPickUpStore(
            @Valid @RequestBody DeliveryPickUpStoreCreateReqVO createReqVO) {
        return success(deliveryPickUpStoreService.createDeliveryPickUpStore(createReqVO));
    }

    /**
     * 更新自提门店
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('trade:delivery:pick-up-store:update')")
    public CommonResult<Boolean> updateDeliveryPickUpStore(
            @Valid @RequestBody DeliveryPickUpStoreUpdateReqVO updateReqVO) {

        deliveryPickUpStoreService.updateDeliveryPickUpStore(updateReqVO);
        return success(true);
    }


    /**
     * 删除自提门店
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('trade:delivery:pick-up-store:delete')")
    public CommonResult<Boolean> deleteDeliveryPickUpStore(@RequestParam("id") Long id) {
        deliveryPickUpStoreService.deleteDeliveryPickUpStore(id);
        return success(true);
    }


    /**
     * 获得自提门店
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('trade:delivery:pick-up-store:query')")
    public CommonResult<DeliveryPickUpStoreRespVO> getDeliveryPickUpStore(@RequestParam("id") Long id) {
        DeliveryPickUpStoreDO deliveryPickUpStore = deliveryPickUpStoreService.getDeliveryPickUpStore(id);
        return success(DeliveryPickUpStoreConvert.INSTANCE.convert(deliveryPickUpStore));
    }


    /**
     * 获得自提门店精简信息列表
     * 
     * @return
     */
    @GetMapping("/list-all-simple")
    public CommonResult<List<DeliveryPickUpStoreSimpleRespVO>> getSimpleDeliveryPickUpStoreList() {
        List<DeliveryPickUpStoreDO> list = deliveryPickUpStoreService
                .getDeliveryPickUpStoreListByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(DeliveryPickUpStoreConvert.INSTANCE.convertList1(list));
    }

    /**
     * 获得自提门店列表
     * 
     * @param ids 编号列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('trade:delivery:pick-up-store:query')")
    public CommonResult<List<DeliveryPickUpStoreRespVO>> getDeliveryPickUpStoreList(
            @RequestParam("ids") Collection<Long> ids) {

        List<DeliveryPickUpStoreDO> list = deliveryPickUpStoreService.getDeliveryPickUpStoreList(ids);
        return success(DeliveryPickUpStoreConvert.INSTANCE.convertList(list));
    }


    /**
     * 获得自提门店分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('trade:delivery:pick-up-store:query')")
    public CommonResult<PageResult<DeliveryPickUpStoreRespVO>> getDeliveryPickUpStorePage(
            @Valid DeliveryPickUpStorePageReqVO pageVO) {

        PageResult<DeliveryPickUpStoreDO> pageResult = deliveryPickUpStoreService.getDeliveryPickUpStorePage(pageVO);
        return success(DeliveryPickUpStoreConvert.INSTANCE.convertPage(pageResult));
    }

}
