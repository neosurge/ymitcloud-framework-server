package com.ymitcloud.module.system.controller.admin.oauth2;

import static com.ymitcloud.framework.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;
import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception0;
import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ymitcloud.framework.common.enums.UserTypeEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.util.http.HttpUtils;
import com.ymitcloud.framework.common.util.json.JsonUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.system.controller.admin.oauth2.vo.open.OAuth2OpenAccessTokenRespVO;
import com.ymitcloud.module.system.controller.admin.oauth2.vo.open.OAuth2OpenAuthorizeInfoRespVO;
import com.ymitcloud.module.system.controller.admin.oauth2.vo.open.OAuth2OpenCheckTokenRespVO;
import com.ymitcloud.module.system.convert.oauth2.OAuth2OpenConvert;
import com.ymitcloud.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.ymitcloud.module.system.dal.dataobject.oauth2.OAuth2ApproveDO;
import com.ymitcloud.module.system.dal.dataobject.oauth2.OAuth2ClientDO;
import com.ymitcloud.module.system.enums.oauth2.OAuth2GrantTypeEnum;
import com.ymitcloud.module.system.service.oauth2.OAuth2ApproveService;
import com.ymitcloud.module.system.service.oauth2.OAuth2ClientService;
import com.ymitcloud.module.system.service.oauth2.OAuth2GrantService;
import com.ymitcloud.module.system.service.oauth2.OAuth2TokenService;
import com.ymitcloud.module.system.util.oauth2.OAuth2Utils;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台 - OAuth2.0 授权，提供给外部应用调用为主
 */
@RestController
@RequestMapping("/system/oauth2")
@Validated
@Slf4j
public class OAuth2OpenController {

    @Resource
    private OAuth2GrantService oauth2GrantService;
    @Resource
    private OAuth2ClientService oauth2ClientService;
    @Resource
    private OAuth2ApproveService oauth2ApproveService;
    @Resource
    private OAuth2TokenService oauth2TokenService;

    /**
     * 获得访问令牌，适合 code 授权码模式，默认需要传递 client_id + client_secret 参数
     * 
     * @param request
     * @param grantType    授权类型 @mock code
     * @param code         授权范围 @mock userinfo.read
     * @param redirectUri  重定向 URI
     * @param state        状态
     * @param username     用户名
     * @param password     密码
     * @param scope
     * @param refreshToken
     * @return
     */
    @PostMapping("/token")
    @PermitAll
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<OAuth2OpenAccessTokenRespVO> postAccessToken(HttpServletRequest request,
            @RequestParam("grant_type") String grantType, @RequestParam(value = "code", required = false) String code, // 授权码模式
            @RequestParam(value = "redirect_uri", required = false) String redirectUri, // 授权码模式
            @RequestParam(value = "state", required = false) String state, // 授权码模式
            @RequestParam(value = "username", required = false) String username, // 密码模式
            @RequestParam(value = "password", required = false) String password, // 密码模式
            @RequestParam(value = "scope", required = false) String scope, // 密码模式
            @RequestParam(value = "refresh_token", required = false) String refreshToken) { // 刷新模式
        List<String> scopes = OAuth2Utils.buildScopes(scope);
        // 1.1 校验授权类型
        OAuth2GrantTypeEnum grantTypeEnum = OAuth2GrantTypeEnum.getByGranType(grantType);
        if (grantTypeEnum == null) {
            throw exception0(BAD_REQUEST.getCode(), StrUtil.format("未知授权类型({})", grantType));
        }
        if (grantTypeEnum == OAuth2GrantTypeEnum.IMPLICIT) {
            throw exception0(BAD_REQUEST.getCode(), "Token 接口不支持 implicit 授权模式");
        }

        // 1.2 校验客户端
        String[] clientIdAndSecret = obtainBasicAuthorization(request);
        OAuth2ClientDO client = oauth2ClientService.validOAuthClientFromCache(clientIdAndSecret[0],
                clientIdAndSecret[1], grantType, scopes, redirectUri);

        // 2. 根据授权模式，获取访问令牌
        OAuth2AccessTokenDO accessTokenDO;
        switch (grantTypeEnum) {
        case AUTHORIZATION_CODE:
            accessTokenDO = oauth2GrantService.grantAuthorizationCodeForAccessToken(client.getClientId(), code,
                    redirectUri, state);
            break;
        case PASSWORD:
            accessTokenDO = oauth2GrantService.grantPassword(username, password, client.getClientId(), scopes);
            break;
        case CLIENT_CREDENTIALS:
            accessTokenDO = oauth2GrantService.grantClientCredentials(client.getClientId(), scopes);
            break;
        case REFRESH_TOKEN:
            accessTokenDO = oauth2GrantService.grantRefreshToken(refreshToken, client.getClientId());
            break;
        default:
            throw new IllegalArgumentException("未知授权类型：" + grantType);
        }
        Assert.notNull(accessTokenDO, "访问令牌不能为空"); // 防御性检查
        return success(OAuth2OpenConvert.INSTANCE.convert(accessTokenDO));
    }

    /**
     * 删除访问令牌
     * 
     * @param request
     * @param token   访问令牌
     * @return
     */
    @DeleteMapping("/token")
    @PermitAll
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<Boolean> revokeToken(HttpServletRequest request, @RequestParam("token") String token) {
        // 校验客户端
        String[] clientIdAndSecret = obtainBasicAuthorization(request);
        OAuth2ClientDO client = oauth2ClientService.validOAuthClientFromCache(clientIdAndSecret[0],
                clientIdAndSecret[1], null, null, null);

        // 删除访问令牌
        return success(oauth2GrantService.revokeToken(client.getClientId(), token));
    }

    /**
     * 校验访问令牌
     * 
     * 对应 Spring Security OAuth 的 CheckTokenEndpoint 类的 checkToken 方法
     * 
     * @param request
     * @param token   访问令牌
     * @return
     */
    @PostMapping("/check-token")
    @PermitAll
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<OAuth2OpenCheckTokenRespVO> checkToken(HttpServletRequest request,
            @RequestParam("token") String token) {
        // 校验客户端
        String[] clientIdAndSecret = obtainBasicAuthorization(request);
        oauth2ClientService.validOAuthClientFromCache(clientIdAndSecret[0], clientIdAndSecret[1], null, null, null);

        // 校验令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.checkAccessToken(token);
        Assert.notNull(accessTokenDO, "访问令牌不能为空"); // 防御性检查
        return success(OAuth2OpenConvert.INSTANCE.convert2(accessTokenDO));
    }

    /**
     * 获得授权信息 适合 code 授权码模式
     *
     * @param clientId 客户端编号
     * @return
     */
    @GetMapping("/authorize")
    public CommonResult<OAuth2OpenAuthorizeInfoRespVO> authorize(@RequestParam("clientId") String clientId) {
        // 0. 校验用户已经登录。通过 Spring Security 实现

        // 1. 获得 Client 客户端的信息
        OAuth2ClientDO client = oauth2ClientService.validOAuthClientFromCache(clientId);
        // 2. 获得用户已经授权的信息
        List<OAuth2ApproveDO> approves = oauth2ApproveService.getApproveList(getLoginUserId(), getUserType(), clientId);
        // 拼接返回
        return success(OAuth2OpenConvert.INSTANCE.convert(client, approves));
    }

    /**
     * 申请授权 适合 code 授权码模式
     * 
     * @param responseType 响应类型 @mock code
     * @param clientId     客户端编号
     * @param scope        授权范围
     * @param redirectUri  重定向 URI
     * @param autoApprove  用户是否接受
     * @param state
     * @return
     */
    @PostMapping("/authorize")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<String> approveOrDeny(@RequestParam("response_type") String responseType,
            @RequestParam("client_id") String clientId, @RequestParam(value = "scope", required = false) String scope,
            @RequestParam("redirect_uri") String redirectUri, @RequestParam(value = "auto_approve") Boolean autoApprove,
            @RequestParam(value = "state", required = false) String state) {
        @SuppressWarnings("unchecked")
        Map<String, Boolean> scopes = JsonUtils.parseObject(scope, Map.class);
        scopes = ObjectUtil.defaultIfNull(scopes, Collections.emptyMap());
        // 0. 校验用户已经登录。通过 Spring Security 实现

        // 1.1 校验 responseType 是否满足 code 或者 token 值
        OAuth2GrantTypeEnum grantTypeEnum = getGrantTypeEnum(responseType);
        // 1.2 校验 redirectUri 重定向域名是否合法 + 校验 scope 是否在 Client 授权范围内
        OAuth2ClientDO client = oauth2ClientService.validOAuthClientFromCache(clientId, null,
                grantTypeEnum.getGrantType(), scopes.keySet(), redirectUri);

        // 2.1 假设 approved 为 null，说明是场景一
        if (Boolean.TRUE.equals(autoApprove)) {
            // 如果无法自动授权通过，则返回空 url，前端不进行跳转
            if (!oauth2ApproveService.checkForPreApproval(getLoginUserId(), getUserType(), clientId, scopes.keySet())) {
                return success(null);
            }
        } else { // 2.2 假设 approved 非 null，说明是场景二
            // 如果计算后不通过，则跳转一个错误链接
            if (!oauth2ApproveService.updateAfterApproval(getLoginUserId(), getUserType(), clientId, scopes)) {
                return success(OAuth2Utils.buildUnsuccessfulRedirect(redirectUri, responseType, state, "access_denied",
                        "User denied access"));
            }
        }

        // 3.1 如果是 code 授权码模式，则发放 code 授权码，并重定向
        List<String> approveScopes = convertList(scopes.entrySet(), Map.Entry::getKey, Map.Entry::getValue);
        if (grantTypeEnum == OAuth2GrantTypeEnum.AUTHORIZATION_CODE) {
            return success(getAuthorizationCodeRedirect(getLoginUserId(), client, approveScopes, redirectUri, state));
        }
        // 3.2 如果是 token 则是 implicit 简化模式，则发送 accessToken 访问令牌，并重定向
        return success(getImplicitGrantRedirect(getLoginUserId(), client, approveScopes, redirectUri, state));
    }

    private static OAuth2GrantTypeEnum getGrantTypeEnum(String responseType) {
        if (StrUtil.equals(responseType, "code")) {
            return OAuth2GrantTypeEnum.AUTHORIZATION_CODE;
        }
        if (StrUtil.equalsAny(responseType, "token")) {
            return OAuth2GrantTypeEnum.IMPLICIT;
        }
        throw exception0(BAD_REQUEST.getCode(), "response_type 参数值只允许 code 和 token");
    }

    private String getImplicitGrantRedirect(Long userId, OAuth2ClientDO client, List<String> scopes, String redirectUri,
            String state) {
        // 1. 创建 access token 访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2GrantService.grantImplicit(userId, getUserType(),
                client.getClientId(), scopes);
        Assert.notNull(accessTokenDO, "访问令牌不能为空"); // 防御性检查
        // 2. 拼接重定向的 URL
        // noinspection unchecked
        return OAuth2Utils.buildImplicitRedirectUri(redirectUri, accessTokenDO.getAccessToken(), state,
                accessTokenDO.getExpiresTime(), scopes,
                JsonUtils.parseObject(client.getAdditionalInformation(), Map.class));
    }

    private String getAuthorizationCodeRedirect(Long userId, OAuth2ClientDO client, List<String> scopes,
            String redirectUri, String state) {
        // 1. 创建 code 授权码
        String authorizationCode = oauth2GrantService.grantAuthorizationCodeForCode(userId, getUserType(),
                client.getClientId(), scopes, redirectUri, state);
        // 2. 拼接重定向的 URL
        return OAuth2Utils.buildAuthorizationCodeRedirectUri(redirectUri, authorizationCode, state);
    }

    private Integer getUserType() {
        return UserTypeEnum.ADMIN.getValue();
    }

    private String[] obtainBasicAuthorization(HttpServletRequest request) {
        String[] clientIdAndSecret = HttpUtils.obtainBasicAuthorization(request);
        if (ArrayUtil.isEmpty(clientIdAndSecret) || clientIdAndSecret.length != 2) {
            throw exception0(BAD_REQUEST.getCode(), "client_id 或 client_secret 未正确传递");
        }
        return clientIdAndSecret;
    }

}
