package com.ymitcloud.module.member.controller.admin.signin;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.member.controller.admin.signin.vo.record.MemberSignInRecordPageReqVO;
import com.ymitcloud.module.member.controller.admin.signin.vo.record.MemberSignInRecordRespVO;
import com.ymitcloud.module.member.convert.signin.MemberSignInRecordConvert;
import com.ymitcloud.module.member.dal.dataobject.signin.MemberSignInRecordDO;
import com.ymitcloud.module.member.dal.dataobject.user.MemberUserDO;
import com.ymitcloud.module.member.service.signin.MemberSignInRecordService;
import com.ymitcloud.module.member.service.user.MemberUserService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 签到记录
 */

@RestController
@RequestMapping("/member/sign-in/record")
@Validated
public class MemberSignInRecordController {

    @Resource
    private MemberSignInRecordService signInRecordService;

    @Resource
    private MemberUserService memberUserService;


    /**
     * 获得签到记录分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('point:sign-in-record:query')")
    public CommonResult<PageResult<MemberSignInRecordRespVO>> getSignInRecordPage(
            @Valid MemberSignInRecordPageReqVO pageVO) {

        // 执行分页查询
        PageResult<MemberSignInRecordDO> pageResult = signInRecordService.getSignInRecordPage(pageVO);
        if (CollectionUtils.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }

        // 拼接结果返回

        List<MemberUserDO> users = memberUserService
                .getUserList(convertSet(pageResult.getList(), MemberSignInRecordDO::getUserId));

        return success(MemberSignInRecordConvert.INSTANCE.convertPage(pageResult, users));
    }
}
