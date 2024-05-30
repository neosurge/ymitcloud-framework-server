package com.ymit.module.system.convert.mail;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailAccount;
import com.ymit.module.system.dal.dataobject.mail.MailAccountDO;

public class MailAccountConvert {

    public static MailAccount convert(MailAccountDO account, String nickname) {
        String from = StrUtil.isNotEmpty(nickname) ? nickname + " <" + account.getMail() + ">" : account.getMail();
        return new MailAccount().setFrom(from).setAuth(true).setUser(account.getUsername()).setPass(account.getPassword()).setHost(account.getHost()).setPort(account.getPort()).setSslEnable(account.getSslEnable());
    }

}
