package com.ymit.module.system.controller.app.dict;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.enums.CommonStatusEnum;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.system.controller.app.dict.vo.AppDictDataRespVO;
import com.ymit.module.system.dal.dataobject.dict.DictDataDO;
import com.ymit.module.system.service.dict.DictDataService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;

/**
 * 用户 App - 字典数据
 */
@RestController
@RequestMapping("/system/dict-data")
@Validated
public class AppDictDataController {

    @Resource
    private DictDataService dictDataService;

    @GetMapping("/type")

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param type 字典类型
     * @return
     */
    public CommonResult<List<AppDictDataRespVO>> getDictDataListByType(@RequestParam("type") String type) {
        List<DictDataDO> list = dictDataService.getDictDataList(CommonStatusEnum.ENABLE.getStatus(), type);
        return success(BeanUtils.toBean(list, AppDictDataRespVO.class));
    }

}
