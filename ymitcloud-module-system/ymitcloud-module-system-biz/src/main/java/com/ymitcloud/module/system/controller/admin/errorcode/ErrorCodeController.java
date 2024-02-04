package com.ymitcloud.module.system.controller.admin.errorcode;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.system.controller.admin.errorcode.vo.ErrorCodePageReqVO;
import com.ymitcloud.module.system.controller.admin.errorcode.vo.ErrorCodeRespVO;
import com.ymitcloud.module.system.controller.admin.errorcode.vo.ErrorCodeSaveReqVO;
import com.ymitcloud.module.system.dal.dataobject.errorcode.ErrorCodeDO;
import com.ymitcloud.module.system.service.errorcode.ErrorCodeService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 错误码
 */
@RestController
@RequestMapping("/system/error-code")
@Validated
public class ErrorCodeController {

    @Resource
    private ErrorCodeService errorCodeService;

    /**
     * 创建错误码
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:error-code:create')")
    public CommonResult<Long> createErrorCode(@Valid @RequestBody ErrorCodeSaveReqVO createReqVO) {
        return success(errorCodeService.createErrorCode(createReqVO));
    }

    /**
     * 更新错误码
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:error-code:update')")
    public CommonResult<Boolean> updateErrorCode(@Valid @RequestBody ErrorCodeSaveReqVO updateReqVO) {
        errorCodeService.updateErrorCode(updateReqVO);
        return success(true);
    }

    /**
     * 删除错误码
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:error-code:delete')")
    public CommonResult<Boolean> deleteErrorCode(@RequestParam("id") Long id) {
        errorCodeService.deleteErrorCode(id);
        return success(true);
    }

    /**
     * 获得错误码
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:error-code:query')")
    public CommonResult<ErrorCodeRespVO> getErrorCode(@RequestParam("id") Long id) {
        ErrorCodeDO errorCode = errorCodeService.getErrorCode(id);
        return success(BeanUtils.toBean(errorCode, ErrorCodeRespVO.class));
    }

    /**
     * 获得错误码分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:error-code:query')")
    public CommonResult<PageResult<ErrorCodeRespVO>> getErrorCodePage(@Valid ErrorCodePageReqVO pageVO) {
        PageResult<ErrorCodeDO> pageResult = errorCodeService.getErrorCodePage(pageVO);
        return success(BeanUtils.toBean(pageResult, ErrorCodeRespVO.class));
    }

    /**
     * 导出错误码 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('system:error-code:export')")
    @OperateLog(type = EXPORT)
    public void exportErrorCodeExcel(@Valid ErrorCodePageReqVO exportReqVO, HttpServletResponse response)
            throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ErrorCodeDO> list = errorCodeService.getErrorCodePage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "错误码.xls", "数据", ErrorCodeRespVO.class,
                BeanUtils.toBean(list, ErrorCodeRespVO.class));
    }

}
