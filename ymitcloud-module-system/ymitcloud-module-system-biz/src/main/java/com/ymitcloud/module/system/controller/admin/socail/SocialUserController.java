package com.ymitcloud.module.system.controller.admin.socail;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.enums.UserTypeEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.system.controller.admin.socail.vo.user.SocialUserBindReqVO;
import com.ymitcloud.module.system.controller.admin.socail.vo.user.SocialUserPageReqVO;
import com.ymitcloud.module.system.controller.admin.socail.vo.user.SocialUserRespVO;
import com.ymitcloud.module.system.controller.admin.socail.vo.user.SocialUserUnbindReqVO;
import com.ymitcloud.module.system.convert.social.SocialUserConvert;
import com.ymitcloud.module.system.dal.dataobject.social.SocialUserDO;
import com.ymitcloud.module.system.service.social.SocialUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

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
        socialUserService.bindSocialUser(
                SocialUserConvert.INSTANCE.convert(getLoginUserId(), UserTypeEnum.ADMIN.getValue(), reqVO));
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
        socialUserService.unbindSocialUser(getLoginUserId(), UserTypeEnum.ADMIN.getValue(), reqVO.getType(),
                reqVO.getOpenid());
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
        SocialUserDO socialUser = socialUserService.getSocialUser(id);
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
        PageResult<SocialUserDO> pageResult = socialUserService.getSocialUserPage(pageVO);
        return success(BeanUtils.toBean(pageResult, SocialUserRespVO.class));
    }

}
