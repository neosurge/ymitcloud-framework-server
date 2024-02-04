package com.ymitcloud.module.pay.dal.mysql.wallet;


import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackagePageReqVO;
import com.ymitcloud.module.pay.dal.dataobject.wallet.PayWalletRechargePackageDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayWalletRechargePackageMapper extends BaseMapperX<PayWalletRechargePackageDO> {

    default PageResult<PayWalletRechargePackageDO> selectPage(WalletRechargePackagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PayWalletRechargePackageDO>()
                .likeIfPresent(PayWalletRechargePackageDO::getName, reqVO.getName())
                .eqIfPresent(PayWalletRechargePackageDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(PayWalletRechargePackageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PayWalletRechargePackageDO::getPayPrice));
    }

    // TODO @jason：这里要有空格哈；String name) {
    default PayWalletRechargePackageDO selectByName(String name){
        return selectOne(PayWalletRechargePackageDO::getName, name);
    }

}
