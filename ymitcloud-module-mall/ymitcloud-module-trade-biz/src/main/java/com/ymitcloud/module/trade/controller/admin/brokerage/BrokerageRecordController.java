package com.ymitcloud.module.trade.controller.admin.brokerage;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.Map;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.member.api.user.MemberUserApi;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;
import com.ymitcloud.module.trade.controller.admin.brokerage.vo.record.BrokerageRecordPageReqVO;
import com.ymitcloud.module.trade.controller.admin.brokerage.vo.record.BrokerageRecordRespVO;
import com.ymitcloud.module.trade.convert.brokerage.BrokerageRecordConvert;
import com.ymitcloud.module.trade.dal.dataobject.brokerage.BrokerageRecordDO;
import com.ymitcloud.module.trade.service.brokerage.BrokerageRecordService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
/**
 * 管理后台 - 佣金记录
 */

@RestController
@RequestMapping("/trade/brokerage-record")
@Validated
public class BrokerageRecordController {

    @Resource
    private BrokerageRecordService brokerageRecordService;

    @Resource
    private MemberUserApi memberUserApi;

/**
 * 获得佣金记录
 * @param id 编号
 * @return
 */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('trade:brokerage-record:query')")
    public CommonResult<BrokerageRecordRespVO> getBrokerageRecord(@RequestParam("id") Integer id) {
        BrokerageRecordDO brokerageRecord = brokerageRecordService.getBrokerageRecord(id);
        return success(BrokerageRecordConvert.INSTANCE.convert(brokerageRecord));
    }

/**
 * 获得佣金记录分页
 * @param pageVO
 * @return
 */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('trade:brokerage-record:query')")
    public CommonResult<PageResult<BrokerageRecordRespVO>> getBrokerageRecordPage(@Valid BrokerageRecordPageReqVO pageVO) {
        PageResult<BrokerageRecordDO> pageResult = brokerageRecordService.getBrokerageRecordPage(pageVO);

        // 查询用户信息
        Set<Long> userIds = convertSet(pageResult.getList(), BrokerageRecordDO::getUserId);
        userIds.addAll(convertList(pageResult.getList(), BrokerageRecordDO::getSourceUserId));
        Map<Long, MemberUserRespDTO> userMap = memberUserApi.getUserMap(userIds);
        // 拼接数据
        return success(BrokerageRecordConvert.INSTANCE.convertPage(pageResult, userMap));
    }

}
