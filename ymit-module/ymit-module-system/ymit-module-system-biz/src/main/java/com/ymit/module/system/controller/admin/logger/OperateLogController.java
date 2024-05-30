package com.ymit.module.system.controller.admin.logger;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageParam;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.excel.core.util.ExcelUtils;
import com.ymit.framework.operatelog.core.annotations.OperateLog;
import com.ymit.module.system.controller.admin.logger.vo.operatelog.OperateLogPageReqVO;
import com.ymit.module.system.controller.admin.logger.vo.operatelog.OperateLogRespVO;
import com.ymit.module.system.convert.logger.OperateLogConvert;
import com.ymit.module.system.dal.dataobject.logger.OperateLogDO;
import com.ymit.module.system.dal.dataobject.user.AdminUserDO;
import com.ymit.module.system.service.logger.OperateLogService;
import com.ymit.module.system.service.user.AdminUserService;
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
import java.util.Map;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.common.util.collection.CollectionUtils.convertList;
import static com.ymit.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

/**
 * 管理后台 - 操作日志
 */
@RestController
@RequestMapping("/system/operate-log")
@Validated
public class OperateLogController {

    @Resource
    private OperateLogService operateLogService;
    @Resource
    private AdminUserService userService;

    /**
     * 查看操作日志分页列表
     *
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:operate-log:query')")
    public CommonResult<PageResult<OperateLogRespVO>> pageOperateLog(@Valid OperateLogPageReqVO pageReqVO) {
        PageResult<OperateLogDO> pageResult = this.operateLogService.getOperateLogPage(pageReqVO);
        // 获得拼接需要的数据
        Map<Long, AdminUserDO> userMap = this.userService.getUserMap(
                convertList(pageResult.getRecords(), OperateLogDO::getUserId));
        return success(new PageResult<>(OperateLogConvert.convertList(pageResult.getRecords(), userMap),
                pageResult.getTotal()));
    }

    /**
     * 导出操作日志
     *
     * @param response
     * @param exportReqVO
     * @throws IOException
     */
    @GetMapping("/export")
    @PreAuthorize("@ss.hasPermission('system:operate-log:export')")
    @OperateLog(type = EXPORT)
    public void exportOperateLog(HttpServletResponse response, @Valid OperateLogPageReqVO exportReqVO) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OperateLogDO> list = this.operateLogService.getOperateLogPage(exportReqVO).getRecords();
        // 输出
        Map<Long, AdminUserDO> userMap = this.userService.getUserMap(
                convertList(list, OperateLogDO::getUserId));
        ExcelUtils.write(response, "操作日志.xls", "数据列表", OperateLogRespVO.class,
                OperateLogConvert.convertList(list, userMap));
    }

}
