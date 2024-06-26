package com.ymit.module.infra.controller.admin.file;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.infra.controller.admin.file.vo.config.FileConfigPageReqVO;
import com.ymit.module.infra.controller.admin.file.vo.config.FileConfigRespVO;
import com.ymit.module.infra.controller.admin.file.vo.config.FileConfigSaveReqVO;
import com.ymit.module.infra.dal.dataobject.file.FileConfigDO;
import com.ymit.module.infra.service.file.FileConfigService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ymit.framework.common.data.CommonResult.success;

/**
 * 管理后台 - 文件配置
 */
@RestController
@RequestMapping("/infra/file-config")
@Validated
public class FileConfigController {

    @Resource
    private FileConfigService fileConfigService;

    /**
     * 创建文件配置
     *
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('infra:file-config:create')")
    public CommonResult<Long> createFileConfig(@Valid @RequestBody FileConfigSaveReqVO createReqVO) {
        return success(this.fileConfigService.createFileConfig(createReqVO));
    }

    /**
     * 更新文件配置
     *
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('infra:file-config:update')")
    public CommonResult<Boolean> updateFileConfig(@Valid @RequestBody FileConfigSaveReqVO updateReqVO) {
        this.fileConfigService.updateFileConfig(updateReqVO);
        return success(true);
    }

    /**
     * 更新文件配置为 Master
     *
     * @param id
     * @return
     */
    @PutMapping("/update-master")
    @PreAuthorize("@ss.hasPermission('infra:file-config:update')")
    public CommonResult<Boolean> updateFileConfigMaster(@RequestParam("id") Long id) {
        this.fileConfigService.updateFileConfigMaster(id);
        return success(true);
    }

    /**
     * 删除文件配置
     *
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('infra:file-config:delete')")
    public CommonResult<Boolean> deleteFileConfig(@RequestParam("id") Long id) {
        this.fileConfigService.deleteFileConfig(id);
        return success(true);
    }

    /**
     * 获得文件配置
     *
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('infra:file-config:query')")
    public CommonResult<FileConfigRespVO> getFileConfig(@RequestParam("id") Long id) {
        FileConfigDO config = this.fileConfigService.getFileConfig(id);
        return success(BeanUtils.toBean(config, FileConfigRespVO.class));
    }

    /**
     * 获得文件配置分页
     *
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('infra:file-config:query')")
    public CommonResult<PageResult<FileConfigRespVO>> getFileConfigPage(@Valid FileConfigPageReqVO pageVO) {
        PageResult<FileConfigDO> pageResult = this.fileConfigService.getFileConfigPage(pageVO);
        return success(BeanUtils.toBean(pageResult, FileConfigRespVO.class));
    }

    /**
     * 测试文件配置是否正确
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/test")
    @PreAuthorize("@ss.hasPermission('infra:file-config:query')")
    public CommonResult<String> testFileConfig(@RequestParam("id") Long id) throws Exception {
        String url = this.fileConfigService.testFileConfig(id);
        return success(url);
    }
}
