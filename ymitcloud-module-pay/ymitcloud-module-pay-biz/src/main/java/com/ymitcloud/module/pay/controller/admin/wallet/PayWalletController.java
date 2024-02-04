package com.ymitcloud.module.pay.controller.admin.wallet;


import static com.ymitcloud.framework.common.enums.UserTypeEnum.MEMBER;
import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.collection.CollectionUtils;

import com.ymitcloud.module.member.api.user.MemberUserApi;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.wallet.PayWalletPageReqVO;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.wallet.PayWalletRespVO;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.wallet.PayWalletUserReqVO;
import com.ymitcloud.module.pay.convert.wallet.PayWalletConvert;
import com.ymitcloud.module.pay.dal.dataobject.wallet.PayWalletDO;
import com.ymitcloud.module.pay.service.wallet.PayWalletService;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台 - 用户钱包
 */

@RestController
@RequestMapping("/pay/wallet")
@Validated
@Slf4j
public class PayWalletController {

    @Resource
    private PayWalletService payWalletService;
    @Resource
    private MemberUserApi memberUserApi;


    /**
     * 获得用户钱包明细
     * 
     * @param reqVO
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('pay:wallet:query')")

    public CommonResult<PayWalletRespVO> getWallet(PayWalletUserReqVO reqVO) {
        PayWalletDO wallet = payWalletService.getOrCreateWallet(reqVO.getUserId(), MEMBER.getValue());
        // TODO jason：如果为空，返回给前端只要 null 就可以了
        MemberUserRespDTO memberUser = memberUserApi.getUser(reqVO.getUserId());
        String nickname = memberUser == null ? "" : memberUser.getNickname();
        String avatar = memberUser == null ? "" : memberUser.getAvatar();
        return success(PayWalletConvert.INSTANCE.convert02(nickname, avatar, wallet));
    }


    /**
     * 获得会员钱包分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('pay:wallet:query')")
    public CommonResult<PageResult<PayWalletRespVO>> getWalletPage(@Valid PayWalletPageReqVO pageVO) {
        if (StrUtil.isNotEmpty(pageVO.getNickname())) {
            List<MemberUserRespDTO> users = memberUserApi.getUserListByNickname(pageVO.getNickname());

            pageVO.setUserIds(CollectionUtils.convertSet(users, MemberUserRespDTO::getId));
        }
        // TODO @jason：管理员也可以先查询下。。
        // 暂时支持查询 userType 会员类型。管理员类型还不知道使用场景
        PageResult<PayWalletDO> pageResult = payWalletService.getWalletPage(MEMBER.getValue(), pageVO);
        if (CollectionUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal()));
        }
        List<MemberUserRespDTO> users = memberUserApi
                .getUserList(CollectionUtils.convertList(pageResult.getList(), PayWalletDO::getUserId));
        Map<Long, MemberUserRespDTO> userMap = CollectionUtils.convertMap(users, MemberUserRespDTO::getId);

        return success(PayWalletConvert.INSTANCE.convertPage(pageResult, userMap));
    }

}
