package com.ymit.module.infra.controller.admin.config;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageParam;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.excel.core.util.ExcelUtils;
import com.ymit.framework.operatelog.core.annotations.OperateLog;
import com.ymit.module.infra.controller.admin.config.vo.ConfigPageReqVO;
import com.ymit.module.infra.controller.admin.config.vo.ConfigRespVO;
import com.ymit.module.infra.controller.admin.config.vo.ConfigSaveReqVO;
import com.ymit.module.infra.convert.config.ConfigConvert;
import com.ymit.module.infra.dal.dataobject.config.ConfigDO;
import com.ymit.module.infra.enums.ErrorCodeConstants;
import com.ymit.module.infra.service.config.ConfigService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymit.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;


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
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('infra:config:create')")
    public CommonResult<Long> createConfig(@Valid @RequestBody ConfigSaveReqVO createReqVO) {
        return success(this.configService.createConfig(createReqVO));
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
        this.configService.updateConfig(updateReqVO);
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
        this.configService.deleteConfig(id);
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
        return success(ConfigConvert.INSTANCE.convert(this.configService.getConfig(id)));
    }

    /**
     * 编号
     *
     * @param key 参数键
     * @return
     */
    @GetMapping(value = "/get-value-by-key")
    public CommonResult<String> getConfigKey(@RequestParam("key") String key) {
        ConfigDO config = this.configService.getConfigByKey(key);
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
        PageResult<ConfigDO> page = this.configService.getConfigPage(pageReqVO);
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
        List<ConfigDO> list = this.configService.getConfigPage(exportReqVO).getRecords();
        // 输出
        ExcelUtils.write(response, "参数配置.xls", "数据", ConfigRespVO.class, ConfigConvert.INSTANCE.convertList(list));
    }

}
