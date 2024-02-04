package com.ymitcloud.module.system.controller.admin.user;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.ymitcloud.module.infra.enums.ErrorCodeConstants.FILE_IS_EMPTY;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ymitcloud.framework.common.enums.UserTypeEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.datapermission.core.annotation.DataPermission;
import com.ymitcloud.module.system.controller.admin.user.vo.profile.UserProfileRespVO;
import com.ymitcloud.module.system.controller.admin.user.vo.profile.UserProfileUpdatePasswordReqVO;
import com.ymitcloud.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import com.ymitcloud.module.system.convert.user.UserConvert;
import com.ymitcloud.module.system.dal.dataobject.dept.DeptDO;
import com.ymitcloud.module.system.dal.dataobject.dept.PostDO;
import com.ymitcloud.module.system.dal.dataobject.permission.RoleDO;
import com.ymitcloud.module.system.dal.dataobject.social.SocialUserDO;
import com.ymitcloud.module.system.dal.dataobject.user.AdminUserDO;
import com.ymitcloud.module.system.service.dept.DeptService;
import com.ymitcloud.module.system.service.dept.PostService;
import com.ymitcloud.module.system.service.permission.PermissionService;
import com.ymitcloud.module.system.service.permission.RoleService;
import com.ymitcloud.module.system.service.social.SocialUserService;
import com.ymitcloud.module.system.service.user.AdminUserService;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台 - 用户个人中心
 */
@RestController
@RequestMapping("/system/user/profile")
@Validated
@Slf4j
public class UserProfileController {

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
        AdminUserDO user = userService.getUser(getLoginUserId());
        // 获得用户角色
        List<RoleDO> userRoles = roleService
                .getRoleListFromCache(permissionService.getUserRoleIdListByUserId(user.getId()));
        // 获得部门信息
        DeptDO dept = user.getDeptId() != null ? deptService.getDept(user.getDeptId()) : null;
        // 获得岗位信息
        List<PostDO> posts = CollUtil.isNotEmpty(user.getPostIds()) ? postService.getPostList(user.getPostIds()) : null;
        // 获得社交用户信息
        List<SocialUserDO> socialUsers = socialService.getSocialUserList(user.getId(), UserTypeEnum.ADMIN.getValue());
        return success(UserConvert.INSTANCE.convert(user, userRoles, dept, posts, socialUsers));
    }

    /**
     * 修改用户个人信息
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update")
    public CommonResult<Boolean> updateUserProfile(@Valid @RequestBody UserProfileUpdateReqVO reqVO) {
        userService.updateUserProfile(getLoginUserId(), reqVO);
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
        userService.updateUserPassword(getLoginUserId(), reqVO);
        return success(true);
    }
    /**
     * 上传用户个人头像
     * 
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update-avatar", method = { RequestMethod.POST, RequestMethod.PUT }) // 解决 uni-app 不支持 Put
    public CommonResult<String> updateUserAvatar(@RequestParam("avatarFile") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw exception(FILE_IS_EMPTY);
        }
        String avatar = userService.updateUserAvatar(getLoginUserId(), file.getInputStream());
        return success(avatar);
    }

}
