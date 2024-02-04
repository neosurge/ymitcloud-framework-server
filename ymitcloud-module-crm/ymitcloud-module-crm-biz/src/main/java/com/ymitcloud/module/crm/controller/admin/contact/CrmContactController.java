package com.ymitcloud.module.crm.controller.admin.contact;



import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertListByFlatMap;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;


import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.number.NumberUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.crm.controller.admin.contact.vo.CrmContactCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.contact.vo.CrmContactPageReqVO;
import com.ymitcloud.module.crm.controller.admin.contact.vo.CrmContactRespVO;
import com.ymitcloud.module.crm.controller.admin.contact.vo.CrmContactSimpleRespVO;
import com.ymitcloud.module.crm.controller.admin.contact.vo.CrmContactUpdateReqVO;
import com.ymitcloud.module.crm.convert.contact.ContactConvert;
import com.ymitcloud.module.crm.dal.dataobject.contact.CrmContactDO;
import com.ymitcloud.module.crm.dal.dataobject.customer.CrmCustomerDO;
import com.ymitcloud.module.crm.enums.ErrorCodeConstants;
import com.ymitcloud.module.crm.service.contact.CrmContactService;
import com.ymitcloud.module.crm.service.customer.CrmCustomerService;
import com.ymitcloud.module.system.api.user.AdminUserApi;
import com.ymitcloud.module.system.api.user.dto.AdminUserRespDTO;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台 - CRM 联系人
 */

@RestController
@RequestMapping("/crm/contact")
@Validated
@Slf4j
public class CrmContactController {

    @Resource
    private CrmContactService contactService;
    @Resource
    private CrmCustomerService customerService;

    @Resource
    private AdminUserApi adminUserApi;


    /**
     * 创建联系人
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('crm:contact:create')")
    public CommonResult<Long> createContact(@Valid @RequestBody CrmContactCreateReqVO createReqVO) {
        return success(contactService.createContact(createReqVO, getLoginUserId()));
    }


    /**
     * 更新联系人
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('crm:contact:update')")
    public CommonResult<Boolean> updateContact(@Valid @RequestBody CrmContactUpdateReqVO updateReqVO) {
        contactService.updateContact(updateReqVO);
        return success(true);
    }


    /**
     * 删除联系人
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('crm:contact:delete')")
    public CommonResult<Boolean> deleteContact(@RequestParam("id") Long id) {
        contactService.deleteContact(id);
        return success(true);
    }


    /**
     * 获得联系人
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:contact:query')")
    public CommonResult<CrmContactRespVO> getContact(@RequestParam("id") Long id) {
        CrmContactDO contact = contactService.getContact(id);
        if (contact == null) {
            throw exception(ErrorCodeConstants.CONTACT_NOT_EXISTS);
        }
        // 1. 获取用户名

        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(CollUtil
                .removeNull(Lists.newArrayList(NumberUtil.parseLong(contact.getCreator()), contact.getOwnerUserId())));
        // 2. 获取客户信息
        List<CrmCustomerDO> customerList = customerService
                .getCustomerList(Collections.singletonList(contact.getCustomerId()));
        // 3. 直属上级
        List<CrmContactDO> parentContactList = contactService
                .getContactList(Collections.singletonList(contact.getParentId()));
        return success(ContactConvert.INSTANCE.convert(contact, userMap, customerList, parentContactList));
    }

    /**
     * 获得联系人列表
     * 
     * @return
     */
    @GetMapping("/simple-all-list")

    @PreAuthorize("@ss.hasPermission('crm:contact:query')")
    public CommonResult<List<CrmContactSimpleRespVO>> getSimpleContactList() {
        List<CrmContactDO> list = contactService.getContactList();
        return success(ContactConvert.INSTANCE.convertAllList(list));
    }


    /**
     * 获得联系人分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('crm:contact:query')")
    public CommonResult<PageResult<CrmContactRespVO>> getContactPage(@Valid CrmContactPageReqVO pageVO) {
        PageResult<CrmContactDO> pageResult = contactService.getContactPage(pageVO);
        return success(convertDetailContactPage(pageResult));
    }


    /**
     * 获得联系人分页，基于指定客户
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page-by-customer")

    public CommonResult<PageResult<CrmContactRespVO>> getContactPageByCustomer(@Valid CrmContactPageReqVO pageVO) {
        Assert.notNull(pageVO.getCustomerId(), "客户编号不能为空");
        PageResult<CrmContactDO> pageResult = contactService.getContactPageByCustomer(pageVO);
        return success(convertDetailContactPage(pageResult));
    }


    /**
     * 导出联系人 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('crm:contact:export')")
    @OperateLog(type = EXPORT)
    public void exportContactExcel(@Valid CrmContactPageReqVO exportReqVO, HttpServletResponse response)
            throws IOException {

        exportReqVO.setPageNo(PageParam.PAGE_SIZE_NONE);
        PageResult<CrmContactDO> pageResult = contactService.getContactPage(exportReqVO);
        ExcelUtils.write(response, "联系人.xls", "数据", CrmContactRespVO.class,
                convertDetailContactPage(pageResult).getList());
    }

    /**
     * 转换成详细的联系人分页，即读取关联信息
     *
     * @param pageResult 联系人分页
     * @return 详细的联系人分页
     */
    private PageResult<CrmContactRespVO> convertDetailContactPage(PageResult<CrmContactDO> pageResult) {
        List<CrmContactDO> contactList = pageResult.getList();
        if (CollUtil.isEmpty(contactList)) {
            return PageResult.empty(pageResult.getTotal());
        }
        // 1. 获取客户列表

        List<CrmCustomerDO> crmCustomerDOList = customerService
                .getCustomerList(convertSet(contactList, CrmContactDO::getCustomerId));

        // 2. 获取创建人、负责人列表
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(convertListByFlatMap(contactList,
                contact -> Stream.of(NumberUtils.parseLong(contact.getCreator()), contact.getOwnerUserId())));
        // 3. 直属上级

        List<CrmContactDO> parentContactList = contactService
                .getContactList(convertSet(contactList, CrmContactDO::getParentId));

        return ContactConvert.INSTANCE.convertPage(pageResult, userMap, crmCustomerDOList, parentContactList);
    }

}
