package com.ymitcloud.module.trade.controller.admin.brokerage;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.member.api.user.MemberUserApi;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;

import com.ymitcloud.module.trade.controller.admin.brokerage.vo.withdraw.BrokerageWithdrawPageReqVO;
import com.ymitcloud.module.trade.controller.admin.brokerage.vo.withdraw.BrokerageWithdrawRejectReqVO;

import com.ymitcloud.module.trade.controller.admin.brokerage.vo.withdraw.BrokerageWithdrawRespVO;
import com.ymitcloud.module.trade.convert.brokerage.BrokerageWithdrawConvert;
import com.ymitcloud.module.trade.dal.dataobject.brokerage.BrokerageWithdrawDO;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageWithdrawStatusEnum;
import com.ymitcloud.module.trade.service.brokerage.BrokerageWithdrawService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 佣金提现
 */

@RestController
@RequestMapping("/trade/brokerage-withdraw")
@Validated
public class BrokerageWithdrawController {

    @Resource
    private BrokerageWithdrawService brokerageWithdrawService;

    @Resource
    private MemberUserApi memberUserApi;


    /**
     * 通过申请
     * 
     * @param id
     * @return
     */
    @PutMapping("/approve")

    @PreAuthorize("@ss.hasPermission('trade:brokerage-withdraw:audit')")
    public CommonResult<Boolean> approveBrokerageWithdraw(@RequestParam("id") Integer id) {
        brokerageWithdrawService.auditBrokerageWithdraw(id, BrokerageWithdrawStatusEnum.AUDIT_SUCCESS, "");
        return success(true);
    }


    /**
     * 驳回申请
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/reject")
    @PreAuthorize("@ss.hasPermission('trade:brokerage-withdraw:audit')")
    public CommonResult<Boolean> rejectBrokerageWithdraw(@Valid @RequestBody BrokerageWithdrawRejectReqVO reqVO) {
        brokerageWithdrawService.auditBrokerageWithdraw(reqVO.getId(), BrokerageWithdrawStatusEnum.AUDIT_FAIL,
                reqVO.getAuditReason());
        return success(true);
    }

    /**
     * 获得佣金提现
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('trade:brokerage-withdraw:query')")
    public CommonResult<BrokerageWithdrawRespVO> getBrokerageWithdraw(@RequestParam("id") Integer id) {
        BrokerageWithdrawDO brokerageWithdraw = brokerageWithdrawService.getBrokerageWithdraw(id);
        return success(BrokerageWithdrawConvert.INSTANCE.convert(brokerageWithdraw));
    }


    /**
     * 获得佣金提现分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('trade:brokerage-withdraw:query')")
    public CommonResult<PageResult<BrokerageWithdrawRespVO>> getBrokerageWithdrawPage(
            @Valid BrokerageWithdrawPageReqVO pageVO) {

        // 分页查询
        PageResult<BrokerageWithdrawDO> pageResult = brokerageWithdrawService.getBrokerageWithdrawPage(pageVO);

        // 拼接信息

        Map<Long, MemberUserRespDTO> userMap = memberUserApi
                .getUserMap(convertSet(pageResult.getList(), BrokerageWithdrawDO::getUserId));

        return success(BrokerageWithdrawConvert.INSTANCE.convertPage(pageResult, userMap));
    }

}
