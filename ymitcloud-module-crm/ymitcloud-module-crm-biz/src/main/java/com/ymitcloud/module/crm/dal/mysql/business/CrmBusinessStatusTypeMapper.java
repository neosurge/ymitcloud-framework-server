package com.ymitcloud.module.crm.dal.mysql.business;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymitcloud.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypePageReqVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypeQueryVO;
import com.ymitcloud.module.crm.dal.dataobject.business.CrmBusinessStatusTypeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商机状态类型 Mapper
 *
 * @author ljlleo
 */
@Mapper
public interface CrmBusinessStatusTypeMapper extends BaseMapperX<CrmBusinessStatusTypeDO> {

    default PageResult<CrmBusinessStatusTypeDO> selectPage(CrmBusinessStatusTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmBusinessStatusTypeDO>()
                .orderByDesc(CrmBusinessStatusTypeDO::getId));
    }

    default List<CrmBusinessStatusTypeDO> selectList(CrmBusinessStatusTypeQueryVO queryVO) {
        return selectList(new LambdaQueryWrapperX<CrmBusinessStatusTypeDO>()
                .eqIfPresent(CrmBusinessStatusTypeDO::getStatus, queryVO.getStatus())
                .inIfPresent(CrmBusinessStatusTypeDO::getId, queryVO.getIdList()));
    }
}
