package com.ymitcloud.module.system.controller.admin.sms;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.Comparator;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.system.controller.admin.sms.vo.channel.SmsChannelPageReqVO;
import com.ymitcloud.module.system.controller.admin.sms.vo.channel.SmsChannelRespVO;
import com.ymitcloud.module.system.controller.admin.sms.vo.channel.SmsChannelSaveReqVO;
import com.ymitcloud.module.system.controller.admin.sms.vo.channel.SmsChannelSimpleRespVO;
import com.ymitcloud.module.system.dal.dataobject.sms.SmsChannelDO;
import com.ymitcloud.module.system.service.sms.SmsChannelService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 短信渠道
 */
@RestController
@RequestMapping("system/sms-channel")
public class SmsChannelController {

    @Resource
    private SmsChannelService smsChannelService;
    /**
     * 创建短信渠道
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:sms-channel:create')")
    public CommonResult<Long> createSmsChannel(@Valid @RequestBody SmsChannelSaveReqVO createReqVO) {
        return success(smsChannelService.createSmsChannel(createReqVO));
    }

    /**
     * 更新短信渠道
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:sms-channel:update')")
    public CommonResult<Boolean> updateSmsChannel(@Valid @RequestBody SmsChannelSaveReqVO updateReqVO) {
        smsChannelService.updateSmsChannel(updateReqVO);
        return success(true);
    }

    /**
     * 删除短信渠道
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:sms-channel:delete')")
    public CommonResult<Boolean> deleteSmsChannel(@RequestParam("id") Long id) {
        smsChannelService.deleteSmsChannel(id);
        return success(true);
    }

    /**
     * 获得短信渠道
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:sms-channel:query')")
    public CommonResult<SmsChannelRespVO> getSmsChannel(@RequestParam("id") Long id) {
        SmsChannelDO channel = smsChannelService.getSmsChannel(id);
        return success(BeanUtils.toBean(channel, SmsChannelRespVO.class));
    }

    /**
     * 获得短信渠道分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:sms-channel:query')")
    public CommonResult<PageResult<SmsChannelRespVO>> getSmsChannelPage(@Valid SmsChannelPageReqVO pageVO) {
        PageResult<SmsChannelDO> pageResult = smsChannelService.getSmsChannelPage(pageVO);
        return success(BeanUtils.toBean(pageResult, SmsChannelRespVO.class));
    }

    /**
     * 获得短信渠道精简列表,包含被禁用的短信渠道
     * 
     * @return
     */
    @GetMapping({ "/list-all-simple", "/simple-list" })
    public CommonResult<List<SmsChannelSimpleRespVO>> getSimpleSmsChannelList() {
        List<SmsChannelDO> list = smsChannelService.getSmsChannelList();
        list.sort(Comparator.comparing(SmsChannelDO::getId));
        return success(BeanUtils.toBean(list, SmsChannelSimpleRespVO.class));
    }

}
