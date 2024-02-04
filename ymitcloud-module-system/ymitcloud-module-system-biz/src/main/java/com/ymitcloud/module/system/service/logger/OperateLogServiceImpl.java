package com.ymitcloud.module.system.service.logger;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.common.util.string.StrUtils;
import com.ymitcloud.module.system.api.logger.dto.OperateLogCreateReqDTO;
import com.ymitcloud.module.system.controller.admin.logger.vo.operatelog.OperateLogPageReqVO;
import com.ymitcloud.module.system.dal.dataobject.logger.OperateLogDO;
import com.ymitcloud.module.system.dal.dataobject.user.AdminUserDO;
import com.ymitcloud.module.system.dal.mysql.logger.OperateLogMapper;
import com.ymitcloud.module.system.service.user.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.Collection;

import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

/**
 * 操作日志 Service 实现类
 *

 * @author 云码源码

 */
@Service
@Validated
@Slf4j
public class OperateLogServiceImpl implements OperateLogService {

    @Resource
    private OperateLogMapper operateLogMapper;

    @Resource
    private AdminUserService userService;

    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        OperateLogDO log = BeanUtils.toBean(createReqDTO, OperateLogDO.class);
        log.setJavaMethodArgs(StrUtils.maxLength(log.getJavaMethodArgs(), OperateLogDO.JAVA_METHOD_ARGS_MAX_LENGTH));
        log.setResultData(StrUtils.maxLength(log.getResultData(), OperateLogDO.RESULT_MAX_LENGTH));
        operateLogMapper.insert(log);
    }

    @Override
    public PageResult<OperateLogDO> getOperateLogPage(OperateLogPageReqVO pageReqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(pageReqVO.getUserNickname())) {
            userIds = convertSet(userService.getUserListByNickname(pageReqVO.getUserNickname()), AdminUserDO::getId);
            if (CollUtil.isEmpty(userIds)) {
                return PageResult.empty();
            }
        }
        // 查询分页
        return operateLogMapper.selectPage(pageReqVO, userIds);
    }

}
