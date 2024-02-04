package com.ymitcloud.module.infra.controller.admin.logger;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogPageReqVO;
import com.ymitcloud.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogRespVO;
import com.ymitcloud.module.infra.dal.dataobject.logger.ApiAccessLogDO;
import com.ymitcloud.module.infra.service.logger.ApiAccessLogService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

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
        PageResult<ApiAccessLogDO> pageResult = apiAccessLogService.getApiAccessLogPage(pageReqVO);
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
        List<ApiAccessLogDO> list = apiAccessLogService.getApiAccessLogPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "API 访问日志.xls", "数据", ApiAccessLogRespVO.class,
                BeanUtils.toBean(list, ApiAccessLogRespVO.class));
    }

}
