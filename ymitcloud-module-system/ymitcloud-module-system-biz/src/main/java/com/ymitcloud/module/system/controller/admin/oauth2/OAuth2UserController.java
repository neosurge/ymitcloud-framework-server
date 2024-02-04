package com.ymitcloud.module.system.controller.admin.oauth2;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.system.controller.admin.oauth2.vo.user.OAuth2UserInfoRespVO;
import com.ymitcloud.module.system.controller.admin.oauth2.vo.user.OAuth2UserUpdateReqVO;
import com.ymitcloud.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import com.ymitcloud.module.system.dal.dataobject.dept.DeptDO;
import com.ymitcloud.module.system.dal.dataobject.dept.PostDO;
import com.ymitcloud.module.system.dal.dataobject.user.AdminUserDO;
import com.ymitcloud.module.system.service.dept.DeptService;
import com.ymitcloud.module.system.service.dept.PostService;
import com.ymitcloud.module.system.service.user.AdminUserService;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台 - OAuth2.0 用户 提供给外部应用调用为主
 *
 * @author 云码源码
 */
@RestController
@RequestMapping("/system/oauth2/user")
@Validated
@Slf4j
public class OAuth2UserController {

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PostService postService;

    /**
     * 获得用户基本信息
     * 
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasScope('user.read')") //
    public CommonResult<OAuth2UserInfoRespVO> getUserInfo() {
        // 获得用户基本信息
        AdminUserDO user = userService.getUser(getLoginUserId());
        OAuth2UserInfoRespVO resp = BeanUtils.toBean(user, OAuth2UserInfoRespVO.class);
        // 获得部门信息
        if (user.getDeptId() != null) {
            DeptDO dept = deptService.getDept(user.getDeptId());
            resp.setDept(BeanUtils.toBean(dept, OAuth2UserInfoRespVO.Dept.class));
        }
        // 获得岗位信息
        if (CollUtil.isNotEmpty(user.getPostIds())) {
            List<PostDO> posts = postService.getPostList(user.getPostIds());
            resp.setPosts(BeanUtils.toBean(posts, OAuth2UserInfoRespVO.Post.class));
        }
        return success(resp);
    }

    /**
     * 更新用户基本信息
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasScope('user.write')")
    public CommonResult<Boolean> updateUserInfo(@Valid @RequestBody OAuth2UserUpdateReqVO reqVO) {
        // 这里将 UserProfileUpdateReqVO =》UserProfileUpdateReqVO 对象，实现接口的复用。
        // 主要是，AdminUserService 没有自己的 BO 对象，所以复用只能这么做
        userService.updateUserProfile(getLoginUserId(), BeanUtils.toBean(reqVO, UserProfileUpdateReqVO.class));
        return success(true);
    }

}
