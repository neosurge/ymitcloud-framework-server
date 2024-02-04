package com.ymitcloud.module.pay.controller.admin.channel;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.List;
import java.util.Set;

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
import com.ymitcloud.module.pay.controller.admin.channel.vo.PayChannelCreateReqVO;
import com.ymitcloud.module.pay.controller.admin.channel.vo.PayChannelRespVO;
import com.ymitcloud.module.pay.controller.admin.channel.vo.PayChannelUpdateReqVO;
import com.ymitcloud.module.pay.convert.channel.PayChannelConvert;
import com.ymitcloud.module.pay.dal.dataobject.channel.PayChannelDO;
import com.ymitcloud.module.pay.service.channel.PayChannelService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 支付渠道
 */

@RestController
@RequestMapping("/pay/channel")
@Validated
public class PayChannelController {

    @Resource
    private PayChannelService channelService;


    /**
     * 创建支付渠道
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('pay:channel:create')")
    public CommonResult<Long> createChannel(@Valid @RequestBody PayChannelCreateReqVO createReqVO) {
        return success(channelService.createChannel(createReqVO));
    }


    /**
     * 更新支付渠道
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('pay:channel:update')")
    public CommonResult<Boolean> updateChannel(@Valid @RequestBody PayChannelUpdateReqVO updateReqVO) {
        channelService.updateChannel(updateReqVO);
        return success(true);
    }


    /**
     * 删除支付渠道
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('pay:channel:delete')")
    public CommonResult<Boolean> deleteChannel(@RequestParam("id") Long id) {
        channelService.deleteChannel(id);
        return success(true);
    }


    /**
     * 获得支付渠道
     * 
     * @param id    编号
     * @param appId
     * @param code
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('pay:channel:query')")
    public CommonResult<PayChannelRespVO> getChannel(@RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "appId", required = false) Long appId,
            @RequestParam(value = "code", required = false) String code) {

        PayChannelDO channel = null;
        if (id != null) {
            channel = channelService.getChannel(id);
        } else if (appId != null && code != null) {
            channel = channelService.getChannelByAppIdAndCode(appId, code);
        }
        return success(PayChannelConvert.INSTANCE.convert(channel));
    }


    /**
     * 获得指定应用的开启的支付渠道编码列表
     * 
     * @param appId 应用编号
     * @return
     */
    @GetMapping("/get-enable-code-list")

    public CommonResult<Set<String>> getEnableChannelCodeList(@RequestParam("appId") Long appId) {
        List<PayChannelDO> channels = channelService.getEnableChannelList(appId);
        return success(convertSet(channels, PayChannelDO::getCode));
    }

}
