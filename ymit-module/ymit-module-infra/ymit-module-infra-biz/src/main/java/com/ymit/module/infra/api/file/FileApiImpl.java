package com.ymit.module.infra.api.file;

import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.infra.api.file.dto.FileRelation;
import com.ymit.module.infra.api.file.dto.FileRelationVO;
import com.ymit.module.infra.service.file.FileRelService;
import com.ymit.module.infra.service.file.FileService;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件 API 实现类
 *
 * @author
 */
@Service
@Validated
public class FileApiImpl implements FileApi {

    @Resource
    private FileService fileService;

    @Resource
    private FileRelService fileRelService;

    @Override
    public String createFile(String name, String path, byte[] content) {
        return this.fileService.createFile(name, path, content).getUrl();
    }

    @Override
    public List<FileRelationVO> batchSaveFileRel(String kind, Long code, List<FileRelation> datas) {
        if (CollectionUtils.isEmpty(datas)) {
            return new ArrayList<>();
        }
        if (StringUtils.isNotBlank(kind) && code.compareTo(0L) > 0) {
            for (FileRelation item : datas) {
                item.setDataKind(kind);
                item.setDataCode(code);
            }
        }
        return BeanUtils.toBean(this.fileRelService.batchSaveFileRel(datas), FileRelationVO.class);
    }

}
