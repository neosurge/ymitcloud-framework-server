package com.ymitcloud.module.bpm.convert.oa;

import com.ymitcloud.module.bpm.controller.admin.oa.vo.BpmOALeaveCreateReqVO;
import com.ymitcloud.module.bpm.controller.admin.oa.vo.BpmOALeaveRespVO;
import com.ymitcloud.module.bpm.dal.dataobject.oa.BpmOALeaveDO;
import com.ymitcloud.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 请假申请 Convert
 *

 * @author 云码

 */
@Mapper
public interface BpmOALeaveConvert {

    BpmOALeaveConvert INSTANCE = Mappers.getMapper(BpmOALeaveConvert.class);

    BpmOALeaveDO convert(BpmOALeaveCreateReqVO bean);

    BpmOALeaveRespVO convert(BpmOALeaveDO bean);

    List<BpmOALeaveRespVO> convertList(List<BpmOALeaveDO> list);

    PageResult<BpmOALeaveRespVO> convertPage(PageResult<BpmOALeaveDO> page);

}
