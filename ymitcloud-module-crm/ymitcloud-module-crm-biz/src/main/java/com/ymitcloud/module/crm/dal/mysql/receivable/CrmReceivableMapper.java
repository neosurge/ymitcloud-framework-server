package com.ymitcloud.module.crm.dal.mysql.receivable;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.receivable.CrmReceivablePageReqVO;
import com.ymitcloud.module.crm.dal.dataobject.receivable.CrmReceivableDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 回款 Mapper
 *
 * @author 赤焰
 */
@Mapper
public interface CrmReceivableMapper extends BaseMapperX<CrmReceivableDO> {

    default PageResult<CrmReceivableDO> selectPage(CrmReceivablePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmReceivableDO>()
                .eqIfPresent(CrmReceivableDO::getNo, reqVO.getNo())
                .eqIfPresent(CrmReceivableDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(CrmReceivableDO::getCustomerId, reqVO.getCustomerId())
                .orderByDesc(CrmReceivableDO::getId));
    }

    default PageResult<CrmReceivableDO> selectPageByCustomer(CrmReceivablePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmReceivableDO>()
                .eq(CrmReceivableDO::getCustomerId, reqVO.getCustomerId()) // 必须传递
                .eqIfPresent(CrmReceivableDO::getNo, reqVO.getNo())
                .eqIfPresent(CrmReceivableDO::getPlanId, reqVO.getPlanId())
                .orderByDesc(CrmReceivableDO::getId));
    }

}
