package com.ymit.module.system.convert.social;

import com.ymit.module.system.api.social.dto.SocialUserBindReqDTO;
import com.ymit.module.system.controller.admin.socail.vo.user.SocialUserBindReqVO;

public class SocialUserConvert {
    public static SocialUserBindReqDTO convert(Long userId, Integer userType, SocialUserBindReqVO reqVO) {
        if (reqVO == null) {
            return null;
        }
        SocialUserBindReqDTO data = new SocialUserBindReqDTO();
        data.setUserId(userId);
        data.setUserType(userType);
        data.setSocialType(reqVO.getType());
        data.setCode(reqVO.getCode());
        data.setState(reqVO.getState());
        return data;
    }

}
