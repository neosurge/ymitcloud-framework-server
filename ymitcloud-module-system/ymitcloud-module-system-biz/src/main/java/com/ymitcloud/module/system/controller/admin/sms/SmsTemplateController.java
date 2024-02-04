package com.ymitcloud.module.system.controller.admin.sms;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
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
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.system.controller.admin.sms.vo.template.SmsTemplatePageReqVO;
import com.ymitcloud.module.system.controller.admin.sms.vo.template.SmsTemplateRespVO;
import com.ymitcloud.module.system.controller.admin.sms.vo.template.SmsTemplateSaveReqVO;
import com.ymitcloud.module.system.controller.admin.sms.vo.template.SmsTemplateSendReqVO;
import com.ymitcloud.module.system.dal.dataobject.sms.SmsTemplateDO;
import com.ymitcloud.module.system.service.sms.SmsSendService;
import com.ymitcloud.module.system.service.sms.SmsTemplateService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


/**
 * 管理后台 - 短信模板
 */
@RestController
@RequestMapping("/system/sms-template")
public class SmsTemplateController {

    @Resource
    private SmsTemplateService smsTemplateService;
    @Resource
    private SmsSendService smsSendService;

    /**
     * 创建短信模板
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:sms-template:create')")
    public CommonResult<Long> createSmsTemplate(@Valid @RequestBody SmsTemplateSaveReqVO createReqVO) {
        return success(smsTemplateService.createSmsTemplate(createReqVO));
    }

    /**
     * 更新短信模板
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:sms-template:update')")
    public CommonResult<Boolean> updateSmsTemplate(@Valid @RequestBody SmsTemplateSaveReqVO updateReqVO) {
        smsTemplateService.updateSmsTemplate(updateReqVO);
        return success(true);
    }

    /**
     * 删除短信模板
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:sms-template:delete')")
    public CommonResult<Boolean> deleteSmsTemplate(@RequestParam("id") Long id) {
        smsTemplateService.deleteSmsTemplate(id);
        return success(true);
    }

    /**
     * 获得短信模板
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:sms-template:query')")
    public CommonResult<SmsTemplateRespVO> getSmsTemplate(@RequestParam("id") Long id) {
        SmsTemplateDO template = smsTemplateService.getSmsTemplate(id);
        return success(BeanUtils.toBean(template, SmsTemplateRespVO.class));
    }

    /**
     * 获得短信模板分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:sms-template:query')")
    public CommonResult<PageResult<SmsTemplateRespVO>> getSmsTemplatePage(@Valid SmsTemplatePageReqVO pageVO) {
        PageResult<SmsTemplateDO> pageResult = smsTemplateService.getSmsTemplatePage(pageVO);
        return success(BeanUtils.toBean(pageResult, SmsTemplateRespVO.class));
    }

    /**
     * 导出短信模板 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('system:sms-template:export')")
    @OperateLog(type = EXPORT)
    public void exportSmsTemplateExcel(@Valid SmsTemplatePageReqVO exportReqVO, HttpServletResponse response)
            throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SmsTemplateDO> list = smsTemplateService.getSmsTemplatePage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "短信模板.xls", "数据", SmsTemplateRespVO.class,
                BeanUtils.toBean(list, SmsTemplateRespVO.class));
    }

    /**
     * 发送短信
     * 
     * @param sendReqVO
     * @return
     */
    @PostMapping("/send-sms")
    @PreAuthorize("@ss.hasPermission('system:sms-template:send-sms')")
    public CommonResult<Long> sendSms(@Valid @RequestBody SmsTemplateSendReqVO sendReqVO) {
        return success(smsSendService.sendSingleSmsToAdmin(sendReqVO.getMobile(), null, sendReqVO.getTemplateCode(),
                sendReqVO.getTemplateParams()));
    }

}
