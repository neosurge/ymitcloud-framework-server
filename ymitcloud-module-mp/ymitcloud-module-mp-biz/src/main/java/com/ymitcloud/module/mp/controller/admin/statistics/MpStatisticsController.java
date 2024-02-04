package com.ymitcloud.module.mp.controller.admin.statistics;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.mp.controller.admin.statistics.vo.MpStatisticsGetReqVO;
import com.ymitcloud.module.mp.controller.admin.statistics.vo.MpStatisticsInterfaceSummaryRespVO;
import com.ymitcloud.module.mp.controller.admin.statistics.vo.MpStatisticsUpstreamMessageRespVO;
import com.ymitcloud.module.mp.controller.admin.statistics.vo.MpStatisticsUserCumulateRespVO;
import com.ymitcloud.module.mp.controller.admin.statistics.vo.MpStatisticsUserSummaryRespVO;
import com.ymitcloud.module.mp.convert.statistics.MpStatisticsConvert;
import com.ymitcloud.module.mp.service.statistics.MpStatisticsService;

import jakarta.annotation.Resource;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeInterfaceResult;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeMsgResult;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserCumulate;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserSummary;

/**
 * 管理后台 - 公众号统计
 */

@RestController
@RequestMapping("/mp/statistics")
@Validated
public class MpStatisticsController {

    @Resource
    private MpStatisticsService mpStatisticsService;


    /**
     * 获得粉丝增减数据
     * 
     * @param getReqVO
     * @return
     */
    @GetMapping("/user-summary")
    @PreAuthorize("@ss.hasPermission('mp:statistics:query')")
    public CommonResult<List<MpStatisticsUserSummaryRespVO>> getUserSummary(MpStatisticsGetReqVO getReqVO) {
        List<WxDataCubeUserSummary> list = mpStatisticsService.getUserSummary(getReqVO.getAccountId(),
                getReqVO.getDate());
        return success(MpStatisticsConvert.INSTANCE.convertList01(list));
    }

    /**
     * 获得粉丝累计数据
     * 
     * @param getReqVO
     * @return
     */
    @GetMapping("/user-cumulate")
    @PreAuthorize("@ss.hasPermission('mp:statistics:query')")
    public CommonResult<List<MpStatisticsUserCumulateRespVO>> getUserCumulate(MpStatisticsGetReqVO getReqVO) {
        List<WxDataCubeUserCumulate> list = mpStatisticsService.getUserCumulate(getReqVO.getAccountId(),
                getReqVO.getDate());
        return success(MpStatisticsConvert.INSTANCE.convertList02(list));
    }

    /**
     * 获取消息发送概况数据
     * 
     * @param getReqVO
     * @return
     */
    @GetMapping("/upstream-message")
    @PreAuthorize("@ss.hasPermission('mp:statistics:query')")
    public CommonResult<List<MpStatisticsUpstreamMessageRespVO>> getUpstreamMessage(MpStatisticsGetReqVO getReqVO) {
        List<WxDataCubeMsgResult> list = mpStatisticsService.getUpstreamMessage(getReqVO.getAccountId(),
                getReqVO.getDate());
        return success(MpStatisticsConvert.INSTANCE.convertList03(list));
    }

    /**
     * 获取消息发送概况数据
     * 
     * @param getReqVO
     * @return
     */
    @GetMapping("/interface-summary")
    @PreAuthorize("@ss.hasPermission('mp:statistics:query')")
    public CommonResult<List<MpStatisticsInterfaceSummaryRespVO>> getInterfaceSummary(MpStatisticsGetReqVO getReqVO) {
        List<WxDataCubeInterfaceResult> list = mpStatisticsService.getInterfaceSummary(getReqVO.getAccountId(),
                getReqVO.getDate());

        return success(MpStatisticsConvert.INSTANCE.convertList04(list));
    }
}
