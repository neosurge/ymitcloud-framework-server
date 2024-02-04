package com.ymitcloud.module.mp.controller.admin.news;


import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.MapUtils.findAndThen;
import static com.ymitcloud.module.mp.enums.ErrorCodeConstants.FREE_PUBLISH_DELETE_FAIL;
import static com.ymitcloud.module.mp.enums.ErrorCodeConstants.FREE_PUBLISH_LIST_FAIL;
import static com.ymitcloud.module.mp.enums.ErrorCodeConstants.FREE_PUBLISH_SUBMIT_FAIL;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.collection.CollectionUtils;
import com.ymitcloud.framework.common.util.object.PageUtils;
import com.ymitcloud.module.mp.controller.admin.news.vo.MpFreePublishPageReqVO;
import com.ymitcloud.module.mp.dal.dataobject.material.MpMaterialDO;
import com.ymitcloud.module.mp.framework.mp.core.MpServiceFactory;
import com.ymitcloud.module.mp.service.material.MpMaterialService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.freepublish.WxMpFreePublishItem;
import me.chanjar.weixin.mp.bean.freepublish.WxMpFreePublishList;


/**
 * 管理后台 - 公众号发布能力
 */

@RestController
@RequestMapping("/mp/free-publish")
@Validated
public class MpFreePublishController {

    @Resource
    private MpServiceFactory mpServiceFactory;

    @Resource
    private MpMaterialService mpMaterialService;


    /**
     * 获得已发布的图文分页
     * 
     * @param reqVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('mp:free-publish:query')")
    public CommonResult<PageResult<WxMpFreePublishItem>> getFreePublishPage(MpFreePublishPageReqVO reqVO) {
        // 从公众号查询已发布的图文列表
        WxMpService mpService = mpServiceFactory.getRequiredMpService(reqVO.getAccountId());
        WxMpFreePublishList publicationRecords;
        try {

            publicationRecords = mpService.getFreePublishService().getPublicationRecords(PageUtils.getStart(reqVO),
                    reqVO.getPageSize());

        } catch (WxErrorException e) {
            throw exception(FREE_PUBLISH_LIST_FAIL, e.getError().getErrorMsg());
        }
        // 查询对应的图片地址。目的：解决公众号的图片链接无法在我们后台展示
        setFreePublishThumbUrl(publicationRecords.getItems());

        // 返回分页
        return success(new PageResult<>(publicationRecords.getItems(), publicationRecords.getTotalCount().longValue()));
    }

    private void setFreePublishThumbUrl(List<WxMpFreePublishItem> items) {
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

        // 2. 设置回 WxMpFreePublishItem 记录
        items.forEach(item -> item.getContent().getNewsItem().forEach(newsItem -> findAndThen(materials,
                newsItem.getThumbMediaId(), material -> newsItem.setThumbUrl(material.getUrl()))));
    }

    /**
     * 发布草稿
     * 
     * @param accountId 公众号账号的编号
     * @param mediaId   要发布的草稿的 media_id
     * @return
     */
    @PostMapping("/submit")
    @PreAuthorize("@ss.hasPermission('mp:free-publish:submit')")
    public CommonResult<String> submitFreePublish(@RequestParam("accountId") Long accountId,
            @RequestParam("mediaId") String mediaId) {

        WxMpService mpService = mpServiceFactory.getRequiredMpService(accountId);
        try {
            String publishId = mpService.getFreePublishService().submit(mediaId);
            return success(publishId);
        } catch (WxErrorException e) {
            throw exception(FREE_PUBLISH_SUBMIT_FAIL, e.getError().getErrorMsg());
        }
    }


    /**
     * 删除草稿
     * 
     * @param accountId 公众号账号的编号
     * @param articleId 发布记录的编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('mp:free-publish:delete')")
    public CommonResult<Boolean> deleteFreePublish(@RequestParam("accountId") Long accountId,
            @RequestParam("articleId") String articleId) {

        WxMpService mpService = mpServiceFactory.getRequiredMpService(accountId);
        try {
            mpService.getFreePublishService().deletePushAllArticle(articleId);
            return success(true);
        } catch (WxErrorException e) {
            throw exception(FREE_PUBLISH_DELETE_FAIL, e.getError().getErrorMsg());
        }
    }

}
