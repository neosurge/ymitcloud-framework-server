package com.ymitcloud.module.trade.controller.admin.brokerage;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertMap;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.Map;
import java.util.Set;

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

import com.ymitcloud.module.trade.controller.admin.brokerage.vo.user.BrokerageUserClearBrokerageUserReqVO;
import com.ymitcloud.module.trade.controller.admin.brokerage.vo.user.BrokerageUserPageReqVO;
import com.ymitcloud.module.trade.controller.admin.brokerage.vo.user.BrokerageUserRespVO;
import com.ymitcloud.module.trade.controller.admin.brokerage.vo.user.BrokerageUserUpdateBrokerageEnabledReqVO;
import com.ymitcloud.module.trade.controller.admin.brokerage.vo.user.BrokerageUserUpdateBrokerageUserReqVO;

import com.ymitcloud.module.trade.convert.brokerage.BrokerageUserConvert;
import com.ymitcloud.module.trade.dal.dataobject.brokerage.BrokerageUserDO;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageRecordBizTypeEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageRecordStatusEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageWithdrawStatusEnum;
import com.ymitcloud.module.trade.service.brokerage.BrokerageRecordService;
import com.ymitcloud.module.trade.service.brokerage.BrokerageUserService;
import com.ymitcloud.module.trade.service.brokerage.BrokerageWithdrawService;

import com.ymitcloud.module.trade.service.brokerage.bo.BrokerageWithdrawSummaryRespBO;
import com.ymitcloud.module.trade.service.brokerage.bo.UserBrokerageSummaryRespBO;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 分销用户
 */

@RestController
@RequestMapping("/trade/brokerage-user")
@Validated
public class BrokerageUserController {

    @Resource
    private BrokerageUserService brokerageUserService;
    @Resource
    private BrokerageRecordService brokerageRecordService;
    @Resource
    private BrokerageWithdrawService brokerageWithdrawService;

    @Resource
    private MemberUserApi memberUserApi;


    /**
     * 修改推广员
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update-bind-user")

    @PreAuthorize("@ss.hasPermission('trade:brokerage-user:update-bind-user')")
    public CommonResult<Boolean> updateBindUser(@Valid @RequestBody BrokerageUserUpdateBrokerageUserReqVO updateReqVO) {
        brokerageUserService.updateBrokerageUserId(updateReqVO.getId(), updateReqVO.getBindUserId());
        return success(true);
    }


    /**
     * 清除推广员
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/clear-bind-user")

    @PreAuthorize("@ss.hasPermission('trade:brokerage-user:clear-bind-user')")
    public CommonResult<Boolean> clearBindUser(@Valid @RequestBody BrokerageUserClearBrokerageUserReqVO updateReqVO) {
        brokerageUserService.updateBrokerageUserId(updateReqVO.getId(), null);
        return success(true);
    }


    /**
     * 修改推广资格
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update-brokerage-enable")
    @PreAuthorize("@ss.hasPermission('trade:brokerage-user:update-brokerage-enable')")
    public CommonResult<Boolean> updateBrokerageEnabled(
            @Valid @RequestBody BrokerageUserUpdateBrokerageEnabledReqVO updateReqVO) {

        brokerageUserService.updateBrokerageUserEnabled(updateReqVO.getId(), updateReqVO.getEnabled());
        return success(true);
    }


    /**
     * 获得分销用户
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('trade:brokerage-user:query')")
    public CommonResult<BrokerageUserRespVO> getBrokerageUser(@RequestParam("id") Long id) {
        BrokerageUserDO brokerageUser = brokerageUserService.getBrokerageUser(id);
        // TODO @疯狂：是不是搞成一个统一的 convert？
        BrokerageUserRespVO respVO = BrokerageUserConvert.INSTANCE.convert(brokerageUser);
        return success(BrokerageUserConvert.INSTANCE.copyTo(memberUserApi.getUser(id), respVO));
    }


    /**
     * 获得分销用户分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('trade:brokerage-user:query')")
    public CommonResult<PageResult<BrokerageUserRespVO>> getBrokerageUserPage(@Valid BrokerageUserPageReqVO pageVO) {
        // 分页查询
        PageResult<BrokerageUserDO> pageResult = brokerageUserService.getBrokerageUserPage(pageVO);

        // 查询用户信息
        Set<Long> userIds = convertSet(pageResult.getList(), BrokerageUserDO::getId);
        Map<Long, MemberUserRespDTO> userMap = memberUserApi.getUserMap(userIds);
        // 合计分佣的推广订单

        Map<Long, UserBrokerageSummaryRespBO> brokerageOrderSummaryMap = brokerageRecordService
                .getUserBrokerageSummaryMapByUserId(userIds, BrokerageRecordBizTypeEnum.ORDER.getType(),
                        BrokerageRecordStatusEnum.SETTLEMENT.getStatus());
        // 合计分佣的推广用户
        // TODO @疯狂：转成 map 批量读取
        Map<Long, Long> brokerageUserCountMap = convertMap(userIds, userId -> userId,
                userId -> brokerageUserService.getBrokerageUserCountByBindUserId(userId, null));
        // 合计分佣的提现
        // TODO @疯狂：如果未来支持了打款这个动作，可能 status 会不对；
        Map<Long, BrokerageWithdrawSummaryRespBO> withdrawMap = brokerageWithdrawService
                .getWithdrawSummaryMapByUserId(userIds, BrokerageWithdrawStatusEnum.AUDIT_SUCCESS);

        // 拼接返回
        return success(BrokerageUserConvert.INSTANCE.convertPage(pageResult, userMap, brokerageUserCountMap,
                brokerageOrderSummaryMap, withdrawMap));
    }

}
