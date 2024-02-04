package com.ymitcloud.module.member.controller.app.user;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.member.controller.app.user.vo.AppMemberUserInfoRespVO;
import com.ymitcloud.module.member.controller.app.user.vo.AppMemberUserResetPasswordReqVO;
import com.ymitcloud.module.member.controller.app.user.vo.AppMemberUserUpdateMobileReqVO;
import com.ymitcloud.module.member.controller.app.user.vo.AppMemberUserUpdatePasswordReqVO;
import com.ymitcloud.module.member.controller.app.user.vo.AppMemberUserUpdateReqVO;

import com.ymitcloud.module.member.convert.user.MemberUserConvert;
import com.ymitcloud.module.member.dal.dataobject.level.MemberLevelDO;
import com.ymitcloud.module.member.dal.dataobject.user.MemberUserDO;
import com.ymitcloud.module.member.service.level.MemberLevelService;
import com.ymitcloud.module.member.service.user.MemberUserService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 APP - 用户个人中心
 */

@RestController
@RequestMapping("/member/user")
@Validated
@Slf4j
public class AppMemberUserController {

    @Resource
    private MemberUserService userService;
    @Resource
    private MemberLevelService levelService;


    /**
     * 获得基本信息
     * 
     * @return
     */
    @GetMapping("/get")

    @PreAuthenticated
    public CommonResult<AppMemberUserInfoRespVO> getUserInfo() {
        MemberUserDO user = userService.getUser(getLoginUserId());
        MemberLevelDO level = levelService.getLevel(user.getLevelId());
        return success(MemberUserConvert.INSTANCE.convert(user, level));
    }


    /**
     * 修改基本信息
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthenticated
    public CommonResult<Boolean> updateUser(@RequestBody @Valid AppMemberUserUpdateReqVO reqVO) {
        userService.updateUser(getLoginUserId(), reqVO);
        return success(true);
    }


    /**
     * 修改用户手机
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update-mobile")

    @PreAuthenticated
    public CommonResult<Boolean> updateUserMobile(@RequestBody @Valid AppMemberUserUpdateMobileReqVO reqVO) {
        userService.updateUserMobile(getLoginUserId(), reqVO);
        return success(true);
    }


    /**
     * 修改用户密码
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update-password")

    @PreAuthenticated
    public CommonResult<Boolean> updatePassword(@RequestBody @Valid AppMemberUserUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(getLoginUserId(), reqVO);
        return success(true);
    }


    /**
     * 重置密码
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/reset-password")

    public CommonResult<Boolean> resetPassword(@RequestBody @Valid AppMemberUserResetPasswordReqVO reqVO) {
        userService.resetUserPassword(reqVO);
        return success(true);
    }

}


