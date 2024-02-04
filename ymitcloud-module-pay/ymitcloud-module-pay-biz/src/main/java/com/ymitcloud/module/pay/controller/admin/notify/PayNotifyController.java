package com.ymitcloud.module.pay.controller.admin.notify;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.framework.pay.core.client.PayClient;
import com.ymitcloud.framework.pay.core.client.dto.order.PayOrderRespDTO;
import com.ymitcloud.framework.pay.core.client.dto.refund.PayRefundRespDTO;
import com.ymitcloud.module.pay.controller.admin.notify.vo.PayNotifyTaskDetailRespVO;
import com.ymitcloud.module.pay.controller.admin.notify.vo.PayNotifyTaskPageReqVO;
import com.ymitcloud.module.pay.controller.admin.notify.vo.PayNotifyTaskRespVO;
import com.ymitcloud.module.pay.convert.notify.PayNotifyTaskConvert;
import com.ymitcloud.module.pay.dal.dataobject.app.PayAppDO;
import com.ymitcloud.module.pay.dal.dataobject.notify.PayNotifyLogDO;
import com.ymitcloud.module.pay.dal.dataobject.notify.PayNotifyTaskDO;

import com.ymitcloud.module.pay.enums.ErrorCodeConstants;

import com.ymitcloud.module.pay.service.app.PayAppService;
import com.ymitcloud.module.pay.service.channel.PayChannelService;
import com.ymitcloud.module.pay.service.notify.PayNotifyService;
import com.ymitcloud.module.pay.service.order.PayOrderService;
import com.ymitcloud.module.pay.service.refund.PayRefundService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台 - 回调通知
 */

@RestController
@RequestMapping("/pay/notify")
@Validated
@Slf4j
public class PayNotifyController {

    @Resource
    private PayOrderService orderService;
    @Resource
    private PayRefundService refundService;
    @Resource
    private PayNotifyService notifyService;
    @Resource
    private PayAppService appService;
    @Resource
    private PayChannelService channelService;


    /**
     * 支付渠道的统一【支付】回调
     * 
     * @param channelId
     * @param params
     * @param body
     * @return
     */
    @PostMapping(value = "/order/{channelId}")
    @PermitAll
    @OperateLog(enable = false) // 回调地址，无需记录操作日志
    public String notifyOrder(@PathVariable("channelId") Long channelId,
            @RequestParam(required = false) Map<String, String> params, @RequestBody(required = false) String body) {

        log.info("[notifyOrder][channelId({}) 回调数据({}/{})]", channelId, params, body);
        // 1. 校验支付渠道是否存在
        PayClient payClient = channelService.getPayClient(channelId);
        if (payClient == null) {
            log.error("[notifyCallback][渠道编号({}) 找不到对应的支付客户端]", channelId);
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CHANNEL_NOT_FOUND);
        }

        // 2. 解析通知数据
        PayOrderRespDTO notify = payClient.parseOrderNotify(params, body);
        orderService.notifyOrder(channelId, notify);
        return "success";
    }


    /**
     * 支付渠道的统一【退款】回调
     * 
     * @param channelId
     * @param params
     * @param body
     * @return
     */
    @PostMapping(value = "/refund/{channelId}")
    @PermitAll
    @OperateLog(enable = false) // 回调地址，无需记录操作日志
    public String notifyRefund(@PathVariable("channelId") Long channelId,
            @RequestParam(required = false) Map<String, String> params, @RequestBody(required = false) String body) {

        log.info("[notifyRefund][channelId({}) 回调数据({}/{})]", channelId, params, body);
        // 1. 校验支付渠道是否存在
        PayClient payClient = channelService.getPayClient(channelId);
        if (payClient == null) {
            log.error("[notifyCallback][渠道编号({}) 找不到对应的支付客户端]", channelId);
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CHANNEL_NOT_FOUND);
        }

        // 2. 解析通知数据
        PayRefundRespDTO notify = payClient.parseRefundNotify(params, body);
        refundService.notifyRefund(channelId, notify);
        return "success";
    }


    /**
     * 获得回调通知的明细
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get-detail")

    @PreAuthorize("@ss.hasPermission('pay:notify:query')")
    public CommonResult<PayNotifyTaskDetailRespVO> getNotifyTaskDetail(@RequestParam("id") Long id) {
        PayNotifyTaskDO task = notifyService.getNotifyTask(id);
        if (task == null) {
            return success(null);
        }
        // 拼接返回
        PayAppDO app = appService.getApp(task.getAppId());
        List<PayNotifyLogDO> logs = notifyService.getNotifyLogList(id);
        return success(PayNotifyTaskConvert.INSTANCE.convert(task, app, logs));
    }


    /**
     * 获得回调通知分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('pay:notify:query')")
    public CommonResult<PageResult<PayNotifyTaskRespVO>> getNotifyTaskPage(@Valid PayNotifyTaskPageReqVO pageVO) {
        PageResult<PayNotifyTaskDO> pageResult = notifyService.getNotifyTaskPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty());
        }
        // 拼接返回
        Map<Long, PayAppDO> appMap = appService.getAppMap(convertList(pageResult.getList(), PayNotifyTaskDO::getAppId));
        return success(PayNotifyTaskConvert.INSTANCE.convertPage(pageResult, appMap));
    }

}
