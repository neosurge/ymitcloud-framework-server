package com.ymitcloud.module.member.controller.app.level;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.member.controller.app.level.vo.experience.AppMemberExperienceRecordRespVO;
import com.ymitcloud.module.member.convert.level.MemberExperienceRecordConvert;
import com.ymitcloud.module.member.dal.dataobject.level.MemberExperienceRecordDO;
import com.ymitcloud.module.member.service.level.MemberExperienceRecordService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 用户 App - 会员经验记录
 */

@RestController
@RequestMapping("/member/experience-record")
@Validated
public class AppMemberExperienceRecordController {

    @Resource
    private MemberExperienceRecordService experienceLogService;


    /**
     * 获得会员经验记录分页
     * 
     * @param pageParam
     * @return
     */
    @GetMapping("/page")
    @PreAuthenticated
    public CommonResult<PageResult<AppMemberExperienceRecordRespVO>> getExperienceRecordPage(
            @Valid PageParam pageParam) {
        PageResult<MemberExperienceRecordDO> pageResult = experienceLogService.getExperienceRecordPage(getLoginUserId(),
                pageParam);

        return success(MemberExperienceRecordConvert.INSTANCE.convertPage02(pageResult));
    }

}
