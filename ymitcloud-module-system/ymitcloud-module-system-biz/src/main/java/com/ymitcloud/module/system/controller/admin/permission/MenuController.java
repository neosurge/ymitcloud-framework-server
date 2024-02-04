package com.ymitcloud.module.system.controller.admin.permission;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.Comparator;
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
import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.system.controller.admin.permission.vo.menu.MenuListReqVO;
import com.ymitcloud.module.system.controller.admin.permission.vo.menu.MenuRespVO;
import com.ymitcloud.module.system.controller.admin.permission.vo.menu.MenuSaveVO;
import com.ymitcloud.module.system.controller.admin.permission.vo.menu.MenuSimpleRespVO;
import com.ymitcloud.module.system.dal.dataobject.permission.MenuDO;
import com.ymitcloud.module.system.service.permission.MenuService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 菜单
 */
@RestController
@RequestMapping("/system/menu")
@Validated
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 创建菜单
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:menu:create')")
    public CommonResult<Long> createMenu(@Valid @RequestBody MenuSaveVO createReqVO) {
        Long menuId = menuService.createMenu(createReqVO);
        return success(menuId);
    }

    /**
     * 修改菜单
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:menu:update')")
    public CommonResult<Boolean> updateMenu(@Valid @RequestBody MenuSaveVO updateReqVO) {
        menuService.updateMenu(updateReqVO);
        return success(true);
    }

    /**
     * 删除菜单
     * 
     * @param id 角色编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:menu:delete')")
    public CommonResult<Boolean> deleteMenu(@RequestParam("id") Long id) {
        menuService.deleteMenu(id);
        return success(true);
    }

    /**
     * 获取菜单列表，用于【菜单管理】界面
     * 
     * @param reqVO
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('system:menu:query')")
    public CommonResult<List<MenuRespVO>> getMenuList(MenuListReqVO reqVO) {
        List<MenuDO> list = menuService.getMenuList(reqVO);
        list.sort(Comparator.comparing(MenuDO::getSort));
        return success(BeanUtils.toBean(list, MenuRespVO.class));
    }

    /**
     * 获取菜单精简信息列表
     * 
     * 只包含被开启的菜单，用于【角色分配菜单】功能的选项，在多租户的场景下，会只返回租户所在套餐有的菜单
     * 
     * @return
     */
    @GetMapping({ "/list-all-simple", "simple-list" })
    public CommonResult<List<MenuSimpleRespVO>> getSimpleMenuList() {
        List<MenuDO> list = menuService
                .getMenuListByTenant(new MenuListReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
        list.sort(Comparator.comparing(MenuDO::getSort));
        return success(BeanUtils.toBean(list, MenuSimpleRespVO.class));
    }

    /**
     * 获取菜单信息
     * 
     * @param id
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:menu:query')")
    public CommonResult<MenuRespVO> getMenu(Long id) {
        MenuDO menu = menuService.getMenu(id);
        return success(BeanUtils.toBean(menu, MenuRespVO.class));
    }

}
