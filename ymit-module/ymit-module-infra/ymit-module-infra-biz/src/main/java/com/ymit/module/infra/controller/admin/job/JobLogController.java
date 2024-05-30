package com.ymit.module.infra.controller.admin.job;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageParam;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.excel.core.util.ExcelUtils;
import com.ymit.framework.operatelog.core.annotations.OperateLog;
import com.ymit.module.infra.controller.admin.job.vo.log.JobLogPageReqVO;
import com.ymit.module.infra.controller.admin.job.vo.log.JobLogRespVO;
import com.ymit.module.infra.dal.dataobject.job.JobLogDO;
import com.ymit.module.infra.service.job.JobLogService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

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
        JobLogDO jobLog = this.jobLogService.getJobLog(id);
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
        PageResult<JobLogDO> pageResult = this.jobLogService.getJobLogPage(pageVO);
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
        List<JobLogDO> list = this.jobLogService.getJobLogPage(exportReqVO).getRecords();
        // 导出 Excel
        ExcelUtils.write(response, "任务日志.xls", "数据", JobLogRespVO.class, BeanUtils.toBean(list, JobLogRespVO.class));
    }

}