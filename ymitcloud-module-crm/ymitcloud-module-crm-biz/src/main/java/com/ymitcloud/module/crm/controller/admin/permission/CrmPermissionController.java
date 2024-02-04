package com.ymitcloud.module.crm.controller.admin.permission;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.ymitcloud.framework.common.util.collection.CollectionUtils;
import com.ymitcloud.module.crm.controller.admin.permission.vo.CrmPermissionCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.permission.vo.CrmPermissionRespVO;
import com.ymitcloud.module.crm.controller.admin.permission.vo.CrmPermissionUpdateReqVO;
import com.ymitcloud.module.crm.convert.permission.CrmPermissionConvert;
import com.ymitcloud.module.crm.dal.dataobject.permission.CrmPermissionDO;

import com.ymitcloud.module.crm.enums.permission.CrmPermissionLevelEnum;
import com.ymitcloud.module.crm.framework.core.annotations.CrmPermission;

import com.ymitcloud.module.crm.service.permission.CrmPermissionService;
import com.ymitcloud.module.system.api.dept.DeptApi;
import com.ymitcloud.module.system.api.dept.PostApi;
import com.ymitcloud.module.system.api.dept.dto.DeptRespDTO;
import com.ymitcloud.module.system.api.dept.dto.PostRespDTO;
import com.ymitcloud.module.system.api.user.AdminUserApi;
import com.ymitcloud.module.system.api.user.dto.AdminUserRespDTO;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - CRM 数据权限
 */

@RestController
@RequestMapping("/crm/permission")
@Validated
public class CrmPermissionController {

    @Resource
    private CrmPermissionService permissionService;

    @Resource
    private AdminUserApi adminUserApi;
    @Resource
    private DeptApi deptApi;
    @Resource
    private PostApi postApi;


    /**
     * 创建数据权限
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('crm:permission:create')")
    @CrmPermission(bizTypeValue = "#reqVO.bizType", bizId = "#reqVO.bizId", level = CrmPermissionLevelEnum.OWNER)
    public CommonResult<Boolean> addPermission(@Valid @RequestBody CrmPermissionCreateReqVO reqVO) {
        permissionService.createPermission(CrmPermissionConvert.INSTANCE.convert(reqVO));
        return success(true);
    }


    /**
     * 编辑数据权限
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('crm:permission:update')")
    @CrmPermission(bizTypeValue = "#updateReqVO.bizType", bizId = "#updateReqVO.bizId", level = CrmPermissionLevelEnum.OWNER)

    public CommonResult<Boolean> updatePermission(@Valid @RequestBody CrmPermissionUpdateReqVO updateReqVO) {
        permissionService.updatePermission(updateReqVO);
        return success(true);
    }


    /**
     * 删除数据权限
     * 
     * @param ids 数据权限编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('crm:permission:delete')")
    public CommonResult<Boolean> deletePermission(@RequestParam("ids") Collection<Long> ids) {
        permissionService.deletePermissionBatch(ids, getLoginUserId());
        return success(true);
    }


    /**
     * 删除自己的数据权限
     * 
     * @param id 数据权限编号
     * @return
     */
    @DeleteMapping("/delete-self")

    @PreAuthorize("@ss.hasPermission('crm:permission:delete')")
    public CommonResult<Boolean> deleteSelfPermission(@RequestParam("id") Long id) {
        permissionService.deleteSelfPermission(id, getLoginUserId());
        return success(true);
    }


    /**
     * 获得数据权限列表
     * 
     * @param bizType CRM 类型
     * @param bizId   CRM 类型数据编号
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('crm:permission:query')")
    public CommonResult<List<CrmPermissionRespVO>> getPermissionList(@RequestParam("bizType") Integer bizType,
            @RequestParam("bizId") Long bizId) {

        List<CrmPermissionDO> permission = permissionService.getPermissionListByBiz(bizType, bizId);
        if (CollUtil.isEmpty(permission)) {
            return success(Collections.emptyList());
        }

        // 拼接数据
        List<AdminUserRespDTO> userList = adminUserApi.getUserList(convertSet(permission, CrmPermissionDO::getUserId));
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(userList, AdminUserRespDTO::getDeptId));

        Set<Long> postIds = CollectionUtils.convertSetByFlatMap(userList, AdminUserRespDTO::getPostIds,
                Collection::stream);

        Map<Long, PostRespDTO> postMap = postApi.getPostMap(postIds);
        return success(CrmPermissionConvert.INSTANCE.convert(permission, userList, deptMap, postMap));
    }

}
