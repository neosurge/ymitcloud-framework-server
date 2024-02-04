package com.ymitcloud.module.crm.service.clue;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueExportReqVO;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmCluePageReqVO;
import com.ymitcloud.module.crm.controller.admin.clue.vo.CrmClueUpdateReqVO;
import com.ymitcloud.module.crm.convert.clue.CrmClueConvert;
import com.ymitcloud.module.crm.dal.dataobject.clue.CrmClueDO;
import com.ymitcloud.module.crm.dal.mysql.clue.CrmClueMapper;
import com.ymitcloud.module.crm.service.customer.CrmCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.module.crm.enums.ErrorCodeConstants.CLUE_NOT_EXISTS;

/**
 * 线索 Service 实现类
 *
 * @author Wanwan
 */
@Service
@Validated
public class CrmClueServiceImpl implements CrmClueService {

    @Resource
    private CrmClueMapper clueMapper;
    @Resource
    private CrmCustomerService customerService;

    @Override
    public Long createClue(CrmClueCreateReqVO createReqVO) {
        // 校验客户是否存在
        customerService.validateCustomer(createReqVO.getCustomerId());
        // 插入
        CrmClueDO clue = CrmClueConvert.INSTANCE.convert(createReqVO);
        clueMapper.insert(clue);
        // 返回
        return clue.getId();
    }

    @Override
    public void updateClue(CrmClueUpdateReqVO updateReqVO) {
        // 校验存在
        validateClueExists(updateReqVO.getId());
        // 校验客户是否存在
        customerService.validateCustomer(updateReqVO.getCustomerId());

        // 更新
        CrmClueDO updateObj = CrmClueConvert.INSTANCE.convert(updateReqVO);
        clueMapper.updateById(updateObj);
    }

    @Override
    public void deleteClue(Long id) {
        // 校验存在
        validateClueExists(id);
        // 删除
        clueMapper.deleteById(id);
    }

    private void validateClueExists(Long id) {
        if (clueMapper.selectById(id) == null) {
            throw exception(CLUE_NOT_EXISTS);
        }
    }

    @Override
    public CrmClueDO getClue(Long id) {
        return clueMapper.selectById(id);
    }

    @Override
    public List<CrmClueDO> getClueList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }
        return clueMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CrmClueDO> getCluePage(CrmCluePageReqVO pageReqVO) {
        return clueMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CrmClueDO> getClueList(CrmClueExportReqVO exportReqVO) {
        return clueMapper.selectList(exportReqVO);
    }

}
