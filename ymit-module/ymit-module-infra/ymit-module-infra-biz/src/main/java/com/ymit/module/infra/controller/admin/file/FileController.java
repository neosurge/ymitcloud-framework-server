package com.ymit.module.infra.controller.admin.file;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.common.util.servlet.ServletUtils;
import com.ymit.module.infra.api.file.dto.FileRelation;
import com.ymit.module.infra.controller.admin.file.vo.file.FilePageReqVO;
import com.ymit.module.infra.controller.admin.file.vo.file.FileRespVO;
import com.ymit.module.infra.controller.admin.file.vo.file.FileUploadReqVO;
import com.ymit.module.infra.controller.admin.file.vo.filerel.FileRelQueryReqVO;
import com.ymit.module.infra.controller.admin.file.vo.filerel.FileRelRespVO;
import com.ymit.module.infra.dal.dataobject.file.FileDO;
import com.ymit.module.infra.dal.dataobject.file.FileRelDO;
import com.ymit.module.infra.service.file.FileRelService;
import com.ymit.module.infra.service.file.FileService;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;

/**
 * 管理后台 - 文件存储
 */
@RestController
@RequestMapping("/infra/file")
@Validated
@Slf4j
public class FileController {

    @Resource
    private FileService fileService;
    @Resource
    private FileRelService fileRelService;

    /**
     * 上传文件
     *
     * @param uploadReqVO
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
//    @OperateLog(module = "infra", name = "文件管理", type = OperateTypeEnum.CREATE)
    public CommonResult<FileDO> uploadFileOld(FileUploadReqVO uploadReqVO) throws Exception {
        MultipartFile file = uploadReqVO.getFile();
        String path = uploadReqVO.getPath();
        return success(this.fileService.createFile(file.getOriginalFilename(), path, IoUtil.readBytes(file.getInputStream())));
    }

    /**
     * 删除文件
     *
     * @param id 编号
     * @return
     * @throws Exception
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('infra:file:delete')")
    public CommonResult<Boolean> deleteFile(@RequestParam("id") Long id) throws Exception {
        this.fileService.deleteFile(id);
        return success(true);
    }

    /**
     * 下载文件
     *
     * @param request
     * @param response
     * @param configId 配置编号
     * @throws Exception
     */
    @GetMapping("/{configId}/get/**")
    @PermitAll
    public void getFileContent(HttpServletRequest request, HttpServletResponse response, @PathVariable("configId") Long configId) throws Exception {
        // 获取请求的路径
        String path = StrUtil.subAfter(request.getRequestURI(), "/get/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }
        // 解码，解决中文路径的问题
        path = URLUtil.decode(path);

        // 读取内容
        byte[] content = this.fileService.getFileContent(configId, path);
        if (content == null) {
            log.warn("[getFileContent][configId({}) path({}) 文件不存在]", configId, path);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        ServletUtils.writeAttachment(response, path, content);
    }

    /**
     * 获得文件分页
     *
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('infra:file:query')")
    public CommonResult<PageResult<FileRespVO>> getFilePage(@Valid FilePageReqVO pageVO) {
        PageResult<FileDO> pageResult = this.fileService.getFilePage(pageVO);
        return success(BeanUtils.toBean(pageResult, FileRespVO.class));
    }

    /**
     * 获得文件关系分页
     *
     * @param queryReqVO 分页请求参数
     * @return 分页列表
     */
    @GetMapping("/page/rels")
    public CommonResult<PageResult<FileRelRespVO>> getFileRelPage(@Valid FileRelQueryReqVO queryReqVO) {
        PageResult<FileRelDO> pageResult = this.fileRelService.getFileRelPage(queryReqVO);
        return success(BeanUtils.toBean(pageResult, FileRelRespVO.class));
    }

    /**
     * 保存文件关联关系
     *
     * @param queryReqVO 关系数据
     * @return 返回值
     */
    @PostMapping("/save/rels")
    public CommonResult<List<FileRelRespVO>> batchSaveRel(@RequestBody @Valid List<FileRelation> queryReqVO) {
        return success(BeanUtils.toBean(this.fileRelService.batchSaveFileRel(queryReqVO), FileRelRespVO.class));
    }
}
