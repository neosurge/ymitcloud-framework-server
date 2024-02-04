package com.ymitcloud.module.system.controller.admin.dept;

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

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.system.controller.admin.dept.vo.dept.DeptListReqVO;
import com.ymitcloud.module.system.controller.admin.dept.vo.dept.DeptRespVO;
import com.ymitcloud.module.system.controller.admin.dept.vo.dept.DeptSaveReqVO;
import com.ymitcloud.module.system.controller.admin.dept.vo.dept.DeptSimpleRespVO;
import com.ymitcloud.module.system.dal.dataobject.dept.DeptDO;
import com.ymitcloud.module.system.service.dept.DeptService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 部门
 */
@RestController
@RequestMapping("/system/dept")
@Validated
public class DeptController {

    @Resource
    private DeptService deptService;

    /**
     * 创建部门
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("create")
    @PreAuthorize("@ss.hasPermission('system:dept:create')")
    public CommonResult<Long> createDept(@Valid @RequestBody DeptSaveReqVO createReqVO) {
        Long deptId = deptService.createDept(createReqVO);
        return success(deptId);
    }

    /**
     * 更新部门
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("update")
    @PreAuthorize("@ss.hasPermission('system:dept:update')")
    public CommonResult<Boolean> updateDept(@Valid @RequestBody DeptSaveReqVO updateReqVO) {
        deptService.updateDept(updateReqVO);
        return success(true);
    }

    /**
     * 删除部门
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("delete")
    @PreAuthorize("@ss.hasPermission('system:dept:delete')")
    public CommonResult<Boolean> deleteDept(@RequestParam("id") Long id) {
        deptService.deleteDept(id);
        return success(true);
    }

    /**
     * 获取部门列表
     * 
     * @param reqVO
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('system:dept:query')")
    public CommonResult<List<DeptRespVO>> getDeptList(DeptListReqVO reqVO) {
        List<DeptDO> list = deptService.getDeptList(reqVO);
        return success(BeanUtils.toBean(list, DeptRespVO.class));
    }

    /**
     * 获取部门精简信息列表,只包含被开启的部门，主要用于前端的下拉选项
     * 
     * @return
     */
    @GetMapping(value = { "/list-all-simple", "/simple-list" })
    public CommonResult<List<DeptSimpleRespVO>> getSimpleDeptList() {
        List<DeptDO> list = deptService.getDeptList(new DeptListReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
        return success(BeanUtils.toBean(list, DeptSimpleRespVO.class));
    }

    /**
     * 获得部门信息
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:dept:query')")
    public CommonResult<DeptRespVO> getDept(@RequestParam("id") Long id) {
        DeptDO dept = deptService.getDept(id);
        return success(BeanUtils.toBean(dept, DeptRespVO.class));
    }

}
