package com.ymitcloud.module.member.controller.admin.address;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.member.controller.admin.address.vo.AddressRespVO;
import com.ymitcloud.module.member.convert.address.AddressConvert;
import com.ymitcloud.module.member.dal.dataobject.address.MemberAddressDO;
import com.ymitcloud.module.member.service.address.AddressService;

import jakarta.annotation.Resource;

/**
 * 管理后台 - 用户收件地址
 */

@RestController
@RequestMapping("/member/address")
@Validated
public class AddressController {

    @Resource
    private AddressService addressService;


    /**
     * 获得用户收件地址列表
     * 
     * @param userId 用户编号
     * @return
     */
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('member:user:query')")
    public CommonResult<List<AddressRespVO>> getAddressList(@RequestParam("userId") Long userId) {
        List<MemberAddressDO> list = addressService.getAddressList(userId);
        return success(AddressConvert.INSTANCE.convertList2(list));
    }

}
