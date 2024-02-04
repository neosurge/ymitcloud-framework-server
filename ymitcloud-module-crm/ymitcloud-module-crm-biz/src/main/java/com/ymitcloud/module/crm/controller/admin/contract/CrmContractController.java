package com.ymitcloud.module.crm.controller.admin.contract;



import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertListByFlatMap;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;


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
import com.ymitcloud.module.crm.controller.admin.contract.vo.ContractRespVO;
import com.ymitcloud.module.crm.controller.admin.contract.vo.CrmContractCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.contract.vo.CrmContractExcelVO;
import com.ymitcloud.module.crm.controller.admin.contract.vo.CrmContractPageReqVO;
import com.ymitcloud.module.crm.controller.admin.contract.vo.CrmContractTransferReqVO;
import com.ymitcloud.module.crm.controller.admin.contract.vo.CrmContractUpdateReqVO;
import com.ymitcloud.module.crm.convert.contract.ContractConvert;
import com.ymitcloud.module.crm.dal.dataobject.contract.CrmContractDO;
import com.ymitcloud.module.crm.dal.dataobject.customer.CrmCustomerDO;
import com.ymitcloud.module.crm.service.contract.CrmContractService;
import com.ymitcloud.module.crm.service.customer.CrmCustomerService;
import com.ymitcloud.module.system.api.user.AdminUserApi;
import com.ymitcloud.module.system.api.user.dto.AdminUserRespDTO;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - CRM 合同
 */

@RestController
@RequestMapping("/crm/contract")
@Validated
public class CrmContractController {

    @Resource
    private CrmContractService contractService;
    @Resource
    private CrmCustomerService customerService;

    @Resource
    private AdminUserApi adminUserApi;


    /**
     * 创建合同
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('crm:contract:create')")
    public CommonResult<Long> createContract(@Valid @RequestBody CrmContractCreateReqVO createReqVO) {
        return success(contractService.createContract(createReqVO, getLoginUserId()));
    }


    /**
     * 更新合同
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('crm:contract:update')")
    public CommonResult<Boolean> updateContract(@Valid @RequestBody CrmContractUpdateReqVO updateReqVO) {
        contractService.updateContract(updateReqVO);
        return success(true);
    }


    /**
     * 删除合同
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('crm:contract:delete')")
    public CommonResult<Boolean> deleteContract(@RequestParam("id") Long id) {
        contractService.deleteContract(id);
        return success(true);
    }


    /**
     * 获得合同
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:contract:query')")
    public CommonResult<ContractRespVO> getContract(@RequestParam("id") Long id) {
        CrmContractDO contract = contractService.getContract(id);
        return success(ContractConvert.INSTANCE.convert(contract));
    }


    /**
     * 获得合同分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('crm:contract:query')")
    public CommonResult<PageResult<ContractRespVO>> getContractPage(@Valid CrmContractPageReqVO pageVO) {
        PageResult<CrmContractDO> pageResult = contractService.getContractPage(pageVO);
        return success(convertDetailContractPage(pageResult));
    }


    /**
     * 获得联系人分页，基于指定客户
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page-by-customer")

    public CommonResult<PageResult<ContractRespVO>> getContractPageByCustomer(@Valid CrmContractPageReqVO pageVO) {
        Assert.notNull(pageVO.getCustomerId(), "客户编号不能为空");
        PageResult<CrmContractDO> pageResult = contractService.getContractPageByCustomer(pageVO);
        return success(convertDetailContractPage(pageResult));
    }


    /**
     * 导出合同 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('crm:contract:export')")
    @OperateLog(type = EXPORT)
    public void exportContractExcel(@Valid CrmContractPageReqVO exportReqVO, HttpServletResponse response)
            throws IOException {

        PageResult<CrmContractDO> pageResult = contractService.getContractPage(exportReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "合同.xls", "数据", CrmContractExcelVO.class,
                ContractConvert.INSTANCE.convertList02(pageResult.getList()));
    }

    /**
     * 转换成详细的合同分页，即读取关联信息
     *
     * @param pageResult 合同分页
     * @return 详细的合同分页
     */
    private PageResult<ContractRespVO> convertDetailContractPage(PageResult<CrmContractDO> pageResult) {
        List<CrmContractDO> contactList = pageResult.getList();
        if (CollUtil.isEmpty(contactList)) {
            return PageResult.empty(pageResult.getTotal());
        }
        // 1. 获取客户列表

        List<CrmCustomerDO> customerList = customerService
                .getCustomerList(convertSet(contactList, CrmContractDO::getCustomerId));

        // 2. 获取创建人、负责人列表
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(convertListByFlatMap(contactList,
                contact -> Stream.of(NumberUtils.parseLong(contact.getCreator()), contact.getOwnerUserId())));
        return ContractConvert.INSTANCE.convertPage(pageResult, userMap, customerList);
    }


    /**
     * 合同转移
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/transfer")

    @PreAuthorize("@ss.hasPermission('crm:contract:update')")
    public CommonResult<Boolean> transfer(@Valid @RequestBody CrmContractTransferReqVO reqVO) {
        contractService.transferContract(reqVO, getLoginUserId());
        return success(true);
    }

}
