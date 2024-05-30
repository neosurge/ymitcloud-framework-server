package com.ymit.module.infra.service.file;


import com.ymit.framework.common.data.PageResult;
import com.ymit.module.infra.api.file.dto.FileRelation;
import com.ymit.module.infra.controller.admin.file.vo.filerel.FileRelQueryReqVO;
import com.ymit.module.infra.dal.dataobject.file.FileRelDO;

import java.util.List;

/**
 * 文件关系 Service 接口
 *
 * @author 超级管理员
 */
public interface FileRelService {
    /**
     * 保存文件关系
     *
     * @param datas 关系数据
     */
    List<FileRelDO> batchSaveFileRel(List<FileRelation> datas);

    /**
     * 获得文件关系
     *
     * @param id 键
     * @return 文件关系
     */
    FileRelDO getFileRel(Long id);

    /**
     * 获得文件关系分页
     *
     * @param queryReqVO 分页查询
     * @return 文件关系分页
     */
    PageResult<FileRelDO> getFileRelPage(FileRelQueryReqVO queryReqVO);


}