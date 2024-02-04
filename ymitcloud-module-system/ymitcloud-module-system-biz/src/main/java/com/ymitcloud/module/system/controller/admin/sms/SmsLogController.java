package com.ymitcloud.module.system.controller.admin.sms;

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
import com.ymitcloud.module.system.controller.admin.sms.vo.log.SmsLogPageReqVO;
import com.ymitcloud.module.system.controller.admin.sms.vo.log.SmsLogRespVO;
import com.ymitcloud.module.system.dal.dataobject.sms.SmsLogDO;
import com.ymitcloud.module.system.service.sms.SmsLogService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 短信日志
 */
@RestController
@RequestMapping("/system/sms-log")
@Validated
public class SmsLogController {

    @Resource
    private SmsLogService smsLogService;
    /**
     * 获得短信日志分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:sms-log:query')")
    public CommonResult<PageResult<SmsLogRespVO>> getSmsLogPage(@Valid SmsLogPageReqVO pageReqVO) {
        PageResult<SmsLogDO> pageResult = smsLogService.getSmsLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SmsLogRespVO.class));
    }
    /**
     * 导出短信日志 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('system:sms-log:export')")
    @OperateLog(type = EXPORT)
    public void exportSmsLogExcel(@Valid SmsLogPageReqVO exportReqVO, HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SmsLogDO> list = smsLogService.getSmsLogPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "短信日志.xls", "数据", SmsLogRespVO.class, BeanUtils.toBean(list, SmsLogRespVO.class));
    }

}
