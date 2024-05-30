package com.ymit.module.infra.controller.admin.logger;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageParam;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.excel.core.util.ExcelUtils;
import com.ymit.framework.operatelog.core.annotations.OperateLog;
import com.ymit.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogPageReqVO;
import com.ymit.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogRespVO;
import com.ymit.module.infra.dal.dataobject.logger.ApiAccessLogDO;
import com.ymit.module.infra.service.logger.ApiAccessLogService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

/**
 * 管理后台 - API 访问日志
 */
@RestController
@RequestMapping("/infra/api-access-log")
@Validated
public class ApiAccessLogController {

    @Resource
    private ApiAccessLogService apiAccessLogService;

    /**
     * 获得API 访问日志分页
     *
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('infra:api-access-log:query')")
    public CommonResult<PageResult<ApiAccessLogRespVO>> getApiAccessLogPage(@Valid ApiAccessLogPageReqVO pageReqVO) {
        PageResult<ApiAccessLogDO> pageResult = this.apiAccessLogService.getApiAccessLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ApiAccessLogRespVO.class));
    }

    /**
     * 导出API 访问日志 Excel
     *
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('infra:api-access-log:export')")
    @OperateLog(type = EXPORT)
    public void exportApiAccessLogExcel(@Valid ApiAccessLogPageReqVO exportReqVO, HttpServletResponse response)
            throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ApiAccessLogDO> list = this.apiAccessLogService.getApiAccessLogPage(exportReqVO).getRecords();
        // 导出 Excel
        ExcelUtils.write(response, "API 访问日志.xls", "数据", ApiAccessLogRespVO.class,
                BeanUtils.toBean(list, ApiAccessLogRespVO.class));
    }

}
