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
import com.ymitcloud.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanPageReqVO;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanRespVO;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanUpdateReqVO;
import com.ymitcloud.module.crm.convert.receivable.CrmReceivablePlanConvert;
import com.ymitcloud.module.crm.dal.dataobject.contract.CrmContractDO;
import com.ymitcloud.module.crm.dal.dataobject.customer.CrmCustomerDO;
import com.ymitcloud.module.crm.dal.dataobject.receivable.CrmReceivableDO;
import com.ymitcloud.module.crm.dal.dataobject.receivable.CrmReceivablePlanDO;
import com.ymitcloud.module.crm.service.contract.CrmContractService;
import com.ymitcloud.module.crm.service.customer.CrmCustomerService;
import com.ymitcloud.module.crm.service.receivable.CrmReceivablePlanService;
import com.ymitcloud.module.crm.service.receivable.CrmReceivableService;
import com.ymitcloud.module.system.api.user.AdminUserApi;
import com.ymitcloud.module.system.api.user.dto.AdminUserRespDTO;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - CRM 回款计划
 */

@RestController
@RequestMapping("/crm/receivable-plan")
@Validated
public class CrmReceivablePlanController {

    @Resource
    private CrmReceivablePlanService receivablePlanService;
    @Resource
    private CrmReceivableService receivableService;
    @Resource
    private CrmContractService contractService;
    @Resource
    private CrmCustomerService customerService;

    @Resource
    private AdminUserApi adminUserApi;


    /**
     * 创建回款计划
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('crm:receivable-plan:create')")
    public CommonResult<Long> createReceivablePlan(@Valid @RequestBody CrmReceivablePlanCreateReqVO createReqVO) {
        return success(receivablePlanService.createReceivablePlan(createReqVO));
    }


    /**
     * 更新回款计划
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('crm:receivable-plan:update')")
    public CommonResult<Boolean> updateReceivablePlan(@Valid @RequestBody CrmReceivablePlanUpdateReqVO updateReqVO) {
        receivablePlanService.updateReceivablePlan(updateReqVO);
        return success(true);
    }


    /**
     * 删除回款计划
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('crm:receivable-plan:delete')")
    public CommonResult<Boolean> deleteReceivablePlan(@RequestParam("id") Long id) {
        receivablePlanService.deleteReceivablePlan(id);
        return success(true);
    }


    /**
     * 获得回款计划
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:receivable-plan:query')")
    public CommonResult<CrmReceivablePlanRespVO> getReceivablePlan(@RequestParam("id") Long id) {
        CrmReceivablePlanDO receivablePlan = receivablePlanService.getReceivablePlan(id);
        return success(CrmReceivablePlanConvert.INSTANCE.convert(receivablePlan));
    }


    /**
     * 获得回款计划分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('crm:receivable-plan:query')")
    public CommonResult<PageResult<CrmReceivablePlanRespVO>> getReceivablePlanPage(
            @Valid CrmReceivablePlanPageReqVO pageReqVO) {

        PageResult<CrmReceivablePlanDO> pageResult = receivablePlanService.getReceivablePlanPage(pageReqVO);
        return success(convertDetailReceivablePlanPage(pageResult));
    }


    /**
     * 获得回款计划分页，基于指定客户
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page-by-customer")
    public CommonResult<PageResult<CrmReceivablePlanRespVO>> getReceivablePlanPageByCustomer(
            @Valid CrmReceivablePlanPageReqVO pageReqVO) {

        Assert.notNull(pageReqVO.getCustomerId(), "客户编号不能为空");
        PageResult<CrmReceivablePlanDO> pageResult = receivablePlanService.getReceivablePlanPageByCustomer(pageReqVO);
        return success(convertDetailReceivablePlanPage(pageResult));
    }


    /**
     * 导出回款计划 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    // TODO 云码：后面在优化导出
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('crm:receivable-plan:export')")
    @OperateLog(type = EXPORT)
    public void exportReceivablePlanExcel(@Valid CrmReceivablePlanPageReqVO exportReqVO, HttpServletResponse response)
            throws IOException {

        PageResult<CrmReceivablePlanDO> pageResult = receivablePlanService.getReceivablePlanPage(exportReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "回款计划.xls", "数据", CrmReceivablePlanRespVO.class,
                convertDetailReceivablePlanPage(pageResult).getList());
    }

    /**
     * 转换成详细的回款计划分页，即读取关联信息
     *
     * @param pageResult 回款计划分页
     * @return 详细的回款计划分页
     */

    private PageResult<CrmReceivablePlanRespVO> convertDetailReceivablePlanPage(
            PageResult<CrmReceivablePlanDO> pageResult) {

        List<CrmReceivablePlanDO> receivablePlanList = pageResult.getList();
        if (CollUtil.isEmpty(receivablePlanList)) {
            return PageResult.empty(pageResult.getTotal());
        }
        // 1. 获取客户列表

        List<CrmCustomerDO> customerList = customerService
                .getCustomerList(convertSet(receivablePlanList, CrmReceivablePlanDO::getCustomerId));

        // 2. 获取创建人、负责人列表
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(convertListByFlatMap(receivablePlanList,
                contact -> Stream.of(NumberUtils.parseLong(contact.getCreator()), contact.getOwnerUserId())));
        // 3. 获得合同列表

        List<CrmContractDO> contractList = contractService
                .getContractList(convertSet(receivablePlanList, CrmReceivablePlanDO::getContractId));
        // 4. 获得还款列表
        List<CrmReceivableDO> receivableList = receivableService
                .getReceivableList(convertSet(receivablePlanList, CrmReceivablePlanDO::getReceivableId));
        return CrmReceivablePlanConvert.INSTANCE.convertPage(pageResult, userMap, customerList, contractList,
                receivableList);

    }

}
