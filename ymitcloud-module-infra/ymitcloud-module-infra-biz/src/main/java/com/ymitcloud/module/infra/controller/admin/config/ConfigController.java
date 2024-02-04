package com.ymitcloud.module.infra.controller.admin.config;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
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

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.infra.controller.admin.config.vo.ConfigPageReqVO;
import com.ymitcloud.module.infra.controller.admin.config.vo.ConfigRespVO;
import com.ymitcloud.module.infra.controller.admin.config.vo.ConfigSaveReqVO;
import com.ymitcloud.module.infra.convert.config.ConfigConvert;
import com.ymitcloud.module.infra.dal.dataobject.config.ConfigDO;
import com.ymitcloud.module.infra.enums.ErrorCodeConstants;
import com.ymitcloud.module.infra.service.config.ConfigService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


/**
 * 管理后台 - 参数配置
 */

@RestController
@RequestMapping("/infra/config")
@Validated
public class ConfigController {

    @Resource
    private ConfigService configService;

    /**
     * 创建参数配置
     * 
     * @param createReqVO
     * @return
     */
    @PreAuthorize("@ss.hasPermission('infra:config:create')")
    public CommonResult<Long> createConfig(@Valid @RequestBody ConfigSaveReqVO createReqVO) {
        return success(configService.createConfig(createReqVO));
    }

    /**
     * 修改参数配置
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('infra:config:update')")
    public CommonResult<Boolean> updateConfig(@Valid @RequestBody ConfigSaveReqVO updateReqVO) {
        configService.updateConfig(updateReqVO);
        return success(true);
    }

    /**
     * 删除参数配置
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('infra:config:delete')")
    public CommonResult<Boolean> deleteConfig(@RequestParam("id") Long id) {
        configService.deleteConfig(id);
        return success(true);
    }

    /**
     * 获得参数配置
     * 
     * @param id 编号
     * @return
     */
    @GetMapping(value = "/get")
    @PreAuthorize("@ss.hasPermission('infra:config:query')")
    public CommonResult<ConfigRespVO> getConfig(@RequestParam("id") Long id) {
        return success(ConfigConvert.INSTANCE.convert(configService.getConfig(id)));
    }

    /**
     * 编号
     * 
     * @param key 参数键
     * @return
     */
    @GetMapping(value = "/get-value-by-key")
    public CommonResult<String> getConfigKey(@RequestParam("key") String key) {
        ConfigDO config = configService.getConfigByKey(key);
        if (config == null) {
            return success(null);
        }
        if (!config.getVisible()) {
            throw exception(ErrorCodeConstants.CONFIG_GET_VALUE_ERROR_IF_VISIBLE);
        }
        return success(config.getValue());
    }

    /**
     * 获取参数配置分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('infra:config:query')")
    public CommonResult<PageResult<ConfigRespVO>> getConfigPage(@Valid ConfigPageReqVO pageReqVO) {
        PageResult<ConfigDO> page = configService.getConfigPage(pageReqVO);
        return success(ConfigConvert.INSTANCE.convertPage(page));
    }

    /**
     * 导出参数配置
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export")
    @PreAuthorize("@ss.hasPermission('infra:config:export')")
    @OperateLog(type = EXPORT)
    public void exportConfig(@Valid ConfigPageReqVO exportReqVO, HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ConfigDO> list = configService.getConfigPage(exportReqVO).getList();
        // 输出
        ExcelUtils.write(response, "参数配置.xls", "数据", ConfigRespVO.class, ConfigConvert.INSTANCE.convertList(list));
    }

}
