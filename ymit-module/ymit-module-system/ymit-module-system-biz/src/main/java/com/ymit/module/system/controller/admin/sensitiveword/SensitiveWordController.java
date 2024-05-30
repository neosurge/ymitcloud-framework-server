package com.ymit.module.system.controller.admin.sensitiveword;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageParam;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.excel.core.util.ExcelUtils;
import com.ymit.framework.operatelog.core.annotations.OperateLog;
import com.ymit.module.system.controller.admin.sensitiveword.vo.SensitiveWordPageReqVO;
import com.ymit.module.system.controller.admin.sensitiveword.vo.SensitiveWordRespVO;
import com.ymit.module.system.controller.admin.sensitiveword.vo.SensitiveWordSaveVO;
import com.ymit.module.system.dal.dataobject.sensitiveword.SensitiveWordDO;
import com.ymit.module.system.service.sensitiveword.SensitiveWordService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;


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
        return success(this.sensitiveWordService.createSensitiveWord(createReqVO));
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
        this.sensitiveWordService.updateSensitiveWord(updateReqVO);
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
        this.sensitiveWordService.deleteSensitiveWord(id);
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
        SensitiveWordDO sensitiveWord = this.sensitiveWordService.getSensitiveWord(id);
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
        PageResult<SensitiveWordDO> pageResult = this.sensitiveWordService.getSensitiveWordPage(pageVO);
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
        List<SensitiveWordDO> list = this.sensitiveWordService.getSensitiveWordPage(exportReqVO).getRecords();
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
        return success(this.sensitiveWordService.getSensitiveWordTagSet());
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
        return success(this.sensitiveWordService.validateText(text, tags));
    }

}
