package com.ymitcloud.module.system.controller.admin.logger;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.system.controller.admin.logger.vo.operatelog.OperateLogPageReqVO;
import com.ymitcloud.module.system.controller.admin.logger.vo.operatelog.OperateLogRespVO;
import com.ymitcloud.module.system.convert.logger.OperateLogConvert;
import com.ymitcloud.module.system.dal.dataobject.logger.OperateLogDO;
import com.ymitcloud.module.system.dal.dataobject.user.AdminUserDO;
import com.ymitcloud.module.system.service.logger.OperateLogService;
import com.ymitcloud.module.system.service.user.AdminUserService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

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
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:operate-log:query')")
    public CommonResult<PageResult<OperateLogRespVO>> pageOperateLog(@Valid OperateLogPageReqVO pageReqVO) {
        PageResult<OperateLogDO> pageResult = operateLogService.getOperateLogPage(pageReqVO);
        // 获得拼接需要的数据
        Map<Long, AdminUserDO> userMap = userService.getUserMap(
                convertList(pageResult.getList(), OperateLogDO::getUserId));
        return success(new PageResult<>(OperateLogConvert.INSTANCE.convertList(pageResult.getList(), userMap),
                pageResult.getTotal()));
    }

    /**
     * 导出操作日志
     * @param response
     * @param exportReqVO
     * @throws IOException
     */
    @GetMapping("/export")
    @PreAuthorize("@ss.hasPermission('system:operate-log:export')")
    @OperateLog(type = EXPORT)
    public void exportOperateLog(HttpServletResponse response, @Valid OperateLogPageReqVO exportReqVO) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OperateLogDO> list = operateLogService.getOperateLogPage(exportReqVO).getList();
        // 输出
        Map<Long, AdminUserDO> userMap = userService.getUserMap(
                convertList(list, OperateLogDO::getUserId));
        ExcelUtils.write(response, "操作日志.xls", "数据列表", OperateLogRespVO.class,
                OperateLogConvert.INSTANCE.convertList(list, userMap));
    }

}
