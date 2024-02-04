package com.ymitcloud.module.crm.controller.admin.customer;



import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.pojo.PageParam.PAGE_SIZE_NONE;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSetByFlatMap;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.mapstruct.ap.internal.util.Collections;
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
import com.ymitcloud.module.crm.controller.admin.customer.vo.CrmCustomerCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.customer.vo.CrmCustomerExcelVO;
import com.ymitcloud.module.crm.controller.admin.customer.vo.CrmCustomerPageReqVO;
import com.ymitcloud.module.crm.controller.admin.customer.vo.CrmCustomerQueryAllRespVO;
import com.ymitcloud.module.crm.controller.admin.customer.vo.CrmCustomerRespVO;
import com.ymitcloud.module.crm.controller.admin.customer.vo.CrmCustomerTransferReqVO;
import com.ymitcloud.module.crm.controller.admin.customer.vo.CrmCustomerUpdateReqVO;
import com.ymitcloud.module.crm.convert.customer.CrmCustomerConvert;
import com.ymitcloud.module.crm.dal.dataobject.customer.CrmCustomerDO;
import com.ymitcloud.module.crm.service.customer.CrmCustomerService;
import com.ymitcloud.module.system.api.dept.DeptApi;
import com.ymitcloud.module.system.api.dept.dto.DeptRespDTO;
import com.ymitcloud.module.system.api.user.AdminUserApi;
import com.ymitcloud.module.system.api.user.dto.AdminUserRespDTO;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - CRM 客户
 */

@RestController
@RequestMapping("/crm/customer")
@Validated
public class CrmCustomerController {

    @Resource
    private CrmCustomerService customerService;

    @Resource
    private DeptApi deptApi;
    @Resource
    private AdminUserApi adminUserApi;


    /**
     * 创建客户
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('crm:customer:create')")
    public CommonResult<Long> createCustomer(@Valid @RequestBody CrmCustomerCreateReqVO createReqVO) {
        return success(customerService.createCustomer(createReqVO, getLoginUserId()));
    }


    /**
     * 更新客户
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('crm:customer:update')")
    public CommonResult<Boolean> updateCustomer(@Valid @RequestBody CrmCustomerUpdateReqVO updateReqVO) {
        customerService.updateCustomer(updateReqVO);
        return success(true);
    }


    /**
     * 删除客户
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('crm:customer:delete')")
    public CommonResult<Boolean> deleteCustomer(@RequestParam("id") Long id) {
        customerService.deleteCustomer(id);
        return success(true);
    }


    /**
     * 获得客户
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:customer:query')")
    public CommonResult<CrmCustomerRespVO> getCustomer(@RequestParam("id") Long id) {
        // 1. 获取客户
        CrmCustomerDO customer = customerService.getCustomer(id);
        if (customer == null) {
            return success(null);
        }
        // 2. 拼接数据

        Map<Long, AdminUserRespDTO> userMap = adminUserApi
                .getUserMap(Collections.asSet(Long.valueOf(customer.getCreator()), customer.getOwnerUserId()));

        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(userMap.values(), AdminUserRespDTO::getDeptId));
        return success(CrmCustomerConvert.INSTANCE.convert(customer, userMap, deptMap));
    }


    /**
     * 获得客户分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('crm:customer:query')")
    public CommonResult<PageResult<CrmCustomerRespVO>> getCustomerPage(@Valid CrmCustomerPageReqVO pageVO) {
        // 1. 查询客户分页
        PageResult<CrmCustomerDO> pageResult = customerService.getCustomerPage(pageVO, getLoginUserId());
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }

        // 2. 拼接数据

        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(convertSetByFlatMap(pageResult.getList(),
                user -> Stream.of(Long.parseLong(user.getCreator()), user.getOwnerUserId())));

        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(userMap.values(), AdminUserRespDTO::getDeptId));
        return success(CrmCustomerConvert.INSTANCE.convertPage(pageResult, userMap, deptMap));
    }


    /**
     * 导出客户 Excel
     * 
     * @param pageVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('crm:customer:export')")
    @OperateLog(type = EXPORT)
    public void exportCustomerExcel(@Valid CrmCustomerPageReqVO pageVO, HttpServletResponse response)
            throws IOException {

        pageVO.setPageSize(PAGE_SIZE_NONE); // 不分页
        List<CrmCustomerDO> list = customerService.getCustomerPage(pageVO, getLoginUserId()).getList();
        // 导出 Excel
        List<CrmCustomerExcelVO> datas = CrmCustomerConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "客户.xls", "数据", CrmCustomerExcelVO.class, datas);
    }


    /**
     * 客户转移
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/transfer")

    @PreAuthorize("@ss.hasPermission('crm:customer:update')")
    public CommonResult<Boolean> transfer(@Valid @RequestBody CrmCustomerTransferReqVO reqVO) {
        customerService.transferCustomer(reqVO, getLoginUserId());
        return success(true);
    }


    /**
     * 锁定/解锁客户
     * 
     * @param updateReqVO
     * @return
     */
    // TODO @Joey：单独建一个属于自己业务的 ReqVO；因为前端如果模拟请求，是不是可以更新其它字段了；
    @PutMapping("/lock")

    @PreAuthorize("@ss.hasPermission('crm:customer:update')")
    public CommonResult<Boolean> lockCustomer(@Valid @RequestBody CrmCustomerUpdateReqVO updateReqVO) {
        customerService.lockCustomer(updateReqVO);
        return success(true);
    }

    // ==================== 公海相关操作 ====================


    /**
     * 数据放入公海
     * 
     * @param id 客户编号
     * @return
     */
    @PutMapping("/put-pool")

    @PreAuthorize("@ss.hasPermission('crm:customer:update')")
    public CommonResult<Boolean> putCustomerPool(@RequestParam("id") Long id) {
        customerService.putCustomerPool(id);
        return success(true);
    }


    /**
     * 领取公海客户
     * 
     * @param ids 编号数组
     * @return
     */
    @PutMapping("/receive")

    @PreAuthorize("@ss.hasPermission('crm:customer:receive')")
    public CommonResult<Boolean> receiveCustomer(@RequestParam(value = "ids") List<Long> ids) {
        customerService.receiveCustomer(ids, getLoginUserId());
        return success(true);
    }


    /**
     * 分配公海给对应负责人
     * 
     * @param ids         客户编号数组
     * @param ownerUserId 分配的负责人编号
     * @return
     */
    @PutMapping("/distribute")
    @PreAuthorize("@ss.hasPermission('crm:customer:distribute')")
    public CommonResult<Boolean> distributeCustomer(@RequestParam(value = "ids") List<Long> ids,
            @RequestParam(value = "ownerUserId") Long ownerUserId) {

        // 领取公海数据
        customerService.receiveCustomer(ids, ownerUserId);
        return success(true);
    }


    /**
     * 查询客户列表
     * 
     * @return
     */
    // TODO 云码：这个接口要调整下
    @GetMapping("/query-all-list")

    @PreAuthorize("@ss.hasPermission('crm:customer:all')")
    public CommonResult<List<CrmCustomerQueryAllRespVO>> queryAll() {
        List<CrmCustomerDO> crmCustomerDOList = customerService.getCustomerList();
        List<CrmCustomerQueryAllRespVO> data = CrmCustomerConvert.INSTANCE.convertQueryAll(crmCustomerDOList);
        return success(data);
    }

}
