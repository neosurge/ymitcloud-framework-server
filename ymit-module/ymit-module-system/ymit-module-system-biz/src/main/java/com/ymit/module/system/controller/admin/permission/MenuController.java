package com.ymit.module.system.controller.admin.permission;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.enums.CommonStatusEnum;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.system.controller.admin.permission.vo.menu.MenuListReqVO;
import com.ymit.module.system.controller.admin.permission.vo.menu.MenuRespVO;
import com.ymit.module.system.controller.admin.permission.vo.menu.MenuSaveVO;
import com.ymit.module.system.controller.admin.permission.vo.menu.MenuSimpleRespVO;
import com.ymit.module.system.dal.dataobject.permission.MenuDO;
import com.ymit.module.system.service.permission.MenuService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;

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
        Long menuId = this.menuService.createMenu(createReqVO);
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
        this.menuService.updateMenu(updateReqVO);
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
        this.menuService.deleteMenu(id);
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
        List<MenuDO> list = this.menuService.getMenuList(reqVO);
        return success(BeanUtils.toBean(list, MenuRespVO.class));
    }

    /**
     * 获取菜单精简信息列表
     * <p>
     * 只包含被开启的菜单，用于【角色分配菜单】功能的选项，在多租户的场景下，会只返回租户所在套餐有的菜单
     *
     * @return
     */
    @GetMapping({"/list-all-simple", "simple-list"})
    public CommonResult<List<MenuSimpleRespVO>> getSimpleMenuList() {
        List<MenuDO> list = this.menuService.getMenuListByTenant(new MenuListReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
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
        MenuDO menu = this.menuService.getMenu(id);
        return success(BeanUtils.toBean(menu, MenuRespVO.class));
    }

}
