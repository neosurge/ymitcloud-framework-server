package com.ymitcloud.module.member.controller.admin.user;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymitcloud.framework.web.core.util.WebFrameworkUtils.getLoginUserId;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.member.controller.admin.user.vo.MemberUserPageReqVO;
import com.ymitcloud.module.member.controller.admin.user.vo.MemberUserRespVO;
import com.ymitcloud.module.member.controller.admin.user.vo.MemberUserUpdateLevelReqVO;
import com.ymitcloud.module.member.controller.admin.user.vo.MemberUserUpdatePointReqVO;
import com.ymitcloud.module.member.controller.admin.user.vo.MemberUserUpdateReqVO;

import com.ymitcloud.module.member.convert.user.MemberUserConvert;
import com.ymitcloud.module.member.dal.dataobject.group.MemberGroupDO;
import com.ymitcloud.module.member.dal.dataobject.level.MemberLevelDO;
import com.ymitcloud.module.member.dal.dataobject.tag.MemberTagDO;
import com.ymitcloud.module.member.dal.dataobject.user.MemberUserDO;
import com.ymitcloud.module.member.enums.point.MemberPointBizTypeEnum;
import com.ymitcloud.module.member.service.group.MemberGroupService;
import com.ymitcloud.module.member.service.level.MemberLevelService;
import com.ymitcloud.module.member.service.point.MemberPointRecordService;
import com.ymitcloud.module.member.service.tag.MemberTagService;
import com.ymitcloud.module.member.service.user.MemberUserService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 会员用户
 */

@RestController
@RequestMapping("/member/user")
@Validated
public class MemberUserController {

    @Resource
    private MemberUserService memberUserService;
    @Resource
    private MemberTagService memberTagService;
    @Resource
    private MemberLevelService memberLevelService;
    @Resource
    private MemberGroupService memberGroupService;
    @Resource
    private MemberPointRecordService memberPointRecordService;


    /**
     * 更新会员用户
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('member:user:update')")
    public CommonResult<Boolean> updateUser(@Valid @RequestBody MemberUserUpdateReqVO updateReqVO) {
        memberUserService.updateUser(updateReqVO);
        return success(true);
    }


    /**
     * 更新会员用户等级
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update-level")

    @PreAuthorize("@ss.hasPermission('member:user:update-level')")
    public CommonResult<Boolean> updateUserLevel(@Valid @RequestBody MemberUserUpdateLevelReqVO updateReqVO) {
        memberLevelService.updateUserLevel(updateReqVO);
        return success(true);
    }


    /**
     * 更新会员用户积分
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update-point")

    @PreAuthorize("@ss.hasPermission('member:user:update-point')")
    public CommonResult<Boolean> updateUserPoint(@Valid @RequestBody MemberUserUpdatePointReqVO updateReqVO) {
        memberPointRecordService.createPointRecord(updateReqVO.getId(), updateReqVO.getPoint(),
                MemberPointBizTypeEnum.ADMIN, String.valueOf(getLoginUserId()));
        return success(true);
    }


    /**
     * 更新会员用户余额
     * 
     * @param id
     * @return
     */
    @PutMapping("/update-balance")

    @PreAuthorize("@ss.hasPermission('member:user:update-balance')")
    public CommonResult<Boolean> updateUserBalance(@Valid @RequestBody Long id) {
        // todo @jason：增加一个【修改余额】
        return success(true);
    }


    /**
     * 获得会员用户
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('member:user:query')")
    public CommonResult<MemberUserRespVO> getUser(@RequestParam("id") Long id) {
        MemberUserDO user = memberUserService.getUser(id);
        return success(MemberUserConvert.INSTANCE.convert03(user));
    }


    /**
     * 获得会员用户分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('member:user:query')")
    public CommonResult<PageResult<MemberUserRespVO>> getUserPage(@Valid MemberUserPageReqVO pageVO) {
        PageResult<MemberUserDO> pageResult = memberUserService.getUserPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty());
        }

        // 处理用户标签返显

        Set<Long> tagIds = pageResult.getList().stream().map(MemberUserDO::getTagIds).filter(Objects::nonNull)
                .flatMap(Collection::stream).collect(Collectors.toSet());
        List<MemberTagDO> tags = memberTagService.getTagList(tagIds);
        // 处理用户级别返显
        List<MemberLevelDO> levels = memberLevelService
                .getLevelList(convertSet(pageResult.getList(), MemberUserDO::getLevelId));
        // 处理用户分组返显
        List<MemberGroupDO> groups = memberGroupService
                .getGroupList(convertSet(pageResult.getList(), MemberUserDO::getGroupId));

        return success(MemberUserConvert.INSTANCE.convertPage(pageResult, tags, levels, groups));
    }

}
