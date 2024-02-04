package com.ymitcloud.module.member.controller.admin.level;

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

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.member.controller.admin.level.vo.level.MemberLevelCreateReqVO;
import com.ymitcloud.module.member.controller.admin.level.vo.level.MemberLevelListReqVO;
import com.ymitcloud.module.member.controller.admin.level.vo.level.MemberLevelRespVO;
import com.ymitcloud.module.member.controller.admin.level.vo.level.MemberLevelSimpleRespVO;
import com.ymitcloud.module.member.controller.admin.level.vo.level.MemberLevelUpdateReqVO;
import com.ymitcloud.module.member.convert.level.MemberLevelConvert;
import com.ymitcloud.module.member.dal.dataobject.level.MemberLevelDO;
import com.ymitcloud.module.member.service.level.MemberLevelService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 会员等级
 */
@RestController
@RequestMapping("/member/level")
@Validated
public class MemberLevelController {

    @Resource
    private MemberLevelService levelService;

    /**
     * 创建会员等级
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('member:level:create')")
    public CommonResult<Long> createLevel(@Valid @RequestBody MemberLevelCreateReqVO createReqVO) {
        return success(levelService.createLevel(createReqVO));
    }

    /**
     * 更新会员等级
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('member:level:update')")
    public CommonResult<Boolean> updateLevel(@Valid @RequestBody MemberLevelUpdateReqVO updateReqVO) {
        levelService.updateLevel(updateReqVO);
        return success(true);
    }

    /**
     * 删除会员等级
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('member:level:delete')")
    public CommonResult<Boolean> deleteLevel(@RequestParam("id") Long id) {
        levelService.deleteLevel(id);
        return success(true);
    }

    /**
     * 获得会员等级
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('member:level:query')")
    public CommonResult<MemberLevelRespVO> getLevel(@RequestParam("id") Long id) {
        MemberLevelDO level = levelService.getLevel(id);
        return success(MemberLevelConvert.INSTANCE.convert(level));
    }

    /**
     * 获取会员等级精简信息列表
     * 
     * @return
     */
    @GetMapping("/list-all-simple")
    public CommonResult<List<MemberLevelSimpleRespVO>> getSimpleLevelList() {
        // 获用户列表，只要开启状态的
        List<MemberLevelDO> list = levelService.getEnableLevelList();
        // 排序后，返回给前端
        return success(MemberLevelConvert.INSTANCE.convertSimpleList(list));
    }

    /**
     * 获得会员等级列表
     * 
     * @param listReqVO
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('member:level:query')")
    public CommonResult<List<MemberLevelRespVO>> getLevelList(@Valid MemberLevelListReqVO listReqVO) {
        List<MemberLevelDO> result = levelService.getLevelList(listReqVO);
        return success(MemberLevelConvert.INSTANCE.convertList(result));
    }

}
