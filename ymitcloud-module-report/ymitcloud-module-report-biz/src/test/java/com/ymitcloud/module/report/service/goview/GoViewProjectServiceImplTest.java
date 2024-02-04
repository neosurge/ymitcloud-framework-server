package com.ymitcloud.module.report.service.goview;



import static com.ymitcloud.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static com.ymitcloud.framework.test.core.util.AssertUtils.assertPojoEquals;
import static com.ymitcloud.framework.test.core.util.AssertUtils.assertServiceException;
import static com.ymitcloud.module.report.enums.ErrorCodeConstants.GO_VIEW_PROJECT_NOT_EXISTS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.test.core.ut.BaseDbUnitTest;
import com.ymitcloud.framework.test.core.util.RandomUtils;
import com.ymitcloud.module.report.controller.admin.goview.vo.project.GoViewProjectCreateReqVO;
import com.ymitcloud.module.report.controller.admin.goview.vo.project.GoViewProjectUpdateReqVO;
import com.ymitcloud.module.report.dal.dataobject.goview.GoViewProjectDO;
import com.ymitcloud.module.report.dal.mysql.goview.GoViewProjectMapper;

import jakarta.annotation.Resource;


/**
 * {@link GoViewProjectServiceImpl} 的单元测试类
 *

 * @author 

 */
@Import(GoViewProjectServiceImpl.class)
public class GoViewProjectServiceImplTest extends BaseDbUnitTest {

    @Resource
    private GoViewProjectServiceImpl goViewProjectService;

    @Resource
    private GoViewProjectMapper goViewProjectMapper;

    @Test
    public void testCreateProject_success() {
        // 准备参数

        GoViewProjectCreateReqVO reqVO = RandomUtils.randomPojo(GoViewProjectCreateReqVO.class);


        // 调用
        Long goViewProjectId = goViewProjectService.createProject(reqVO);
        // 断言
        assertNotNull(goViewProjectId);
        // 校验记录的属性是否正确
        GoViewProjectDO goViewProject = goViewProjectMapper.selectById(goViewProjectId);
        assertPojoEquals(reqVO, goViewProject);
    }

    @Test
    public void testUpdateProject_success() {
        // mock 数据

        GoViewProjectDO dbGoViewProject = RandomUtils.randomPojo(GoViewProjectDO.class);
        goViewProjectMapper.insert(dbGoViewProject);// @Sql: 先插入出一条存在的数据
        // 准备参数
        GoViewProjectUpdateReqVO reqVO = RandomUtils.randomPojo(GoViewProjectUpdateReqVO.class, o -> {
            o.setId(dbGoViewProject.getId()); // 设置更新的 ID
            o.setStatus(RandomUtils.randomCommonStatus());

        });

        // 调用
        goViewProjectService.updateProject(reqVO);
        // 校验是否更新正确
        GoViewProjectDO goViewProject = goViewProjectMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, goViewProject);
    }

    @Test
    public void testUpdateProject_notExists() {
        // 准备参数

        GoViewProjectUpdateReqVO reqVO = RandomUtils.randomPojo(GoViewProjectUpdateReqVO.class);


        // 调用, 并断言异常
        assertServiceException(() -> goViewProjectService.updateProject(reqVO), GO_VIEW_PROJECT_NOT_EXISTS);
    }

    @Test
    public void testDeleteProject_success() {
        // mock 数据

        GoViewProjectDO dbGoViewProject = RandomUtils.randomPojo(GoViewProjectDO.class);

        goViewProjectMapper.insert(dbGoViewProject);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbGoViewProject.getId();

        // 调用
        goViewProjectService.deleteProject(id);
        // 校验数据不存在了
        assertNull(goViewProjectMapper.selectById(id));
    }

    @Test
    public void testDeleteProject_notExists() {
        // 准备参数

        Long id = RandomUtils.randomLongId();


        // 调用, 并断言异常
        assertServiceException(() -> goViewProjectService.deleteProject(id), GO_VIEW_PROJECT_NOT_EXISTS);
    }

    @Test
    public void testGetProject() {
        // mock 数据

        GoViewProjectDO dbGoViewProject = RandomUtils.randomPojo(GoViewProjectDO.class);

        goViewProjectMapper.insert(dbGoViewProject);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbGoViewProject.getId();

        // 调用
        GoViewProjectDO goViewProject = goViewProjectService.getProject(id);
        // 断言
        assertPojoEquals(dbGoViewProject, goViewProject);
    }

    @Test
    public void testGetMyGoViewProjectPage() {
        // mock 数据

        GoViewProjectDO dbGoViewProject = RandomUtils.randomPojo(GoViewProjectDO.class, o -> { // 等会查询到

            o.setCreator("1");
        });
        goViewProjectMapper.insert(dbGoViewProject);
        // 测试 userId 不匹配
        goViewProjectMapper.insert(cloneIgnoreId(dbGoViewProject, o -> o.setCreator("2")));
        // 准备参数
        PageParam reqVO = new PageParam();
        Long userId = 1L;

        // 调用
        PageResult<GoViewProjectDO> pageResult = goViewProjectService.getMyProjectPage(reqVO, userId);
        // 断言
        assertEquals(1, pageResult.getTotal());
        assertEquals(1, pageResult.getList().size());
        assertPojoEquals(dbGoViewProject, pageResult.getList().get(0));
    }

}