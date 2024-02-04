package com.ymitcloud.module.report.service.ureport;

import com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.report.controller.admin.ureport.vo.UReportDataPageReqVO;
import com.ymitcloud.module.report.controller.admin.ureport.vo.UReportDataSaveReqVO;
import com.ymitcloud.module.report.dal.dataobject.ureport.UReportDataDO;
import com.ymitcloud.module.report.dal.mysql.ureport.UReportDataMapper;
import com.ymitcloud.module.report.enums.ErrorCodeConstants;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * Ureport2报表 Service 实现类
 *

 * @author 

 */
@Service
@Validated
public class UReportDataServiceImpl implements UReportDataService {

    @Resource
    private UReportDataMapper uReportDataMapper;

    @Override
    public Long createUReportData(UReportDataSaveReqVO createReqVO) {
        // TODO @赤焰：名字不要重复的校验，要加下
        UReportDataDO uReportData = BeanUtils.toBean(createReqVO, UReportDataDO.class);
        uReportDataMapper.insert(uReportData);
        return uReportData.getId();
    }

    @Override
    public void updateUReportData(UReportDataSaveReqVO updateReqVO) {
        // 校验存在
        validateUReportDataExists(updateReqVO.getId());
        // TODO @赤焰：名字不要重复的校验，要加下
        // 更新
        UReportDataDO updateObj = BeanUtils.toBean(updateReqVO, UReportDataDO.class);
        uReportDataMapper.updateById(updateObj);
    }

    @Override
    public void deleteUReportData(Long id) {
        // 校验存在
        validateUReportDataExists(id);
        // 删除
        uReportDataMapper.deleteById(id);
    }

    private void validateUReportDataExists(Long id) {
        if (uReportDataMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.UREPORT_DATA_NOT_EXISTS);
        }
    }

    @Override
    public void validateUReportDataExists(String name) {
        if (uReportDataMapper.selectListByName(name) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.UREPORT_DATA_NOT_EXISTS);
        }
    }

    @Override
    public UReportDataDO getUReportData(Long id) {
        return uReportDataMapper.selectById(id);
    }

    @Override
    public PageResult<UReportDataDO> getUReportDataPage(UReportDataPageReqVO pageReqVO) {
        return uReportDataMapper.selectPage(pageReqVO);
    }

    @Override
    public int deleteByName(String name) {
        return uReportDataMapper.deleteByName(name);
    }

    @Override
    public UReportDataDO selectOneByName(String name) {
        return uReportDataMapper.selectByName(name);
    }

    @Override
    public List<UReportDataDO> getReportDataList() {
        return uReportDataMapper.selectList();
    }

}