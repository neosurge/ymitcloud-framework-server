package com.ymitcloud.module.infra.controller.admin.demo.demo02;

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
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.infra.controller.admin.demo.demo02.vo.Demo02CategoryListReqVO;
import com.ymitcloud.module.infra.controller.admin.demo.demo02.vo.Demo02CategoryRespVO;
import com.ymitcloud.module.infra.controller.admin.demo.demo02.vo.Demo02CategorySaveReqVO;
import com.ymitcloud.module.infra.dal.dataobject.demo.demo02.Demo02CategoryDO;
import com.ymitcloud.module.infra.service.demo.demo02.Demo02CategoryService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 示例分类
 */
@RestController
@RequestMapping("/infra/demo02-category")
@Validated
public class Demo02CategoryController {

    @Resource
    private Demo02CategoryService demo02CategoryService;

    /**
     * 创建示例分类
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('infra:demo02-category:create')")
    public CommonResult<Long> createDemo02Category(@Valid @RequestBody Demo02CategorySaveReqVO createReqVO) {
        return success(demo02CategoryService.createDemo02Category(createReqVO));
    }

    /**
     * 更新示例分类
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('infra:demo02-category:update')")
    public CommonResult<Boolean> updateDemo02Category(@Valid @RequestBody Demo02CategorySaveReqVO updateReqVO) {
        demo02CategoryService.updateDemo02Category(updateReqVO);
        return success(true);
    }

    /**
     * 删除示例分类
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('infra:demo02-category:delete')")
    public CommonResult<Boolean> deleteDemo02Category(@RequestParam("id") Long id) {
        demo02CategoryService.deleteDemo02Category(id);
        return success(true);
    }

    /**
     * 获得示例分类
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('infra:demo02-category:query')")
    public CommonResult<Demo02CategoryRespVO> getDemo02Category(@RequestParam("id") Long id) {
        Demo02CategoryDO demo02Category = demo02CategoryService.getDemo02Category(id);
        return success(BeanUtils.toBean(demo02Category, Demo02CategoryRespVO.class));
    }

    /**
     * 获得示例分类列表
     * 
     * @param listReqVO
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('infra:demo02-category:query')")
    public CommonResult<List<Demo02CategoryRespVO>> getDemo02CategoryList(@Valid Demo02CategoryListReqVO listReqVO) {
        List<Demo02CategoryDO> list = demo02CategoryService.getDemo02CategoryList(listReqVO);
        return success(BeanUtils.toBean(list, Demo02CategoryRespVO.class));
    }

    /**
     * 导出示例分类 Excel
     * 
     * @param listReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('infra:demo02-category:export')")
    @OperateLog(type = EXPORT)
    public void exportDemo02CategoryExcel(@Valid Demo02CategoryListReqVO listReqVO, HttpServletResponse response)
            throws IOException {
        List<Demo02CategoryDO> list = demo02CategoryService.getDemo02CategoryList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "示例分类.xls", "数据", Demo02CategoryRespVO.class,
                BeanUtils.toBean(list, Demo02CategoryRespVO.class));
    }

}