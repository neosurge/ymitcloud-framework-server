package com.ymitcloud.module.crm.service.business;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.crm.controller.admin.business.vo.status.CrmBusinessStatusPageReqVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.status.CrmBusinessStatusQueryVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.status.CrmBusinessStatusSaveReqVO;
import com.ymitcloud.module.crm.dal.dataobject.business.CrmBusinessStatusDO;
import com.ymitcloud.module.crm.dal.mysql.business.CrmBusinessStatusMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

import java.util.List;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.module.crm.enums.ErrorCodeConstants.BUSINESS_STATUS_NOT_EXISTS;

/**
 * 商机状态 Service 实现类
 *
 * @author ljlleo
 */
@Service
@Validated
public class CrmBusinessStatusServiceImpl implements CrmBusinessStatusService {

    @Resource
    private CrmBusinessStatusMapper businessStatusMapper;

    @Override
    public Long createBusinessStatus(CrmBusinessStatusSaveReqVO createReqVO) {
        // 插入
        CrmBusinessStatusDO businessStatus = BeanUtils.toBean(createReqVO, CrmBusinessStatusDO.class);
        businessStatusMapper.insert(businessStatus);
        // 返回
        return businessStatus.getId();
    }

    @Override
    public void updateBusinessStatus(CrmBusinessStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateBusinessStatusExists(updateReqVO.getId());
        // 更新
        CrmBusinessStatusDO updateObj = BeanUtils.toBean(updateReqVO, CrmBusinessStatusDO.class);
        businessStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteBusinessStatus(Long id) {
        // 校验存在
        validateBusinessStatusExists(id);
        // TODO @ljlleo 这里可以考虑，如果有商机在使用，不允许删除
        // 删除
        businessStatusMapper.deleteById(id);
    }

    private void validateBusinessStatusExists(Long id) {
        if (businessStatusMapper.selectById(id) == null) {
            throw exception(BUSINESS_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public CrmBusinessStatusDO getBusinessStatus(Long id) {
        return businessStatusMapper.selectById(id);
    }

    @Override
    public PageResult<CrmBusinessStatusDO> getBusinessStatusPage(CrmBusinessStatusPageReqVO pageReqVO) {
        return businessStatusMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CrmBusinessStatusDO> selectList(CrmBusinessStatusQueryVO queryVO) {
        return businessStatusMapper.selectList(queryVO);
    }

}
