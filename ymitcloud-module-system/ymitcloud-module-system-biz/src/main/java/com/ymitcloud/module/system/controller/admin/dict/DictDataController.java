package com.ymitcloud.module.system.controller.admin.dict;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.system.controller.admin.dict.vo.data.DictDataPageReqVO;
import com.ymitcloud.module.system.controller.admin.dict.vo.data.DictDataRespVO;
import com.ymitcloud.module.system.controller.admin.dict.vo.data.DictDataSaveReqVO;
import com.ymitcloud.module.system.controller.admin.dict.vo.data.DictDataSimpleRespVO;
import com.ymitcloud.module.system.dal.dataobject.dict.DictDataDO;
import com.ymitcloud.module.system.service.dict.DictDataService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 字典数据
 */
@RestController
@RequestMapping("/system/dict-data")
@Validated
public class DictDataController {

    @Resource
    private DictDataService dictDataService;

    /**
     * 新增字典数据
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:dict:create')")
    public CommonResult<Long> createDictData(@Valid @RequestBody DictDataSaveReqVO createReqVO) {
        Long dictDataId = dictDataService.createDictData(createReqVO);
        return success(dictDataId);
    }

    /**
     * 修改字典数据
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:dict:update')")
    public CommonResult<Boolean> updateDictData(@Valid @RequestBody DictDataSaveReqVO updateReqVO) {
        dictDataService.updateDictData(updateReqVO);
        return success(true);
    }

    /**
     * 删除字典数据
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:dict:delete')")
    public CommonResult<Boolean> deleteDictData(Long id) {
        dictDataService.deleteDictData(id);
        return success(true);
    }

    /**
     * 获得全部字典数据列表，一般用于管理后台缓存字典数据在本地
     * 
     * @return
     */
    @GetMapping(value = { "/list-all-simple", "simple-list" })
    // 无需添加权限认证，因为前端全局都需要
    public CommonResult<List<DictDataSimpleRespVO>> getSimpleDictDataList() {
        List<DictDataDO> list = dictDataService.getDictDataList(CommonStatusEnum.ENABLE.getStatus(), null);
        return success(BeanUtils.toBean(list, DictDataSimpleRespVO.class));
    }

    /**
     * 获得字典类型的分页列表
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:dict:query')")
    public CommonResult<PageResult<DictDataRespVO>> getDictTypePage(@Valid DictDataPageReqVO pageReqVO) {
        PageResult<DictDataDO> pageResult = dictDataService.getDictDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DictDataRespVO.class));
    }

    /**
     * 查询字典数据详细
     * 
     * @param id 编号
     * @return
     */
    @GetMapping(value = "/get")
    @PreAuthorize("@ss.hasPermission('system:dict:query')")
    public CommonResult<DictDataRespVO> getDictData(@RequestParam("id") Long id) {
        DictDataDO dictData = dictDataService.getDictData(id);
        return success(BeanUtils.toBean(dictData, DictDataRespVO.class));
    }

    /**
     * 导出字典数据
     * 
     * @param response
     * @param exportReqVO
     * @throws IOException
     */
    @GetMapping("/export")
    @PreAuthorize("@ss.hasPermission('system:dict:export')")
    @OperateLog(type = EXPORT)
    public void export(HttpServletResponse response, @Valid DictDataPageReqVO exportReqVO) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DictDataDO> list = dictDataService.getDictDataPage(exportReqVO).getList();
        // 输出
        ExcelUtils.write(response, "字典数据.xls", "数据", DictDataRespVO.class,
                BeanUtils.toBean(list, DictDataRespVO.class));
    }

}
