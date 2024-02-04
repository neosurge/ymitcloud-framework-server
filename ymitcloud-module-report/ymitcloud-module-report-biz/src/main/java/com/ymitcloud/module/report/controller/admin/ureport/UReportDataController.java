package com.ymitcloud.module.report.controller.admin.ureport;


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
import com.ymitcloud.module.report.controller.admin.ureport.vo.UReportDataPageReqVO;
import com.ymitcloud.module.report.controller.admin.ureport.vo.UReportDataRespVO;
import com.ymitcloud.module.report.controller.admin.ureport.vo.UReportDataSaveReqVO;
import com.ymitcloud.module.report.dal.dataobject.ureport.UReportDataDO;
import com.ymitcloud.module.report.service.ureport.UReportDataService;



import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


/**
 * 管理后台 - Ureport2 报表
 */

@RestController
@RequestMapping("/report/ureport-data")
@Validated
public class UReportDataController {

    @Resource
    private UReportDataService uReportDataService;


    /**
     * 创建 Ureport2 报表
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('report:ureport-data:create')")
    public CommonResult<Long> createUReportData(@Valid @RequestBody UReportDataSaveReqVO createReqVO) {
        return success(uReportDataService.createUReportData(createReqVO));
    }


    /**
     * 更新 Ureport2 报表
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('report:ureport-data:update')")
    public CommonResult<Boolean> updateUReportData(@Valid @RequestBody UReportDataSaveReqVO updateReqVO) {
        uReportDataService.updateUReportData(updateReqVO);
        return success(true);
    }


    /**
     * 删除 Ureport2 报表
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('report:ureport-data:delete')")
    public CommonResult<Boolean> deleteUReportData(@RequestParam("id") Long id) {
        uReportDataService.deleteUReportData(id);
        return success(true);
    }


    /**
     * 获得Ureport2报表
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('report:ureport-data:query')")
    public CommonResult<UReportDataRespVO> getUReportData(@RequestParam("id") Long id) {
        UReportDataDO uReportData = uReportDataService.getUReportData(id);
        return success(BeanUtils.toBean(uReportData, UReportDataRespVO.class));
    }


    /**
     * 获得Ureport2报表分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('report:ureport-data:query')")
    public CommonResult<PageResult<UReportDataRespVO>> getUReportDataPage(@Valid UReportDataPageReqVO pageReqVO) {
        PageResult<UReportDataDO> pageResult = uReportDataService.getUReportDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UReportDataRespVO.class));
    }


    /**
     * 导出 Ureport2 报表 Excel
     * 
     * @param pageReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('report:ureport-data:export')")
    @OperateLog(type = EXPORT)
    public void exportUReportDataExcel(@Valid UReportDataPageReqVO pageReqVO, HttpServletResponse response)
            throws IOException {

        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UReportDataDO> list = uReportDataService.getUReportDataPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "Ureport2 报表.xls", "数据", UReportDataRespVO.class,

                BeanUtils.toBean(list, UReportDataRespVO.class));

    }

}
