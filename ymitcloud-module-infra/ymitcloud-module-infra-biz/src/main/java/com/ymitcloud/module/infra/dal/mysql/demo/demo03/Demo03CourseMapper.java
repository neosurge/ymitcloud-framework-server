package com.ymitcloud.module.infra.dal.mysql.demo.demo03;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymitcloud.module.infra.dal.dataobject.demo.demo03.Demo03CourseDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学生课程 Mapper
 *

 * @author 云码源码

 */
@Mapper
public interface Demo03CourseMapper extends BaseMapperX<Demo03CourseDO> {

    default PageResult<Demo03CourseDO> selectPage(PageParam reqVO, Long studentId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<Demo03CourseDO>()
                .eq(Demo03CourseDO::getStudentId, studentId)
                .orderByDesc(Demo03CourseDO::getId));
    }

    default List<Demo03CourseDO> selectListByStudentId(Long studentId) {
        return selectList(Demo03CourseDO::getStudentId, studentId);
    }

    default int deleteByStudentId(Long studentId) {
        return delete(Demo03CourseDO::getStudentId, studentId);
    }

}