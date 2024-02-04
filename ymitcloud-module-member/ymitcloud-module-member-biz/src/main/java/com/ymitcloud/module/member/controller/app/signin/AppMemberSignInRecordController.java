package com.ymitcloud.module.member.controller.app.signin;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.member.controller.app.signin.vo.record.AppMemberSignInRecordRespVO;
import com.ymitcloud.module.member.controller.app.signin.vo.record.AppMemberSignInRecordSummaryRespVO;
import com.ymitcloud.module.member.convert.signin.MemberSignInRecordConvert;
import com.ymitcloud.module.member.dal.dataobject.signin.MemberSignInRecordDO;
import com.ymitcloud.module.member.service.signin.MemberSignInRecordService;

import jakarta.annotation.Resource;

/**
 * 管理后台 - 签到记录
 */
@RestController
@RequestMapping("/member/sign-in/record")
@Validated
public class AppMemberSignInRecordController {

    @Resource
    private MemberSignInRecordService signInRecordService;

    /**
     * 获得个人签到统计
     * 
     * @return
     */
    @GetMapping("/get-summary")
    @PreAuthenticated
    public CommonResult<AppMemberSignInRecordSummaryRespVO> getSignInRecordSummary() {
        return success(signInRecordService.getSignInRecordSummary(getLoginUserId()));
    }

    /**
     * 签到
     * 
     * @return
     */
    @PostMapping("/create")
    @PreAuthenticated
    public CommonResult<AppMemberSignInRecordRespVO> createSignInRecord() {
        MemberSignInRecordDO recordDO = signInRecordService.createSignRecord(getLoginUserId());
        return success(MemberSignInRecordConvert.INSTANCE.coverRecordToAppRecordVo(recordDO));
    }

    /**
     * 获得签到记录分页
     * 
     * @param pageParam
     * @return
     */
    @GetMapping("/page")
    @PreAuthenticated
    public CommonResult<PageResult<AppMemberSignInRecordRespVO>> getSignRecordPage(PageParam pageParam) {
        PageResult<MemberSignInRecordDO> pageResult = signInRecordService.getSignRecordPage(getLoginUserId(),
                pageParam);
        return success(MemberSignInRecordConvert.INSTANCE.convertPage02(pageResult));
    }

}
