package com.ymitcloud.module.promotion.controller.admin.combination;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.promotion.controller.admin.combination.vo.recrod.CombinationRecordPageItemRespVO;
import com.ymitcloud.module.promotion.controller.admin.combination.vo.recrod.CombinationRecordReqPageVO;
import com.ymitcloud.module.promotion.controller.admin.combination.vo.recrod.CombinationRecordSummaryVO;
import com.ymitcloud.module.promotion.convert.combination.CombinationActivityConvert;
import com.ymitcloud.module.promotion.dal.dataobject.combination.CombinationActivityDO;
import com.ymitcloud.module.promotion.dal.dataobject.combination.CombinationProductDO;
import com.ymitcloud.module.promotion.dal.dataobject.combination.CombinationRecordDO;
import com.ymitcloud.module.promotion.enums.combination.CombinationRecordStatusEnum;
import com.ymitcloud.module.promotion.service.combination.CombinationActivityService;
import com.ymitcloud.module.promotion.service.combination.CombinationRecordService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 拼团记录
 */

@RestController
@RequestMapping("/promotion/combination-record")
@Validated
public class CombinationRecordController {

    @Resource
    private CombinationActivityService combinationActivityService;
    @Resource
    @Lazy
    private CombinationRecordService combinationRecordService;


    /**
     * 获得拼团记录分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('promotion:combination-record:query')")
    public CommonResult<PageResult<CombinationRecordPageItemRespVO>> getBargainRecordPage(
            @Valid CombinationRecordReqPageVO pageVO) {
        PageResult<CombinationRecordDO> recordPage = combinationRecordService.getCombinationRecordPage(pageVO);
        // 拼接数据
        List<CombinationActivityDO> activities = combinationActivityService
                .getCombinationActivityListByIds(convertSet(recordPage.getList(), CombinationRecordDO::getActivityId));

        List<CombinationProductDO> products = combinationActivityService.getCombinationProductListByActivityIds(
                convertSet(recordPage.getList(), CombinationRecordDO::getActivityId));
        return success(CombinationActivityConvert.INSTANCE.convert(recordPage, activities, products));
    }


    /**
     * 获得拼团记录的概要信息,用于拼团记录页面展示
     * 
     * @return
     */
    @GetMapping("/get-summary")

    @PreAuthorize("@ss.hasPermission('promotion:combination-record:query')")
    public CommonResult<CombinationRecordSummaryVO> getCombinationRecordSummary() {
        CombinationRecordSummaryVO summaryVO = new CombinationRecordSummaryVO();
        summaryVO.setUserCount(combinationRecordService.getCombinationUserCount()); // 获取拼团用户参与数量
        summaryVO.setSuccessCount(combinationRecordService.getCombinationRecordCount( // 获取成团记录
                CombinationRecordStatusEnum.SUCCESS.getStatus(), null, CombinationRecordDO.HEAD_ID_GROUP));
        summaryVO.setVirtualGroupCount(combinationRecordService.getCombinationRecordCount(// 获取虚拟成团记录
                null, Boolean.TRUE, CombinationRecordDO.HEAD_ID_GROUP));
        return success(summaryVO);
    }

}
