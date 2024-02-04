package com.ymitcloud.module.member.controller.app.point;


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
import com.ymitcloud.module.member.controller.app.point.vo.AppMemberPointRecordRespVO;
import com.ymitcloud.module.member.convert.point.MemberPointRecordConvert;
import com.ymitcloud.module.member.dal.dataobject.point.MemberPointRecordDO;
import com.ymitcloud.module.member.service.point.MemberPointRecordService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 用户 App - 签到记录
 */

@RestController
@RequestMapping("/member/point/record")
@Validated
public class AppMemberPointRecordController {

    @Resource
    private MemberPointRecordService pointRecordService;


    /**
     * 获得用户积分记录分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthenticated
    public CommonResult<PageResult<AppMemberPointRecordRespVO>> getPointRecordPage(@Valid PageParam pageVO) {
        PageResult<MemberPointRecordDO> pageResult = pointRecordService.getPointRecordPage(getLoginUserId(), pageVO);
        return success(MemberPointRecordConvert.INSTANCE.convertPage02(pageResult));
    }

}
