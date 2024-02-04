package com.ymitcloud.module.infra.controller.admin.file;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.common.util.servlet.ServletUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.infra.controller.admin.file.vo.file.FilePageReqVO;
import com.ymitcloud.module.infra.controller.admin.file.vo.file.FileRespVO;
import com.ymitcloud.module.infra.controller.admin.file.vo.file.FileUploadReqVO;
import com.ymitcloud.module.infra.dal.dataobject.file.FileDO;
import com.ymitcloud.module.infra.service.file.FileService;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import lombok.extern.slf4j.Slf4j;

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

    /**
     * 上传文件
     * 
     * @param uploadReqVO
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    @OperateLog(logArgs = false) // 上传文件，没有记录操作日志的必要
    public CommonResult<String> uploadFile(FileUploadReqVO uploadReqVO) throws Exception {
        MultipartFile file = uploadReqVO.getFile();
        String path = uploadReqVO.getPath();
        return success(
                fileService.createFile(file.getOriginalFilename(), path, IoUtil.readBytes(file.getInputStream())));
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
        fileService.deleteFile(id);
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
    public void getFileContent(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("configId") Long configId) throws Exception {
        // 获取请求的路径
        String path = StrUtil.subAfter(request.getRequestURI(), "/get/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }
        // 解码，解决中文路径的问题 https://gitee.com/zhijiantianya/ymitcloud/pulls/807/
        path = URLUtil.decode(path);

        // 读取内容
        byte[] content = fileService.getFileContent(configId, path);
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
        PageResult<FileDO> pageResult = fileService.getFilePage(pageVO);
        return success(BeanUtils.toBean(pageResult, FileRespVO.class));
    }

}
