package com.ymit.module.system.convert.oauth2;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.ymit.framework.common.core.KeyValue;
import com.ymit.framework.common.enums.UserTypeEnum;
import com.ymit.framework.common.util.collection.CollectionUtils;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.security.core.util.SecurityFrameworkUtils;
import com.ymit.module.system.controller.admin.oauth2.vo.open.OAuth2OpenAccessTokenRespVO;
import com.ymit.module.system.controller.admin.oauth2.vo.open.OAuth2OpenAuthorizeInfoRespVO;
import com.ymit.module.system.controller.admin.oauth2.vo.open.OAuth2OpenCheckTokenRespVO;
import com.ymit.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.ymit.module.system.dal.dataobject.oauth2.OAuth2ApproveDO;
import com.ymit.module.system.dal.dataobject.oauth2.OAuth2ClientDO;
import com.ymit.module.system.util.oauth2.OAuth2Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OAuth2OpenConvert {


    public static OAuth2OpenAccessTokenRespVO convert(OAuth2AccessTokenDO bean) {
        if (bean == null) {
            return null;
        }
        OAuth2OpenAccessTokenRespVO respVO = BeanUtils.toBean(bean, OAuth2OpenAccessTokenRespVO.class);
        respVO.setTokenType(SecurityFrameworkUtils.AUTHORIZATION_BEARER.toLowerCase());
        respVO.setExpiresIn(OAuth2Utils.getExpiresIn(bean.getExpiresTime()));
        respVO.setScope(OAuth2Utils.buildScopeStr(bean.getScopes()));
        return respVO;
    }

    public static OAuth2OpenCheckTokenRespVO convert2(OAuth2AccessTokenDO bean) {
        if (bean == null) {
            return null;
        }
        OAuth2OpenCheckTokenRespVO respVO = BeanUtils.toBean(bean, OAuth2OpenCheckTokenRespVO.class);
        respVO.setExp(LocalDateTimeUtil.toEpochMilli(bean.getExpiresTime()) / 1000L);
        respVO.setUserType(UserTypeEnum.ADMIN.getValue());
        return respVO;
    }

    public static OAuth2OpenAuthorizeInfoRespVO convert(OAuth2ClientDO client, List<OAuth2ApproveDO> approves) {
        // 构建 scopes
        List<KeyValue<String, Boolean>> scopes = new ArrayList<>(client.getScopes().size());
        Map<String, OAuth2ApproveDO> approveMap = CollectionUtils.convertMap(approves, OAuth2ApproveDO::getScope);
        client.getScopes().forEach(scope -> {
            OAuth2ApproveDO approve = approveMap.get(scope);
            scopes.add(new KeyValue<>(scope, approve != null ? approve.getApproved() : false));
        });
        // 拼接返回
        return new OAuth2OpenAuthorizeInfoRespVO(new OAuth2OpenAuthorizeInfoRespVO.Client(client.getName(), client.getLogo()), scopes);
    }

}
