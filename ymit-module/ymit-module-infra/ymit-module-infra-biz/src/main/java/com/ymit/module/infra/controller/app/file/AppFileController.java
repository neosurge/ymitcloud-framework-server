package com.ymit.module.infra.controller.app.file;

import cn.hutool.core.io.IoUtil;
import com.ymit.framework.common.data.CommonResult;
import com.ymit.module.infra.controller.app.file.vo.AppFileUploadReqVO;
import com.ymit.module.infra.dal.dataobject.file.FileDO;
import com.ymit.module.infra.service.file.FileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.ymit.framework.common.data.CommonResult.success;

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
    public CommonResult<FileDO> uploadFile(AppFileUploadReqVO uploadReqVO) throws Exception {
        MultipartFile file = uploadReqVO.getFile();
        String path = uploadReqVO.getPath();
        return success(this.fileService.createFile(file.getOriginalFilename(), path, IoUtil.readBytes(file.getInputStream())));
    }

}
