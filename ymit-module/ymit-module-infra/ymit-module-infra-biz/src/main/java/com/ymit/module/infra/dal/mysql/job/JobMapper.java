package com.ymit.module.infra.dal.mysql.job;

import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.mybatis.core.mapper.BaseMapperX;
import com.ymit.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymit.module.infra.controller.admin.job.vo.job.JobPageReqVO;
import com.ymit.module.infra.dal.dataobject.job.JobDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务 Mapper
 *
 * @author
 */
@Mapper
public interface JobMapper extends BaseMapperX<JobDO> {

    default JobDO selectByHandlerName(String handlerName) {
        return this.selectOne(JobDO::getHandlerName, handlerName);
    }

    default PageResult<JobDO> selectPage(JobPageReqVO reqVO) {
        return this.selectPage(reqVO, new LambdaQueryWrapperX<JobDO>()
                .likeIfPresent(JobDO::getName, reqVO.getName())
                .eqIfPresent(JobDO::getStatus, reqVO.getStatus())
                .likeIfPresent(JobDO::getHandlerName, reqVO.getHandlerName())
        );
    }

}
