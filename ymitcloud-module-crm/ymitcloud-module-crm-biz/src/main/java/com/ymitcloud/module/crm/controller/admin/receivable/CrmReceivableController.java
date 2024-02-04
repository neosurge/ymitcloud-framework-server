package com.ymitcloud.module.crm.controller.admin.receivable;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertListByFlatMap;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
import com.ymitcloud.framework.common.util.number.NumberUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.receivable.CrmReceivableCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.receivable.CrmReceivablePageReqVO;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.receivable.CrmReceivableRespVO;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.receivable.CrmReceivableUpdateReqVO;
import com.ymitcloud.module.crm.convert.receivable.CrmReceivableConvert;
import com.ymitcloud.module.crm.dal.dataobject.contract.CrmContractDO;
import com.ymitcloud.module.crm.dal.dataobject.customer.CrmCustomerDO;
import com.ymitcloud.module.crm.dal.dataobject.receivable.CrmReceivableDO;
import com.ymitcloud.module.crm.service.contract.CrmContractService;
import com.ymitcloud.module.crm.service.customer.CrmCustomerService;
import com.ymitcloud.module.crm.service.receivable.CrmReceivableService;
import com.ymitcloud.module.system.api.user.AdminUserApi;
import com.ymitcloud.module.system.api.user.dto.AdminUserRespDTO;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - CRM 回款
 */

@RestController
@RequestMapping("/crm/receivable")
@Validated
public class CrmReceivableController {

    @Resource
    private CrmReceivableService receivableService;
    @Resource
    private CrmContractService contractService;
    @Resource
    private CrmCustomerService customerService;

    @Resource
    private AdminUserApi adminUserApi;


    /**
     * 创建回款
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('crm:receivable:create')")
    public CommonResult<Long> createReceivable(@Valid @RequestBody CrmReceivableCreateReqVO createReqVO) {
        return success(receivableService.createReceivable(createReqVO));
    }


    /**
     * 更新回款
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('crm:receivable:update')")
    public CommonResult<Boolean> updateReceivable(@Valid @RequestBody CrmReceivableUpdateReqVO updateReqVO) {
        receivableService.updateReceivable(updateReqVO);
        return success(true);
    }


    /**
     * 删除回款
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('crm:receivable:delete')")
    public CommonResult<Boolean> deleteReceivable(@RequestParam("id") Long id) {
        receivableService.deleteReceivable(id);
        return success(true);
    }


    /**
     * 获得回款
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:receivable:query')")
    public CommonResult<CrmReceivableRespVO> getReceivable(@RequestParam("id") Long id) {
        CrmReceivableDO receivable = receivableService.getReceivable(id);
        return success(CrmReceivableConvert.INSTANCE.convert(receivable));
    }


    /**
     * 获得回款分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('crm:receivable:query')")
    public CommonResult<PageResult<CrmReceivableRespVO>> getReceivablePage(@Valid CrmReceivablePageReqVO pageReqVO) {
        PageResult<CrmReceivableDO> pageResult = receivableService.getReceivablePage(pageReqVO);
        return success(convertDetailReceivablePage(pageResult));
    }


    /**
     * 获得回款分页，基于指定客户
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page-by-customer")
    public CommonResult<PageResult<CrmReceivableRespVO>> getReceivablePageByCustomer(
            @Valid CrmReceivablePageReqVO pageReqVO) {

        Assert.notNull(pageReqVO.getCustomerId(), "客户编号不能为空");
        PageResult<CrmReceivableDO> pageResult = receivableService.getReceivablePageByCustomer(pageReqVO);
        return success(convertDetailReceivablePage(pageResult));
    }


    /**
     * 导出回款 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    // TODO 云码：后面在优化导出
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('crm:receivable:export')")
    @OperateLog(type = EXPORT)
    public void exportReceivableExcel(@Valid CrmReceivablePageReqVO exportReqVO, HttpServletResponse response)
            throws IOException {

        PageResult<CrmReceivableDO> pageResult = receivableService.getReceivablePage(exportReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "回款.xls", "数据", CrmReceivableRespVO.class,
                convertDetailReceivablePage(pageResult).getList());
    }

    /**
     * 转换成详细的回款分页，即读取关联信息
     *
     * @param pageResult 回款分页
     * @return 详细的回款分页
     */
    private PageResult<CrmReceivableRespVO> convertDetailReceivablePage(PageResult<CrmReceivableDO> pageResult) {
        List<CrmReceivableDO> receivableList = pageResult.getList();
        if (CollUtil.isEmpty(receivableList)) {
            return PageResult.empty(pageResult.getTotal());
        }
        // 1. 获取客户列表

        List<CrmCustomerDO> customerList = customerService
                .getCustomerList(convertSet(receivableList, CrmReceivableDO::getCustomerId));

        // 2. 获取创建人、负责人列表
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(convertListByFlatMap(receivableList,
                contact -> Stream.of(NumberUtils.parseLong(contact.getCreator()), contact.getOwnerUserId())));
        // 3. 获得合同列表

        List<CrmContractDO> contractList = contractService
                .getContractList(convertSet(receivableList, CrmReceivableDO::getContractId));

        return CrmReceivableConvert.INSTANCE.convertPage(pageResult, userMap, customerList, contractList);
    }

}
