package com.ymitcloud.module.mp.controller.admin.account;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.mp.controller.admin.account.vo.MpAccountCreateReqVO;
import com.ymitcloud.module.mp.controller.admin.account.vo.MpAccountPageReqVO;
import com.ymitcloud.module.mp.controller.admin.account.vo.MpAccountRespVO;
import com.ymitcloud.module.mp.controller.admin.account.vo.MpAccountSimpleRespVO;
import com.ymitcloud.module.mp.controller.admin.account.vo.MpAccountUpdateReqVO;
import com.ymitcloud.module.mp.convert.account.MpAccountConvert;
import com.ymitcloud.module.mp.dal.dataobject.account.MpAccountDO;
import com.ymitcloud.module.mp.service.account.MpAccountService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 公众号账号
 */

@RestController
@RequestMapping("/mp/account")
@Validated
public class MpAccountController {

    @Resource
    private MpAccountService mpAccountService;


    /**
     * 创建公众号账号
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('mp:account:create')")
    public CommonResult<Long> createAccount(@Valid @RequestBody MpAccountCreateReqVO createReqVO) {
        return success(mpAccountService.createAccount(createReqVO));
    }


    /**
     * 更新公众号账号
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('mp:account:update')")
    public CommonResult<Boolean> updateAccount(@Valid @RequestBody MpAccountUpdateReqVO updateReqVO) {
        mpAccountService.updateAccount(updateReqVO);
        return success(true);
    }


    /**
     * 删除公众号账号
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('mp:account:delete')")
    public CommonResult<Boolean> deleteAccount(@RequestParam("id") Long id) {
        mpAccountService.deleteAccount(id);
        return success(true);
    }


    /**
     * 获得公众号账号
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('mp:account:query')")
    public CommonResult<MpAccountRespVO> getAccount(@RequestParam("id") Long id) {
        MpAccountDO wxAccount = mpAccountService.getAccount(id);
        return success(MpAccountConvert.INSTANCE.convert(wxAccount));
    }


    /**
     * 获得公众号账号分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('mp:account:query')")
    public CommonResult<PageResult<MpAccountRespVO>> getAccountPage(@Valid MpAccountPageReqVO pageVO) {
        PageResult<MpAccountDO> pageResult = mpAccountService.getAccountPage(pageVO);
        return success(MpAccountConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 获取公众号账号精简信息列表
     * 
     * @return
     */
    @GetMapping("/list-all-simple")

    @PreAuthorize("@ss.hasPermission('mp:account:query')")
    public CommonResult<List<MpAccountSimpleRespVO>> getSimpleAccounts() {
        List<MpAccountDO> list = mpAccountService.getAccountList();
        return success(MpAccountConvert.INSTANCE.convertList02(list));
    }


    /**
     * 生成公众号二维码
     * 
     * @param id 编号
     * @return
     */
    @PutMapping("/generate-qr-code")

    @PreAuthorize("@ss.hasPermission('mp:account:qr-code')")
    public CommonResult<Boolean> generateAccountQrCode(@RequestParam("id") Long id) {
        mpAccountService.generateAccountQrCode(id);
        return success(true);
    }


    /**
     * 清空公众号 API 配额
     * 
     * @param id 编号
     * @return
     */
    @PutMapping("/clear-quota")

    @PreAuthorize("@ss.hasPermission('mp:account:clear-quota')")
    public CommonResult<Boolean> clearAccountQuota(@RequestParam("id") Long id) {
        mpAccountService.clearAccountQuota(id);
        return success(true);
    }

}
