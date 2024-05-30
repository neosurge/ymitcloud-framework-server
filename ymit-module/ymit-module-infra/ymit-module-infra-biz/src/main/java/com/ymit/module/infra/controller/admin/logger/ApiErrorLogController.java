package com.ymit.module.infra.controller.admin.logger;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageParam;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.excel.core.util.ExcelUtils;
import com.ymit.framework.operatelog.core.annotations.OperateLog;
import com.ymit.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogPageReqVO;
import com.ymit.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogRespVO;
import com.ymit.module.infra.dal.dataobject.logger.ApiErrorLogDO;
import com.ymit.module.infra.service.logger.ApiErrorLogService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static com.ymit.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 管理后台 - API 错误日志
 */
@RestController
@RequestMapping("/infra/api-error-log")
@Validated
public class ApiErrorLogController {

    @Resource
    private ApiErrorLogService apiErrorLogService;

    /**
     * 更新 API 错误日志的状态
     *
     * @param id            编号
     * @param processStatus 处理状态
     * @return
     */
    @PutMapping("/update-status")
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:update-status')")
    public CommonResult<Boolean> updateApiErrorLogProcess(@RequestParam("id") Long id,
                                                          @RequestParam("processStatus") Integer processStatus) {
        this.apiErrorLogService.updateApiErrorLogProcess(id, processStatus, getLoginUserId());
        return success(true);
    }

    /**
     * 获得 API 错误日志分页
     *
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:query')")
    public CommonResult<PageResult<ApiErrorLogRespVO>> getApiErrorLogPage(@Valid ApiErrorLogPageReqVO pageReqVO) {
        PageResult<ApiErrorLogDO> pageResult = this.apiErrorLogService.getApiErrorLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ApiErrorLogRespVO.class));
    }

    /**
     * 导出 API 错误日志 Excel
     *
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:export')")
    @OperateLog(type = EXPORT)
    public void exportApiErrorLogExcel(@Valid ApiErrorLogPageReqVO exportReqVO, HttpServletResponse response)
            throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ApiErrorLogDO> list = this.apiErrorLogService.getApiErrorLogPage(exportReqVO).getRecords();
        // 导出 Excel
        ExcelUtils.write(response, "API 错误日志.xls", "数据", ApiErrorLogRespVO.class,
                BeanUtils.toBean(list, ApiErrorLogRespVO.class));
    }

}
