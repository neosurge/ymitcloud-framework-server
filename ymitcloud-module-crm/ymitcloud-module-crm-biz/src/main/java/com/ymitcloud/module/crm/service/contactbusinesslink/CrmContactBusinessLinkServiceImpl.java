package com.ymitcloud.module.crm.service.contactbusinesslink;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.collection.CollectionUtils;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymitcloud.module.crm.controller.admin.business.vo.business.CrmBusinessRespVO;
import com.ymitcloud.module.crm.controller.admin.contactbusinesslink.vo.CrmContactBusinessLinkPageReqVO;
import com.ymitcloud.module.crm.controller.admin.contactbusinesslink.vo.CrmContactBusinessLinkSaveReqVO;
import com.ymitcloud.module.crm.convert.business.CrmBusinessConvert;
import com.ymitcloud.module.crm.convert.contactbusinessslink.CrmContactBusinessLinkConvert;
import com.ymitcloud.module.crm.dal.dataobject.business.CrmBusinessDO;
import com.ymitcloud.module.crm.dal.dataobject.contactbusinesslink.CrmContactBusinessLinkDO;
import com.ymitcloud.module.crm.dal.mysql.contactbusinesslink.CrmContactBusinessLinkMapper;
import com.ymitcloud.module.crm.service.business.CrmBusinessService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.module.crm.enums.ErrorCodeConstants.CONTACT_BUSINESS_LINK_NOT_EXISTS;

// TODO @puhui999：数据权限的校验；每个操作；
/**
 * 联系人商机关联 Service 实现类
 *

 * @author 

 */
@Service
@Validated
public class CrmContactBusinessLinkServiceImpl implements CrmContactBusinessLinkService {

    @Resource
    private CrmContactBusinessLinkMapper contactBusinessLinkMapper;
    @Resource
    private CrmBusinessService crmBusinessService;

    @Override
    public Long createContactBusinessLink(CrmContactBusinessLinkSaveReqVO createReqVO) {
        CrmContactBusinessLinkDO contactBusinessLink = BeanUtils.toBean(createReqVO, CrmContactBusinessLinkDO.class);
        contactBusinessLinkMapper.insert(contactBusinessLink);
        return contactBusinessLink.getId();
    }

    @Override
    public void createContactBusinessLinkBatch(List<CrmContactBusinessLinkSaveReqVO> createReqVOList) {
        // 插入
        // TODO @zyna：如果已经关联过，不用重复插入；
        // TODO @zyna：contact 和 business 存在校验，挪到这里，Controller 不用 @Transactional 注解，添加到这里哈。尽量业务都在 Service；
        List<CrmContactBusinessLinkDO> saveDoList = CrmContactBusinessLinkConvert.INSTANCE.convert(createReqVOList);
        contactBusinessLinkMapper.insertBatch(saveDoList);
    }

    @Override
    public void updateContactBusinessLink(CrmContactBusinessLinkSaveReqVO updateReqVO) {
        // 校验存在
        validateContactBusinessLinkExists(updateReqVO.getId());
        // 更新
        CrmContactBusinessLinkDO updateObj = BeanUtils.toBean(updateReqVO, CrmContactBusinessLinkDO.class);
        contactBusinessLinkMapper.updateById(updateObj);
    }

    @Override
    public void deleteContactBusinessLink(List<CrmContactBusinessLinkSaveReqVO> createReqVO) {
        // 删除
        createReqVO.forEach(item -> {
            contactBusinessLinkMapper.delete(new LambdaQueryWrapperX<CrmContactBusinessLinkDO>()
                    .eq(CrmContactBusinessLinkDO::getBusinessId,item.getBusinessId())
                    .eq(CrmContactBusinessLinkDO::getContactId,item.getContactId())
                    .eq(CrmContactBusinessLinkDO::getDeleted,0));
        });
    }

    private void validateContactBusinessLinkExists(Long id) {
        if (contactBusinessLinkMapper.selectById(id) == null) {
            throw exception(CONTACT_BUSINESS_LINK_NOT_EXISTS);
        }
    }

    @Override
    public CrmContactBusinessLinkDO getContactBusinessLink(Long id) {
        return contactBusinessLinkMapper.selectById(id);
    }

    @Override
    public PageResult<CrmBusinessRespVO> getContactBusinessLinkPageByContact(CrmContactBusinessLinkPageReqVO pageReqVO) {
        CrmContactBusinessLinkPageReqVO crmContactBusinessLinkPageReqVO = new CrmContactBusinessLinkPageReqVO();
        crmContactBusinessLinkPageReqVO.setContactId(pageReqVO.getContactId());
        PageResult<CrmContactBusinessLinkDO> businessLinkDOS = contactBusinessLinkMapper.selectPageByContact(crmContactBusinessLinkPageReqVO);
        List<CrmBusinessDO> businessDOS = crmBusinessService.getBusinessList(CollectionUtils.convertList(businessLinkDOS.getList(),
                CrmContactBusinessLinkDO::getBusinessId));
        PageResult<CrmBusinessRespVO> pageResult = new PageResult<CrmBusinessRespVO>();
        pageResult.setList(CrmBusinessConvert.INSTANCE.convert(businessDOS));
        pageResult.setTotal(businessLinkDOS.getTotal());
        return pageResult;

    }

    @Override
    public PageResult<CrmContactBusinessLinkDO> getContactBusinessLinkPage(CrmContactBusinessLinkPageReqVO pageReqVO) {
        return contactBusinessLinkMapper.selectPage(pageReqVO);
    }

}