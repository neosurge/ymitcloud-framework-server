package com.ymitcloud.module.mp.controller.admin.message;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

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
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.mp.controller.admin.message.vo.autoreply.MpAutoReplyCreateReqVO;
import com.ymitcloud.module.mp.controller.admin.message.vo.autoreply.MpAutoReplyRespVO;
import com.ymitcloud.module.mp.controller.admin.message.vo.autoreply.MpAutoReplyUpdateReqVO;
import com.ymitcloud.module.mp.controller.admin.message.vo.message.MpMessagePageReqVO;
import com.ymitcloud.module.mp.convert.message.MpAutoReplyConvert;
import com.ymitcloud.module.mp.dal.dataobject.message.MpAutoReplyDO;
import com.ymitcloud.module.mp.service.message.MpAutoReplyService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - 公众号自动回复
 */

@RestController
@RequestMapping("/mp/auto-reply")
@Validated
public class MpAutoReplyController {

    @Resource
    private MpAutoReplyService mpAutoReplyService;


    /**
     * 获得公众号自动回复分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('mp:auto-reply:query')")
    public CommonResult<PageResult<MpAutoReplyRespVO>> getAutoReplyPage(@Valid MpMessagePageReqVO pageVO) {
        PageResult<MpAutoReplyDO> pageResult = mpAutoReplyService.getAutoReplyPage(pageVO);
        return success(MpAutoReplyConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 获得公众号自动回复
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('mp:auto-reply:query')")
    public CommonResult<MpAutoReplyRespVO> getAutoReply(@RequestParam("id") Long id) {
        MpAutoReplyDO autoReply = mpAutoReplyService.getAutoReply(id);
        return success(MpAutoReplyConvert.INSTANCE.convert(autoReply));
    }


    /**
     * 创建公众号自动回复
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('mp:auto-reply:create')")
    public CommonResult<Long> createAutoReply(@Valid @RequestBody MpAutoReplyCreateReqVO createReqVO) {
        return success(mpAutoReplyService.createAutoReply(createReqVO));
    }


    /**
     * 更新公众号自动回复
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('mp:auto-reply:update')")
    public CommonResult<Boolean> updateAutoReply(@Valid @RequestBody MpAutoReplyUpdateReqVO updateReqVO) {
        mpAutoReplyService.updateAutoReply(updateReqVO);
        return success(true);
    }


    /**
     * 删除公众号自动回复
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('mp:auto-reply:delete')")
    public CommonResult<Boolean> deleteAutoReply(@RequestParam("id") Long id) {
        mpAutoReplyService.deleteAutoReply(id);
        return success(true);
    }

}
