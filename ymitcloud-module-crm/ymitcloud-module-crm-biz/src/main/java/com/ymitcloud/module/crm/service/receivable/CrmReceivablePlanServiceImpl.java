package com.ymitcloud.module.crm.service.receivable;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanPageReqVO;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanUpdateReqVO;
import com.ymitcloud.module.crm.convert.receivable.CrmReceivablePlanConvert;
import com.ymitcloud.module.crm.dal.dataobject.contract.CrmContractDO;
import com.ymitcloud.module.crm.dal.dataobject.customer.CrmCustomerDO;
import com.ymitcloud.module.crm.dal.dataobject.receivable.CrmReceivablePlanDO;
import com.ymitcloud.module.crm.dal.mysql.receivable.CrmReceivablePlanMapper;
import com.ymitcloud.module.crm.enums.common.CrmBizTypeEnum;
import com.ymitcloud.module.crm.enums.permission.CrmPermissionLevelEnum;
import com.ymitcloud.module.crm.framework.core.annotations.CrmPermission;
import com.ymitcloud.module.crm.service.contract.CrmContractService;
import com.ymitcloud.module.crm.service.customer.CrmCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.module.crm.enums.ErrorCodeConstants.*;

// TODO @liuhongfeng：参考 CrmReceivableServiceImpl 写的 todo 哈；
// TODO @puhui999：数据权限
/**
 * 回款计划 Service 实现类
 *

 * @author 

 */
@Service
@Validated
public class CrmReceivablePlanServiceImpl implements CrmReceivablePlanService {

    @Resource
    private CrmReceivablePlanMapper receivablePlanMapper;

    @Resource
    private CrmContractService contractService;
    @Resource
    private CrmCustomerService customerService;

    @Override
    public Long createReceivablePlan(CrmReceivablePlanCreateReqVO createReqVO) {
        // 插入
        CrmReceivablePlanDO receivablePlan = CrmReceivablePlanConvert.INSTANCE.convert(createReqVO);
        receivablePlan.setFinishStatus(false);

        checkReceivablePlan(receivablePlan);

        receivablePlanMapper.insert(receivablePlan);
        // 返回
        return receivablePlan.getId();
    }

    private void checkReceivablePlan(CrmReceivablePlanDO receivablePlan) {

        if(ObjectUtil.isNull(receivablePlan.getContractId())){
            throw exception(CONTRACT_NOT_EXISTS);
        }

        CrmContractDO contract = contractService.getContract(receivablePlan.getContractId());
        if(ObjectUtil.isNull(contract)){
            throw exception(CONTRACT_NOT_EXISTS);
        }

        CrmCustomerDO customer = customerService.getCustomer(receivablePlan.getCustomerId());
        if(ObjectUtil.isNull(customer)){
            throw exception(CUSTOMER_NOT_EXISTS);
        }

    }

    @Override
    public void updateReceivablePlan(CrmReceivablePlanUpdateReqVO updateReqVO) {
        // 校验存在
        validateReceivablePlanExists(updateReqVO.getId());

        // 更新
        CrmReceivablePlanDO updateObj = CrmReceivablePlanConvert.INSTANCE.convert(updateReqVO);
        receivablePlanMapper.updateById(updateObj);
    }

    @Override
    public void deleteReceivablePlan(Long id) {
        // 校验存在
        validateReceivablePlanExists(id);
        // 删除
        receivablePlanMapper.deleteById(id);
    }

    private void validateReceivablePlanExists(Long id) {
        if (receivablePlanMapper.selectById(id) == null) {
            throw exception(RECEIVABLE_PLAN_NOT_EXISTS);
        }
    }

    @Override
    public CrmReceivablePlanDO getReceivablePlan(Long id) {
        return receivablePlanMapper.selectById(id);
    }

    @Override
    public List<CrmReceivablePlanDO> getReceivablePlanList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }
        return receivablePlanMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CrmReceivablePlanDO> getReceivablePlanPage(CrmReceivablePlanPageReqVO pageReqVO) {
        return receivablePlanMapper.selectPage(pageReqVO);
    }

    @Override
    @CrmPermission(bizType = CrmBizTypeEnum.CRM_CUSTOMER, bizId = "#pageReqVO.customerId", level = CrmPermissionLevelEnum.READ)
    public PageResult<CrmReceivablePlanDO> getReceivablePlanPageByCustomer(CrmReceivablePlanPageReqVO pageReqVO) {
        return receivablePlanMapper.selectPageByCustomer(pageReqVO);
    }

}
