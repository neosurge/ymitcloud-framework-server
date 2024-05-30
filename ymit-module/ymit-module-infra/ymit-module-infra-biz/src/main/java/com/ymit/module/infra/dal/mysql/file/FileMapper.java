package com.ymit.module.infra.dal.mysql.file;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.mybatis.core.mapper.BaseMapperX;
import com.ymit.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymit.module.infra.controller.admin.file.vo.file.FilePageReqVO;
import com.ymit.module.infra.dal.dataobject.file.FileDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.function.Consumer;

/**
 * 文件操作 Mapper
 *
 * @author 云码源码
 */
@Mapper
public interface FileMapper extends BaseMapperX<FileDO> {
    private LambdaQueryWrapperX<FileDO> buildQueryWrapper(FilePageReqVO reqVO) {
        LambdaQueryWrapperX<FileDO> queryWrapper = new LambdaQueryWrapperX<FileDO>()
                .inIfPresent(FileDO::getId, reqVO.getIds())
                .eqIfPresent(FileDO::getConfigId, reqVO.getConfigId())
                .likeIfPresent(FileDO::getName, reqVO.getName())
                .likeIfPresent(FileDO::getPath, reqVO.getPath())
                .eqIfPresent(FileDO::getUrl, reqVO.getUrl())
                .likeIfPresent(FileDO::getMime, reqVO.getMime())
                .eqIfPresent(FileDO::getSize, reqVO.getSize())
//                .eqIfPresent(FileDO::getDataKind, reqVO.getDataKind())
//                .inIfPresent(FileDO::getDataKind, reqVO.getDataKinds())
//                .eqIfPresent(FileDO::getDataCode, reqVO.getDataCode())
//                .inIfPresent(FileDO::getDataCode, reqVO.getDataCodes())
//                .eqIfPresent(FileDO::getUseScene, reqVO.getUseScene())
//                .inIfPresent(FileDO::getUseScene, reqVO.getUseScenes())
//                .eqIfPresent(FileDO::getType, reqVO.getType())
//                .inIfPresent(FileDO::getType, reqVO.getTypes())
//                .eqIfPresent(FileDO::getExt, reqVO.getExt())
//                .inIfPresent(FileDO::getExt, reqVO.getExts())
//                .eqIfPresent(FileDO::getSort, reqVO.getSort())
//                .eqIfPresent(FileDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(FileDO::getCreateTime, reqVO.getCreateTime());
        //仅传入关键词时，才进行条件追加
        if (StringUtils.isNotBlank(reqVO.getSearchWord())) {
            Consumer<LambdaQueryWrapper<FileDO>> subLikeQuery = q -> q
                    .or(false)
                    .or().like(FileDO::getName, reqVO.getSearchWord())
                    //.or().like(FileDO::getPath, reqVO.getSearchWord())
                    //.or().like(FileDO::getUrl, reqVO.getSearchWord())
                    //.or().like(FileDO::getMime, reqVO.getSearchWord())
                    //.or().like(FileDO::getDataKind, reqVO.getSearchWord())
                    //.or().like(FileDO::getUseScene, reqVO.getSearchWord())
                    //.or().like(FileDO::getType, reqVO.getSearchWord())
                    //.or().like(FileDO::getExt, reqVO.getSearchWord())
                    //.or().like(FileDO::getRemark, reqVO.getSearchWord())
                    //.or().like(FileDO::getCreator, reqVO.getSearchWord())
                    //.or().like(FileDO::getUpdater, reqVO.getSearchWord())
                    ;
            queryWrapper.and(subLikeQuery);
        }
        return queryWrapper;
    }

    //default PageResult<FileDO> selectPage(FilePageReqVO reqVO) {
    //    return this.selectPage(reqVO, new LambdaQueryWrapperX<FileDO>()
    //            .likeIfPresent(FileDO::getPath, reqVO.getPath())
    //            .likeIfPresent(FileDO::getMime, reqVO.getMime())
    //            .betweenIfPresent(FileDO::getCreateTime, reqVO.getCreateTime())
    //            .orderByAsc(FileDO::getSort));
    //}
    default PageResult<FileDO> selectPage(FilePageReqVO reqVO) {
        LambdaQueryWrapperX<FileDO> queryWrapperX = this.buildQueryWrapper(reqVO);
        queryWrapperX.orderByAsc(FileDO::getId);
        return this.selectPage(reqVO, queryWrapperX);
    }

    default List<FileDO> selectList(FilePageReqVO reqVO) {
        LambdaQueryWrapperX<FileDO> queryWrapperX = this.buildQueryWrapper(reqVO);
        queryWrapperX.orderByAsc(FileDO::getId);
        return this.selectList(queryWrapperX);
    }
}
