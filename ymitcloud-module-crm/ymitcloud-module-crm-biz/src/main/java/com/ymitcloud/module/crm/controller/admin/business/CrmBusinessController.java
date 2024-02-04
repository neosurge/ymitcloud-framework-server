package com.ymitcloud.module.crm.controller.admin.business;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;

import com.ymitcloud.module.crm.controller.admin.business.vo.business.CrmBusinessCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.business.CrmBusinessExcelVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.business.CrmBusinessPageReqVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.business.CrmBusinessRespVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.business.CrmBusinessTransferReqVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.business.CrmBusinessUpdateReqVO;

import com.ymitcloud.module.crm.controller.admin.business.vo.status.CrmBusinessStatusQueryVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypeQueryVO;
import com.ymitcloud.module.crm.controller.admin.contract.vo.CrmContractPageReqVO;
import com.ymitcloud.module.crm.convert.business.CrmBusinessConvert;
import com.ymitcloud.module.crm.dal.dataobject.business.CrmBusinessDO;
import com.ymitcloud.module.crm.dal.dataobject.business.CrmBusinessStatusDO;
import com.ymitcloud.module.crm.dal.dataobject.business.CrmBusinessStatusTypeDO;
import com.ymitcloud.module.crm.dal.dataobject.customer.CrmCustomerDO;
import com.ymitcloud.module.crm.service.business.CrmBusinessService;
import com.ymitcloud.module.crm.service.business.CrmBusinessStatusService;
import com.ymitcloud.module.crm.service.business.CrmBusinessStatusTypeService;
import com.ymitcloud.module.crm.service.customer.CrmCustomerService;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 商机
 */

@RestController
@RequestMapping("/crm/business")
@Validated
public class CrmBusinessController {

    @Resource
    private CrmBusinessService businessService;

    @Resource
    private CrmCustomerService customerService;

    @Resource
    private CrmBusinessStatusTypeService businessStatusTypeService;

    @Resource
    private CrmBusinessStatusService businessStatusService;


    /**
     * 创建商机
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('crm:business:create')")
    public CommonResult<Long> createBusiness(@Valid @RequestBody CrmBusinessCreateReqVO createReqVO) {
        return success(businessService.createBusiness(createReqVO, getLoginUserId()));
    }


    /**
     * 更新商机
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('crm:business:update')")
    public CommonResult<Boolean> updateBusiness(@Valid @RequestBody CrmBusinessUpdateReqVO updateReqVO) {
        businessService.updateBusiness(updateReqVO);
        return success(true);
    }


    /**
     * 删除商机
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('crm:business:delete')")
    public CommonResult<Boolean> deleteBusiness(@RequestParam("id") Long id) {
        businessService.deleteBusiness(id);
        return success(true);
    }


    /**
     * 获得商机
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:business:query')")
    public CommonResult<CrmBusinessRespVO> getBusiness(@RequestParam("id") Long id) {
        CrmBusinessDO business = businessService.getBusiness(id);
        return success(CrmBusinessConvert.INSTANCE.convert(business));
    }


    /**
     * 获得商机分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('crm:business:query')")
    public CommonResult<PageResult<CrmBusinessRespVO>> getBusinessPage(@Valid CrmBusinessPageReqVO pageVO) {
        PageResult<CrmBusinessDO> pageResult = businessService.getBusinessPage(pageVO, getLoginUserId());
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }
        // 处理客户名称回显
        // TODO @ljlleo：可以使用 CollectionUtils.convertSet 替代常用的 stream 操作，更简洁一点；下面几个也是哈；

        Set<Long> customerIds = pageResult.getList().stream().map(CrmBusinessDO::getCustomerId).filter(Objects::nonNull)
                .collect(Collectors.toSet());
        List<CrmCustomerDO> customerList = customerService.getCustomerList(customerIds);
        // 处理商机状态类型名称回显
        Set<Long> statusTypeIds = pageResult.getList().stream().map(CrmBusinessDO::getStatusTypeId)
                .filter(Objects::nonNull).collect(Collectors.toSet());

        CrmBusinessStatusTypeQueryVO queryStatusTypeVO = new CrmBusinessStatusTypeQueryVO();
        queryStatusTypeVO.setIdList(statusTypeIds);
        List<CrmBusinessStatusTypeDO> statusTypeList = businessStatusTypeService.selectList(queryStatusTypeVO);
        // 处理商机状态名称回显

        Set<Long> statusIds = pageResult.getList().stream().map(CrmBusinessDO::getStatusId).filter(Objects::nonNull)
                .collect(Collectors.toSet());

        CrmBusinessStatusQueryVO queryVO = new CrmBusinessStatusQueryVO();
        queryVO.setIdList(statusIds);
        List<CrmBusinessStatusDO> statusList = businessStatusService.selectList(queryVO);
        return success(CrmBusinessConvert.INSTANCE.convertPage(pageResult, customerList, statusTypeList, statusList));
    }


    /**
     * 获得商机分页，基于指定客户
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page-by-customer")
    public CommonResult<PageResult<CrmBusinessRespVO>> getBusinessPageByCustomer(
            @Valid CrmContractPageReqVO pageReqVO) {

        Assert.notNull(pageReqVO.getCustomerId(), "客户编号不能为空");
        PageResult<CrmBusinessDO> pageResult = businessService.getBusinessPageByCustomer(pageReqVO);
        // 处理客户名称回显
        // TODO @ljlleo：可以使用 CollectionUtils.convertSet 替代常用的 stream 操作，更简洁一点；下面几个也是哈；

        Set<Long> customerIds = pageResult.getList().stream().map(CrmBusinessDO::getCustomerId).filter(Objects::nonNull)
                .collect(Collectors.toSet());
        List<CrmCustomerDO> customerList = customerService.getCustomerList(customerIds);
        // 处理商机状态类型名称回显
        Set<Long> statusTypeIds = pageResult.getList().stream().map(CrmBusinessDO::getStatusTypeId)
                .filter(Objects::nonNull).collect(Collectors.toSet());

        CrmBusinessStatusTypeQueryVO queryStatusTypeVO = new CrmBusinessStatusTypeQueryVO();
        queryStatusTypeVO.setIdList(statusTypeIds);
        List<CrmBusinessStatusTypeDO> statusTypeList = businessStatusTypeService.selectList(queryStatusTypeVO);
        // 处理商机状态名称回显

        Set<Long> statusIds = pageResult.getList().stream().map(CrmBusinessDO::getStatusId).filter(Objects::nonNull)
                .collect(Collectors.toSet());

        CrmBusinessStatusQueryVO queryVO = new CrmBusinessStatusQueryVO();
        queryVO.setIdList(statusIds);
        List<CrmBusinessStatusDO> statusList = businessStatusService.selectList(queryVO);
        return success(CrmBusinessConvert.INSTANCE.convertPage(pageResult, customerList, statusTypeList, statusList));
    }


    /**
     * 获得商机公海分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/pool-page")

    @PreAuthorize("@ss.hasPermission('crm:business:query')")
    public CommonResult<PageResult<CrmBusinessRespVO>> getBusinessPoolPage(@Valid CrmBusinessPageReqVO pageVO) {
        // TODO puhui999: 等数据权限完善后再实现
        return null;
    }


    /**
     * 导出商机 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('crm:business:export')")
    @OperateLog(type = EXPORT)
    public void exportBusinessExcel(@Valid CrmBusinessPageReqVO exportReqVO, HttpServletResponse response)
            throws IOException {

        PageResult<CrmBusinessDO> pageResult = businessService.getBusinessPage(exportReqVO, getLoginUserId());
        // 导出 Excel
        ExcelUtils.write(response, "商机.xls", "数据", CrmBusinessExcelVO.class,
                CrmBusinessConvert.INSTANCE.convertList02(pageResult.getList()));
    }


    /**
     * 商机转移
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/transfer")

    @PreAuthorize("@ss.hasPermission('crm:business:update')")
    public CommonResult<Boolean> transfer(@Valid @RequestBody CrmBusinessTransferReqVO reqVO) {
        businessService.transferBusiness(reqVO, getLoginUserId());
        return success(true);
    }

}
