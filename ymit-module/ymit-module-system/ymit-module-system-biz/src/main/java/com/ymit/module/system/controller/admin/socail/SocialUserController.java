package com.ymit.module.system.controller.admin.socail;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.enums.UserTypeEnum;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.system.controller.admin.socail.vo.user.SocialUserBindReqVO;
import com.ymit.module.system.controller.admin.socail.vo.user.SocialUserPageReqVO;
import com.ymit.module.system.controller.admin.socail.vo.user.SocialUserRespVO;
import com.ymit.module.system.controller.admin.socail.vo.user.SocialUserUnbindReqVO;
import com.ymit.module.system.convert.social.SocialUserConvert;
import com.ymit.module.system.dal.dataobject.social.SocialUserDO;
import com.ymit.module.system.service.social.SocialUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 管理后台 - 社交用户
 */

@RestController
@RequestMapping("/system/social-user")
@Validated
public class SocialUserController {

    @Resource
    private SocialUserService socialUserService;

    /**
     * 社交绑定，使用 code 授权码
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/bind")
    public CommonResult<Boolean> socialBind(@RequestBody @Valid SocialUserBindReqVO reqVO) {
        this.socialUserService.bindSocialUser(SocialUserConvert.convert(getLoginUserId(), UserTypeEnum.ADMIN.getValue(), reqVO));
        return CommonResult.success(true);
    }

    /**
     * 取消社交绑定
     *
     * @param reqVO
     * @return
     */
    @DeleteMapping("/unbind")
    public CommonResult<Boolean> socialUnbind(@RequestBody SocialUserUnbindReqVO reqVO) {
        this.socialUserService.unbindSocialUser(getLoginUserId(), UserTypeEnum.ADMIN.getValue(), reqVO.getType(), reqVO.getOpenid());
        return CommonResult.success(true);
    }

    // ==================== 社交用户 CRUD ====================

    /**
     * 获得社交用户
     *
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:social-user:query')")
    public CommonResult<SocialUserRespVO> getSocialUser(@RequestParam("id") Long id) {
        SocialUserDO socialUser = this.socialUserService.getSocialUser(id);
        return success(BeanUtils.toBean(socialUser, SocialUserRespVO.class));
    }

    /**
     * 获得社交用户分页
     *
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:social-user:query')")
    public CommonResult<PageResult<SocialUserRespVO>> getSocialUserPage(@Valid SocialUserPageReqVO pageVO) {
        PageResult<SocialUserDO> pageResult = this.socialUserService.getSocialUserPage(pageVO);
        return success(BeanUtils.toBean(pageResult, SocialUserRespVO.class));
    }

}
