package com.ymitcloud.module.system.controller.admin.mail;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

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
import com.ymitcloud.module.system.controller.admin.mail.vo.template.MailTemplatePageReqVO;
import com.ymitcloud.module.system.controller.admin.mail.vo.template.MailTemplateRespVO;
import com.ymitcloud.module.system.controller.admin.mail.vo.template.MailTemplateSaveReqVO;
import com.ymitcloud.module.system.controller.admin.mail.vo.template.MailTemplateSendReqVO;
import com.ymitcloud.module.system.controller.admin.mail.vo.template.MailTemplateSimpleRespVO;
import com.ymitcloud.module.system.dal.dataobject.mail.MailTemplateDO;
import com.ymitcloud.module.system.service.mail.MailSendService;
import com.ymitcloud.module.system.service.mail.MailTemplateService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 邮件模版
 */
@RestController
@RequestMapping("/system/mail-template")
public class MailTemplateController {

    @Resource
    private MailTemplateService mailTempleService;
    @Resource
    private MailSendService mailSendService;

    /**
     * 创建邮件模版
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:mail-template:create')")
    public CommonResult<Long> createMailTemplate(@Valid @RequestBody MailTemplateSaveReqVO createReqVO) {
        return success(mailTempleService.createMailTemplate(createReqVO));
    }

    /**
     * 修改邮件模版
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:mail-template:update')")
    public CommonResult<Boolean> updateMailTemplate(@Valid @RequestBody MailTemplateSaveReqVO updateReqVO) {
        mailTempleService.updateMailTemplate(updateReqVO);
        return success(true);
    }

    /**
     * 删除邮件模版
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:mail-template:delete')")
    public CommonResult<Boolean> deleteMailTemplate(@RequestParam("id") Long id) {
        mailTempleService.deleteMailTemplate(id);
        return success(true);
    }
    /**
     * 编号
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:mail-template:get')")
    public CommonResult<MailTemplateRespVO> getMailTemplate(@RequestParam("id") Long id) {
        MailTemplateDO template = mailTempleService.getMailTemplate(id);
        return success(BeanUtils.toBean(template, MailTemplateRespVO.class));
    }

    /**
     * 获得邮件模版分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:mail-template:query')")
    public CommonResult<PageResult<MailTemplateRespVO>> getMailTemplatePage(@Valid MailTemplatePageReqVO pageReqVO) {
        PageResult<MailTemplateDO> pageResult = mailTempleService.getMailTemplatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MailTemplateRespVO.class));
    }

    /**
     * 获得邮件模版精简列表
     * @return
     */
    @GetMapping({ "/list-all-simple", "simple-list" })
    public CommonResult<List<MailTemplateSimpleRespVO>> getSimpleTemplateList() {
        List<MailTemplateDO> list = mailTempleService.getMailTemplateList();
        return success(BeanUtils.toBean(list, MailTemplateSimpleRespVO.class));
    }

    /**
     * 发送短信
     * @param sendReqVO
     * @return
     */
    @PostMapping("/send-mail")
    @PreAuthorize("@ss.hasPermission('system:mail-template:send-mail')")
    public CommonResult<Long> sendMail(@Valid @RequestBody MailTemplateSendReqVO sendReqVO) {
        return success(mailSendService.sendSingleMailToAdmin(sendReqVO.getMail(), getLoginUserId(),
                sendReqVO.getTemplateCode(), sendReqVO.getTemplateParams()));
    }

}
