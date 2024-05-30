package com.ymit.module.system.controller.admin.user.vo.profile;

import com.ymit.module.system.controller.admin.dept.vo.dept.DeptSimpleRespVO;
import com.ymit.module.system.controller.admin.dept.vo.post.PostSimpleRespVO;
import com.ymit.module.system.controller.admin.permission.vo.role.RoleSimpleRespVO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理后台 - 用户个人中心信息 Response VO
 */
@Data
public class UserProfileRespVO {
    /**
     * 用户编号
     */
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户性别，参见 SexEnum 枚举类
     */
    private Integer sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 最后登录 IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 所属角色
     */
    private List<RoleSimpleRespVO> roles;
    /**
     * 所在部门
     */
    private DeptSimpleRespVO dept;
    /**
     * 所属岗位数组
     */
    private List<PostSimpleRespVO> posts;
    /**
     * 社交用户数组
     */
    private List<SocialUser> socialUsers;

    /**
     * 社交用户
     */
    @Data
    public static class SocialUser {

        /**
         * 社交平台的类型，参见 SocialTypeEnum 枚举类
         */
        private Integer type;

        /**
         * 社交用户的 openid
         */
        private String openid;

    }

}
