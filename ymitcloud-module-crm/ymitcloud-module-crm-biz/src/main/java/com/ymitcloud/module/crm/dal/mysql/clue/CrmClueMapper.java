package com.ymitcloud.module.crm.dal.mysql.clue;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueExportReqVO;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmCluePageReqVO;
import com.ymitcloud.module.crm.dal.dataobject.clue.CrmClueDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 线索 Mapper
 *
 * @author Wanwan
 */
@Mapper
public interface CrmClueMapper extends BaseMapperX<CrmClueDO> {

    default PageResult<CrmClueDO> selectPage(CrmCluePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmClueDO>()
                .likeIfPresent(CrmClueDO::getName, reqVO.getName())
                .likeIfPresent(CrmClueDO::getTelephone, reqVO.getTelephone())
                .likeIfPresent(CrmClueDO::getMobile, reqVO.getMobile())
                .orderByDesc(CrmClueDO::getId));
    }

    default List<CrmClueDO> selectList(CrmClueExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CrmClueDO>()
                .eqIfPresent(CrmClueDO::getTransformStatus, reqVO.getTransformStatus())
                .eqIfPresent(CrmClueDO::getFollowUpStatus, reqVO.getFollowUpStatus())
                .likeIfPresent(CrmClueDO::getName, reqVO.getName())
                .eqIfPresent(CrmClueDO::getCustomerId, reqVO.getCustomerId())
                .betweenIfPresent(CrmClueDO::getContactNextTime, reqVO.getContactNextTime())
                .likeIfPresent(CrmClueDO::getTelephone, reqVO.getTelephone())
                .likeIfPresent(CrmClueDO::getMobile, reqVO.getMobile())
                .likeIfPresent(CrmClueDO::getAddress, reqVO.getAddress())
                .betweenIfPresent(CrmClueDO::getContactLastTime, reqVO.getContactLastTime())
                .betweenIfPresent(CrmClueDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CrmClueDO::getId));
    }

}
