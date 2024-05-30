package com.ymit.module.infra.dal.mysql.file;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.mybatis.core.mapper.BaseMapperX;
import com.ymit.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymit.module.infra.controller.admin.file.vo.filerel.FileRelQueryReqVO;
import com.ymit.module.infra.dal.dataobject.file.FileRelDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.function.Consumer;

/**
 * 文件关系 Mapper
 */
@Mapper
public interface FileRelMapper extends BaseMapperX<FileRelDO> {

    default LambdaQueryWrapperX<FileRelDO> buildQueryWrapper(FileRelQueryReqVO reqVO) {
        LambdaQueryWrapperX<FileRelDO> queryWrapper = new LambdaQueryWrapperX<FileRelDO>()
                .inIfPresent(FileRelDO::getId, reqVO.getIds())
                .eqIfPresent(FileRelDO::getFileId, reqVO.getFileId())
                .eqIfPresent(FileRelDO::getConfigId, reqVO.getConfigId())
                .likeIfPresent(FileRelDO::getName, reqVO.getName())
                .eqIfPresent(FileRelDO::getPath, reqVO.getPath())
                .eqIfPresent(FileRelDO::getUrl, reqVO.getUrl())
                .eqIfPresent(FileRelDO::getMime, reqVO.getMime())
                .eqIfPresent(FileRelDO::getSize, reqVO.getSize())
                .eqIfPresent(FileRelDO::getDataKind, reqVO.getDataKind())
                .eqIfPresent(FileRelDO::getDataCode, reqVO.getDataCode())
                .eqIfPresent(FileRelDO::getUseScene, reqVO.getUseScene())
                .eqIfPresent(FileRelDO::getType, reqVO.getType())
                .inIfPresent(FileRelDO::getType, reqVO.getTypes())
                .eqIfPresent(FileRelDO::getExt, reqVO.getExt())
                .inIfPresent(FileRelDO::getExt, reqVO.getExts())
                .eqIfPresent(FileRelDO::getSort, reqVO.getSort())
                .eqIfPresent(FileRelDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(FileRelDO::getCreateTime, reqVO.getCreateTime());
        //仅传入关键词时，才进行条件追加
        if (StringUtils.isNotBlank(reqVO.getSearchWord())) {
            Consumer<LambdaQueryWrapper<FileRelDO>> subLikeQuery = q -> q
                    .or(false)
                    //.or().like(FileRelDO::getName, reqVO.getSearchWord())
                    //.or().like(FileRelDO::getPath, reqVO.getSearchWord())
                    //.or().like(FileRelDO::getUrl, reqVO.getSearchWord())
                    //.or().like(FileRelDO::getMime, reqVO.getSearchWord())
                    //.or().like(FileRelDO::getDataKind, reqVO.getSearchWord())
                    //.or().like(FileRelDO::getUseScene, reqVO.getSearchWord())
                    //.or().like(FileRelDO::getType, reqVO.getSearchWord())
                    //.or().like(FileRelDO::getExt, reqVO.getSearchWord())
                    //.or().like(FileRelDO::getRemark, reqVO.getSearchWord())
                    //.or().like(FileRelDO::getCreator, reqVO.getSearchWord())
                    //.or().like(FileRelDO::getUpdater, reqVO.getSearchWord())
                    ;
            queryWrapper.and(subLikeQuery);
        }
        return queryWrapper;
    }

    default PageResult<FileRelDO> selectPage(FileRelQueryReqVO reqVO) {
        LambdaQueryWrapperX<FileRelDO> queryWrapperX = this.buildQueryWrapper(reqVO);
        queryWrapperX.orderByDesc(FileRelDO::getId);
        return this.selectPage(reqVO, queryWrapperX);
    }

    default List<FileRelDO> selectList(FileRelQueryReqVO reqVO) {
        LambdaQueryWrapperX<FileRelDO> queryWrapperX = this.buildQueryWrapper(reqVO);
        queryWrapperX.orderByDesc(FileRelDO::getId);
        return this.selectList(queryWrapperX);
    }

}