package com.ymitcloud.module.system.api.dict;

import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.system.api.dict.dto.DictDataRespDTO;
import com.ymitcloud.module.system.dal.dataobject.dict.DictDataDO;
import com.ymitcloud.module.system.service.dict.DictDataService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;

/**
 * 字典数据 API 实现类
 *

 * @author 

 */
@Service
public class DictDataApiImpl implements DictDataApi {

    @Resource
    private DictDataService dictDataService;

    @Override
    public void validateDictDataList(String dictType, Collection<String> values) {
        dictDataService.validateDictDataList(dictType, values);
    }

    @Override
    public DictDataRespDTO getDictData(String dictType, String value) {
        DictDataDO dictData = dictDataService.getDictData(dictType, value);
        return BeanUtils.toBean(dictData, DictDataRespDTO.class);
    }

    @Override
    public DictDataRespDTO parseDictData(String dictType, String label) {
        DictDataDO dictData = dictDataService.parseDictData(dictType, label);
        return BeanUtils.toBean(dictData, DictDataRespDTO.class);
    }

}
