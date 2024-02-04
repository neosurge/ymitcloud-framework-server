package com.ymitcloud.module.system.controller.admin.mail;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.system.controller.admin.mail.vo.log.MailLogPageReqVO;
import com.ymitcloud.module.system.controller.admin.mail.vo.log.MailLogRespVO;
import com.ymitcloud.module.system.dal.dataobject.mail.MailLogDO;
import com.ymitcloud.module.system.service.mail.MailLogService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 邮件日志
 */
@RestController
@RequestMapping("/system/mail-log")
public class MailLogController {

    @Resource
    private MailLogService mailLogService;

    /**
     * 获得邮箱日志分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:mail-log:query')")
    public CommonResult<PageResult<MailLogRespVO>> getMailLogPage(@Valid MailLogPageReqVO pageVO) {
        PageResult<MailLogDO> pageResult = mailLogService.getMailLogPage(pageVO);
        return success(BeanUtils.toBean(pageResult, MailLogRespVO.class));
    }

    /**
     * 获得邮箱日志
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:mail-log:query')")
    public CommonResult<MailLogRespVO> getMailTemplate(@RequestParam("id") Long id) {
        MailLogDO log = mailLogService.getMailLog(id);
        return success(BeanUtils.toBean(log, MailLogRespVO.class));
    }

}
