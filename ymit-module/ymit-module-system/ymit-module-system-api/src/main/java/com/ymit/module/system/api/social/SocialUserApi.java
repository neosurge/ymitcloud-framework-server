package com.ymit.module.system.api.social;

import com.ymit.framework.common.exception.ServiceException;
import com.ymit.module.system.api.social.dto.SocialUserBindReqDTO;
import com.ymit.module.system.api.social.dto.SocialUserRespDTO;
import com.ymit.module.system.api.social.dto.SocialUserUnbindReqDTO;
import jakarta.validation.Valid;

/**
 * 社交用户的 API 接口
 *
 * @author 云码源码
 */
public interface SocialUserApi {

    /**
     * 绑定社交用户
     *
     * @param reqDTO 绑定信息
     * @return 社交用户 openid
     */
    String bindSocialUser(@Valid SocialUserBindReqDTO reqDTO);

    /**
     * 取消绑定社交用户
     *
     * @param reqDTO 解绑
     */
    void unbindSocialUser(@Valid SocialUserUnbindReqDTO reqDTO);

    /**
     * 获得社交用户
     * <p>
     * 在认证信息不正确的情况下，也会抛出 {@link ServiceException} 业务异常
     *
     * @param userType   用户类型
     * @param socialType 社交平台的类型
     * @param code       授权码
     * @param state      state
     * @return 社交用户
     */
    SocialUserRespDTO getSocialUser(Integer userType, Integer socialType,
                                    String code, String state);

}
