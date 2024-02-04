package com.ymitcloud.module.member.controller.admin.config;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.member.controller.admin.config.vo.MemberConfigRespVO;
import com.ymitcloud.module.member.controller.admin.config.vo.MemberConfigSaveReqVO;
import com.ymitcloud.module.member.convert.config.MemberConfigConvert;
import com.ymitcloud.module.member.dal.dataobject.config.MemberConfigDO;
import com.ymitcloud.module.member.service.config.MemberConfigService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 会员设置
 */
@RestController
@RequestMapping("/member/config")
@Validated
public class MemberConfigController {

    @Resource
    private MemberConfigService memberConfigService;
    /**
     * 保存会员配置
     * 
     * @param saveReqVO
     * @return
     */
    @PutMapping("/save")
    @PreAuthorize("@ss.hasPermission('member:config:save')")
    public CommonResult<Boolean> saveConfig(@Valid @RequestBody MemberConfigSaveReqVO saveReqVO) {
        memberConfigService.saveConfig(saveReqVO);
        return success(true);
    }

    /**
     * 获得会员配置
     * 
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('member:config:query')")
    public CommonResult<MemberConfigRespVO> getConfig() {
        MemberConfigDO config = memberConfigService.getConfig();
        return success(MemberConfigConvert.INSTANCE.convert(config));
    }

}
