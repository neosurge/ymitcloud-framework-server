package com.ymitcloud.module.member.controller.admin.point;


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
import com.ymitcloud.module.member.controller.admin.point.vo.recrod.MemberPointRecordPageReqVO;
import com.ymitcloud.module.member.controller.admin.point.vo.recrod.MemberPointRecordRespVO;
import com.ymitcloud.module.member.convert.point.MemberPointRecordConvert;
import com.ymitcloud.module.member.dal.dataobject.point.MemberPointRecordDO;
import com.ymitcloud.module.member.dal.dataobject.user.MemberUserDO;
import com.ymitcloud.module.member.service.point.MemberPointRecordService;
import com.ymitcloud.module.member.service.user.MemberUserService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 签到记录
 */

@RestController
@RequestMapping("/member/point/record")
@Validated
public class MemberPointRecordController {

    @Resource
    private MemberPointRecordService pointRecordService;

    @Resource
    private MemberUserService memberUserService;


    /**
     * 获得用户积分记录分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('point:record:query')")
    public CommonResult<PageResult<MemberPointRecordRespVO>> getPointRecordPage(
            @Valid MemberPointRecordPageReqVO pageVO) {

        // 执行分页查询
        PageResult<MemberPointRecordDO> pageResult = pointRecordService.getPointRecordPage(pageVO);
        if (CollectionUtils.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }

        // 拼接结果返回

        List<MemberUserDO> users = memberUserService
                .getUserList(convertSet(pageResult.getList(), MemberPointRecordDO::getUserId));

        return success(MemberPointRecordConvert.INSTANCE.convertPage(pageResult, users));
    }

}
