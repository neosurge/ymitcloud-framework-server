package com.ymitcloud.module.crm.dal.mysql.customer;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymitcloud.module.crm.controller.admin.customer.vo.limitconfig.CrmCustomerLimitConfigPageReqVO;
import com.ymitcloud.module.crm.dal.dataobject.customer.CrmCustomerLimitConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户限制配置 Mapper
 *
 * @author Wanwan
 */
@Mapper
public interface CrmCustomerLimitConfigMapper extends BaseMapperX<CrmCustomerLimitConfigDO> {

    default PageResult<CrmCustomerLimitConfigDO> selectPage(CrmCustomerLimitConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmCustomerLimitConfigDO>()
                .eqIfPresent(CrmCustomerLimitConfigDO::getType, reqVO.getType())
                .orderByDesc(CrmCustomerLimitConfigDO::getId));
    }

}
