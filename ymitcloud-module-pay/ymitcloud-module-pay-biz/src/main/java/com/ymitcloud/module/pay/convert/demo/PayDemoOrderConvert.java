package com.ymitcloud.module.pay.convert.demo;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.pay.controller.admin.demo.vo.order.PayDemoOrderCreateReqVO;
import com.ymitcloud.module.pay.controller.admin.demo.vo.order.PayDemoOrderRespVO;
import com.ymitcloud.module.pay.dal.dataobject.demo.PayDemoOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 示例订单 Convert
 *

 * @author 

 */
@Mapper
public interface PayDemoOrderConvert {

    PayDemoOrderConvert INSTANCE = Mappers.getMapper(PayDemoOrderConvert.class);

    PayDemoOrderDO convert(PayDemoOrderCreateReqVO bean);

    PayDemoOrderRespVO convert(PayDemoOrderDO bean);

    PageResult<PayDemoOrderRespVO> convertPage(PageResult<PayDemoOrderDO> page);

}
