package com.ymitcloud.module.product.convert.property;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.product.controller.admin.property.vo.value.ProductPropertyValueCreateReqVO;
import com.ymitcloud.module.product.controller.admin.property.vo.value.ProductPropertyValueRespVO;
import com.ymitcloud.module.product.controller.admin.property.vo.value.ProductPropertyValueUpdateReqVO;
import com.ymitcloud.module.product.dal.dataobject.property.ProductPropertyValueDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 属性值 Convert
 *

 * @author 

 */
@Mapper
public interface ProductPropertyValueConvert {

    ProductPropertyValueConvert INSTANCE = Mappers.getMapper(ProductPropertyValueConvert.class);

    ProductPropertyValueDO convert(ProductPropertyValueCreateReqVO bean);

    ProductPropertyValueDO convert(ProductPropertyValueUpdateReqVO bean);

    ProductPropertyValueRespVO convert(ProductPropertyValueDO bean);

    List<ProductPropertyValueRespVO> convertList(List<ProductPropertyValueDO> list);

    PageResult<ProductPropertyValueRespVO> convertPage(PageResult<ProductPropertyValueDO> page);

}
