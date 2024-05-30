package com.ymit.module.system.convert.user;

import com.ymit.framework.common.util.collection.CollectionUtils;
import com.ymit.framework.common.util.collection.MapUtils;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.system.controller.admin.dept.vo.dept.DeptSimpleRespVO;
import com.ymit.module.system.controller.admin.dept.vo.post.PostSimpleRespVO;
import com.ymit.module.system.controller.admin.permission.vo.role.RoleSimpleRespVO;
import com.ymit.module.system.controller.admin.user.vo.profile.UserProfileRespVO;
import com.ymit.module.system.controller.admin.user.vo.user.UserRespVO;
import com.ymit.module.system.controller.admin.user.vo.user.UserSimpleRespVO;
import com.ymit.module.system.dal.dataobject.dept.DeptDO;
import com.ymit.module.system.dal.dataobject.dept.PostDO;
import com.ymit.module.system.dal.dataobject.permission.RoleDO;
import com.ymit.module.system.dal.dataobject.social.SocialUserDO;
import com.ymit.module.system.dal.dataobject.user.AdminUserDO;

import java.util.List;
import java.util.Map;

public class UserConvert {


    public static List<UserRespVO> convertList(List<AdminUserDO> list, Map<Long, DeptDO> deptMap) {
        return CollectionUtils.convertList(list, user -> convert(user, deptMap.get(user.getDeptId())));
    }

    public static UserRespVO convert(AdminUserDO user, DeptDO dept) {
        UserRespVO userVO = BeanUtils.toBean(user, UserRespVO.class);
        if (dept != null) {
            userVO.setDeptName(dept.getName());
        }
        return userVO;
    }

    public static List<UserSimpleRespVO> convertSimpleList(List<AdminUserDO> list, Map<Long, DeptDO> deptMap) {
        return CollectionUtils.convertList(list, user -> {
            UserSimpleRespVO userVO = BeanUtils.toBean(user, UserSimpleRespVO.class);
            MapUtils.findAndThen(deptMap, user.getDeptId(), dept -> userVO.setDeptName(dept.getName()));
            return userVO;
        });
    }

    public static UserProfileRespVO convert(AdminUserDO user, List<RoleDO> userRoles, DeptDO dept, List<PostDO> posts, List<SocialUserDO> socialUsers) {
        if (user == null) {
            return null;
        }
        UserProfileRespVO userVO = BeanUtils.toBean(user, UserProfileRespVO.class);
        userVO.setRoles(BeanUtils.toBean(userRoles, RoleSimpleRespVO.class));
        userVO.setDept(BeanUtils.toBean(dept, DeptSimpleRespVO.class));
        userVO.setPosts(BeanUtils.toBean(posts, PostSimpleRespVO.class));
        userVO.setSocialUsers(BeanUtils.toBean(socialUsers, UserProfileRespVO.SocialUser.class));
        return userVO;
    }

}
