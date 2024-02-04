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
import com.ymitcloud.module.member.controller.admin.level.vo.record.MemberLevelRecordPageReqVO;
import com.ymitcloud.module.member.controller.admin.level.vo.record.MemberLevelRecordRespVO;
import com.ymitcloud.module.member.convert.level.MemberLevelRecordConvert;
import com.ymitcloud.module.member.dal.dataobject.level.MemberLevelRecordDO;
import com.ymitcloud.module.member.service.level.MemberLevelRecordService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - 会员等级记录
 */

@RestController
@RequestMapping("/member/level-record")
@Validated
public class MemberLevelRecordController {

    @Resource
    private MemberLevelRecordService levelLogService;


    /**
     * 获得会员等级记录
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('member:level-record:query')")
    public CommonResult<MemberLevelRecordRespVO> getLevelRecord(@RequestParam("id") Long id) {
        MemberLevelRecordDO levelLog = levelLogService.getLevelRecord(id);
        return success(MemberLevelRecordConvert.INSTANCE.convert(levelLog));
    }


    /**
     * 获得会员等级记录分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('member:level-record:query')")
    public CommonResult<PageResult<MemberLevelRecordRespVO>> getLevelRecordPage(
            @Valid MemberLevelRecordPageReqVO pageVO) {
        PageResult<MemberLevelRecordDO> pageResult = levelLogService.getLevelRecordPage(pageVO);
        return success(MemberLevelRecordConvert.INSTANCE.convertPage(pageResult));
    }

}
