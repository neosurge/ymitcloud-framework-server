package com.ymit.module.system.api.social;

import com.ymit.module.system.api.social.dto.SocialUserBindReqDTO;
import com.ymit.module.system.api.social.dto.SocialUserRespDTO;
import com.ymit.module.system.api.social.dto.SocialUserUnbindReqDTO;
import com.ymit.module.system.service.social.SocialUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 社交用户的 API 实现类
 *
 * @author 云码源码
 */
@Service
@Validated
public class SocialUserApiImpl implements SocialUserApi {

    @Resource
    private SocialUserService socialUserService;

    @Override
    public String bindSocialUser(SocialUserBindReqDTO reqDTO) {
        return socialUserService.bindSocialUser(reqDTO);
    }

    @Override
    public void unbindSocialUser(SocialUserUnbindReqDTO reqDTO) {
        socialUserService.unbindSocialUser(reqDTO.getUserId(), reqDTO.getUserType(),
                reqDTO.getSocialType(), reqDTO.getOpenid());
    }

    @Override
    public SocialUserRespDTO getSocialUser(Integer userType, Integer socialType, String code, String state) {
        return socialUserService.getSocialUser(userType, socialType, code, state);
    }

}
