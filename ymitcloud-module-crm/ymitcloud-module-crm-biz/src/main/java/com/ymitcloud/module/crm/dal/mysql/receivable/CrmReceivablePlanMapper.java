package com.ymitcloud.module.crm.dal.mysql.receivable;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanPageReqVO;
import com.ymitcloud.module.crm.dal.dataobject.receivable.CrmReceivablePlanDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 回款计划 Mapper
 *

 * @author 

 */
@Mapper
public interface CrmReceivablePlanMapper extends BaseMapperX<CrmReceivablePlanDO> {

    default PageResult<CrmReceivablePlanDO> selectPage(CrmReceivablePlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmReceivablePlanDO>()
                .eqIfPresent(CrmReceivablePlanDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CrmReceivablePlanDO::getContractId, reqVO.getContractId())
                .orderByDesc(CrmReceivablePlanDO::getId));
    }

    default PageResult<CrmReceivablePlanDO> selectPageByCustomer(CrmReceivablePlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmReceivablePlanDO>()
                .eq(CrmReceivablePlanDO::getCustomerId, reqVO.getCustomerId()) // 必须传递
                .eqIfPresent(CrmReceivablePlanDO::getContractId, reqVO.getContractId())
                .orderByDesc(CrmReceivablePlanDO::getId));
    }

}
