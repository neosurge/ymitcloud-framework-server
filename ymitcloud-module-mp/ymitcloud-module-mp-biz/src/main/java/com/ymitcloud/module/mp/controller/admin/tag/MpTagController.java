package com.ymitcloud.module.mp.controller.admin.tag;


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
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.mp.controller.admin.tag.vo.MpTagCreateReqVO;
import com.ymitcloud.module.mp.controller.admin.tag.vo.MpTagPageReqVO;
import com.ymitcloud.module.mp.controller.admin.tag.vo.MpTagRespVO;
import com.ymitcloud.module.mp.controller.admin.tag.vo.MpTagSimpleRespVO;
import com.ymitcloud.module.mp.controller.admin.tag.vo.MpTagUpdateReqVO;
import com.ymitcloud.module.mp.convert.tag.MpTagConvert;
import com.ymitcloud.module.mp.dal.dataobject.tag.MpTagDO;
import com.ymitcloud.module.mp.service.tag.MpTagService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 公众号标签
 */

@RestController
@RequestMapping("/mp/tag")
@Validated
public class MpTagController {

    @Resource
    private MpTagService mpTagService;


    /**
     * 创建公众号标签
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('mp:tag:create')")
    public CommonResult<Long> createTag(@Valid @RequestBody MpTagCreateReqVO createReqVO) {
        return success(mpTagService.createTag(createReqVO));
    }


    /**
     * 更新公众号标签
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('mp:tag:update')")
    public CommonResult<Boolean> updateTag(@Valid @RequestBody MpTagUpdateReqVO updateReqVO) {
        mpTagService.updateTag(updateReqVO);
        return success(true);
    }


    /**
     * 删除公众号标签
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('mp:tag:delete')")
    public CommonResult<Boolean> deleteTag(@RequestParam("id") Long id) {
        mpTagService.deleteTag(id);
        return success(true);
    }


    /**
     * 获取公众号标签详情
     * 
     * @param id
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('mp:tag:query')")
    public CommonResult<MpTagRespVO> get(@RequestParam("id") Long id) {
        MpTagDO mpTagDO = mpTagService.get(id);
        return success(MpTagConvert.INSTANCE.convert(mpTagDO));
    }


    /**
     * 获取公众号标签分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('mp:tag:query')")
    public CommonResult<PageResult<MpTagRespVO>> getTagPage(MpTagPageReqVO pageReqVO) {
        PageResult<MpTagDO> pageResult = mpTagService.getTagPage(pageReqVO);
        return success(MpTagConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 获取公众号账号精简信息列表
     * 
     * @return
     */
    @GetMapping("/list-all-simple")

    @PreAuthorize("@ss.hasPermission('mp:account:query')")
    public CommonResult<List<MpTagSimpleRespVO>> getSimpleTags() {
        List<MpTagDO> list = mpTagService.getTagList();
        return success(MpTagConvert.INSTANCE.convertList02(list));
    }


    /**
     * 同步公众号标签
     * 
     * @param accountId 公众号账号的编号
     * @return
     */
    @PostMapping("/sync")

    @PreAuthorize("@ss.hasPermission('mp:tag:sync')")
    public CommonResult<Boolean> syncTag(@RequestParam("accountId") Long accountId) {
        mpTagService.syncTag(accountId);
        return success(true);
    }

}
