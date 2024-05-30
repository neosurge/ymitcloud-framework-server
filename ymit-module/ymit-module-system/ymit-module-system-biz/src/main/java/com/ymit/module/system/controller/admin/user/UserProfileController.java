package com.ymit.module.system.controller.admin.user;

import cn.hutool.core.collection.CollUtil;
import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.enums.UserTypeEnum;
import com.ymit.framework.common.exception.ErrorCode;
import com.ymit.framework.datapermission.core.annotation.DataPermission;
import com.ymit.module.system.controller.admin.user.vo.profile.UserProfileRespVO;
import com.ymit.module.system.controller.admin.user.vo.profile.UserProfileUpdatePasswordReqVO;
import com.ymit.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import com.ymit.module.system.convert.user.UserConvert;
import com.ymit.module.system.dal.dataobject.dept.DeptDO;
import com.ymit.module.system.dal.dataobject.dept.PostDO;
import com.ymit.module.system.dal.dataobject.permission.RoleDO;
import com.ymit.module.system.dal.dataobject.social.SocialUserDO;
import com.ymit.module.system.dal.dataobject.user.AdminUserDO;
import com.ymit.module.system.service.dept.DeptService;
import com.ymit.module.system.service.dept.PostService;
import com.ymit.module.system.service.permission.PermissionService;
import com.ymit.module.system.service.permission.RoleService;
import com.ymit.module.system.service.social.SocialUserService;
import com.ymit.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymit.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 管理后台 - 用户个人中心
 */
@RestController
@RequestMapping("/system/user/profile")
@Validated
@Slf4j
public class UserProfileController {

    //TODO:此处需要处理
    private static final ErrorCode FILE_IS_EMPTY = new ErrorCode(333, "文件不存在");
    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PostService postService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;
    @Resource
    private SocialUserService socialService;

    /**
     * 获得登录用户信息
     *
     * @return
     */
    @GetMapping("/get")
    @DataPermission(enable = false) // 关闭数据权限，避免只查看自己时，查询不到部门。
    public CommonResult<UserProfileRespVO> getUserProfile() {
        // 获得用户基本信息
        AdminUserDO user = this.userService.getUser(getLoginUserId());
        // 获得用户角色
        List<RoleDO> userRoles = this.roleService
                .getRoleListFromCache(this.permissionService.getUserRoleIdListByUserId(user.getId()));
        // 获得部门信息
        DeptDO dept = user.getDeptId() != null ? this.deptService.getDept(user.getDeptId()) : null;
        // 获得岗位信息
        List<PostDO> posts = CollUtil.isNotEmpty(user.getPostIds()) ? this.postService.getPostList(user.getPostIds()) : null;
        // 获得社交用户信息
        List<SocialUserDO> socialUsers = this.socialService.getSocialUserList(user.getId(), UserTypeEnum.ADMIN.getValue());
        return success(UserConvert.convert(user, userRoles, dept, posts, socialUsers));
    }

    /**
     * 修改用户个人信息
     *
     * @param reqVO
     * @return
     */
    @PutMapping("/update")
    public CommonResult<Boolean> updateUserProfile(@Valid @RequestBody UserProfileUpdateReqVO reqVO) {
        this.userService.updateUserProfile(getLoginUserId(), reqVO);
        return success(true);
    }

    /**
     * 修改用户个人密码
     *
     * @param reqVO
     * @return
     */
    @PutMapping("/update-password")
    public CommonResult<Boolean> updateUserProfilePassword(@Valid @RequestBody UserProfileUpdatePasswordReqVO reqVO) {
        this.userService.updateUserPassword(getLoginUserId(), reqVO);
        return success(true);
    }

    /**
     * 上传用户个人头像
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update-avatar", method = {RequestMethod.POST, RequestMethod.PUT}) // 解决 uni-app 不支持 Put
    public CommonResult<String> updateUserAvatar(@RequestParam("avatarFile") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw exception(FILE_IS_EMPTY);
        }
        String avatar = this.userService.updateUserAvatar(getLoginUserId(), file.getInputStream());
        return success(avatar);
    }

}
