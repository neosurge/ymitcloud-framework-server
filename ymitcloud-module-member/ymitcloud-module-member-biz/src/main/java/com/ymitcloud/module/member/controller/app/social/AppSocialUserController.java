package com.ymitcloud.module.member.controller.app.social;

import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.enums.UserTypeEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.member.controller.app.social.vo.AppSocialUserBindReqVO;
import com.ymitcloud.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import com.ymitcloud.module.member.convert.social.SocialUserConvert;
import com.ymitcloud.module.system.api.social.SocialUserApi;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 用户 App - 社交用户
 */
@RestController
@RequestMapping("/system/social-user")
@Validated
public class AppSocialUserController {

    @Resource
    private SocialUserApi socialUserApi;

    /**
     * 社交绑定，使用 code 授权码
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/bind")
    public CommonResult<Boolean> socialBind(@RequestBody @Valid AppSocialUserBindReqVO reqVO) {
        socialUserApi.bindSocialUser(
                SocialUserConvert.INSTANCE.convert(getLoginUserId(), UserTypeEnum.MEMBER.getValue(), reqVO));
        return CommonResult.success(true);
    }

    /**
     * 取消社交绑定
     * 
     * @param reqVO
     * @return
     */
    @DeleteMapping("/unbind")
    @PreAuthenticated
    public CommonResult<Boolean> socialUnbind(@RequestBody AppSocialUserUnbindReqVO reqVO) {
        socialUserApi.unbindSocialUser(
                SocialUserConvert.INSTANCE.convert(getLoginUserId(), UserTypeEnum.MEMBER.getValue(), reqVO));
        return CommonResult.success(true);
    }

}
