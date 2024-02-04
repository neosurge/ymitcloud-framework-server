package com.ymitcloud.module.pay.convert.transfer;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.pay.core.client.dto.transfer.PayTransferUnifiedReqDTO;
import com.ymitcloud.module.pay.api.transfer.dto.PayTransferCreateReqDTO;
import com.ymitcloud.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import com.ymitcloud.module.pay.controller.admin.transfer.vo.PayTransferCreateReqVO;
import com.ymitcloud.module.pay.controller.admin.transfer.vo.PayTransferPageItemRespVO;
import com.ymitcloud.module.pay.controller.admin.transfer.vo.PayTransferRespVO;
import com.ymitcloud.module.pay.dal.dataobject.transfer.PayTransferDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayTransferConvert {

    PayTransferConvert INSTANCE = Mappers.getMapper(PayTransferConvert.class);

    PayTransferDO convert(PayTransferCreateReqDTO dto);

    PayTransferUnifiedReqDTO convert2(PayTransferDO dto);

    PayTransferCreateReqDTO convert(PayTransferCreateReqVO vo);

    PayTransferCreateReqDTO convert(PayDemoTransferCreateReqVO vo);

    PayTransferRespVO  convert(PayTransferDO bean);

    PageResult<PayTransferPageItemRespVO> convertPage(PageResult<PayTransferDO> pageResult);
}
