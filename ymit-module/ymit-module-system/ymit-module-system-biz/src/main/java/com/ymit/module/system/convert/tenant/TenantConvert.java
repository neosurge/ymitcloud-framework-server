package com.ymit.module.system.convert.tenant;

import com.ymit.module.system.controller.admin.tenant.vo.tenant.TenantSaveReqVO;
import com.ymit.module.system.controller.admin.user.vo.user.UserSaveReqVO;

/**
 * 租户 Convert
 *
 * @author 云码源码
 */
public class TenantConvert {

    public static UserSaveReqVO convert02(TenantSaveReqVO bean) {
        if (bean == null) {
            return null;
        }
        UserSaveReqVO reqVO = new UserSaveReqVO();
        reqVO.setUsername(bean.getUsername());
        reqVO.setPassword(bean.getPassword());
        reqVO.setNickname(bean.getContactName());
        reqVO.setMobile(bean.getContactMobile());
        return reqVO;
    }

}
