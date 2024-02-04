package com.ymitcloud.module.infra.controller.admin.job;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.infra.controller.admin.job.vo.log.JobLogPageReqVO;
import com.ymitcloud.module.infra.controller.admin.job.vo.log.JobLogRespVO;
import com.ymitcloud.module.infra.dal.dataobject.job.JobLogDO;
import com.ymitcloud.module.infra.service.job.JobLogService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 定时任务日志
 */
@RestController
@RequestMapping("/infra/job-log")
@Validated
public class JobLogController {

    @Resource
    private JobLogService jobLogService;

    /**
     * 获得定时任务日志
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('infra:job:query')")
    public CommonResult<JobLogRespVO> getJobLog(@RequestParam("id") Long id) {
        JobLogDO jobLog = jobLogService.getJobLog(id);
        return success(BeanUtils.toBean(jobLog, JobLogRespVO.class));
    }
    /**
     * 获得定时任务日志分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('infra:job:query')")
    public CommonResult<PageResult<JobLogRespVO>> getJobLogPage(@Valid JobLogPageReqVO pageVO) {
        PageResult<JobLogDO> pageResult = jobLogService.getJobLogPage(pageVO);
        return success(BeanUtils.toBean(pageResult, JobLogRespVO.class));
    }

    /**
     * 导出定时任务日志 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('infra:job:export')")
    @OperateLog(type = EXPORT)
    public void exportJobLogExcel(@Valid JobLogPageReqVO exportReqVO, HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JobLogDO> list = jobLogService.getJobLogPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "任务日志.xls", "数据", JobLogRespVO.class, BeanUtils.toBean(list, JobLogRespVO.class));
    }

}