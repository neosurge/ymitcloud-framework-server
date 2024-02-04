package com.ymitcloud.module.pay.controller.app.channel;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.List;
import java.util.Set;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.pay.dal.dataobject.channel.PayChannelDO;
import com.ymitcloud.module.pay.service.channel.PayChannelService;

import jakarta.annotation.Resource;

/**
 * 用户 App - 支付渠道
 */

@RestController
@RequestMapping("/pay/channel")
@Validated
public class AppPayChannelController {

    @Resource
    private PayChannelService channelService;


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
