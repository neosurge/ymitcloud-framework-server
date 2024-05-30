package com.ymit.module.infra.controller.admin.job;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageParam;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.excel.core.util.ExcelUtils;
import com.ymit.framework.operatelog.core.annotations.OperateLog;
import com.ymit.framework.quartz.core.util.CronUtils;
import com.ymit.module.infra.controller.admin.job.vo.job.JobPageReqVO;
import com.ymit.module.infra.controller.admin.job.vo.job.JobRespVO;
import com.ymit.module.infra.controller.admin.job.vo.job.JobSaveReqVO;
import com.ymit.module.infra.dal.dataobject.job.JobDO;
import com.ymit.module.infra.service.job.JobService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.quartz.SchedulerException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;


/**
 * 管理后台 - 定时任务
 */

@RestController
@RequestMapping("/infra/job")
@Validated
public class JobController {

    @Resource
    private JobService jobService;

    /**
     * 创建定时任务
     *
     * @param createReqVO
     * @return
     * @throws SchedulerException
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('infra:job:create')")
    public CommonResult<Long> createJob(@Valid @RequestBody JobSaveReqVO createReqVO) throws SchedulerException {
        return success(this.jobService.createJob(createReqVO));
    }

    /**
     * 更新定时任务
     *
     * @param updateReqVO
     * @return
     * @throws SchedulerException
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('infra:job:update')")
    public CommonResult<Boolean> updateJob(@Valid @RequestBody JobSaveReqVO updateReqVO) throws SchedulerException {
        this.jobService.updateJob(updateReqVO);
        return success(true);
    }

    /**
     * 更新定时任务的状态
     *
     * @param id     编号
     * @param status 状态
     * @return
     * @throws SchedulerException
     */
    @PutMapping("/update-status")
    @PreAuthorize("@ss.hasPermission('infra:job:update')")
    public CommonResult<Boolean> updateJobStatus(@RequestParam(value = "id") Long id,
                                                 @RequestParam("status") Integer status) throws SchedulerException {
        this.jobService.updateJobStatus(id, status);
        return success(true);
    }

    /**
     * 删除定时任务
     *
     * @param id 编号
     * @return
     * @throws SchedulerException
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('infra:job:delete')")
    public CommonResult<Boolean> deleteJob(@RequestParam("id") Long id) throws SchedulerException {
        this.jobService.deleteJob(id);
        return success(true);
    }

    /**
     * 触发定时任务
     *
     * @param id 编号
     * @return
     * @throws SchedulerException
     */
    @PutMapping("/trigger")
    @PreAuthorize("@ss.hasPermission('infra:job:trigger')")
    public CommonResult<Boolean> triggerJob(@RequestParam("id") Long id) throws SchedulerException {
        this.jobService.triggerJob(id);
        return success(true);
    }

    /**
     * 获得定时任务
     *
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('infra:job:query')")
    public CommonResult<JobRespVO> getJob(@RequestParam("id") Long id) {
        JobDO job = this.jobService.getJob(id);
        return success(BeanUtils.toBean(job, JobRespVO.class));
    }

    /**
     * 获得定时任务分页
     *
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('infra:job:query')")
    public CommonResult<PageResult<JobRespVO>> getJobPage(@Valid JobPageReqVO pageVO) {
        PageResult<JobDO> pageResult = this.jobService.getJobPage(pageVO);
        return success(BeanUtils.toBean(pageResult, JobRespVO.class));
    }

    /**
     * 导出定时任务 Excel
     *
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('infra:job:export')")
    @OperateLog(type = EXPORT)
    public void exportJobExcel(@Valid JobPageReqVO exportReqVO, HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JobDO> list = this.jobService.getJobPage(exportReqVO).getRecords();
        // 导出 Excel
        ExcelUtils.write(response, "定时任务.xls", "数据", JobRespVO.class, BeanUtils.toBean(list, JobRespVO.class));
    }

    /**
     * 获得定时任务的下 n 次执行时间
     *
     * @param id    编号
     * @param count 数量
     * @return
     */
    @GetMapping("/get_next_times")
    @PreAuthorize("@ss.hasPermission('infra:job:query')")
    public CommonResult<List<LocalDateTime>> getJobNextTimes(@RequestParam("id") Long id,
                                                             @RequestParam(value = "count", required = false, defaultValue = "5") Integer count) {
        JobDO job = this.jobService.getJob(id);
        if (job == null) {
            return success(Collections.emptyList());
        }
        return success(CronUtils.getNextTimes(job.getCronExpression(), count));
    }

}
