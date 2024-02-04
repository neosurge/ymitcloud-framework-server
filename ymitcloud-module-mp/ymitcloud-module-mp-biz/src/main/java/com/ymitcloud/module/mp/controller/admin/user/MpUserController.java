package com.ymitcloud.module.mp.controller.admin.user;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.mp.controller.admin.user.vo.MpUserPageReqVO;
import com.ymitcloud.module.mp.controller.admin.user.vo.MpUserRespVO;
import com.ymitcloud.module.mp.controller.admin.user.vo.MpUserUpdateReqVO;
import com.ymitcloud.module.mp.convert.user.MpUserConvert;
import com.ymitcloud.module.mp.dal.dataobject.user.MpUserDO;
import com.ymitcloud.module.mp.service.user.MpUserService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - 公众号粉丝
 */

@RestController
@RequestMapping("/mp/user")
@Validated
public class MpUserController {

    @Resource
    private MpUserService mpUserService;


    /**
     * 获得公众号粉丝分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('mp:user:query')")
    public CommonResult<PageResult<MpUserRespVO>> getUserPage(@Valid MpUserPageReqVO pageVO) {
        PageResult<MpUserDO> pageResult = mpUserService.getUserPage(pageVO);
        return success(MpUserConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 获得公众号粉丝
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('mp:user:query')")
    public CommonResult<MpUserRespVO> getUser(@RequestParam("id") Long id) {
        return success(MpUserConvert.INSTANCE.convert(mpUserService.getUser(id)));
    }


    /**
     * 更新公众号粉丝
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('mp:user:update')")
    public CommonResult<Boolean> updateUser(@Valid @RequestBody MpUserUpdateReqVO updateReqVO) {
        mpUserService.updateUser(updateReqVO);
        return success(true);
    }


    /**
     * 同步公众号粉丝
     * 
     * @param accountId 公众号账号的编号
     * @return
     */
    @PostMapping("/sync")

    @PreAuthorize("@ss.hasPermission('mp:user:sync')")
    public CommonResult<Boolean> syncUser(@RequestParam("accountId") Long accountId) {
        mpUserService.syncUser(accountId);
        return success(true);
    }

}
