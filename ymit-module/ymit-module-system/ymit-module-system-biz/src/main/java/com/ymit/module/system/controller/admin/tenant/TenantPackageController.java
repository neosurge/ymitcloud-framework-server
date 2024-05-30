package com.ymit.module.system.controller.admin.tenant;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.enums.CommonStatusEnum;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.system.controller.admin.tenant.vo.packages.TenantPackagePageReqVO;
import com.ymit.module.system.controller.admin.tenant.vo.packages.TenantPackageRespVO;
import com.ymit.module.system.controller.admin.tenant.vo.packages.TenantPackageSaveReqVO;
import com.ymit.module.system.controller.admin.tenant.vo.packages.TenantPackageSimpleRespVO;
import com.ymit.module.system.dal.dataobject.tenant.TenantPackageDO;
import com.ymit.module.system.service.tenant.TenantPackageService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;

/**
 * 管理后台 - 租户套餐
 */
@RestController
@RequestMapping("/system/tenant-package")
@Validated
public class TenantPackageController {

    @Resource
    private TenantPackageService tenantPackageService;

    /**
     * 创建租户套餐
     *
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:tenant-package:create')")
    public CommonResult<Long> createTenantPackage(@Valid @RequestBody TenantPackageSaveReqVO createReqVO) {
        return success(this.tenantPackageService.createTenantPackage(createReqVO));
    }

    /**
     * 更新租户套餐
     *
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:tenant-package:update')")
    public CommonResult<Boolean> updateTenantPackage(@Valid @RequestBody TenantPackageSaveReqVO updateReqVO) {
        this.tenantPackageService.updateTenantPackage(updateReqVO);
        return success(true);
    }

    /**
     * 删除租户套餐
     *
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:tenant-package:delete')")
    public CommonResult<Boolean> deleteTenantPackage(@RequestParam("id") Long id) {
        this.tenantPackageService.deleteTenantPackage(id);
        return success(true);
    }

    /**
     * 获得租户套餐
     *
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:tenant-package:query')")
    public CommonResult<TenantPackageRespVO> getTenantPackage(@RequestParam("id") Long id) {
        TenantPackageDO tenantPackage = this.tenantPackageService.getTenantPackage(id);
        return success(BeanUtils.toBean(tenantPackage, TenantPackageRespVO.class));
    }

    /**
     * 获得租户套餐分页
     *
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:tenant-package:query')")
    public CommonResult<PageResult<TenantPackageRespVO>> getTenantPackagePage(@Valid TenantPackagePageReqVO pageVO) {
        PageResult<TenantPackageDO> pageResult = this.tenantPackageService.getTenantPackagePage(pageVO);
        return success(BeanUtils.toBean(pageResult, TenantPackageRespVO.class));
    }

    /**
     * 获取租户套餐精简信息列表,只包含被开启的租户套餐，主要用于前端的下拉选项
     *
     * @return
     */
    @GetMapping({"/get-simple-list", "simple-list"})
    public CommonResult<List<TenantPackageSimpleRespVO>> getTenantPackageList() {
        List<TenantPackageDO> list = this.tenantPackageService
                .getTenantPackageListByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(BeanUtils.toBean(list, TenantPackageSimpleRespVO.class));
    }

}
