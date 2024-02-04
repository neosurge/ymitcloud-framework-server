package com.ymitcloud.module.system.controller.admin.mail;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ymitcloud.module.system.controller.admin.mail.vo.account.MailAccountPageReqVO;
import com.ymitcloud.module.system.controller.admin.mail.vo.account.MailAccountRespVO;
import com.ymitcloud.module.system.controller.admin.mail.vo.account.MailAccountSaveReqVO;
import com.ymitcloud.module.system.controller.admin.mail.vo.account.MailAccountSimpleRespVO;
import com.ymitcloud.module.system.dal.dataobject.mail.MailAccountDO;
import com.ymitcloud.module.system.service.mail.MailAccountService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 邮箱账号
 */
@RestController
@RequestMapping("/system/mail-account")
public class MailAccountController {

    @Resource
    private MailAccountService mailAccountService;

    /**
     * 创建邮箱账号
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:mail-account:create')")
    public CommonResult<Long> createMailAccount(@Valid @RequestBody MailAccountSaveReqVO createReqVO) {
        return success(mailAccountService.createMailAccount(createReqVO));
    }

    /**
     * 修改邮箱账号
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:mail-account:update')")
    public CommonResult<Boolean> updateMailAccount(@Valid @RequestBody MailAccountSaveReqVO updateReqVO) {
        mailAccountService.updateMailAccount(updateReqVO);
        return success(true);
    }

    /**
     * 删除邮箱账号
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:mail-account:delete')")
    public CommonResult<Boolean> deleteMailAccount(@RequestParam Long id) {
        mailAccountService.deleteMailAccount(id);
        return success(true);
    }

    /**
     * 获得邮箱账号
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:mail-account:get')")
    public CommonResult<MailAccountRespVO> getMailAccount(@RequestParam("id") Long id) {
        MailAccountDO account = mailAccountService.getMailAccount(id);
        return success(BeanUtils.toBean(account, MailAccountRespVO.class));
    }

    /**
     * 获得邮箱账号分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:mail-account:query')")
    public CommonResult<PageResult<MailAccountRespVO>> getMailAccountPage(@Valid MailAccountPageReqVO pageReqVO) {
        PageResult<MailAccountDO> pageResult = mailAccountService.getMailAccountPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MailAccountRespVO.class));
    }

    /**
     * 获得邮箱账号精简列表
     * 
     * @return
     */
    @GetMapping({ "/list-all-simple", "simple-list" })
    public CommonResult<List<MailAccountSimpleRespVO>> getSimpleMailAccountList() {
        List<MailAccountDO> list = mailAccountService.getMailAccountList();
        return success(BeanUtils.toBean(list, MailAccountSimpleRespVO.class));
    }

}
