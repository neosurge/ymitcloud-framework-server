package com.ymit.module.system.controller.admin.notice;

import cn.hutool.core.lang.Assert;
import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.system.controller.admin.notice.vo.NoticePageReqVO;
import com.ymit.module.system.controller.admin.notice.vo.NoticeRespVO;
import com.ymit.module.system.controller.admin.notice.vo.NoticeSaveReqVO;
import com.ymit.module.system.dal.dataobject.notice.NoticeDO;
import com.ymit.module.system.service.notice.NoticeService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ymit.framework.common.data.CommonResult.success;

/**
 * 管理后台 - 通知公告 //TODO:待处理
 */
@RestController
@RequestMapping("/system/notice")
@Validated
public class NoticeController {

    @Resource
    private NoticeService noticeService;

//    @Resource
//    private WebSocketSenderApi webSocketSenderApi;

    /**
     * 创建通知公告
     *
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:notice:create')")
    public CommonResult<Long> createNotice(@Valid @RequestBody NoticeSaveReqVO createReqVO) {
        Long noticeId = this.noticeService.createNotice(createReqVO);
        return success(noticeId);
    }

    /**
     * 修改通知公告
     *
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:notice:update')")
    public CommonResult<Boolean> updateNotice(@Valid @RequestBody NoticeSaveReqVO updateReqVO) {
        this.noticeService.updateNotice(updateReqVO);
        return success(true);
    }

    /**
     * 删除通知公告
     *
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:notice:delete')")
    public CommonResult<Boolean> deleteNotice(@RequestParam("id") Long id) {
        this.noticeService.deleteNotice(id);
        return success(true);
    }

    /**
     * 获取通知公告列表
     *
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:notice:query')")
    public CommonResult<PageResult<NoticeRespVO>> getNoticePage(@Validated NoticePageReqVO pageReqVO) {
        PageResult<NoticeDO> pageResult = this.noticeService.getNoticePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, NoticeRespVO.class));
    }

    /**
     * 获得通知公告
     *
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:notice:query')")
    public CommonResult<NoticeRespVO> getNotice(@RequestParam("id") Long id) {
        NoticeDO notice = this.noticeService.getNotice(id);
        return success(BeanUtils.toBean(notice, NoticeRespVO.class));
    }

    /**
     * 推送通知公告
     *
     * @param id 编号
     * @return
     */
    @PostMapping("/push")
    @PreAuthorize("@ss.hasPermission('system:notice:update')")
    public CommonResult<Boolean> push(@RequestParam("id") Long id) {
        NoticeDO notice = this.noticeService.getNotice(id);
        Assert.notNull(notice, "公告不能为空");
        // 通过 websocket 推送给在线的用户 //TODO:待处理
//        webSocketSenderApi.sendObject(UserTypeEnum.ADMIN.getValue(), "notice-push", notice);
        return success(true);
    }

}
