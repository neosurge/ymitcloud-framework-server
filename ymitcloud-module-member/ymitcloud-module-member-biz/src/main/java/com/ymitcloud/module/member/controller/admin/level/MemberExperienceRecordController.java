package com.ymitcloud.module.member.controller.admin.level;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.member.controller.admin.level.vo.experience.MemberExperienceRecordPageReqVO;
import com.ymitcloud.module.member.controller.admin.level.vo.experience.MemberExperienceRecordRespVO;
import com.ymitcloud.module.member.convert.level.MemberExperienceRecordConvert;
import com.ymitcloud.module.member.dal.dataobject.level.MemberExperienceRecordDO;
import com.ymitcloud.module.member.service.level.MemberExperienceRecordService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - 会员经验记录
 */

@RestController
@RequestMapping("/member/experience-record")
@Validated
public class MemberExperienceRecordController {

    @Resource
    private MemberExperienceRecordService experienceLogService;


    /**
     * 获得会员经验记录
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('member:experience-record:query')")
    public CommonResult<MemberExperienceRecordRespVO> getExperienceRecord(@RequestParam("id") Long id) {
        MemberExperienceRecordDO experienceLog = experienceLogService.getExperienceRecord(id);
        return success(MemberExperienceRecordConvert.INSTANCE.convert(experienceLog));
    }


    /**
     * 获得会员经验记录分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('member:experience-record:query')")
    public CommonResult<PageResult<MemberExperienceRecordRespVO>> getExperienceRecordPage(
            @Valid MemberExperienceRecordPageReqVO pageVO) {
        PageResult<MemberExperienceRecordDO> pageResult = experienceLogService.getExperienceRecordPage(pageVO);
        return success(MemberExperienceRecordConvert.INSTANCE.convertPage(pageResult));
    }

}
