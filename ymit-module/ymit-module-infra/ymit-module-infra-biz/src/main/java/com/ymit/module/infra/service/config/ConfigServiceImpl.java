package com.ymit.module.infra.service.config;

import com.google.common.annotations.VisibleForTesting;
import com.ymit.framework.common.data.PageResult;
import com.ymit.module.infra.controller.admin.config.vo.ConfigPageReqVO;
import com.ymit.module.infra.controller.admin.config.vo.ConfigSaveReqVO;
import com.ymit.module.infra.convert.config.ConfigConvert;
import com.ymit.module.infra.dal.dataobject.config.ConfigDO;
import com.ymit.module.infra.dal.mysql.config.ConfigMapper;
import com.ymit.module.infra.enums.config.ConfigTypeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.ymit.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymit.module.infra.enums.ErrorCodeConstants.*;

/**
 * 参数配置 Service 实现类
 */
@Service
@Slf4j
@Validated
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private ConfigMapper configMapper;

    @Override
    public Long createConfig(ConfigSaveReqVO createReqVO) {
        // 校验参数配置 key 的唯一性
        this.validateConfigKeyUnique(null, createReqVO.getKey());

        // 插入参数配置
        ConfigDO config = ConfigConvert.INSTANCE.convert(createReqVO);
        config.setType(ConfigTypeEnum.CUSTOM.getType());
        this.configMapper.insert(config);
        return config.getId();
    }

    @Override
    public void updateConfig(ConfigSaveReqVO updateReqVO) {
        // 校验自己存在
        ConfigDO config = this.validateConfigExists(updateReqVO.getId());
        // 内置配置时，不允许修改key
        if (ConfigTypeEnum.SYSTEM.getType().equals(config.getType())) {
            if (config.getConfigKey().equals(updateReqVO.getKey())) {
                throw exception(CONFIG_CAN_NOT_MODIFY_KEY_SYSTEM_TYPE);
            }
        }
        // 校验参数配置 key 的唯一性
        this.validateConfigKeyUnique(updateReqVO.getId(), updateReqVO.getKey());

        // 更新参数配置
        ConfigDO updateObj = ConfigConvert.INSTANCE.convert(updateReqVO);
        this.configMapper.updateById(updateObj);
    }

    @Override
    public void deleteConfig(Long id) {
        // 校验配置存在
        ConfigDO config = this.validateConfigExists(id);
        // 内置配置，不允许删除
        if (ConfigTypeEnum.SYSTEM.getType().equals(config.getType())) {
            throw exception(CONFIG_CAN_NOT_DELETE_SYSTEM_TYPE);
        }
        // 删除
        this.configMapper.deleteById(id);
    }

    @Override
    public ConfigDO getConfig(Long id) {
        return this.configMapper.selectById(id);
    }

    @Override
    public ConfigDO getConfigByKey(String key) {
        return this.configMapper.selectByKey(key);
    }

    @Override
    public PageResult<ConfigDO> getConfigPage(ConfigPageReqVO pageReqVO) {
        return this.configMapper.selectPage(pageReqVO);
    }

    @VisibleForTesting
    public ConfigDO validateConfigExists(Long id) {
        if (id == null) {
            return null;
        }
        ConfigDO config = this.configMapper.selectById(id);
        if (config == null) {
            throw exception(CONFIG_NOT_EXISTS);
        }
        return config;
    }

    @VisibleForTesting
    public void validateConfigKeyUnique(Long id, String key) {
        ConfigDO config = this.configMapper.selectByKey(key);
        if (config == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的参数配置
        if (id == null) {
            throw exception(CONFIG_KEY_DUPLICATE);
        }
        if (!config.getId().equals(id)) {
            throw exception(CONFIG_KEY_DUPLICATE);
        }
    }

}
