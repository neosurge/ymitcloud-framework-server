package com.ymitcloud.module.infra.controller.admin.demo.demo01;

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
import com.ymitcloud.module.infra.controller.admin.demo.demo01.vo.Demo01ContactPageReqVO;
import com.ymitcloud.module.infra.controller.admin.demo.demo01.vo.Demo01ContactRespVO;
import com.ymitcloud.module.infra.controller.admin.demo.demo01.vo.Demo01ContactSaveReqVO;
import com.ymitcloud.module.infra.dal.dataobject.demo.demo01.Demo01ContactDO;
import com.ymitcloud.module.infra.service.demo.demo01.Demo01ContactService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 示例联系人
 */
@RestController
@RequestMapping("/infra/demo01-contact")
@Validated
public class Demo01ContactController {

    @Resource
    private Demo01ContactService demo01ContactService;

    /**
     * 创建示例联系人
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('infra:demo01-contact:create')")
    public CommonResult<Long> createDemo01Contact(@Valid @RequestBody Demo01ContactSaveReqVO createReqVO) {
        return success(demo01ContactService.createDemo01Contact(createReqVO));
    }
    /**
     * 更新示例联系人
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('infra:demo01-contact:update')")
    public CommonResult<Boolean> updateDemo01Contact(@Valid @RequestBody Demo01ContactSaveReqVO updateReqVO) {
        demo01ContactService.updateDemo01Contact(updateReqVO);
        return success(true);
    }

    /**
     * 删除示例联系人
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('infra:demo01-contact:delete')")
    public CommonResult<Boolean> deleteDemo01Contact(@RequestParam("id") Long id) {
        demo01ContactService.deleteDemo01Contact(id);
        return success(true);
    }

    /**
     * 获得示例联系人
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('infra:demo01-contact:query')")
    public CommonResult<Demo01ContactRespVO> getDemo01Contact(@RequestParam("id") Long id) {
        Demo01ContactDO demo01Contact = demo01ContactService.getDemo01Contact(id);
        return success(BeanUtils.toBean(demo01Contact, Demo01ContactRespVO.class));
    }

    /**
     * 获得示例联系人分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('infra:demo01-contact:query')")
    public CommonResult<PageResult<Demo01ContactRespVO>> getDemo01ContactPage(@Valid Demo01ContactPageReqVO pageReqVO) {
        PageResult<Demo01ContactDO> pageResult = demo01ContactService.getDemo01ContactPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, Demo01ContactRespVO.class));
    }

    /*
     * 导出示例联系人 Excel
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('infra:demo01-contact:export')")
    @OperateLog(type = EXPORT)
    public void exportDemo01ContactExcel(@Valid Demo01ContactPageReqVO pageReqVO, HttpServletResponse response)
            throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<Demo01ContactDO> list = demo01ContactService.getDemo01ContactPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "示例联系人.xls", "数据", Demo01ContactRespVO.class,
                BeanUtils.toBean(list, Demo01ContactRespVO.class));
    }

}