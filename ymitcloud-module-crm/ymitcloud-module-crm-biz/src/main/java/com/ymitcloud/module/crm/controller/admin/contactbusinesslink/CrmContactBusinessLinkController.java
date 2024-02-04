package com.ymitcloud.module.crm.controller.admin.contactbusinesslink;


import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.module.crm.enums.ErrorCodeConstants.BUSINESS_NOT_EXISTS;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.crm.controller.admin.business.vo.business.CrmBusinessRespVO;
import com.ymitcloud.module.crm.controller.admin.contactbusinesslink.vo.CrmContactBusinessLinkPageReqVO;
import com.ymitcloud.module.crm.controller.admin.contactbusinesslink.vo.CrmContactBusinessLinkRespVO;
import com.ymitcloud.module.crm.controller.admin.contactbusinesslink.vo.CrmContactBusinessLinkSaveReqVO;
import com.ymitcloud.module.crm.dal.dataobject.business.CrmBusinessDO;
import com.ymitcloud.module.crm.dal.dataobject.contactbusinesslink.CrmContactBusinessLinkDO;
import com.ymitcloud.module.crm.service.business.CrmBusinessService;
import com.ymitcloud.module.crm.service.contactbusinesslink.CrmContactBusinessLinkService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - CRM 联系人商机关联
 */

@RestController
@RequestMapping("/crm/contact-business-link")
@Validated
public class CrmContactBusinessLinkController {

    @Resource
    private CrmContactBusinessLinkService contactBusinessLinkService;
    @Resource
    private CrmBusinessService crmBusinessService;


    /**
     * 创建联系人商机关联
     * 
     * @param createReqVO
     * @return
     */
    // TODO @zyna：createContactBusinessLink 和 createContactBusinessLinkBatch
    // 是不是合并成一个接口？contactId、List<businessId>
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('crm:contact-business-link:create')")
    public CommonResult<Long> createContactBusinessLink(
            @Valid @RequestBody CrmContactBusinessLinkSaveReqVO createReqVO) {
        return success(contactBusinessLinkService.createContactBusinessLink(createReqVO));
    }

    /**
     * 创建联系人商机关联
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create-batch")

    @PreAuthorize("@ss.hasPermission('crm:contact-business-link:create')")
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<Boolean> createContactBusinessLinkBatch(
            @Valid @RequestBody List<CrmContactBusinessLinkSaveReqVO> createReqVO) {
        createReqVO.stream().forEach(item -> {
            CrmBusinessDO crmBusinessDO = crmBusinessService.getBusiness(item.getBusinessId());

            if (crmBusinessDO == null) {

                throw exception(BUSINESS_NOT_EXISTS);
            }
        });
        contactBusinessLinkService.createContactBusinessLinkBatch(createReqVO);
        return success(true);
    }


    /**
     * 更新联系人商机关联
     * 
     * @param updateReqVO
     * @return
     */
    // TODO @zyna：这个接口是不是可以删除掉了哈？应该不存在更新。
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('crm:contact-business-link:update')")
    public CommonResult<Boolean> updateContactBusinessLink(
            @Valid @RequestBody CrmContactBusinessLinkSaveReqVO updateReqVO) {

        contactBusinessLinkService.updateContactBusinessLink(updateReqVO);
        return success(true);
    }


    /**
     * 批量删除联系人商机关联
     * 
     * @param deleteList
     * @return
     */
    // TODO @zyna：删除，是不是传递 ids？
    @DeleteMapping("/delete-batch")
    @PreAuthorize("@ss.hasPermission('crm:contact-business-link:delete')")
    public CommonResult<Boolean> deleteContactBusinessLinkBatch(
            @Valid @RequestBody List<CrmContactBusinessLinkSaveReqVO> deleteList) {

        contactBusinessLinkService.deleteContactBusinessLink(deleteList);
        return success(true);
    }


    /**
     * 获得联系人商机关联
     * 
     * @param id 编号
     * @return
     */
    // TODO @zyna：这个接口是不是可以删除掉了哈？应该不存在单个读取；
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:contact-business-link:query')")
    public CommonResult<CrmContactBusinessLinkRespVO> getContactBusinessLink(@RequestParam("id") Long id) {
        CrmContactBusinessLinkDO contactBusinessLink = contactBusinessLinkService.getContactBusinessLink(id);
        return success(BeanUtils.toBean(contactBusinessLink, CrmContactBusinessLinkRespVO.class));
    }


    /**
     * 获得联系人商机关联
     * 
     * @param pageReqVO
     * @return
     */
    // TODO @zyna：这个可以转化下，使用客户编号去查询，就是使用 CrmBusinessController 的
    // getBusinessPageByCustomer 接口；目的是：复用
    @GetMapping("/page-by-contact")
    @PreAuthorize("@ss.hasPermission('crm:contact-business-link:query')")
    public CommonResult<PageResult<CrmBusinessRespVO>> getContactBusinessLinkByContact(
            @Valid CrmContactBusinessLinkPageReqVO pageReqVO) {
        PageResult<CrmBusinessRespVO> contactBusinessLink = contactBusinessLinkService
                .getContactBusinessLinkPageByContact(pageReqVO);
        return success(contactBusinessLink);
    }

    /**
     * 获得联系人商机关联分页
     * 
     * @param pageReqVO
     * @return
     */
    // TODO @zyna：这个优化下，搞到 CrmBusinessController 里去，加一个 CrmBusinessController 的
    // getBusinessPageByContact 接口；目的是：
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('crm:contact-business-link:query')")
    public CommonResult<PageResult<CrmContactBusinessLinkRespVO>> getContactBusinessLinkPage(
            @Valid CrmContactBusinessLinkPageReqVO pageReqVO) {
        PageResult<CrmContactBusinessLinkDO> pageResult = contactBusinessLinkService
                .getContactBusinessLinkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CrmContactBusinessLinkRespVO.class));
    }

    // TODO @zyna：最终梳理完后，应该就 2 个接口，要不直接合并到 CrmContactController
    // 中，不作为独立模块，就关联、接触关联。其实和 user 设置它有哪些岗位、部门是类似的。


}