package com.ymitcloud.module.infra.controller.admin.demo.demo03;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
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
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.infra.controller.admin.demo.demo03.vo.Demo03StudentPageReqVO;
import com.ymitcloud.module.infra.controller.admin.demo.demo03.vo.Demo03StudentRespVO;
import com.ymitcloud.module.infra.controller.admin.demo.demo03.vo.Demo03StudentSaveReqVO;
import com.ymitcloud.module.infra.dal.dataobject.demo.demo03.Demo03CourseDO;
import com.ymitcloud.module.infra.dal.dataobject.demo.demo03.Demo03GradeDO;
import com.ymitcloud.module.infra.dal.dataobject.demo.demo03.Demo03StudentDO;
import com.ymitcloud.module.infra.service.demo.demo03.Demo03StudentService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 学生
 */
@RestController
@RequestMapping("/infra/demo03-student")
@Validated
public class Demo03StudentController {

    @Resource
    private Demo03StudentService demo03StudentService;

    /**
     * 创建学生
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:create')")
    public CommonResult<Long> createDemo03Student(@Valid @RequestBody Demo03StudentSaveReqVO createReqVO) {
        return success(demo03StudentService.createDemo03Student(createReqVO));
    }

    /**
     * 更新学生
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:update')")
    public CommonResult<Boolean> updateDemo03Student(@Valid @RequestBody Demo03StudentSaveReqVO updateReqVO) {
        demo03StudentService.updateDemo03Student(updateReqVO);
        return success(true);
    }

    /**
     * 删除学生
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:delete')")
    public CommonResult<Boolean> deleteDemo03Student(@RequestParam("id") Long id) {
        demo03StudentService.deleteDemo03Student(id);
        return success(true);
    }

    /**
     * 获得学生
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:query')")
    public CommonResult<Demo03StudentRespVO> getDemo03Student(@RequestParam("id") Long id) {
        Demo03StudentDO demo03Student = demo03StudentService.getDemo03Student(id);
        return success(BeanUtils.toBean(demo03Student, Demo03StudentRespVO.class));
    }

    /**
     * 获得学生分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:query')")
    public CommonResult<PageResult<Demo03StudentRespVO>> getDemo03StudentPage(@Valid Demo03StudentPageReqVO pageReqVO) {
        PageResult<Demo03StudentDO> pageResult = demo03StudentService.getDemo03StudentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, Demo03StudentRespVO.class));
    }

    /**
     * 导出学生 Excel
     * 
     * @param pageReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:export')")
    @OperateLog(type = EXPORT)
    public void exportDemo03StudentExcel(@Valid Demo03StudentPageReqVO pageReqVO, HttpServletResponse response)
            throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<Demo03StudentDO> list = demo03StudentService.getDemo03StudentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "学生.xls", "数据", Demo03StudentRespVO.class,
                BeanUtils.toBean(list, Demo03StudentRespVO.class));
    }

    // ==================== 子表（学生课程） ====================

    /**
     * 获得学生课程分页
     * 
     * @param pageReqVO
     * @param studentId 学生编号
     * @return
     */
    @GetMapping("/demo03-course/page")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:query')")
    public CommonResult<PageResult<Demo03CourseDO>> getDemo03CoursePage(PageParam pageReqVO,
            @RequestParam("studentId") Long studentId) {
        return success(demo03StudentService.getDemo03CoursePage(pageReqVO, studentId));
    }

    /**
     * 创建学生课程
     * 
     * @param demo03Course
     * @return
     */
    @PostMapping("/demo03-course/create")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:create')")
    public CommonResult<Long> createDemo03Course(@Valid @RequestBody Demo03CourseDO demo03Course) {
        return success(demo03StudentService.createDemo03Course(demo03Course));
    }

    /**
     * 更新学生课程
     * 
     * @param demo03Course
     * @return
     */
    @PutMapping("/demo03-course/update")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:update')")
    public CommonResult<Boolean> updateDemo03Course(@Valid @RequestBody Demo03CourseDO demo03Course) {
        demo03StudentService.updateDemo03Course(demo03Course);
        return success(true);
    }

    /**
     * 删除学生课程
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/demo03-course/delete")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:delete')")
    public CommonResult<Boolean> deleteDemo03Course(@RequestParam("id") Long id) {
        demo03StudentService.deleteDemo03Course(id);
        return success(true);
    }

    /**
     * 获得学生课程
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/demo03-course/get")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:query')")
    public CommonResult<Demo03CourseDO> getDemo03Course(@RequestParam("id") Long id) {
        return success(demo03StudentService.getDemo03Course(id));
    }

    /**
     * 获得学生课程列表
     * 
     * @param studentId 学生编号
     * @return
     */
    @GetMapping("/demo03-course/list-by-student-id")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:query')")
    public CommonResult<List<Demo03CourseDO>> getDemo03CourseListByStudentId(
            @RequestParam("studentId") Long studentId) {
        return success(demo03StudentService.getDemo03CourseListByStudentId(studentId));
    }

    // ==================== 子表（学生班级） ====================

    /**
     * 获得学生班级分页
     * 
     * @param pageReqVO
     * @param studentId 学生编号
     * @return
     */
    @GetMapping("/demo03-grade/page")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:query')")
    public CommonResult<PageResult<Demo03GradeDO>> getDemo03GradePage(PageParam pageReqVO,
            @RequestParam("studentId") Long studentId) {
        return success(demo03StudentService.getDemo03GradePage(pageReqVO, studentId));
    }

    /**
     * 创建学生班级
     * 
     * @param demo03Grade
     * @return
     */
    @PostMapping("/demo03-grade/create")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:create')")
    public CommonResult<Long> createDemo03Grade(@Valid @RequestBody Demo03GradeDO demo03Grade) {
        return success(demo03StudentService.createDemo03Grade(demo03Grade));
    }

    /**
     * 更新学生班级
     * 
     * @param demo03Grade
     * @return
     */
    @PutMapping("/demo03-grade/update")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:update')")
    public CommonResult<Boolean> updateDemo03Grade(@Valid @RequestBody Demo03GradeDO demo03Grade) {
        demo03StudentService.updateDemo03Grade(demo03Grade);
        return success(true);
    }

    /**
     * 删除学生班级
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/demo03-grade/delete")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:delete')")
    public CommonResult<Boolean> deleteDemo03Grade(@RequestParam("id") Long id) {
        demo03StudentService.deleteDemo03Grade(id);
        return success(true);
    }

    /**
     * 获得学生班级
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/demo03-grade/get")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:query')")
    public CommonResult<Demo03GradeDO> getDemo03Grade(@RequestParam("id") Long id) {
        return success(demo03StudentService.getDemo03Grade(id));
    }

    /**
     * 获得学生班级
     * 
     * @param studentId 学生编号
     * @return
     */
    @GetMapping("/demo03-grade/get-by-student-id")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:query')")
    public CommonResult<Demo03GradeDO> getDemo03GradeByStudentId(@RequestParam("studentId") Long studentId) {
        return success(demo03StudentService.getDemo03GradeByStudentId(studentId));
    }

}