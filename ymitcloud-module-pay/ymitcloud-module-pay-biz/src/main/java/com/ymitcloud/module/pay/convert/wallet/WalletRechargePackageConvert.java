package com.ymitcloud.module.pay.convert.wallet;

import java.util.*;

import com.ymitcloud.framework.common.pojo.PageResult;

import com.ymitcloud.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackageCreateReqVO;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackageRespVO;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackageUpdateReqVO;
import com.ymitcloud.module.pay.dal.dataobject.wallet.PayWalletRechargePackageDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletRechargePackageConvert {

    WalletRechargePackageConvert INSTANCE = Mappers.getMapper(WalletRechargePackageConvert.class);

    PayWalletRechargePackageDO convert(WalletRechargePackageCreateReqVO bean);

    PayWalletRechargePackageDO convert(WalletRechargePackageUpdateReqVO bean);

    WalletRechargePackageRespVO convert(PayWalletRechargePackageDO bean);

    List<WalletRechargePackageRespVO> convertList(List<PayWalletRechargePackageDO> list);

    PageResult<WalletRechargePackageRespVO> convertPage(PageResult<PayWalletRechargePackageDO> page);

}
