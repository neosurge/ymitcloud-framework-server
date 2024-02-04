package com.ymitcloud.module.crm.service.contact;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.crm.controller.admin.contact.vo.CrmContactBaseVO;
import com.ymitcloud.module.crm.controller.admin.contact.vo.CrmContactCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.contact.vo.CrmContactPageReqVO;
import com.ymitcloud.module.crm.controller.admin.contact.vo.CrmContactUpdateReqVO;
import com.ymitcloud.module.crm.convert.contact.ContactConvert;
import com.ymitcloud.module.crm.dal.dataobject.contact.CrmContactDO;
import com.ymitcloud.module.crm.dal.mysql.contact.CrmContactMapper;
import com.ymitcloud.module.crm.framework.core.annotations.CrmPermission;
import com.ymitcloud.module.crm.enums.common.CrmBizTypeEnum;
import com.ymitcloud.module.crm.enums.permission.CrmPermissionLevelEnum;
import com.ymitcloud.module.crm.service.customer.CrmCustomerService;
import com.ymitcloud.module.crm.service.permission.CrmPermissionService;
import com.ymitcloud.module.crm.service.permission.bo.CrmPermissionCreateReqBO;
import com.ymitcloud.module.system.api.user.AdminUserApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.module.crm.enums.ErrorCodeConstants.CONTACT_NOT_EXISTS;
import static com.ymitcloud.module.crm.enums.ErrorCodeConstants.CUSTOMER_NOT_EXISTS;
import static com.ymitcloud.module.system.enums.ErrorCodeConstants.USER_NOT_EXISTS;

/**
 * CRM 联系人 Service 实现类
 *

 * @author 

 */
@Service
@Validated
public class CrmContactServiceImpl implements CrmContactService {

    @Resource
    private CrmContactMapper contactMapper;

    @Resource
    private CrmCustomerService customerService;
    @Resource
    private CrmPermissionService crmPermissionService;

    @Resource
    private AdminUserApi adminUserApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createContact(CrmContactCreateReqVO createReqVO, Long userId) {
        // 1.1 校验
        validateRelationDataExists(createReqVO);
        // 1.2 插入
        CrmContactDO contact = ContactConvert.INSTANCE.convert(createReqVO);
        contactMapper.insert(contact);

        // 2. 创建数据权限
        crmPermissionService.createPermission(new CrmPermissionCreateReqBO().setUserId(userId)
                .setBizType(CrmBizTypeEnum.CRM_CONTACT.getType()).setBizId(contact.getId())
                .setLevel(CrmPermissionLevelEnum.OWNER.getLevel()));
        return contact.getId();
    }

    @Override
    @CrmPermission(bizType = CrmBizTypeEnum.CRM_CONTACT, bizId = "#updateReqVO.id", level = CrmPermissionLevelEnum.WRITE)
    public void updateContact(CrmContactUpdateReqVO updateReqVO) {
        // 1. 校验存在
        validateContactExists(updateReqVO.getId());
        validateRelationDataExists(updateReqVO);
        // 2. 更新
        CrmContactDO updateObj = ContactConvert.INSTANCE.convert(updateReqVO);
        contactMapper.updateById(updateObj);
    }

    /**
     * 校验关联的数据都存在
     *
     * @param saveReqVO 新增/修改请求 VO
     */
    private void validateRelationDataExists(CrmContactBaseVO saveReqVO){
        // 1. 校验客户
        if (saveReqVO.getCustomerId() != null && customerService.getCustomer(saveReqVO.getCustomerId()) == null) {
            throw exception(CUSTOMER_NOT_EXISTS);
        }
        // 2. 校验负责人
        if (saveReqVO.getOwnerUserId() != null && adminUserApi.getUser(saveReqVO.getOwnerUserId()) == null) {
            throw exception(USER_NOT_EXISTS);
        }
        // 3. 直属上级
        if (saveReqVO.getParentId() != null && contactMapper.selectById(saveReqVO.getParentId()) == null) {
            throw exception(CONTACT_NOT_EXISTS);
        }
    }

    @Override
    @CrmPermission(bizType = CrmBizTypeEnum.CRM_CONTACT, bizId = "#id", level = CrmPermissionLevelEnum.WRITE)
    public void deleteContact(Long id) {
        // 校验存在
        validateContactExists(id);
        // 删除
        contactMapper.deleteById(id);
    }

    private void validateContactExists(Long id) {
        if (contactMapper.selectById(id) == null) {
            throw exception(CONTACT_NOT_EXISTS);
        }
    }


    // TODO 云码：是否要做数据权限的校验？？？

    @Override
    @CrmPermission(bizType = CrmBizTypeEnum.CRM_CONTACT, bizId = "#id", level = CrmPermissionLevelEnum.READ)
    public CrmContactDO getContact(Long id) {
        return contactMapper.selectById(id);
    }

    @Override
    public List<CrmContactDO> getContactList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }
        return contactMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CrmContactDO> getContactPage(CrmContactPageReqVO pageReqVO) {
        // TODO puhui999：后面要改成，基于数据权限的查询
        return contactMapper.selectPage(pageReqVO);
    }

    @Override
    @CrmPermission(bizType = CrmBizTypeEnum.CRM_CUSTOMER, bizId = "#pageReqVO.customerId", level = CrmPermissionLevelEnum.READ)
    public PageResult<CrmContactDO> getContactPageByCustomer(CrmContactPageReqVO pageReqVO) {
        return contactMapper.selectPageByCustomer(pageReqVO);
    }

    @Override
    public List<CrmContactDO> getContactList() {
        return contactMapper.selectList();
    }

}
