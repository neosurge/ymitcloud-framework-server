package com.ymitcloud.module.member.controller.app.address;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.member.controller.app.address.vo.AppAddressCreateReqVO;
import com.ymitcloud.module.member.controller.app.address.vo.AppAddressRespVO;
import com.ymitcloud.module.member.controller.app.address.vo.AppAddressUpdateReqVO;
import com.ymitcloud.module.member.convert.address.AddressConvert;
import com.ymitcloud.module.member.dal.dataobject.address.MemberAddressDO;
import com.ymitcloud.module.member.service.address.AddressService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 用户 APP - 用户收件地址
 */

@RestController
@RequestMapping("/member/address")
@Validated
public class AppAddressController {

    @Resource
    private AddressService addressService;


    /**
     * 创建用户收件地址
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthenticated
    public CommonResult<Long> createAddress(@Valid @RequestBody AppAddressCreateReqVO createReqVO) {
        return success(addressService.createAddress(getLoginUserId(), createReqVO));
    }


    /**
     * 更新用户收件地址
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthenticated
    public CommonResult<Boolean> updateAddress(@Valid @RequestBody AppAddressUpdateReqVO updateReqVO) {
        addressService.updateAddress(getLoginUserId(), updateReqVO);
        return success(true);
    }


    /**
     * 删除用户收件地址
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthenticated
    public CommonResult<Boolean> deleteAddress(@RequestParam("id") Long id) {
        addressService.deleteAddress(getLoginUserId(), id);
        return success(true);
    }


    /**
     * 获得用户收件地址
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthenticated
    public CommonResult<AppAddressRespVO> getAddress(@RequestParam("id") Long id) {
        MemberAddressDO address = addressService.getAddress(getLoginUserId(), id);
        return success(AddressConvert.INSTANCE.convert(address));
    }


    /**
     * 获得默认的用户收件地址
     * 
     * @return
     */
    @GetMapping("/get-default")

    @PreAuthenticated
    public CommonResult<AppAddressRespVO> getDefaultUserAddress() {
        MemberAddressDO address = addressService.getDefaultUserAddress(getLoginUserId());
        return success(AddressConvert.INSTANCE.convert(address));
    }


    /**
     * 获得用户收件地址列表
     * 
     * @return
     */
    @GetMapping("/list")

    @PreAuthenticated
    public CommonResult<List<AppAddressRespVO>> getAddressList() {
        List<MemberAddressDO> list = addressService.getAddressList(getLoginUserId());
        return success(AddressConvert.INSTANCE.convertList(list));
    }

}
