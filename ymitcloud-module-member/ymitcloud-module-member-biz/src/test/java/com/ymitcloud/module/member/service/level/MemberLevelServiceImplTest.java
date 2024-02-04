package com.ymitcloud.module.member.service.level;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.util.collection.ArrayUtils;
import com.ymitcloud.framework.test.core.ut.BaseDbUnitTest;
import com.ymitcloud.framework.test.core.util.AssertUtils;

import com.ymitcloud.framework.test.core.util.RandomUtils;

import com.ymitcloud.module.member.controller.admin.level.vo.level.MemberLevelCreateReqVO;
import com.ymitcloud.module.member.controller.admin.level.vo.level.MemberLevelListReqVO;
import com.ymitcloud.module.member.controller.admin.level.vo.level.MemberLevelUpdateReqVO;
import com.ymitcloud.module.member.dal.dataobject.level.MemberLevelDO;
import com.ymitcloud.module.member.dal.mysql.level.MemberLevelMapper;
import com.ymitcloud.module.member.service.user.MemberUserService;
import com.ymitcloud.module.member.enums.ErrorCodeConstants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;

import static cn.hutool.core.util.RandomUtil.randomInt;
import static com.ymitcloud.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static com.ymitcloud.framework.test.core.util.AssertUtils.assertPojoEquals;
import static com.ymitcloud.framework.test.core.util.AssertUtils.assertServiceException;
import static org.junit.jupiter.api.Assertions.*;


// TODO 云码：完全 review 完，在去 review 单测

/**
 * {@link MemberLevelServiceImpl} 的单元测试类
 *
 * @author owen
 */
@Import(MemberLevelServiceImpl.class)
public class MemberLevelServiceImplTest extends BaseDbUnitTest {

    @Resource
    private MemberLevelServiceImpl levelService;

    @Resource
    private MemberLevelMapper memberlevelMapper;

    @MockBean
    private MemberLevelRecordService memberLevelRecordService;
    @MockBean
    private MemberExperienceRecordService memberExperienceRecordService;
    @MockBean
    private MemberUserService memberUserService;

    @Test
    public void testCreateLevel_success() {
        // 准备参数

        MemberLevelCreateReqVO reqVO = RandomUtils.randomPojo(MemberLevelCreateReqVO.class, o -> {
            o.setDiscountPercent(randomInt());
            o.setIcon(RandomUtils.randomURL());
            o.setBackgroundUrl(RandomUtils.randomURL());
            o.setStatus(RandomUtils.randomCommonStatus());

        });

        // 调用
        Long levelId = levelService.createLevel(reqVO);
        // 断言
        assertNotNull(levelId);
        // 校验记录的属性是否正确
        MemberLevelDO level = memberlevelMapper.selectById(levelId);
        assertPojoEquals(reqVO, level);
    }

    @Test
    public void testUpdateLevel_success() {
        // mock 数据

        MemberLevelDO dbLevel = RandomUtils.randomPojo(MemberLevelDO.class);
        memberlevelMapper.insert(dbLevel);// @Sql: 先插入出一条存在的数据
        // 准备参数
        MemberLevelUpdateReqVO reqVO = RandomUtils.randomPojo(MemberLevelUpdateReqVO.class, o -> {
            o.setId(dbLevel.getId()); // 设置更新的 ID
            // 以下要保持一致
            o.setName(dbLevel.getName());
            o.setLevel(dbLevel.getLevel());
            o.setExperience(dbLevel.getExperience());
            // 以下是要修改的字段
            o.setDiscountPercent(randomInt());
            o.setIcon(RandomUtils.randomURL());
            o.setBackgroundUrl(RandomUtils.randomURL());
            o.setStatus(RandomUtils.randomCommonStatus());

        });

        // 调用
        levelService.updateLevel(reqVO);
        // 校验是否更新正确
        MemberLevelDO level = memberlevelMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, level);
    }

    @Test
    public void testUpdateLevel_notExists() {
        // 准备参数

        MemberLevelUpdateReqVO reqVO = RandomUtils.randomPojo(MemberLevelUpdateReqVO.class);


        // 调用, 并断言异常
        assertServiceException(() -> levelService.updateLevel(reqVO), ErrorCodeConstants.LEVEL_NOT_EXISTS);
    }

    @Test
    public void testDeleteLevel_success() {
        // mock 数据

        MemberLevelDO dbLevel = RandomUtils.randomPojo(MemberLevelDO.class);

        memberlevelMapper.insert(dbLevel);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbLevel.getId();

        // 调用
        levelService.deleteLevel(id);
        // 校验数据不存在了
        assertNull(memberlevelMapper.selectById(id));
    }

    @Test
    public void testDeleteLevel_notExists() {
        // 准备参数

        Long id = RandomUtils.randomLongId();


        // 调用, 并断言异常
        assertServiceException(() -> levelService.deleteLevel(id), ErrorCodeConstants.LEVEL_NOT_EXISTS);
    }

    @Test
    public void testGetLevelList() {
        // mock 数据

        MemberLevelDO dbLevel = RandomUtils.randomPojo(MemberLevelDO.class, o -> { // 等会查询到

            o.setName("黄金会员");
            o.setStatus(1);
        });
        memberlevelMapper.insert(dbLevel);
        // 测试 name 不匹配
        memberlevelMapper.insert(cloneIgnoreId(dbLevel, o -> o.setName("")));
        // 测试 status 不匹配
        memberlevelMapper.insert(cloneIgnoreId(dbLevel, o -> o.setStatus(0)));
        // 准备参数
        MemberLevelListReqVO reqVO = new MemberLevelListReqVO();
        reqVO.setName("黄金会员");
        reqVO.setStatus(1);

        // 调用
        List<MemberLevelDO> list = levelService.getLevelList(reqVO);
        // 断言
        assertEquals(1, list.size());
        assertPojoEquals(dbLevel, list.get(0));
    }

    @Test
    public void testCreateLevel_nameUnique() {
        // 准备参数

        String name = RandomUtils.randomString();


        // mock 数据
        memberlevelMapper.insert(randomLevelDO(o -> o.setName(name)));

        // 调用，校验异常
        List<MemberLevelDO> list = memberlevelMapper.selectList();

        AssertUtils.assertServiceException(() -> levelService.validateNameUnique(list, null, name),
                ErrorCodeConstants.LEVEL_NAME_EXISTS, name);

    }

    @Test
    public void testUpdateLevel_nameUnique() {
        // 准备参数

        Long id = RandomUtils.randomLongId();
        String name = RandomUtils.randomString();


        // mock 数据
        memberlevelMapper.insert(randomLevelDO(o -> o.setName(name)));

        // 调用，校验异常
        List<MemberLevelDO> list = memberlevelMapper.selectList();

        AssertUtils.assertServiceException(() -> levelService.validateNameUnique(list, id, name),
                ErrorCodeConstants.LEVEL_NAME_EXISTS, name);

    }

    @Test
    public void testCreateLevel_levelUnique() {
        // 准备参数

        Integer level = RandomUtils.randomInteger();
        String name = RandomUtils.randomString();


        // mock 数据
        memberlevelMapper.insert(randomLevelDO(o -> {
            o.setLevel(level);
            o.setName(name);
        }));

        // 调用，校验异常
        List<MemberLevelDO> list = memberlevelMapper.selectList();

        assertServiceException(() -> levelService.validateLevelUnique(list, null, level),
                ErrorCodeConstants.LEVEL_VALUE_EXISTS, level, name);

    }

    @Test
    public void testUpdateLevel_levelUnique() {
        // 准备参数

        Long id = RandomUtils.randomLongId();
        Integer level = RandomUtils.randomInteger();
        String name = RandomUtils.randomString();


        // mock 数据
        memberlevelMapper.insert(randomLevelDO(o -> {
            o.setLevel(level);
            o.setName(name);
        }));

        // 调用，校验异常
        List<MemberLevelDO> list = memberlevelMapper.selectList();

        assertServiceException(() -> levelService.validateLevelUnique(list, id, level),
                ErrorCodeConstants.LEVEL_VALUE_EXISTS, level, name);

    }

    @Test
    public void testCreateLevel_experienceOutRange() {
        // 准备参数
        int level = 10;
        int experience = 10;

        String name = RandomUtils.randomString();


        // mock 数据
        memberlevelMapper.insert(randomLevelDO(o -> {
            o.setLevel(level);
            o.setExperience(experience);
            o.setName(name);
        }));
        List<MemberLevelDO> list = memberlevelMapper.selectList();

        // 调用，校验异常

        assertServiceException(() -> levelService.validateExperienceOutRange(list, null, level + 1, experience - 1),
                ErrorCodeConstants.LEVEL_EXPERIENCE_MIN, name, level);
        // 调用，校验异常
        assertServiceException(() -> levelService.validateExperienceOutRange(list, null, level - 1, experience + 1),
                ErrorCodeConstants.LEVEL_EXPERIENCE_MAX, name, level);

    }

    @Test
    public void testUpdateLevel_experienceOutRange() {
        // 准备参数
        int level = 10;
        int experience = 10;

        Long id = RandomUtils.randomLongId();
        String name = RandomUtils.randomString();


        // mock 数据
        memberlevelMapper.insert(randomLevelDO(o -> {
            o.setLevel(level);
            o.setExperience(experience);
            o.setName(name);
        }));
        List<MemberLevelDO> list = memberlevelMapper.selectList();

        // 调用，校验异常

        assertServiceException(() -> levelService.validateExperienceOutRange(list, id, level + 1, experience - 1),
                ErrorCodeConstants.LEVEL_EXPERIENCE_MIN, name, level);
        // 调用，校验异常
        assertServiceException(() -> levelService.validateExperienceOutRange(list, id, level - 1, experience + 1),
                ErrorCodeConstants.LEVEL_EXPERIENCE_MAX, name, level);

    }

    // ========== 随机对象 ==========

    @SafeVarargs
    private static MemberLevelDO randomLevelDO(Consumer<MemberLevelDO>... consumers) {
        Consumer<MemberLevelDO> consumer = (o) -> {
            o.setStatus(CommonStatusEnum.ENABLE.getStatus());
            o.setDiscountPercent(randomInt(0, 100));

            o.setIcon(RandomUtils.randomURL());
            o.setBackgroundUrl(RandomUtils.randomURL());
        };
        return RandomUtils.randomPojo(MemberLevelDO.class, ArrayUtils.append(consumer, consumers));

    }
}
