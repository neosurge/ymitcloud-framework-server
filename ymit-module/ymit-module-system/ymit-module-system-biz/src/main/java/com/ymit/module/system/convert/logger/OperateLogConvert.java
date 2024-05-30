package com.ymit.module.system.convert.logger;

import com.ymit.framework.common.util.collection.CollectionUtils;
import com.ymit.framework.common.util.collection.MapUtils;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.system.controller.admin.logger.vo.operatelog.OperateLogRespVO;
import com.ymit.module.system.dal.dataobject.logger.OperateLogDO;
import com.ymit.module.system.dal.dataobject.user.AdminUserDO;

import java.util.List;
import java.util.Map;

//@Mapper
public class OperateLogConvert {

//    OperateLogConvert INSTANCE = Mappers.getMapper(OperateLogConvert.class);

    public static List<OperateLogRespVO> convertList(List<OperateLogDO> list, Map<Long, AdminUserDO> userMap) {
        return CollectionUtils.convertList(list, log -> {
            OperateLogRespVO logVO = BeanUtils.toBean(log, OperateLogRespVO.class);
            MapUtils.findAndThen(userMap, log.getUserId(), user -> logVO.setUserNickname(user.getNickname()));
            return logVO;
        });
    }

}
