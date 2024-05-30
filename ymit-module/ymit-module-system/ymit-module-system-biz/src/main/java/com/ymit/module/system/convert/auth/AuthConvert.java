package com.ymit.module.system.convert.auth;

import cn.hutool.core.collection.CollUtil;
import com.ymit.framework.common.util.collection.CollectionUtils;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import com.ymit.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import com.ymit.module.system.api.social.dto.SocialUserBindReqDTO;
import com.ymit.module.system.controller.admin.auth.vo.*;
import com.ymit.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.ymit.module.system.dal.dataobject.permission.MenuDO;
import com.ymit.module.system.dal.dataobject.permission.RoleDO;
import com.ymit.module.system.dal.dataobject.user.AdminUserDO;
import com.ymit.module.system.enums.permission.MenuTypeEnum;
import org.slf4j.LoggerFactory;

import java.util.*;


public class AuthConvert {
    public static AuthLoginRespVO convert(OAuth2AccessTokenDO bean) {
        if (bean == null) {
            return null;
        }
        return BeanUtils.toBean(bean, AuthLoginRespVO.class);
    }

    public static AuthPermissionInfoRespVO convert(AdminUserDO user, List<RoleDO> roleList, List<MenuDO> menuList) {
        return AuthPermissionInfoRespVO.builder().user(AuthPermissionInfoRespVO.UserVO.builder().id(user.getId()).nickname(user.getNickname()).avatar(user.getAvatar()).build()).roles(CollectionUtils.convertSet(roleList, RoleDO::getCode))
                // 权限标识信息
                .permissions(CollectionUtils.convertSet(menuList, MenuDO::getPermission))
                // 菜单树
                .menus(buildMenuTree(menuList)).build();
    }

    public static AuthPermissionInfoRespVO.MenuVO convertTreeNode(MenuDO menu) {
        if (menu == null) {
            return null;
        }
        return BeanUtils.toBean(menu, AuthPermissionInfoRespVO.MenuVO.class);
    }

    /**
     * 将菜单列表，构建成菜单树
     *
     * @param menuList 菜单列表
     * @return 菜单树
     */
    public static List<AuthPermissionInfoRespVO.MenuVO> buildMenuTree(List<MenuDO> menuList) {
        if (CollUtil.isEmpty(menuList)) {
            return Collections.emptyList();
        }
        // 移除按钮
        menuList.removeIf(menu -> menu.getType().equals(MenuTypeEnum.BUTTON.getType()));
        // 排序，保证菜单的有序性
        menuList.sort((a, b) -> b.getSort() - a.getSort());

        // 构建菜单树
        // 使用 LinkedHashMap 的原因，是为了排序 。实际也可以用 Stream API ，就是太丑了。
        Map<Long, AuthPermissionInfoRespVO.MenuVO> treeNodeMap = new LinkedHashMap<>();
        menuList.forEach(menu -> treeNodeMap.put(menu.getId(), AuthConvert.convertTreeNode(menu)));
        // 处理父子关系
        treeNodeMap.values().stream().filter(node -> !node.getParentId().equals(MenuDO.ID_ROOT)).forEach(childNode -> {
            // 获得父节点
            AuthPermissionInfoRespVO.MenuVO parentNode = treeNodeMap.get(childNode.getParentId());
            if (parentNode == null) {
                LoggerFactory.getLogger(AuthConvert.class).error("[buildRouterTree][resource({}) 找不到父资源({})]", childNode.getId(), childNode.getParentId());
                return;
            }
            // 将自己添加到父节点中
            if (parentNode.getChildren() == null) {
                parentNode.setChildren(new ArrayList<>());
            }
            parentNode.getChildren().add(childNode);
        });
        // 获得到所有的根节点
        return CollectionUtils.filterList(treeNodeMap.values(), node -> MenuDO.ID_ROOT.equals(node.getParentId()));
    }

    public static SocialUserBindReqDTO convert(Long userId, Integer userType, AuthSocialLoginReqVO reqVO) {
        if (reqVO == null) {
            return null;
        }
        SocialUserBindReqDTO data = new SocialUserBindReqDTO();
        data.setUserId(userId);
        data.setUserType(userType);
        data.setSocialType(reqVO.getType());
        data.setCode(reqVO.getCode());
        data.setState(reqVO.getState());
        return data;
    }

    public static SmsCodeSendReqDTO convert(AuthSmsSendReqVO reqVO) {
        if (reqVO == null) {
            return null;
        }
        return BeanUtils.toBean(reqVO, SmsCodeSendReqDTO.class);
    }

    public static SmsCodeUseReqDTO convert(AuthSmsLoginReqVO reqVO, Integer scene, String usedIp) {
        if (reqVO == null) {
            return null;
        }
        SmsCodeUseReqDTO data = new SmsCodeUseReqDTO();
        data.setMobile(reqVO.getMobile());
        data.setScene(scene);
        data.setCode(reqVO.getCode());
        data.setUsedIp(usedIp);
        return data;

    }

}
