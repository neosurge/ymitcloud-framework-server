package com.ymitcloud.module.mp.controller.admin.message;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.mp.controller.admin.message.vo.message.MpMessagePageReqVO;
import com.ymitcloud.module.mp.controller.admin.message.vo.message.MpMessageRespVO;
import com.ymitcloud.module.mp.controller.admin.message.vo.message.MpMessageSendReqVO;
import com.ymitcloud.module.mp.convert.message.MpMessageConvert;
import com.ymitcloud.module.mp.dal.dataobject.message.MpMessageDO;
import com.ymitcloud.module.mp.service.message.MpMessageService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - 公众号消息
 */

@RestController
@RequestMapping("/mp/message")
@Validated
public class MpMessageController {

    @Resource
    private MpMessageService mpMessageService;


    /**
     * 获得公众号消息分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('mp:message:query')")
    public CommonResult<PageResult<MpMessageRespVO>> getMessagePage(@Valid MpMessagePageReqVO pageVO) {
        PageResult<MpMessageDO> pageResult = mpMessageService.getMessagePage(pageVO);
        return success(MpMessageConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 给粉丝发送消息
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/send")

    @PreAuthorize("@ss.hasPermission('mp:message:send')")
    public CommonResult<MpMessageRespVO> sendMessage(@Valid @RequestBody MpMessageSendReqVO reqVO) {
        MpMessageDO message = mpMessageService.sendKefuMessage(reqVO);
        return success(MpMessageConvert.INSTANCE.convert(message));
    }

}
