package com.ymitcloud.module.member.controller.admin.signin;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.member.controller.admin.signin.vo.config.MemberSignInConfigCreateReqVO;
import com.ymitcloud.module.member.controller.admin.signin.vo.config.MemberSignInConfigRespVO;
import com.ymitcloud.module.member.controller.admin.signin.vo.config.MemberSignInConfigUpdateReqVO;
import com.ymitcloud.module.member.convert.signin.MemberSignInConfigConvert;
import com.ymitcloud.module.member.dal.dataobject.signin.MemberSignInConfigDO;
import com.ymitcloud.module.member.service.signin.MemberSignInConfigService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 签到规则
 */
@RestController
@RequestMapping("/member/sign-in/config")
@Validated
public class MemberSignInConfigController {

    @Resource
    private MemberSignInConfigService signInConfigService;

    /**
     * 创建签到规则
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('point:sign-in-config:create')")
    public CommonResult<Long> createSignInConfig(@Valid @RequestBody MemberSignInConfigCreateReqVO createReqVO) {
        return success(signInConfigService.createSignInConfig(createReqVO));
    }

    /**
     * 更新签到规则
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('point:sign-in-config:update')")
    public CommonResult<Boolean> updateSignInConfig(@Valid @RequestBody MemberSignInConfigUpdateReqVO updateReqVO) {
        signInConfigService.updateSignInConfig(updateReqVO);
        return success(true);
    }

    /**
     * 删除签到规则
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('point:sign-in-config:delete')")
    public CommonResult<Boolean> deleteSignInConfig(@RequestParam("id") Long id) {
        signInConfigService.deleteSignInConfig(id);
        return success(true);
    }

    /**
     * 获得签到规则
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('point:sign-in-config:query')")
    public CommonResult<MemberSignInConfigRespVO> getSignInConfig(@RequestParam("id") Long id) {
        MemberSignInConfigDO signInConfig = signInConfigService.getSignInConfig(id);
        return success(MemberSignInConfigConvert.INSTANCE.convert(signInConfig));
    }

    /**
     * 获得签到规则列表
     * 
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('point:sign-in-config:query')")
    public CommonResult<List<MemberSignInConfigRespVO>> getSignInConfigList() {
        List<MemberSignInConfigDO> list = signInConfigService.getSignInConfigList();
        return success(MemberSignInConfigConvert.INSTANCE.convertList(list));
    }

}
