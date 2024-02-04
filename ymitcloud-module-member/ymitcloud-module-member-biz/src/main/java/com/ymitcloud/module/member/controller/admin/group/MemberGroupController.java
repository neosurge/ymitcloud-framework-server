package com.ymitcloud.module.member.controller.admin.group;

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

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.member.controller.admin.group.vo.MemberGroupCreateReqVO;
import com.ymitcloud.module.member.controller.admin.group.vo.MemberGroupPageReqVO;
import com.ymitcloud.module.member.controller.admin.group.vo.MemberGroupRespVO;
import com.ymitcloud.module.member.controller.admin.group.vo.MemberGroupSimpleRespVO;
import com.ymitcloud.module.member.controller.admin.group.vo.MemberGroupUpdateReqVO;
import com.ymitcloud.module.member.convert.group.MemberGroupConvert;
import com.ymitcloud.module.member.dal.dataobject.group.MemberGroupDO;
import com.ymitcloud.module.member.service.group.MemberGroupService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 用户分组
 */
@RestController
@RequestMapping("/member/group")
@Validated
public class MemberGroupController {

    @Resource
    private MemberGroupService groupService;

    /**
     * 创建用户分组
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('member:group:create')")
    public CommonResult<Long> createGroup(@Valid @RequestBody MemberGroupCreateReqVO createReqVO) {
        return success(groupService.createGroup(createReqVO));
    }

    /**
     * 更新用户分组
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('member:group:update')")
    public CommonResult<Boolean> updateGroup(@Valid @RequestBody MemberGroupUpdateReqVO updateReqVO) {
        groupService.updateGroup(updateReqVO);
        return success(true);
    }

    /**
     * 删除用户分组
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('member:group:delete')")
    public CommonResult<Boolean> deleteGroup(@RequestParam("id") Long id) {
        groupService.deleteGroup(id);
        return success(true);
    }

    /**
     * 获得用户分组
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('member:group:query')")
    public CommonResult<MemberGroupRespVO> getGroup(@RequestParam("id") Long id) {
        MemberGroupDO group = groupService.getGroup(id);
        return success(MemberGroupConvert.INSTANCE.convert(group));
    }

    /**
     * 获取会员分组精简信息列表
     * 
     * @return
     */
    @GetMapping("/list-all-simple")
    public CommonResult<List<MemberGroupSimpleRespVO>> getSimpleGroupList() {
        // 获用户列表，只要开启状态的
        List<MemberGroupDO> list = groupService.getEnableGroupList();
        return success(MemberGroupConvert.INSTANCE.convertSimpleList(list));
    }

    /**
     * 获得用户分组分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('member:group:query')")
    public CommonResult<PageResult<MemberGroupRespVO>> getGroupPage(@Valid MemberGroupPageReqVO pageVO) {
        PageResult<MemberGroupDO> pageResult = groupService.getGroupPage(pageVO);
        return success(MemberGroupConvert.INSTANCE.convertPage(pageResult));
    }

}
