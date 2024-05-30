package com.ymit.module.infra.service.file;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.IdUtil;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.json.JsonUtils;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.common.util.validation.ValidationUtils;
import com.ymit.framework.file.core.client.FileClient;
import com.ymit.framework.file.core.client.FileClientConfig;
import com.ymit.framework.file.core.client.FileClientFactory;
import com.ymit.framework.file.core.enums.FileStorageEnum;
import com.ymit.module.infra.controller.admin.file.vo.config.FileConfigPageReqVO;
import com.ymit.module.infra.controller.admin.file.vo.config.FileConfigSaveReqVO;
import com.ymit.module.infra.dal.dataobject.file.FileConfigDO;
import com.ymit.module.infra.dal.mysql.file.FileConfigMapper;
import jakarta.annotation.Resource;
import jakarta.validation.Validator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;

import static com.ymit.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymit.framework.common.util.cache.CacheUtils.buildAsyncReloadingCache;
import static com.ymit.module.infra.enums.ErrorCodeConstants.FILE_CONFIG_DELETE_FAIL_MASTER;
import static com.ymit.module.infra.enums.ErrorCodeConstants.FILE_CONFIG_NOT_EXISTS;

/**
 * 文件配置 Service 实现类
 *
 * @author 云码源码
 */
@Service
@Validated
@Slf4j
public class FileConfigServiceImpl implements FileConfigService {

    private static final Long CACHE_MASTER_ID = 0L;
    @Resource
    private FileClientFactory fileClientFactory;
    @Resource
    private FileConfigMapper fileConfigMapper;
    /**
     * {@link FileClient} 缓存，通过它异步刷新 fileClientFactory
     */
    @Getter
    private final LoadingCache<Long, FileClient> clientCache = buildAsyncReloadingCache(Duration.ofSeconds(10L),
            new CacheLoader<Long, FileClient>() {

                @Override
                public FileClient load(Long id) {
                    FileConfigDO config = Objects.equals(CACHE_MASTER_ID, id) ?
                            FileConfigServiceImpl.this.fileConfigMapper.selectByMaster() : FileConfigServiceImpl.this.fileConfigMapper.selectById(id);
                    if (config != null) {
                        FileConfigServiceImpl.this.fileClientFactory.createOrUpdateFileClient(config.getId(), config.getStorage(), config.getConfig());
                    }
                    return FileConfigServiceImpl.this.fileClientFactory.getFileClient(null == config ? id : config.getId());
                }

            });
    @Resource
    private Validator validator;

    @Override
    public Long createFileConfig(FileConfigSaveReqVO createReqVO) {
        FileConfigDO fileConfig = BeanUtils.toBean(createReqVO, FileConfigDO.class)
                .setConfig(this.parseClientConfig(createReqVO.getStorage(), createReqVO.getConfig()))
                .setMaster(false); // 默认非 master
        this.fileConfigMapper.insert(fileConfig);
        return fileConfig.getId();
    }

    @Override
    public void updateFileConfig(FileConfigSaveReqVO updateReqVO) {
        // 校验存在
        FileConfigDO config = this.validateFileConfigExists(updateReqVO.getId());
        // 更新
        FileConfigDO updateObj = BeanUtils.toBean(updateReqVO, FileConfigDO.class)
                .setConfig(this.parseClientConfig(config.getStorage(), updateReqVO.getConfig()));
        this.fileConfigMapper.updateById(updateObj);

        // 清空缓存
        this.clearCache(config.getId(), null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFileConfigMaster(Long id) {
        // 校验存在
        this.validateFileConfigExists(id);
        // 更新其它为非 master
        this.fileConfigMapper.updateBatch(new FileConfigDO().setMaster(false));
        // 更新
        this.fileConfigMapper.updateById(new FileConfigDO().setId(id).setMaster(true));

        // 清空缓存
        this.clearCache(null, true);
    }

    private FileClientConfig parseClientConfig(Integer storage, Map<String, Object> config) {
        // 获取配置类
        Class<? extends FileClientConfig> configClass = FileStorageEnum.getByStorage(storage)
                .getConfigClass();
        FileClientConfig clientConfig = JsonUtils.parseObject2(JsonUtils.toJsonString(config), configClass);
        // 参数校验
        ValidationUtils.validate(this.validator, clientConfig);
        // 设置参数
        return clientConfig;
    }

    @Override
    public void deleteFileConfig(Long id) {
        // 校验存在
        FileConfigDO config = this.validateFileConfigExists(id);
        if (Boolean.TRUE.equals(config.getMaster())) {
            throw exception(FILE_CONFIG_DELETE_FAIL_MASTER);
        }
        // 删除
        this.fileConfigMapper.deleteById(id);

        // 清空缓存
        this.clearCache(id, null);
    }

    /**
     * 清空指定文件配置
     *
     * @param id     配置编号
     * @param master 是否主配置
     */
    private void clearCache(Long id, Boolean master) {
        if (id != null) {
            this.clientCache.invalidate(id);
        }
        if (Boolean.TRUE.equals(master)) {
            this.clientCache.invalidate(CACHE_MASTER_ID);
        }
    }

    private FileConfigDO validateFileConfigExists(Long id) {
        FileConfigDO config = this.fileConfigMapper.selectById(id);
        if (config == null) {
            throw exception(FILE_CONFIG_NOT_EXISTS);
        }
        return config;
    }

    @Override
    public FileConfigDO getFileConfig(Long id) {
        return this.fileConfigMapper.selectById(id);
    }

    @Override
    public PageResult<FileConfigDO> getFileConfigPage(FileConfigPageReqVO pageReqVO) {
        return this.fileConfigMapper.selectPage(pageReqVO);
    }

    @Override
    public String testFileConfig(Long id) throws Exception {
        // 校验存在
        this.validateFileConfigExists(id);
        // 上传文件
        byte[] content = ResourceUtil.readBytes("file/erweima.jpg");
        return this.getFileClient(id).upload(content, IdUtil.fastSimpleUUID() + ".jpg", "image/jpeg");
    }

    @Override
    public FileClient getFileClient(Long id) {
        return this.clientCache.getUnchecked(id);
    }

    @Override
    public FileClient getMasterFileClient() {
        return this.clientCache.getUnchecked(CACHE_MASTER_ID);
    }

}
