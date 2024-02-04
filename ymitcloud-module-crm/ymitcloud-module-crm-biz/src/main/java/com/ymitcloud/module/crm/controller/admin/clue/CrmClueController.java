package com.ymitcloud.module.crm.controller.admin.clue;


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
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;

import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueExcelVO;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueExportReqVO;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmCluePageReqVO;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueRespVO;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueUpdateReqVO;
import com.ymitcloud.module.crm.convert.clue.CrmClueConvert;
import com.ymitcloud.module.crm.dal.dataobject.clue.CrmClueDO;
import com.ymitcloud.module.crm.service.clue.CrmClueService;


import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


/**
 * 管理后台 - 线索
 */

@RestController
@RequestMapping("/crm/clue")
@Validated
public class CrmClueController {

    @Resource
    private CrmClueService clueService;


    /**
     * 创建线索
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('crm:clue:create')")
    public CommonResult<Long> createClue(@Valid @RequestBody CrmClueCreateReqVO createReqVO) {
        return success(clueService.createClue(createReqVO));
    }


    /**
     * 更新线索
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('crm:clue:update')")
    public CommonResult<Boolean> updateClue(@Valid @RequestBody CrmClueUpdateReqVO updateReqVO) {
        clueService.updateClue(updateReqVO);
        return success(true);
    }


    /**
     * 删除线索
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('crm:clue:delete')")
    public CommonResult<Boolean> deleteClue(@RequestParam("id") Long id) {
        clueService.deleteClue(id);
        return success(true);
    }


    /**
     * 获得线索
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:clue:query')")
    public CommonResult<CrmClueRespVO> getClue(@RequestParam("id") Long id) {
        CrmClueDO clue = clueService.getClue(id);
        return success(CrmClueConvert.INSTANCE.convert(clue));
    }


    /**
     * 获得线索分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('crm:clue:query')")
    public CommonResult<PageResult<CrmClueRespVO>> getCluePage(@Valid CrmCluePageReqVO pageVO) {
        PageResult<CrmClueDO> pageResult = clueService.getCluePage(pageVO);
        return success(CrmClueConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 导出线索 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('crm:clue:export')")
    @OperateLog(type = EXPORT)
    public void exportClueExcel(@Valid CrmClueExportReqVO exportReqVO, HttpServletResponse response)
            throws IOException {

        List<CrmClueDO> list = clueService.getClueList(exportReqVO);
        // 导出 Excel
        List<CrmClueExcelVO> datas = CrmClueConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "线索.xls", "数据", CrmClueExcelVO.class, datas);
    }

}
