package com.ymit.module.infra.controller.admin.job.vo.log;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.infra.enums.DictTypeConstants;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 定时任务日志 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class JobLogRespVO {
    /**
     * 日志编号
     */
    @ExcelProperty("日志编号")
    private Long id;
    /**
     * 任务编号
     */
    @ExcelProperty("任务编号")
    private Long jobId;
    /**
     * 处理器的名字
     */
    @ExcelProperty("处理器的名字")
    private String handlerName;
    /**
     * 处理器的参数
     */
    @ExcelProperty("处理器的参数")
    private String handlerParam;
    /**
     * 第几次执行
     */
    @ExcelProperty("第几次执行")
    private Integer executeIndex;
    /**
     * 开始执行时间
     */
    @ExcelProperty("开始执行时间")
    private LocalDateTime beginTime;
    /**
     * 结束执行时间
     */
    @ExcelProperty("结束执行时间")
    private LocalDateTime endTime;
    /**
     * 执行时长
     */
    @ExcelProperty("执行时长")
    private Integer duration;
    /**
     * 任务状态，参见 JobLogStatusEnum 枚举
     */
    @ExcelProperty(value = "任务状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.JOB_STATUS)
    private Integer status;
    /**
     * 结果数据
     */
    @ExcelProperty("结果数据")
    private String result;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
