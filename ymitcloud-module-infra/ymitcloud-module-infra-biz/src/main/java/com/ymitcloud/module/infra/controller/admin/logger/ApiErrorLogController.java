package com.ymitcloud.module.infra.controller.admin.logger;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogPageReqVO;
import com.ymitcloud.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogRespVO;
import com.ymitcloud.module.infra.dal.dataobject.logger.ApiErrorLogDO;
import com.ymitcloud.module.infra.service.logger.ApiErrorLogService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

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
        apiErrorLogService.updateApiErrorLogProcess(id, processStatus, getLoginUserId());
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
        PageResult<ApiErrorLogDO> pageResult = apiErrorLogService.getApiErrorLogPage(pageReqVO);
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
        List<ApiErrorLogDO> list = apiErrorLogService.getApiErrorLogPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "API 错误日志.xls", "数据", ApiErrorLogRespVO.class,
                BeanUtils.toBean(list, ApiErrorLogRespVO.class));
    }

}
