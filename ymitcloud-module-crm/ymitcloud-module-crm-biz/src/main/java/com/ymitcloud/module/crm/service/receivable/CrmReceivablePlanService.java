package com.ymitcloud.module.crm.service.receivable;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanPageReqVO;
import com.ymitcloud.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanUpdateReqVO;
import com.ymitcloud.module.crm.dal.dataobject.customer.CrmCustomerDO;
import com.ymitcloud.module.crm.dal.dataobject.receivable.CrmReceivablePlanDO;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * CRM 回款计划 Service 接口
 *

 * @author 

 */
public interface CrmReceivablePlanService {

    /**
     * 创建回款计划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createReceivablePlan(@Valid CrmReceivablePlanCreateReqVO createReqVO);

    /**
     * 更新回款计划
     *
     * @param updateReqVO 更新信息
     */
    void updateReceivablePlan(@Valid CrmReceivablePlanUpdateReqVO updateReqVO);

    /**
     * 删除回款计划
     *
     * @param id 编号
     */
    void deleteReceivablePlan(Long id);

    /**
     * 获得回款计划
     *
     * @param id 编号
     * @return 回款计划
     */
    CrmReceivablePlanDO getReceivablePlan(Long id);

    /**
     * 获得回款计划列表
     *
     * @param ids 编号
     * @return 回款计划列表
     */
    List<CrmReceivablePlanDO> getReceivablePlanList(Collection<Long> ids);

    /**
     * 获得回款计划分页
     *
     * 数据权限：基于 {@link CrmReceivablePlanDO} 读取
     *
     * @param pageReqVO 分页查询
     * @return 回款计划分页
     */
    PageResult<CrmReceivablePlanDO> getReceivablePlanPage(CrmReceivablePlanPageReqVO pageReqVO);

    /**
     * 获得回款计划分页，基于指定客户
     *
     * 数据权限：基于 {@link CrmCustomerDO} 读取
     *
     * @param pageReqVO 分页查询
     * @return 回款计划分页
     */
    PageResult<CrmReceivablePlanDO> getReceivablePlanPageByCustomer(CrmReceivablePlanPageReqVO pageReqVO);

}
