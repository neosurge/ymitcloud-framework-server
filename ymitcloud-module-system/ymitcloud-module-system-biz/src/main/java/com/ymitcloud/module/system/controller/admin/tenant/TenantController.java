package com.ymitcloud.module.system.controller.admin.tenant;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.system.controller.admin.tenant.vo.tenant.TenantPageReqVO;
import com.ymitcloud.module.system.controller.admin.tenant.vo.tenant.TenantRespVO;
import com.ymitcloud.module.system.controller.admin.tenant.vo.tenant.TenantSaveReqVO;
import com.ymitcloud.module.system.controller.admin.tenant.vo.tenant.TenantSimpleRespVO;
import com.ymitcloud.module.system.dal.dataobject.tenant.TenantDO;
import com.ymitcloud.module.system.service.tenant.TenantService;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 租户
 */
@RestController
@RequestMapping("/system/tenant")
public class TenantController {

    @Resource
    private TenantService tenantService;

    /**
     * 使用租户名，获得租户编号,登录界面，根据用户的租户名，获得租户编号
     * 
     * @param name 租户名
     * @return
     */
    @GetMapping("/get-id-by-name")
    @PermitAll
    public CommonResult<Long> getTenantIdByName(@RequestParam("name") String name) {
        TenantDO tenant = tenantService.getTenantByName(name);
        return success(tenant != null ? tenant.getId() : null);
    }

    /**
     * 使用域名，获得租户信息,登录界面，根据用户的域名，获得租户信息
     * 
     * @param website 域名
     * @return
     */
    @GetMapping("/get-by-website")
    @PermitAll
    public CommonResult<TenantSimpleRespVO> getTenantByWebsite(@RequestParam("website") String website) {
        TenantDO tenant = tenantService.getTenantByWebsite(website);
        return success(BeanUtils.toBean(tenant, TenantSimpleRespVO.class));
    }

    /**
     * 创建租户
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:tenant:create')")
    public CommonResult<Long> createTenant(@Valid @RequestBody TenantSaveReqVO createReqVO) {
        return success(tenantService.createTenant(createReqVO));
    }

    /**
     * 更新租户
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:tenant:update')")
    public CommonResult<Boolean> updateTenant(@Valid @RequestBody TenantSaveReqVO updateReqVO) {
        tenantService.updateTenant(updateReqVO);
        return success(true);
    }

    /**
     * 删除租户
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:tenant:delete')")
    public CommonResult<Boolean> deleteTenant(@RequestParam("id") Long id) {
        tenantService.deleteTenant(id);
        return success(true);
    }

    /**
     * 获得租户
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:tenant:query')")
    public CommonResult<TenantRespVO> getTenant(@RequestParam("id") Long id) {
        TenantDO tenant = tenantService.getTenant(id);
        return success(BeanUtils.toBean(tenant, TenantRespVO.class));
    }

    /**
     * 获得租户分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:tenant:query')")
    public CommonResult<PageResult<TenantRespVO>> getTenantPage(@Valid TenantPageReqVO pageVO) {
        PageResult<TenantDO> pageResult = tenantService.getTenantPage(pageVO);
        return success(BeanUtils.toBean(pageResult, TenantRespVO.class));
    }

    /**
     * 导出租户 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('system:tenant:export')")
    @OperateLog(type = EXPORT)
    public void exportTenantExcel(@Valid TenantPageReqVO exportReqVO, HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TenantDO> list = tenantService.getTenantPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "租户.xls", "数据", TenantRespVO.class, BeanUtils.toBean(list, TenantRespVO.class));
    }

}
