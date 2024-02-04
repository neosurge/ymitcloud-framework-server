package com.ymitcloud.module.member.controller.admin.tag;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.Collection;
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
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.member.controller.admin.tag.vo.MemberTagCreateReqVO;
import com.ymitcloud.module.member.controller.admin.tag.vo.MemberTagPageReqVO;
import com.ymitcloud.module.member.controller.admin.tag.vo.MemberTagRespVO;
import com.ymitcloud.module.member.controller.admin.tag.vo.MemberTagUpdateReqVO;
import com.ymitcloud.module.member.convert.tag.MemberTagConvert;
import com.ymitcloud.module.member.dal.dataobject.tag.MemberTagDO;
import com.ymitcloud.module.member.service.tag.MemberTagService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 会员标签
 */
@RestController
@RequestMapping("/member/tag")
@Validated
public class MemberTagController {

    @Resource
    private MemberTagService tagService;

    /**
     * 创建会员标签
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('member:tag:create')")
    public CommonResult<Long> createTag(@Valid @RequestBody MemberTagCreateReqVO createReqVO) {
        return success(tagService.createTag(createReqVO));
    }

    /**
     * 更新会员标签
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('member:tag:update')")
    public CommonResult<Boolean> updateTag(@Valid @RequestBody MemberTagUpdateReqVO updateReqVO) {
        tagService.updateTag(updateReqVO);
        return success(true);
    }

    /**
     * 删除会员标签
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('member:tag:delete')")
    public CommonResult<Boolean> deleteTag(@RequestParam("id") Long id) {
        tagService.deleteTag(id);
        return success(true);
    }

    /**
     * 获得会员标签
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('member:tag:query')")
    public CommonResult<MemberTagRespVO> getMemberTag(@RequestParam("id") Long id) {
        MemberTagDO tag = tagService.getTag(id);
        return success(MemberTagConvert.INSTANCE.convert(tag));
    }

    /**
     * 获取会员标签精简信息列表
     * 
     * @return
     */
    @GetMapping("/list-all-simple")
    public CommonResult<List<MemberTagRespVO>> getSimpleTagList() {
        // 获用户列表，只要开启状态的
        List<MemberTagDO> list = tagService.getTagList();
        // 排序后，返回给前端
        return success(MemberTagConvert.INSTANCE.convertList(list));
    }

    /**
     * 获得会员标签列表
     * 
     * @param ids 编号列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('member:tag:query')")
    public CommonResult<List<MemberTagRespVO>> getMemberTagList(@RequestParam("ids") Collection<Long> ids) {
        List<MemberTagDO> list = tagService.getTagList(ids);
        return success(MemberTagConvert.INSTANCE.convertList(list));
    }

    /**
     * 获得会员标签分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('member:tag:query')")
    public CommonResult<PageResult<MemberTagRespVO>> getTagPage(@Valid MemberTagPageReqVO pageVO) {
        PageResult<MemberTagDO> pageResult = tagService.getTagPage(pageVO);
        return success(MemberTagConvert.INSTANCE.convertPage(pageResult));
    }

}
