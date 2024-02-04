package com.ymitcloud.module.rule.liteflow;

import com.yomahub.liteflow.script.ScriptExecuteWrap;
import com.yomahub.liteflow.script.body.JaninoCommonScriptBody;
import com.yomahub.liteflow.slot.DefaultContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.nio.charset.StandardCharsets;

public class SendMailScriptBody implements JaninoCommonScriptBody {
    @Override
    public Void body(ScriptExecuteWrap wrap) {
        DefaultContext ctx = (DefaultContext) wrap.cmp.getContextBean(DefaultContext.class);
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.163.com");
        javaMailSender.setUsername("ymitcloud");
        javaMailSender.setPassword("WOOGBTLFEIVGNZMN");
        javaMailSender.setPort(25);
        javaMailSender.setProtocol("smtp");
        javaMailSender.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        SimpleMailMessage smm = new SimpleMailMessage();
        // 设置收件人，寄件人
        smm.setTo("fan_yawei@163.com");
        smm.setFrom("ymitcloud@163.com");
        smm.setSubject("水位告警");
        smm.setText("测试水库的水位已经超过了:" + ctx.getData("waterLevel")+",你们这群人干什么吃的?");
        // 发送邮件
        javaMailSender.send(smm);

        //必须返回null
        return null;
    }
}
