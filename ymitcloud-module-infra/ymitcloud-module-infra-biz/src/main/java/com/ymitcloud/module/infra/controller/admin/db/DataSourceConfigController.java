package com.ymitcloud.module.infra.controller.admin.db;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

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

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.infra.controller.admin.db.vo.DataSourceConfigRespVO;
import com.ymitcloud.module.infra.controller.admin.db.vo.DataSourceConfigSaveReqVO;
import com.ymitcloud.module.infra.dal.dataobject.db.DataSourceConfigDO;
import com.ymitcloud.module.infra.service.db.DataSourceConfigService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 数据源配置
 */
@RestController
@RequestMapping("/infra/data-source-config")
@Validated
public class DataSourceConfigController {

    @Resource
    private DataSourceConfigService dataSourceConfigService;

    /**
     * 创建数据源配置
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('infra:data-source-config:create')")
    public CommonResult<Long> createDataSourceConfig(@Valid @RequestBody DataSourceConfigSaveReqVO createReqVO) {
        return success(dataSourceConfigService.createDataSourceConfig(createReqVO));
    }

    /**
     * 更新数据源配置
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('infra:data-source-config:update')")
    public CommonResult<Boolean> updateDataSourceConfig(@Valid @RequestBody DataSourceConfigSaveReqVO updateReqVO) {
        dataSourceConfigService.updateDataSourceConfig(updateReqVO);
        return success(true);
    }

    /**
     * 删除数据源配置
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('infra:data-source-config:delete')")
    public CommonResult<Boolean> deleteDataSourceConfig(@RequestParam("id") Long id) {
        dataSourceConfigService.deleteDataSourceConfig(id);
        return success(true);
    }

    /**
     * 获得数据源配置
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('infra:data-source-config:query')")
    public CommonResult<DataSourceConfigRespVO> getDataSourceConfig(@RequestParam("id") Long id) {
        DataSourceConfigDO config = dataSourceConfigService.getDataSourceConfig(id);
        return success(BeanUtils.toBean(config, DataSourceConfigRespVO.class));
    }

    /**
     * 获得数据源配置列表
     * 
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('infra:data-source-config:query')")
    public CommonResult<List<DataSourceConfigRespVO>> getDataSourceConfigList() {
        List<DataSourceConfigDO> list = dataSourceConfigService.getDataSourceConfigList();
        return success(BeanUtils.toBean(list, DataSourceConfigRespVO.class));
    }

}
