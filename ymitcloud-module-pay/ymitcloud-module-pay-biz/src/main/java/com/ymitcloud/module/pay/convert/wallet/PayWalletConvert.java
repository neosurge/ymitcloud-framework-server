package com.ymitcloud.module.pay.convert.wallet;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.collection.MapUtils;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.wallet.PayWalletRespVO;
import com.ymitcloud.module.pay.controller.app.wallet.vo.wallet.AppPayWalletRespVO;
import com.ymitcloud.module.pay.dal.dataobject.wallet.PayWalletDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper
public interface PayWalletConvert {

    PayWalletConvert INSTANCE = Mappers.getMapper(PayWalletConvert.class);

    AppPayWalletRespVO convert(PayWalletDO bean);

    PayWalletRespVO convert02(String nickname,String avatar, PayWalletDO bean);

    PageResult<PayWalletRespVO> convertPage(PageResult<PayWalletDO> page);

    default PageResult<PayWalletRespVO> convertPage(PageResult<PayWalletDO> page, Map<Long, MemberUserRespDTO> userMap) {
        PageResult<PayWalletRespVO> pageResult = convertPage(page);
        pageResult.getList().forEach(wallet -> MapUtils.findAndThen(userMap, wallet.getUserId(),
                user -> wallet.setNickname(user.getNickname()).setAvatar(user.getAvatar())));
        return pageResult;
    }

}
