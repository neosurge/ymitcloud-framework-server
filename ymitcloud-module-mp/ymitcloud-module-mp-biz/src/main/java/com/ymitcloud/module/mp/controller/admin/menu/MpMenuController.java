package com.ymitcloud.module.mp.controller.admin.menu;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.mp.controller.admin.menu.vo.MpMenuRespVO;
import com.ymitcloud.module.mp.controller.admin.menu.vo.MpMenuSaveReqVO;
import com.ymitcloud.module.mp.convert.menu.MpMenuConvert;
import com.ymitcloud.module.mp.dal.dataobject.menu.MpMenuDO;
import com.ymitcloud.module.mp.service.menu.MpMenuService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 公众号菜单
 */

@RestController
@RequestMapping("/mp/menu")
@Validated
public class MpMenuController {

    @Resource
    private MpMenuService mpMenuService;


    /**
     * 保存公众号菜单
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/save")

    @PreAuthorize("@ss.hasPermission('mp:menu:save')")
    public CommonResult<Boolean> saveMenu(@Valid @RequestBody MpMenuSaveReqVO createReqVO) {
        mpMenuService.saveMenu(createReqVO);
        return success(true);
    }


    /**
     * 删除公众号菜单
     * 
     * @param accountId 公众号账号的编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('mp:menu:delete')")
    public CommonResult<Boolean> deleteMenu(@RequestParam("accountId") Long accountId) {
        mpMenuService.deleteMenuByAccountId(accountId);
        return success(true);
    }


    /**
     * 获得公众号菜单列表
     * 
     * @param accountId 公众号账号的编号
     * @return
     */
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('mp:menu:query')")
    public CommonResult<List<MpMenuRespVO>> getMenuList(@RequestParam("accountId") Long accountId) {
        List<MpMenuDO> list = mpMenuService.getMenuListByAccountId(accountId);
        return success(MpMenuConvert.INSTANCE.convertList(list));
    }

}
