package com.ymitcloud.module.member.controller.app.signin;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.member.controller.app.signin.vo.config.AppMemberSignInConfigRespVO;
import com.ymitcloud.module.member.convert.signin.MemberSignInConfigConvert;
import com.ymitcloud.module.member.dal.dataobject.signin.MemberSignInConfigDO;
import com.ymitcloud.module.member.service.signin.MemberSignInConfigService;

import jakarta.annotation.Resource;

/**
 * 用户 App - 签到规则
 */
@RestController
@RequestMapping("/member/sign-in/config")
@Validated
public class AppMemberSignInConfigController {

    @Resource
    private MemberSignInConfigService signInConfigService;

    /**
     * 获得签到规则列表
     * 
     * @return
     */
    @GetMapping("/list")
    public CommonResult<List<AppMemberSignInConfigRespVO>> getSignInConfigList() {
        List<MemberSignInConfigDO> pageResult = signInConfigService
                .getSignInConfigList(CommonStatusEnum.ENABLE.getStatus());
        return success(MemberSignInConfigConvert.INSTANCE.convertList02(pageResult));
    }

}
