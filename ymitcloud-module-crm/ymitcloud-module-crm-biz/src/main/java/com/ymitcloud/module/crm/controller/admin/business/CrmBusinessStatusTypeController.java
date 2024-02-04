package com.ymitcloud.module.crm.controller.admin.business;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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


import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.crm.controller.admin.business.vo.status.CrmBusinessStatusQueryVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.status.CrmBusinessStatusRespVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypePageReqVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypeQueryVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypeRespVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypeSaveReqVO;
import com.ymitcloud.module.crm.convert.businessstatus.CrmBusinessStatusConvert;
import com.ymitcloud.module.crm.convert.businessstatustype.CrmBusinessStatusTypeConvert;
import com.ymitcloud.module.crm.dal.dataobject.business.CrmBusinessStatusDO;
import com.ymitcloud.module.crm.dal.dataobject.business.CrmBusinessStatusTypeDO;
import com.ymitcloud.module.crm.service.business.CrmBusinessStatusService;
import com.ymitcloud.module.crm.service.business.CrmBusinessStatusTypeService;
import com.ymitcloud.module.system.api.dept.DeptApi;
import com.ymitcloud.module.system.api.dept.dto.DeptRespDTO;



import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


/**
 * 管理后台 - 商机状态类型
 */

@RestController
@RequestMapping("/crm/business-status-type")
@Validated
public class CrmBusinessStatusTypeController {

    @Resource
    private CrmBusinessStatusTypeService businessStatusTypeService;

    @Resource
    private CrmBusinessStatusService businessStatusService;

    @Resource
    private DeptApi deptApi;


    /**
     * 创建商机状态类型
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('crm:business-status-type:create')")
    public CommonResult<Long> createBusinessStatusType(@Valid @RequestBody CrmBusinessStatusTypeSaveReqVO createReqVO) {
        return success(businessStatusTypeService.createBusinessStatusType(createReqVO));
    }


    /**
     * 更新商机状态类型
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('crm:business-status-type:update')")
    public CommonResult<Boolean> updateBusinessStatusType(
            @Valid @RequestBody CrmBusinessStatusTypeSaveReqVO updateReqVO) {

        businessStatusTypeService.updateBusinessStatusType(updateReqVO);
        return success(true);
    }


    /**
     * 删除商机状态类型
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('crm:business-status-type:delete')")
    public CommonResult<Boolean> deleteBusinessStatusType(@RequestParam("id") Long id) {
        businessStatusTypeService.deleteBusinessStatusType(id);
        return success(true);
    }


    /**
     * 获得商机状态类型
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:business-status-type:query')")
    public CommonResult<CrmBusinessStatusTypeRespVO> getBusinessStatusType(@RequestParam("id") Long id) {
        CrmBusinessStatusTypeDO businessStatusType = businessStatusTypeService.getBusinessStatusType(id);
        // 处理状态回显
        // TODO @ljlleo：可以使用 CollectionUtils.convertSet 替代常用的 stream 操作，更简洁一点；下面几个也是哈；
        CrmBusinessStatusQueryVO queryVO = new CrmBusinessStatusQueryVO();
        queryVO.setTypeId(id);
        List<CrmBusinessStatusDO> statusList = businessStatusService.selectList(queryVO);
        return success(CrmBusinessStatusTypeConvert.INSTANCE.convert(businessStatusType, statusList));
    }


    /**
     * 获得商机状态类型分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('crm:business-status-type:query')")
    public CommonResult<PageResult<CrmBusinessStatusTypeRespVO>> getBusinessStatusTypePage(
            @Valid CrmBusinessStatusTypePageReqVO pageReqVO) {
        PageResult<CrmBusinessStatusTypeDO> pageResult = businessStatusTypeService.getBusinessStatusTypePage(pageReqVO);
        // 处理部门回显
        // TODO @ljlleo：可以使用 CollectionUtils.convertSet 替代常用的 stream 操作，更简洁一点；下面几个也是哈；
        Set<Long> deptIds = pageResult.getList().stream().map(CrmBusinessStatusTypeDO::getDeptIds)
                .filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toSet());

        List<DeptRespDTO> deptList = deptApi.getDeptList(deptIds);
        return success(CrmBusinessStatusTypeConvert.INSTANCE.convertPage(pageResult, deptList));
    }


    /**
     * 导出商机状态类型 Excel
     * 
     * @param pageReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('crm:business-status-type:export')")
    @OperateLog(type = EXPORT)
    public void exportBusinessStatusTypeExcel(@Valid CrmBusinessStatusTypePageReqVO pageReqVO,
            HttpServletResponse response) throws IOException {

        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CrmBusinessStatusTypeDO> list = businessStatusTypeService.getBusinessStatusTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "商机状态类型.xls", "数据", CrmBusinessStatusTypeRespVO.class,

                BeanUtils.toBean(list, CrmBusinessStatusTypeRespVO.class));
    }

    /**
     * 获得商机状态类型列表
     * 
     * @return
     */
    @GetMapping("/get-simple-list")

    @PreAuthorize("@ss.hasPermission('crm:business-status-type:query')")
    public CommonResult<List<CrmBusinessStatusTypeRespVO>> getBusinessStatusTypeList() {
        CrmBusinessStatusTypeQueryVO queryVO = new CrmBusinessStatusTypeQueryVO();
        queryVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        List<CrmBusinessStatusTypeDO> list = businessStatusTypeService.selectList(queryVO);
        return success(BeanUtils.toBean(list, CrmBusinessStatusTypeRespVO.class));
    }


    /**
     * 获得商机状态列表
     * 
     * @param typeId
     * @return
     */
    @GetMapping("/get-status-list")
    @PreAuthorize("@ss.hasPermission('crm:business-status:query')")
    public CommonResult<List<CrmBusinessStatusRespVO>> getBusinessStatusListByTypeId(
            @RequestParam("typeId") Long typeId) {

        CrmBusinessStatusQueryVO queryVO = new CrmBusinessStatusQueryVO();
        queryVO.setTypeId(typeId);
        List<CrmBusinessStatusDO> list = businessStatusService.selectList(queryVO);
        return success(CrmBusinessStatusConvert.INSTANCE.convertList(list));
    }

}
