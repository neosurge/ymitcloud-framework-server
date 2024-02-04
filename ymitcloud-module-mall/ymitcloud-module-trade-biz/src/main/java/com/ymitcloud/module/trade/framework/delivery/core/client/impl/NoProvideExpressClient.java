package com.ymitcloud.module.trade.framework.delivery.core.client.impl;

import com.ymitcloud.module.trade.framework.delivery.core.client.ExpressClient;
import com.ymitcloud.module.trade.framework.delivery.core.client.dto.ExpressTrackQueryReqDTO;
import com.ymitcloud.module.trade.framework.delivery.core.client.dto.ExpressTrackRespDTO;

import java.util.List;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.module.trade.enums.ErrorCodeConstants.EXPRESS_CLIENT_NOT_PROVIDE;

/**
 * 未实现的快递客户端，用来提醒用户需要接入快递服务商，
 *
 * @author jason
 */
public class NoProvideExpressClient implements ExpressClient {

    @Override
    public List<ExpressTrackRespDTO> getExpressTrackList(ExpressTrackQueryReqDTO reqDTO) {
        throw exception(EXPRESS_CLIENT_NOT_PROVIDE);
    }

}