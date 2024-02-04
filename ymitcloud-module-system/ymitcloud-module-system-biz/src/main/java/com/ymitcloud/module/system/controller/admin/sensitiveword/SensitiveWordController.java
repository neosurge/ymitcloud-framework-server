package com.ymitcloud.module.system.controller.admin.sensitiveword;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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
import com.ymitcloud.module.system.controller.admin.sensitiveword.vo.SensitiveWordPageReqVO;
import com.ymitcloud.module.system.controller.admin.sensitiveword.vo.SensitiveWordRespVO;
import com.ymitcloud.module.system.controller.admin.sensitiveword.vo.SensitiveWordSaveVO;
import com.ymitcloud.module.system.dal.dataobject.sensitiveword.SensitiveWordDO;
import com.ymitcloud.module.system.service.sensitiveword.SensitiveWordService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


/**
 * 管理后台 - 敏感词
 */
@RestController
@RequestMapping("/system/sensitive-word")
@Validated
public class SensitiveWordController {

    @Resource
    private SensitiveWordService sensitiveWordService;

    /**
     * 创建敏感词
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:sensitive-word:create')")
    public CommonResult<Long> createSensitiveWord(@Valid @RequestBody SensitiveWordSaveVO createReqVO) {
        return success(sensitiveWordService.createSensitiveWord(createReqVO));
    }

    /**
     * 更新敏感词
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:sensitive-word:update')")
    public CommonResult<Boolean> updateSensitiveWord(@Valid @RequestBody SensitiveWordSaveVO updateReqVO) {
        sensitiveWordService.updateSensitiveWord(updateReqVO);
        return success(true);
    }
    /**
     * 删除敏感词
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:sensitive-word:delete')")
    public CommonResult<Boolean> deleteSensitiveWord(@RequestParam("id") Long id) {
        sensitiveWordService.deleteSensitiveWord(id);
        return success(true);
    }

    /**
     * 获得敏感词
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:sensitive-word:query')")
    public CommonResult<SensitiveWordRespVO> getSensitiveWord(@RequestParam("id") Long id) {
        SensitiveWordDO sensitiveWord = sensitiveWordService.getSensitiveWord(id);
        return success(BeanUtils.toBean(sensitiveWord, SensitiveWordRespVO.class));
    }
    /**
     * 获得敏感词分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:sensitive-word:query')")
    public CommonResult<PageResult<SensitiveWordRespVO>> getSensitiveWordPage(@Valid SensitiveWordPageReqVO pageVO) {
        PageResult<SensitiveWordDO> pageResult = sensitiveWordService.getSensitiveWordPage(pageVO);
        return success(BeanUtils.toBean(pageResult, SensitiveWordRespVO.class));
    }

    /**
     * 导出敏感词 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('system:sensitive-word:export')")
    @OperateLog(type = EXPORT)
    public void exportSensitiveWordExcel(@Valid SensitiveWordPageReqVO exportReqVO, HttpServletResponse response)
            throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SensitiveWordDO> list = sensitiveWordService.getSensitiveWordPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "敏感词.xls", "数据", SensitiveWordRespVO.class,
                BeanUtils.toBean(list, SensitiveWordRespVO.class));
    }

    /**
     * 获取所有敏感词的标签数组
     * 
     * @return
     */
    @GetMapping("/get-tags")
    @PreAuthorize("@ss.hasPermission('system:sensitive-word:query')")
    public CommonResult<Set<String>> getSensitiveWordTagSet() {
        return success(sensitiveWordService.getSensitiveWordTagSet());
    }

    /**
     * 获得文本所包含的不合法的敏感词数组
     * 
     * @param text
     * @param tags
     * @return
     */
    @GetMapping("/validate-text")
    public CommonResult<List<String>> validateText(@RequestParam("text") String text,
            @RequestParam(value = "tags", required = false) List<String> tags) {
        return success(sensitiveWordService.validateText(text, tags));
    }

}
