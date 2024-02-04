package com.ymitcloud.module.crm.controller.admin.customer;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSetByFlatMap;

import java.util.Collection;
import java.util.Map;

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
import com.ymitcloud.module.crm.controller.admin.customer.vo.limitconfig.CrmCustomerLimitConfigCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.customer.vo.limitconfig.CrmCustomerLimitConfigPageReqVO;
import com.ymitcloud.module.crm.controller.admin.customer.vo.limitconfig.CrmCustomerLimitConfigRespVO;
import com.ymitcloud.module.crm.controller.admin.customer.vo.limitconfig.CrmCustomerLimitConfigUpdateReqVO;
import com.ymitcloud.module.crm.convert.customer.CrmCustomerLimitConfigConvert;
import com.ymitcloud.module.crm.dal.dataobject.customer.CrmCustomerLimitConfigDO;
import com.ymitcloud.module.crm.service.customer.CrmCustomerLimitConfigService;
import com.ymitcloud.module.system.api.dept.DeptApi;
import com.ymitcloud.module.system.api.dept.dto.DeptRespDTO;
import com.ymitcloud.module.system.api.user.AdminUserApi;
import com.ymitcloud.module.system.api.user.dto.AdminUserRespDTO;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - CRM 客户限制配置
 */

@RestController
@RequestMapping("/crm/customer-limit-config")
@Validated
public class CrmCustomerLimitConfigController {

    @Resource
    private CrmCustomerLimitConfigService customerLimitConfigService;

    @Resource
    private DeptApi deptApi;
    @Resource
    private AdminUserApi adminUserApi;


    /**
     * 创建客户限制配置
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('crm:customer-limit-config:create')")
    public CommonResult<Long> createCustomerLimitConfig(
            @Valid @RequestBody CrmCustomerLimitConfigCreateReqVO createReqVO) {
        return success(customerLimitConfigService.createCustomerLimitConfig(createReqVO));
    }

    /**
     * 更新客户限制配置
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    public CommonResult<Boolean> updateCustomerLimitConfig(
            @Valid @RequestBody CrmCustomerLimitConfigUpdateReqVO updateReqVO) {

        customerLimitConfigService.updateCustomerLimitConfig(updateReqVO);
        return success(true);
    }


    /**
     * 删除客户限制配置
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('crm:customer-limit-config:delete')")
    public CommonResult<Boolean> deleteCustomerLimitConfig(@RequestParam("id") Long id) {
        customerLimitConfigService.deleteCustomerLimitConfig(id);
        return success(true);
    }


    /**
     * 获得客户限制配置
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:customer-limit-config:query')")
    public CommonResult<CrmCustomerLimitConfigRespVO> getCustomerLimitConfig(@RequestParam("id") Long id) {
        CrmCustomerLimitConfigDO customerLimitConfig = customerLimitConfigService.getCustomerLimitConfig(id);
        // 拼接数据
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(customerLimitConfig.getUserIds());
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(customerLimitConfig.getDeptIds());
        return success(CrmCustomerLimitConfigConvert.INSTANCE.convert(customerLimitConfig, userMap, deptMap));
    }


    /**
     * 获得客户限制配置分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('crm:customer-limit-config:query')")
    public CommonResult<PageResult<CrmCustomerLimitConfigRespVO>> getCustomerLimitConfigPage(
            @Valid CrmCustomerLimitConfigPageReqVO pageVO) {

        PageResult<CrmCustomerLimitConfigDO> pageResult = customerLimitConfigService.getCustomerLimitConfigPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }
        // 拼接数据
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(
                convertSetByFlatMap(pageResult.getList(), CrmCustomerLimitConfigDO::getUserIds, Collection::stream));
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(
                convertSetByFlatMap(pageResult.getList(), CrmCustomerLimitConfigDO::getDeptIds, Collection::stream));
        return success(CrmCustomerLimitConfigConvert.INSTANCE.convertPage(pageResult, userMap, deptMap));
    }

}
