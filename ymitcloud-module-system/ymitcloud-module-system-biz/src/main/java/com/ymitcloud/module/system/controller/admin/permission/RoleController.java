package com.ymitcloud.module.system.controller.admin.permission;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static java.util.Collections.singleton;

import java.io.IOException;
import java.util.Comparator;
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

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.system.controller.admin.permission.vo.role.RolePageReqVO;
import com.ymitcloud.module.system.controller.admin.permission.vo.role.RoleRespVO;
import com.ymitcloud.module.system.controller.admin.permission.vo.role.RoleSaveReqVO;
import com.ymitcloud.module.system.controller.admin.permission.vo.role.RoleSimpleRespVO;
import com.ymitcloud.module.system.controller.admin.permission.vo.role.RoleUpdateStatusReqVO;
import com.ymitcloud.module.system.dal.dataobject.permission.RoleDO;
import com.ymitcloud.module.system.service.permission.RoleService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 角色
 */
@RestController
@RequestMapping("/system/role")
@Validated
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 创建角色
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:role:create')")
    public CommonResult<Long> createRole(@Valid @RequestBody RoleSaveReqVO createReqVO) {
        return success(roleService.createRole(createReqVO, null));
    }

    /**
     * 修改角色
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:role:update')")
    public CommonResult<Boolean> updateRole(@Valid @RequestBody RoleSaveReqVO updateReqVO) {
        roleService.updateRole(updateReqVO);
        return success(true);
    }

    /**
     * 修改角色状态
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update-status")
    @PreAuthorize("@ss.hasPermission('system:role:update')")
    public CommonResult<Boolean> updateRoleStatus(@Valid @RequestBody RoleUpdateStatusReqVO reqVO) {
        roleService.updateRoleStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }

    /**
     * 删除角色
     * 
     * @param id 角色编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:role:delete')")
    public CommonResult<Boolean> deleteRole(@RequestParam("id") Long id) {
        roleService.deleteRole(id);
        return success(true);
    }

    /**
     * 获得角色信息
     * 
     * @param id
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:role:query')")
    public CommonResult<RoleRespVO> getRole(@RequestParam("id") Long id) {
        RoleDO role = roleService.getRole(id);
        return success(BeanUtils.toBean(role, RoleRespVO.class));
    }

    /**
     * 获得角色分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:role:query')")
    public CommonResult<PageResult<RoleRespVO>> getRolePage(RolePageReqVO pageReqVO) {
        PageResult<RoleDO> pageResult = roleService.getRolePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RoleRespVO.class));
    }

    /**
     * 获取角色精简信息列表,只包含被开启的角色，主要用于前端的下拉选项
     * 
     * @return
     */
    @GetMapping({ "/list-all-simple", "/simple-list" })
    public CommonResult<List<RoleSimpleRespVO>> getSimpleRoleList() {
        List<RoleDO> list = roleService.getRoleListByStatus(singleton(CommonStatusEnum.ENABLE.getStatus()));
        list.sort(Comparator.comparing(RoleDO::getSort));
        return success(BeanUtils.toBean(list, RoleSimpleRespVO.class));
    }
    /**
     * 导出角色 Excel
     * 
     * @param response
     * @param exportReqVO
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @OperateLog(type = EXPORT)
    @PreAuthorize("@ss.hasPermission('system:role:export')")
    public void export(HttpServletResponse response, @Validated RolePageReqVO exportReqVO) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoleDO> list = roleService.getRolePage(exportReqVO).getList();
        // 输出
        ExcelUtils.write(response, "角色数据.xls", "数据", RoleRespVO.class, BeanUtils.toBean(list, RoleRespVO.class));
    }

}
