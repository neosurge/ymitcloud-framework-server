package com.ymitcloud.module.crm.convert.clue;

import java.util.*;

import com.ymitcloud.framework.common.pojo.PageResult;

import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueExcelVO;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueRespVO;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueUpdateReqVO;
import com.ymitcloud.module.crm.dal.dataobject.clue.CrmClueDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ymitcloud.module.crm.controller.admin.clue.vo.*;


/**
 * 线索 Convert
 *
 * @author Wanwan
 */
@Mapper
public interface CrmClueConvert {

    CrmClueConvert INSTANCE = Mappers.getMapper(CrmClueConvert.class);

    CrmClueDO convert(CrmClueCreateReqVO bean);

    CrmClueDO convert(CrmClueUpdateReqVO bean);

    CrmClueRespVO convert(CrmClueDO bean);

    PageResult<CrmClueRespVO> convertPage(PageResult<CrmClueDO> page);

    List<CrmClueExcelVO> convertList02(List<CrmClueDO> list);

}
