package com.ymitcloud.module.infra.controller.app.file;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.infra.controller.app.file.vo.AppFileUploadReqVO;
import com.ymitcloud.module.infra.service.file.FileService;

import cn.hutool.core.io.IoUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 App - 文件存储
 */
@RestController
@RequestMapping("/infra/file")
@Validated
@Slf4j
public class AppFileController {

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
    public CommonResult<String> uploadFile(AppFileUploadReqVO uploadReqVO) throws Exception {
        MultipartFile file = uploadReqVO.getFile();
        String path = uploadReqVO.getPath();
        return success(
                fileService.createFile(file.getOriginalFilename(), path, IoUtil.readBytes(file.getInputStream())));
    }

}
