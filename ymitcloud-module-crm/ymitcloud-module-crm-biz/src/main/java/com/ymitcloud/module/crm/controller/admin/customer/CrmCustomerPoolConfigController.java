package com.ymitcloud.module.crm.controller.admin.customer;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.crm.controller.admin.customer.vo.poolconfig.CrmCustomerPoolConfigRespVO;
import com.ymitcloud.module.crm.controller.admin.customer.vo.poolconfig.CrmCustomerPoolConfigSaveReqVO;
import com.ymitcloud.module.crm.convert.customer.CrmCustomerConvert;
import com.ymitcloud.module.crm.dal.dataobject.customer.CrmCustomerPoolConfigDO;
import com.ymitcloud.module.crm.service.customer.CrmCustomerPoolConfigService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - CRM 客户公海配置
 */

@RestController
@RequestMapping("/crm/customer-pool-config")
@Validated
public class CrmCustomerPoolConfigController {

    @Resource
    private CrmCustomerPoolConfigService customerPoolConfigService;


    /**
     * 获取客户公海规则设置
     * 
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:customer-pool-config:query')")
    public CommonResult<CrmCustomerPoolConfigRespVO> getCustomerPoolConfig() {
        CrmCustomerPoolConfigDO customerPoolConfig = customerPoolConfigService.getCustomerPoolConfig();
        return success(CrmCustomerConvert.INSTANCE.convert(customerPoolConfig));
    }


    /**
     * 更新客户公海规则设置
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/save")
    @PreAuthorize("@ss.hasPermission('crm:customer-pool-config:update')")
    public CommonResult<Boolean> saveCustomerPoolConfig(
            @Valid @RequestBody CrmCustomerPoolConfigSaveReqVO updateReqVO) {

        customerPoolConfigService.saveCustomerPoolConfig(updateReqVO);
        return success(true);
    }

}
