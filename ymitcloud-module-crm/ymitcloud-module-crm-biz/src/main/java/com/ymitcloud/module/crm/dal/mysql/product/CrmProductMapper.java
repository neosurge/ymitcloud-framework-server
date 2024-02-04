package com.ymitcloud.module.crm.dal.mysql.product;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymitcloud.module.crm.controller.admin.product.vo.product.CrmProductPageReqVO;
import com.ymitcloud.module.crm.dal.dataobject.product.CrmProductDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * CRM 产品 Mapper
 *
 * @author ZanGe丶
 */
@Mapper
public interface CrmProductMapper extends BaseMapperX<CrmProductDO> {

    default PageResult<CrmProductDO> selectPage(CrmProductPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmProductDO>()
                .likeIfPresent(CrmProductDO::getName, reqVO.getName())
                .eqIfPresent(CrmProductDO::getStatus, reqVO.getStatus())
                .orderByDesc(CrmProductDO::getId));
    }

    default CrmProductDO selectByNo(String no) {
        return selectOne(CrmProductDO::getNo, no);
    }

}
