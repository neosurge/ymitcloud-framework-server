package com.ymitcloud.module.report.controller.admin.goview;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

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
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.report.controller.admin.goview.vo.project.GoViewProjectCreateReqVO;
import com.ymitcloud.module.report.controller.admin.goview.vo.project.GoViewProjectRespVO;
import com.ymitcloud.module.report.controller.admin.goview.vo.project.GoViewProjectUpdateReqVO;
import com.ymitcloud.module.report.convert.goview.GoViewProjectConvert;
import com.ymitcloud.module.report.dal.dataobject.goview.GoViewProjectDO;
import com.ymitcloud.module.report.service.goview.GoViewProjectService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - GoView 项目
 */

@RestController
@RequestMapping("/report/go-view/project")
@Validated
public class GoViewProjectController {

    @Resource
    private GoViewProjectService goViewProjectService;


    /**
     * 创建项目
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('report:go-view-project:create')")
    public CommonResult<Long> createProject(@Valid @RequestBody GoViewProjectCreateReqVO createReqVO) {
        return success(goViewProjectService.createProject(createReqVO));
    }


    /**
     * 更新项目
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('report:go-view-project:update')")
    public CommonResult<Boolean> updateProject(@Valid @RequestBody GoViewProjectUpdateReqVO updateReqVO) {
        goViewProjectService.updateProject(updateReqVO);
        return success(true);
    }


    /**
     * 删除 GoView 项目
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('report:go-view-project:delete')")
    public CommonResult<Boolean> deleteProject(@RequestParam("id") Long id) {
        goViewProjectService.deleteProject(id);
        return success(true);
    }


    /**
     * 获得项目
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('report:go-view-project:query')")
    public CommonResult<GoViewProjectRespVO> getProject(@RequestParam("id") Long id) {
        GoViewProjectDO project = goViewProjectService.getProject(id);
        return success(GoViewProjectConvert.INSTANCE.convert(project));
    }


    /**
     * 获得我的项目分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/my-page")
    @PreAuthorize("@ss.hasPermission('report:go-view-project:query')")
    public CommonResult<PageResult<GoViewProjectRespVO>> getMyProjectPage(@Valid PageParam pageVO) {
        PageResult<GoViewProjectDO> pageResult = goViewProjectService.getMyProjectPage(pageVO, getLoginUserId());

        return success(GoViewProjectConvert.INSTANCE.convertPage(pageResult));
    }

}
