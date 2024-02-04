package com.ymitcloud.module.system.controller.admin.user;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.system.controller.admin.user.vo.user.UserImportExcelVO;
import com.ymitcloud.module.system.controller.admin.user.vo.user.UserImportRespVO;
import com.ymitcloud.module.system.controller.admin.user.vo.user.UserPageReqVO;
import com.ymitcloud.module.system.controller.admin.user.vo.user.UserRespVO;
import com.ymitcloud.module.system.controller.admin.user.vo.user.UserSaveReqVO;
import com.ymitcloud.module.system.controller.admin.user.vo.user.UserSimpleRespVO;
import com.ymitcloud.module.system.controller.admin.user.vo.user.UserUpdatePasswordReqVO;
import com.ymitcloud.module.system.controller.admin.user.vo.user.UserUpdateStatusReqVO;
import com.ymitcloud.module.system.convert.user.UserConvert;
import com.ymitcloud.module.system.dal.dataobject.dept.DeptDO;
import com.ymitcloud.module.system.dal.dataobject.user.AdminUserDO;
import com.ymitcloud.module.system.enums.common.SexEnum;
import com.ymitcloud.module.system.service.dept.DeptService;
import com.ymitcloud.module.system.service.user.AdminUserService;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 用户
 */
@RestController
@RequestMapping("/system/user")
@Validated
public class UserController {

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;

    /**
     * 新增用户
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:user:create')")
    public CommonResult<Long> createUser(@Valid @RequestBody UserSaveReqVO reqVO) {
        Long id = userService.createUser(reqVO);
        return success(id);
    }

    /**
     * 修改用户
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("update")
    @PreAuthorize("@ss.hasPermission('system:user:update')")
    public CommonResult<Boolean> updateUser(@Valid @RequestBody UserSaveReqVO reqVO) {
        userService.updateUser(reqVO);
        return success(true);
    }

    /**
     * 删除用户
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:user:delete')")
    public CommonResult<Boolean> deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return success(true);
    }

    /**
     * 重置用户密码
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update-password")
    @PreAuthorize("@ss.hasPermission('system:user:update-password')")
    public CommonResult<Boolean> updateUserPassword(@Valid @RequestBody UserUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(reqVO.getId(), reqVO.getPassword());
        return success(true);
    }

    /**
     * 修改用户状态
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update-status")
    @PreAuthorize("@ss.hasPermission('system:user:update')")
    public CommonResult<Boolean> updateUserStatus(@Valid @RequestBody UserUpdateStatusReqVO reqVO) {
        userService.updateUserStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }

    /**
     * 获得用户分页列表
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:user:list')")
    public CommonResult<PageResult<UserRespVO>> getUserPage(@Valid UserPageReqVO pageReqVO) {
        // 获得用户分页列表
        PageResult<AdminUserDO> pageResult = userService.getUserPage(pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal()));
        }
        // 拼接数据
        Map<Long, DeptDO> deptMap = deptService.getDeptMap(convertList(pageResult.getList(), AdminUserDO::getDeptId));
        return success(new PageResult<>(UserConvert.INSTANCE.convertList(pageResult.getList(), deptMap),
                pageResult.getTotal()));
    }

    /**
     * 获取用户精简信息列表,只包含被开启的用户，主要用于前端的下拉选项
     * 
     * @return
     */
    @GetMapping({ "/list-all-simple", "/simple-list" })
    public CommonResult<List<UserSimpleRespVO>> getSimpleUserList() {
        List<AdminUserDO> list = userService.getUserListByStatus(CommonStatusEnum.ENABLE.getStatus());
        // 拼接数据
        Map<Long, DeptDO> deptMap = deptService.getDeptMap(convertList(list, AdminUserDO::getDeptId));
        return success(UserConvert.INSTANCE.convertSimpleList(list, deptMap));
    }

    /**
     * 获得用户详情
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:user:query')")
    public CommonResult<UserRespVO> getUser(@RequestParam("id") Long id) {
        AdminUserDO user = userService.getUser(id);
        // 拼接数据
        DeptDO dept = deptService.getDept(user.getDeptId());
        return success(UserConvert.INSTANCE.convert(user, dept));
    }

    /**
     * 导出用户
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export")
    @PreAuthorize("@ss.hasPermission('system:user:export')")
    @OperateLog(type = EXPORT)
    public void exportUserList(@Validated UserPageReqVO exportReqVO, HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AdminUserDO> list = userService.getUserPage(exportReqVO).getList();
        // 输出 Excel
        Map<Long, DeptDO> deptMap = deptService.getDeptMap(convertList(list, AdminUserDO::getDeptId));
        ExcelUtils.write(response, "用户数据.xls", "数据", UserRespVO.class, UserConvert.INSTANCE.convertList(list, deptMap));
    }

    /**
     * 获得导入用户模板
     * 
     * @param response
     * @throws IOException
     */
    @GetMapping("/get-import-template")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导出 demo
        List<UserImportExcelVO> list = Arrays.asList(
                UserImportExcelVO.builder().username("yunai").deptId(1L).email("yunai@ymitcloud.com").mobile("15601691300")
                        .nickname("云码").status(CommonStatusEnum.ENABLE.getStatus()).sex(SexEnum.MALE.getSex()).build(),
                UserImportExcelVO.builder().username("yuanma").deptId(2L).email("yuanma@ymitcloud.com")
                        .mobile("15601701300").nickname("源码").status(CommonStatusEnum.DISABLE.getStatus())
                        .sex(SexEnum.FEMALE.getSex()).build());
        // 输出
        ExcelUtils.write(response, "用户导入模板.xls", "用户列表", UserImportExcelVO.class, list);
    }

    /**
     * 导入用户
     * 
     * @param file          Excel 文件
     * @param updateSupport 是否支持更新，默认为 false
     * @return
     * @throws Exception
     */
    @PostMapping("/import")
    @PreAuthorize("@ss.hasPermission('system:user:import')")
    public CommonResult<UserImportRespVO> importExcel(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport)
            throws Exception {
        List<UserImportExcelVO> list = ExcelUtils.read(file, UserImportExcelVO.class);
        return success(userService.importUserList(list, updateSupport));
    }

}
