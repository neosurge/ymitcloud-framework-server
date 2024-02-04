package com.ymitcloud.module.mp.controller.admin.news;


import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.MapUtils.findAndThen;
import static com.ymitcloud.module.mp.enums.ErrorCodeConstants.DRAFT_CREATE_FAIL;
import static com.ymitcloud.module.mp.enums.ErrorCodeConstants.DRAFT_DELETE_FAIL;
import static com.ymitcloud.module.mp.enums.ErrorCodeConstants.DRAFT_LIST_FAIL;
import static com.ymitcloud.module.mp.enums.ErrorCodeConstants.DRAFT_UPDATE_FAIL;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.ymitcloud.framework.common.util.collection.CollectionUtils;
import com.ymitcloud.framework.common.util.object.PageUtils;
import com.ymitcloud.module.mp.controller.admin.news.vo.MpDraftPageReqVO;
import com.ymitcloud.module.mp.dal.dataobject.material.MpMaterialDO;
import com.ymitcloud.module.mp.framework.mp.core.MpServiceFactory;
import com.ymitcloud.module.mp.service.material.MpMaterialService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.draft.WxMpAddDraft;
import me.chanjar.weixin.mp.bean.draft.WxMpDraftArticles;
import me.chanjar.weixin.mp.bean.draft.WxMpDraftItem;
import me.chanjar.weixin.mp.bean.draft.WxMpDraftList;
import me.chanjar.weixin.mp.bean.draft.WxMpUpdateDraft;

/**
 * 管理后台 - 公众号草稿
 */

@RestController
@RequestMapping("/mp/draft")
@Validated
public class MpDraftController {

    @Resource
    private MpServiceFactory mpServiceFactory;

    @Resource
    private MpMaterialService mpMaterialService;


    /**
     * 获得草稿分页
     * 
     * @param reqVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('mp:draft:query')")
    public CommonResult<PageResult<WxMpDraftItem>> getDraftPage(MpDraftPageReqVO reqVO) {
        // 从公众号查询草稿箱
        WxMpService mpService = mpServiceFactory.getRequiredMpService(reqVO.getAccountId());
        WxMpDraftList draftList;
        try {
            draftList = mpService.getDraftService().listDraft(PageUtils.getStart(reqVO), reqVO.getPageSize());
        } catch (WxErrorException e) {
            throw exception(DRAFT_LIST_FAIL, e.getError().getErrorMsg());
        }
        // 查询对应的图片地址。目的：解决公众号的图片链接无法在我们后台展示
        setDraftThumbUrl(draftList.getItems());

        // 返回分页
        return success(new PageResult<>(draftList.getItems(), draftList.getTotalCount().longValue()));
    }

    private void setDraftThumbUrl(List<WxMpDraftItem> items) {
        // 1.1 获得 mediaId 数组
        Set<String> mediaIds = new HashSet<>();

        items.forEach(
                item -> item.getContent().getNewsItem().forEach(newsItem -> mediaIds.add(newsItem.getThumbMediaId())));

        if (CollUtil.isEmpty(mediaIds)) {
            return;
        }
        // 1.2 批量查询对应的 Media 素材

        Map<String, MpMaterialDO> materials = CollectionUtils
                .convertMap(mpMaterialService.getMaterialListByMediaId(mediaIds), MpMaterialDO::getMediaId);

        // 2. 设置回 WxMpDraftItem 记录
        items.forEach(item -> item.getContent().getNewsItem().forEach(newsItem -> findAndThen(materials,
                newsItem.getThumbMediaId(), material -> newsItem.setThumbUrl(material.getUrl()))));
    }

    /**
     * 创建草稿
     * 
     * @param accountId 公众号账号的编号
     * @param draft
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('mp:draft:create')")
    public CommonResult<String> deleteDraft(@RequestParam("accountId") Long accountId,
            @RequestBody WxMpAddDraft draft) {

        WxMpService mpService = mpServiceFactory.getRequiredMpService(accountId);
        try {
            String mediaId = mpService.getDraftService().addDraft(draft);
            return success(mediaId);
        } catch (WxErrorException e) {
            throw exception(DRAFT_CREATE_FAIL, e.getError().getErrorMsg());
        }
    }


    /**
     * 更新草稿
     * 
     * @param accountId 公众号账号的编号
     * @param mediaId   草稿素材的编号
     * @param articles
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('mp:draft:update')")
    public CommonResult<Boolean> deleteDraft(@RequestParam("accountId") Long accountId,
            @RequestParam("mediaId") String mediaId, @RequestBody List<WxMpDraftArticles> articles) {

        WxMpService mpService = mpServiceFactory.getRequiredMpService(accountId);
        try {
            for (int i = 0; i < articles.size(); i++) {
                WxMpDraftArticles article = articles.get(i);
                mpService.getDraftService().updateDraft(new WxMpUpdateDraft(mediaId, i, article));
            }
            return success(true);
        } catch (WxErrorException e) {
            throw exception(DRAFT_UPDATE_FAIL, e.getError().getErrorMsg());
        }
    }


    /**
     * 删除草稿
     * 
     * @param accountId 公众号账号的编号
     * @param mediaId   草稿素材的编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('mp:draft:delete')")
    public CommonResult<Boolean> deleteDraft(@RequestParam("accountId") Long accountId,
            @RequestParam("mediaId") String mediaId) {

        WxMpService mpService = mpServiceFactory.getRequiredMpService(accountId);
        try {
            mpService.getDraftService().delDraft(mediaId);
            return success(true);
        } catch (WxErrorException e) {
            throw exception(DRAFT_DELETE_FAIL, e.getError().getErrorMsg());
        }
    }

}
