package com.ymit.module.infra.controller.admin.codegen;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ZipUtil;
import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.common.util.servlet.ServletUtils;
import com.ymit.module.infra.controller.admin.codegen.vo.CodegenCreateListReqVO;
import com.ymit.module.infra.controller.admin.codegen.vo.CodegenDetailRespVO;
import com.ymit.module.infra.controller.admin.codegen.vo.CodegenPreviewRespVO;
import com.ymit.module.infra.controller.admin.codegen.vo.CodegenUpdateReqVO;
import com.ymit.module.infra.controller.admin.codegen.vo.table.CodegenTablePageReqVO;
import com.ymit.module.infra.controller.admin.codegen.vo.table.CodegenTableRespVO;
import com.ymit.module.infra.controller.admin.codegen.vo.table.DatabaseTableRespVO;
import com.ymit.module.infra.convert.codegen.CodegenConvert;
import com.ymit.module.infra.dal.dataobject.codegen.CodegenColumnDO;
import com.ymit.module.infra.dal.dataobject.codegen.CodegenTableDO;
import com.ymit.module.infra.service.codegen.CodegenService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 管理后台 - 代码生成器
 */
@RestController
@RequestMapping("/infra/codegen")
@Validated
public class CodegenController {

    @Resource
    private CodegenService codegenService;

    /**
     * 获得数据库自带的表定义列表
     *
     * @param dataSourceConfigId 数据源配置的编号
     * @param name               表名，模糊匹配
     * @param comment            描述，模糊匹配
     * @return
     */
    @GetMapping("/db/table/list")
    @PreAuthorize("@ss.hasPermission('infra:codegen:query')")
    public CommonResult<List<DatabaseTableRespVO>> getDatabaseTableList(
            @RequestParam(value = "dataSourceConfigId") Long dataSourceConfigId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "comment", required = false) String comment) {
        return success(this.codegenService.getDatabaseTableList(dataSourceConfigId, name, comment));
    }

    /**
     * 获得表定义列表
     *
     * @param dataSourceConfigId 数据源配置的编号
     * @return
     */
    @GetMapping("/table/list")
    @PreAuthorize("@ss.hasPermission('infra:codegen:query')")
    public CommonResult<List<CodegenTableRespVO>> getCodegenTableList(
            @RequestParam(value = "dataSourceConfigId") Long dataSourceConfigId) {
        List<CodegenTableDO> list = this.codegenService.getCodegenTableList(dataSourceConfigId);
        return success(BeanUtils.toBean(list, CodegenTableRespVO.class));
    }

    /**
     * 获得表定义分页
     *
     * @param pageReqVO
     * @return
     */
    @GetMapping("/table/page")
    @PreAuthorize("@ss.hasPermission('infra:codegen:query')")
    public CommonResult<PageResult<CodegenTableRespVO>> getCodegenTablePage(@Valid CodegenTablePageReqVO pageReqVO) {
        PageResult<CodegenTableDO> pageResult = this.codegenService.getCodegenTablePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CodegenTableRespVO.class));
    }

    /**
     * 获得表和字段的明细
     *
     * @param tableId 表编号
     * @return
     */
    @GetMapping("/detail")
    @PreAuthorize("@ss.hasPermission('infra:codegen:query')")
    public CommonResult<CodegenDetailRespVO> getCodegenDetail(@RequestParam("tableId") Long tableId) {
        CodegenTableDO table = this.codegenService.getCodegenTable(tableId);
        List<CodegenColumnDO> columns = this.codegenService.getCodegenColumnListByTableId(tableId);
        // 拼装返回
        return success(CodegenConvert.INSTANCE.convert(table, columns));
    }

    /**
     * 基于数据库的表结构，创建代码生成器的表和字段定义
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/create-list")
    @PreAuthorize("@ss.hasPermission('infra:codegen:create')")
    public CommonResult<List<Long>> createCodegenList(@Valid @RequestBody CodegenCreateListReqVO reqVO) {
        return success(this.codegenService.createCodegenList(getLoginUserId(), reqVO));
    }

    /**
     * 更新数据库的表和字段定义
     *
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('infra:codegen:update')")
    public CommonResult<Boolean> updateCodegen(@Valid @RequestBody CodegenUpdateReqVO updateReqVO) {
        this.codegenService.updateCodegen(updateReqVO);
        return success(true);
    }

    /**
     * 基于数据库的表结构，同步数据库的表和字段定义
     *
     * @param tableId 表编号
     * @return
     */
    @PutMapping("/sync-from-db")
    @PreAuthorize("@ss.hasPermission('infra:codegen:update')")
    public CommonResult<Boolean> syncCodegenFromDB(@RequestParam("tableId") Long tableId) {
        this.codegenService.syncCodegenFromDB(tableId);
        return success(true);
    }

    /**
     * 删除数据库的表和字段定义
     *
     * @param tableId 表编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('infra:codegen:delete')")
    public CommonResult<Boolean> deleteCodegen(@RequestParam("tableId") Long tableId) {
        this.codegenService.deleteCodegen(tableId);
        return success(true);
    }

    /**
     * 预览生成代码
     *
     * @param tableId 表编号
     * @return
     */
    @GetMapping("/preview")
    @PreAuthorize("@ss.hasPermission('infra:codegen:preview')")
    public CommonResult<List<CodegenPreviewRespVO>> previewCodegen(@RequestParam("tableId") Long tableId) {
        Map<String, String> codes = this.codegenService.generationCodes(tableId);
        return success(CodegenConvert.INSTANCE.convert(codes));
    }

    /**
     * 下载生成代码
     *
     * @param tableId  表编号
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    @PreAuthorize("@ss.hasPermission('infra:codegen:download')")
    public void downloadCodegen(@RequestParam("tableId") Long tableId, HttpServletResponse response)
            throws IOException {
        // 生成代码
        Map<String, String> codes = this.codegenService.generationCodes(tableId);
        // 构建 zip 包
        String[] paths = codes.keySet().toArray(new String[0]);
        ByteArrayInputStream[] ins = codes.values().stream().map(IoUtil::toUtf8Stream)
                .toArray(ByteArrayInputStream[]::new);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipUtil.zip(outputStream, paths, ins);
        // 输出
        ServletUtils.writeAttachment(response, "codegen.zip", outputStream.toByteArray());
    }

}
