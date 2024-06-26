package com.ymit.module.infra.convert.file;

import com.ymit.module.infra.controller.admin.file.vo.config.FileConfigSaveReqVO;
import com.ymit.module.infra.dal.dataobject.file.FileConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 文件配置 Convert
 *
 * @author 云码源码
 */
@Mapper
public interface FileConfigConvert {

    FileConfigConvert INSTANCE = Mappers.getMapper(FileConfigConvert.class);

    @Mappings({
            @Mapping(target = "config", ignore = true),
            @Mapping(target = "master", ignore = true)
    })
    FileConfigDO convert(FileConfigSaveReqVO bean);

}
