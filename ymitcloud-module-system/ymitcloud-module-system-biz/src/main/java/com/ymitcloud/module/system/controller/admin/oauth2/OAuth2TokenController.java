package com.ymitcloud.module.system.controller.admin.oauth2;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.system.controller.admin.oauth2.vo.token.OAuth2AccessTokenPageReqVO;
import com.ymitcloud.module.system.controller.admin.oauth2.vo.token.OAuth2AccessTokenRespVO;
import com.ymitcloud.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.ymitcloud.module.system.enums.logger.LoginLogTypeEnum;
import com.ymitcloud.module.system.service.auth.AdminAuthService;
import com.ymitcloud.module.system.service.oauth2.OAuth2TokenService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - OAuth2.0 令牌
 */
@RestController
@RequestMapping("/system/oauth2-token")
public class OAuth2TokenController {

    @Resource
    private OAuth2TokenService oauth2TokenService;
    @Resource
    private AdminAuthService authService;

    /**
     * 获得访问令牌分页，只返回有效期内的
     * 
     * @param reqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:oauth2-token:page')")
    public CommonResult<PageResult<OAuth2AccessTokenRespVO>> getAccessTokenPage(
            @Valid OAuth2AccessTokenPageReqVO reqVO) {
        PageResult<OAuth2AccessTokenDO> pageResult = oauth2TokenService.getAccessTokenPage(reqVO);
        return success(BeanUtils.toBean(pageResult, OAuth2AccessTokenRespVO.class));
    }

    /**
     * 删除访问令牌
     * 
     * @param accessToken 访问令牌
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:oauth2-token:delete')")
    public CommonResult<Boolean> deleteAccessToken(@RequestParam("accessToken") String accessToken) {
        authService.logout(accessToken, LoginLogTypeEnum.LOGOUT_DELETE.getType());
        return success(true);
    }

}
