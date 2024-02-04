package com.ymitcloud.module.pay.convert.demo;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import com.ymitcloud.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferRespVO;
import com.ymitcloud.module.pay.dal.dataobject.demo.PayDemoTransferDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jason
 */
@Mapper
public interface PayDemoTransferConvert {

    PayDemoTransferConvert INSTANCE = Mappers.getMapper(PayDemoTransferConvert.class);

    PayDemoTransferDO convert(PayDemoTransferCreateReqVO bean);

    PageResult<PayDemoTransferRespVO> convertPage(PageResult<PayDemoTransferDO> pageResult);
}
