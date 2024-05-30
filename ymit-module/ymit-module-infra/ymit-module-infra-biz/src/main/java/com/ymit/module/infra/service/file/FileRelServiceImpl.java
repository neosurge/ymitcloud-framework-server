package com.ymit.module.infra.service.file;

import cn.hutool.core.io.FileUtil;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.exception.util.ServiceExceptionUtil;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymit.module.infra.api.file.dto.FileRelation;
import com.ymit.module.infra.controller.admin.file.vo.file.FilePageReqVO;
import com.ymit.module.infra.controller.admin.file.vo.filerel.FileRelQueryReqVO;
import com.ymit.module.infra.dal.dataobject.file.FileDO;
import com.ymit.module.infra.dal.dataobject.file.FileRelDO;
import com.ymit.module.infra.dal.mysql.file.FileRelMapper;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;

/**
 * 文件关系 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class FileRelServiceImpl implements FileRelService {

    @Resource
    private FileRelMapper fileRelMapper;

    @Resource
    private FileService fileService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FileRelDO> batchSaveFileRel(List<FileRelation> datas) {
        if (CollectionUtils.isEmpty(datas)) {
            return new ArrayList<>();
        }
        Set<String> dataKinds = new HashSet<>();
        Set<Long> dataCodes = new HashSet<>();
        Set<Long> fileIds = new HashSet<>();
        int sort = 0;
        for (var data : datas) {
            if (StringUtils.isBlank(data.getDataKind())) {
                throw ServiceExceptionUtil.exception0(100000, "文件源不能为空");
            }
            if (data.getDataCode() == null || data.getDataCode().equals(0L)) {
                throw ServiceExceptionUtil.exception0(100000, "文件源不能为空");
            }
            if (StringUtils.isBlank(data.getUseScene())) {
                throw ServiceExceptionUtil.exception0(100000, "文件场景不能为空");
            }
            data.setSort(sort++);
            dataKinds.add(data.getDataKind());
            dataCodes.add(data.getDataCode());
            fileIds.add(data.getFileId());
        }
        this.fileRelMapper.delete(new LambdaQueryWrapperX<FileRelDO>().in(FileRelDO::getDataCode, dataCodes).in(FileRelDO::getDataKind, dataKinds));
        List<FileRelDO> rels = BeanUtils.toBean(datas, FileRelDO.class);
        FilePageReqVO fileReq = new FilePageReqVO().setIds(fileIds);
        fileReq.setPageSize(Integer.MAX_VALUE);
        List<FileDO> files = this.fileService.getFilePage(fileReq).getRecords();
        for (var item : rels) {
            Optional<FileDO> first = files.stream().filter(c -> c.getId().equals(item.getFileId())).findFirst();
            if (first.isPresent()) {
                FileDO file = first.get();
                item.setConfigId(file.getConfigId());
                item.setName(file.getName());
                item.setPath(file.getPath());
                item.setUrl(file.getUrl());
                item.setMime(file.getMime());
                item.setSize(file.getSize());
                item.setExt(FileUtil.extName(file.getName()));
                item.setType(this.getFileType(item.getExt()));
            }
        }
        this.fileRelMapper.insertBatch(rels);
        return this.fileRelMapper.selectList(new LambdaQueryWrapperX<FileRelDO>().in(FileRelDO::getDataCode, dataCodes).in(FileRelDO::getDataKind, dataKinds));
    }

    private String getFileType(String ext) {
        return "";
    }

    @Override
    public FileRelDO getFileRel(Long id) {
        return this.fileRelMapper.selectById(id);
    }

    @Override
    public PageResult<FileRelDO> getFileRelPage(FileRelQueryReqVO queryReqVO) {
        return this.fileRelMapper.selectPage(queryReqVO);
    }

}