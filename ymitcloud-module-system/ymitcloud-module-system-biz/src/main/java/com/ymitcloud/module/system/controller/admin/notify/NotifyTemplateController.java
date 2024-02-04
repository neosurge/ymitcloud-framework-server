package com.ymitcloud.module.system.controller.admin.notify;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

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

import com.ymitcloud.framework.common.enums.UserTypeEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.system.controller.admin.notify.vo.template.NotifyTemplatePageReqVO;
import com.ymitcloud.module.system.controller.admin.notify.vo.template.NotifyTemplateRespVO;
import com.ymitcloud.module.system.controller.admin.notify.vo.template.NotifyTemplateSaveReqVO;
import com.ymitcloud.module.system.controller.admin.notify.vo.template.NotifyTemplateSendReqVO;
import com.ymitcloud.module.system.dal.dataobject.notify.NotifyTemplateDO;
import com.ymitcloud.module.system.service.notify.NotifySendService;
import com.ymitcloud.module.system.service.notify.NotifyTemplateService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 站内信模版
 */
@RestController
@RequestMapping("/system/notify-template")
@Validated
public class NotifyTemplateController {

    @Resource
    private NotifyTemplateService notifyTemplateService;

    @Resource
    private NotifySendService notifySendService;

    /**
     * 创建站内信模版
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:notify-template:create')")
    public CommonResult<Long> createNotifyTemplate(@Valid @RequestBody NotifyTemplateSaveReqVO createReqVO) {
        return success(notifyTemplateService.createNotifyTemplate(createReqVO));
    }

    /**
     * 更新站内信模版
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:notify-template:update')")
    public CommonResult<Boolean> updateNotifyTemplate(@Valid @RequestBody NotifyTemplateSaveReqVO updateReqVO) {
        notifyTemplateService.updateNotifyTemplate(updateReqVO);
        return success(true);
    }

    /**
     * 删除站内信模版
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:notify-template:delete')")
    public CommonResult<Boolean> deleteNotifyTemplate(@RequestParam("id") Long id) {
        notifyTemplateService.deleteNotifyTemplate(id);
        return success(true);
    }

    /**
     * 获得站内信模版
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:notify-template:query')")
    public CommonResult<NotifyTemplateRespVO> getNotifyTemplate(@RequestParam("id") Long id) {
        NotifyTemplateDO template = notifyTemplateService.getNotifyTemplate(id);
        return success(BeanUtils.toBean(template, NotifyTemplateRespVO.class));
    }
    /**
     * 获得站内信模版分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:notify-template:query')")
    public CommonResult<PageResult<NotifyTemplateRespVO>> getNotifyTemplatePage(@Valid NotifyTemplatePageReqVO pageVO) {
        PageResult<NotifyTemplateDO> pageResult = notifyTemplateService.getNotifyTemplatePage(pageVO);
        return success(BeanUtils.toBean(pageResult, NotifyTemplateRespVO.class));
    }
    /**
     * 发送站内信
     * 
     * @param sendReqVO
     * @return
     */
    @PostMapping("/send-notify")
    @PreAuthorize("@ss.hasPermission('system:notify-template:send-notify')")
    public CommonResult<Long> sendNotify(@Valid @RequestBody NotifyTemplateSendReqVO sendReqVO) {
        if (UserTypeEnum.MEMBER.getValue().equals(sendReqVO.getUserType())) {
            return success(notifySendService.sendSingleNotifyToMember(sendReqVO.getUserId(),
                    sendReqVO.getTemplateCode(), sendReqVO.getTemplateParams()));
        } else {
            return success(notifySendService.sendSingleNotifyToAdmin(sendReqVO.getUserId(), sendReqVO.getTemplateCode(),
                    sendReqVO.getTemplateParams()));
        }
    }
}
