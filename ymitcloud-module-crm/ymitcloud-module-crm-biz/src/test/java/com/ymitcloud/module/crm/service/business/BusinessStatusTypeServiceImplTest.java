package com.ymitcloud.module.crm.service.business;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.test.core.ut.BaseDbUnitTest;
import com.ymitcloud.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypePageReqVO;
import com.ymitcloud.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypeSaveReqVO;
import com.ymitcloud.module.crm.dal.dataobject.business.CrmBusinessStatusTypeDO;
import com.ymitcloud.module.crm.dal.mysql.business.CrmBusinessStatusTypeMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import jakarta.annotation.Resource;

import static com.ymitcloud.framework.test.core.util.AssertUtils.assertPojoEquals;
import static com.ymitcloud.framework.test.core.util.AssertUtils.assertServiceException;
import static com.ymitcloud.framework.test.core.util.RandomUtils.randomLongId;
import static com.ymitcloud.framework.test.core.util.RandomUtils.randomPojo;
import static com.ymitcloud.module.crm.enums.ErrorCodeConstants.BUSINESS_STATUS_TYPE_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;


// TODO 云码：后续再 review

/**
 * {@link CrmBusinessStatusTypeServiceImpl} 的单元测试类
 *
 * @author ljlleo
 */

@Disabled // TODO 云码：后续 fix 补充的单测

@Import(CrmBusinessStatusTypeServiceImpl.class)
public class BusinessStatusTypeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CrmBusinessStatusTypeServiceImpl businessStatusTypeService;

    @Resource
    private CrmBusinessStatusTypeMapper businessStatusTypeMapper;

    @Test
    public void testCreateBusinessStatusType_success() {
        // 准备参数
        CrmBusinessStatusTypeSaveReqVO createReqVO = randomPojo(CrmBusinessStatusTypeSaveReqVO.class).setId(null);

        // 调用
        Long businessStatusTypeId = businessStatusTypeService.createBusinessStatusType(createReqVO);
        // 断言
        assertNotNull(businessStatusTypeId);
        // 校验记录的属性是否正确
        CrmBusinessStatusTypeDO businessStatusType = businessStatusTypeMapper.selectById(businessStatusTypeId);
        assertPojoEquals(createReqVO, businessStatusType, "id");
    }

    @Test
    public void testUpdateBusinessStatusType_success() {
        // mock 数据
        CrmBusinessStatusTypeDO dbBusinessStatusType = randomPojo(CrmBusinessStatusTypeDO.class);
        businessStatusTypeMapper.insert(dbBusinessStatusType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CrmBusinessStatusTypeSaveReqVO updateReqVO = randomPojo(CrmBusinessStatusTypeSaveReqVO.class, o -> {
            o.setId(dbBusinessStatusType.getId()); // 设置更新的 ID
        });

        // 调用
        businessStatusTypeService.updateBusinessStatusType(updateReqVO);
        // 校验是否更新正确
        CrmBusinessStatusTypeDO businessStatusType = businessStatusTypeMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, businessStatusType);
    }

    @Test
    public void testUpdateBusinessStatusType_notExists() {
        // 准备参数
        CrmBusinessStatusTypeSaveReqVO updateReqVO = randomPojo(CrmBusinessStatusTypeSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> businessStatusTypeService.updateBusinessStatusType(updateReqVO), BUSINESS_STATUS_TYPE_NOT_EXISTS);
    }

    @Test
    public void testDeleteBusinessStatusType_success() {
        // mock 数据
        CrmBusinessStatusTypeDO dbBusinessStatusType = randomPojo(CrmBusinessStatusTypeDO.class);
        businessStatusTypeMapper.insert(dbBusinessStatusType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBusinessStatusType.getId();

        // 调用
        businessStatusTypeService.deleteBusinessStatusType(id);
       // 校验数据不存在了
       assertNull(businessStatusTypeMapper.selectById(id));
    }

    @Test
    public void testDeleteBusinessStatusType_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> businessStatusTypeService.deleteBusinessStatusType(id), BUSINESS_STATUS_TYPE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBusinessStatusTypePage() {
       // mock 数据
       CrmBusinessStatusTypeDO dbBusinessStatusType = randomPojo(CrmBusinessStatusTypeDO.class, o -> { // 等会查询到
       });
       businessStatusTypeMapper.insert(dbBusinessStatusType);
       // 准备参数
       CrmBusinessStatusTypePageReqVO reqVO = new CrmBusinessStatusTypePageReqVO();

       // 调用
       PageResult<CrmBusinessStatusTypeDO> pageResult = businessStatusTypeService.getBusinessStatusTypePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBusinessStatusType, pageResult.getList().get(0));
    }

}
