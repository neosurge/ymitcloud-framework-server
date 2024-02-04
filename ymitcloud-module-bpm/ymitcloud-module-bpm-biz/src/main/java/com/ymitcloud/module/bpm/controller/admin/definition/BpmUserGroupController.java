package com.ymitcloud.module.bpm.controller.admin.definition;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

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
import com.ymitcloud.framework.common.pojo.PageResult;

import com.ymitcloud.module.bpm.controller.admin.definition.vo.group.BpmUserGroupCreateReqVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.group.BpmUserGroupPageReqVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.group.BpmUserGroupRespVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.group.BpmUserGroupUpdateReqVO;
import com.ymitcloud.module.bpm.convert.definition.BpmUserGroupConvert;
import com.ymitcloud.module.bpm.dal.dataobject.definition.BpmUserGroupDO;
import com.ymitcloud.module.bpm.service.definition.BpmUserGroupService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 用户组
 */

@RestController
@RequestMapping("/bpm/user-group")
@Validated
public class BpmUserGroupController {

    @Resource
    private BpmUserGroupService userGroupService;


    /**
     * 创建用户组
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('bpm:user-group:create')")
    public CommonResult<Long> createUserGroup(@Valid @RequestBody BpmUserGroupCreateReqVO createReqVO) {
        return success(userGroupService.createUserGroup(createReqVO));
    }


    /**
     * 更新用户组
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('bpm:user-group:update')")
    public CommonResult<Boolean> updateUserGroup(@Valid @RequestBody BpmUserGroupUpdateReqVO updateReqVO) {
        userGroupService.updateUserGroup(updateReqVO);
        return success(true);
    }


    /**
     * 删除用户组
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('bpm:user-group:delete')")
    public CommonResult<Boolean> deleteUserGroup(@RequestParam("id") Long id) {
        userGroupService.deleteUserGroup(id);
        return success(true);
    }


    /**
     * 获得用户组
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('bpm:user-group:query')")
    public CommonResult<BpmUserGroupRespVO> getUserGroup(@RequestParam("id") Long id) {
        BpmUserGroupDO userGroup = userGroupService.getUserGroup(id);
        return success(BpmUserGroupConvert.INSTANCE.convert(userGroup));
    }


    /**
     * 获得用户组分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('bpm:user-group:query')")
    public CommonResult<PageResult<BpmUserGroupRespVO>> getUserGroupPage(@Valid BpmUserGroupPageReqVO pageVO) {
        PageResult<BpmUserGroupDO> pageResult = userGroupService.getUserGroupPage(pageVO);
        return success(BpmUserGroupConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 获取用户组精简信息列表，只包含被开启的用户组，主要用于前端的下拉选项
     * 
     * @return
     */
    @GetMapping("/list-all-simple")

    public CommonResult<List<BpmUserGroupRespVO>> getSimpleUserGroups() {
        // 获用户门列表，只要开启状态的
        List<BpmUserGroupDO> list = userGroupService.getUserGroupListByStatus(CommonStatusEnum.ENABLE.getStatus());
        // 排序后，返回给前端
        return success(BpmUserGroupConvert.INSTANCE.convertList2(list));
    }

}
