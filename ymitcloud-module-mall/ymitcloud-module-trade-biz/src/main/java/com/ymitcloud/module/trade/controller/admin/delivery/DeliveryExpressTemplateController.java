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

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate.DeliveryExpressTemplateCreateReqVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate.DeliveryExpressTemplateDetailRespVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate.DeliveryExpressTemplatePageReqVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate.DeliveryExpressTemplateRespVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate.DeliveryExpressTemplateSimpleRespVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate.DeliveryExpressTemplateUpdateReqVO;
import com.ymitcloud.module.trade.convert.delivery.DeliveryExpressTemplateConvert;
import com.ymitcloud.module.trade.dal.dataobject.delivery.DeliveryExpressTemplateDO;
import com.ymitcloud.module.trade.service.delivery.DeliveryExpressTemplateService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 快递运费模板
 */

@RestController
@RequestMapping("/trade/delivery/express-template")
@Validated
public class DeliveryExpressTemplateController {

    @Resource
    private DeliveryExpressTemplateService deliveryExpressTemplateService;


    /**
     * 创建快递运费模板
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express-template:create')")
    public CommonResult<Long> createDeliveryExpressTemplate(
            @Valid @RequestBody DeliveryExpressTemplateCreateReqVO createReqVO) {
        return success(deliveryExpressTemplateService.createDeliveryExpressTemplate(createReqVO));
    }

    /**
     * 更新快递运费模板
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express-template:update')")
    public CommonResult<Boolean> updateDeliveryExpressTemplate(
            @Valid @RequestBody DeliveryExpressTemplateUpdateReqVO updateReqVO) {

        deliveryExpressTemplateService.updateDeliveryExpressTemplate(updateReqVO);
        return success(true);
    }


    /**
     * 删除快递运费模板
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('trade:delivery:express-template:delete')")
    public CommonResult<Boolean> deleteDeliveryExpressTemplate(@RequestParam("id") Long id) {
        deliveryExpressTemplateService.deleteDeliveryExpressTemplate(id);
        return success(true);
    }


    /**
     * 获得快递运费模板
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('trade:delivery:express-template:query')")
    public CommonResult<DeliveryExpressTemplateDetailRespVO> getDeliveryExpressTemplate(@RequestParam("id") Long id) {
        return success(deliveryExpressTemplateService.getDeliveryExpressTemplate(id));
    }


    /**
     * 获得快递运费模板列表
     * 
     * @param ids 编号列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express-template:query')")
    public CommonResult<List<DeliveryExpressTemplateRespVO>> getDeliveryExpressTemplateList(
            @RequestParam("ids") Collection<Long> ids) {

        List<DeliveryExpressTemplateDO> list = deliveryExpressTemplateService.getDeliveryExpressTemplateList(ids);
        return success(DeliveryExpressTemplateConvert.INSTANCE.convertList(list));
    }


    /**
     * 获取快递模版精简信息列表
     * 
     * @return
     */
    @GetMapping("/list-all-simple")

    public CommonResult<List<DeliveryExpressTemplateSimpleRespVO>> getSimpleTemplateList() {
        // 获取运费模版列表，只要开启状态的
        List<DeliveryExpressTemplateDO> list = deliveryExpressTemplateService.getDeliveryExpressTemplateList();
        // 排序后，返回给前端
        return success(DeliveryExpressTemplateConvert.INSTANCE.convertList1(list));
    }


    /**
     * 获得快递运费模板分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express-template:query')")
    public CommonResult<PageResult<DeliveryExpressTemplateRespVO>> getDeliveryExpressTemplatePage(
            @Valid DeliveryExpressTemplatePageReqVO pageVO) {
        PageResult<DeliveryExpressTemplateDO> pageResult = deliveryExpressTemplateService
                .getDeliveryExpressTemplatePage(pageVO);

        return success(DeliveryExpressTemplateConvert.INSTANCE.convertPage(pageResult));
    }

}
