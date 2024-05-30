package com.ymit.module.system.controller.admin.notify;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.enums.UserTypeEnum;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.system.controller.admin.notify.vo.message.NotifyMessageMyPageReqVO;
import com.ymit.module.system.controller.admin.notify.vo.message.NotifyMessagePageReqVO;
import com.ymit.module.system.controller.admin.notify.vo.message.NotifyMessageRespVO;
import com.ymit.module.system.dal.dataobject.notify.NotifyMessageDO;
import com.ymit.module.system.service.notify.NotifyMessageService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 管理后台 - 我的站内信
 */
@RestController
@RequestMapping("/system/notify-message")
@Validated
public class NotifyMessageController {

    @Resource
    private NotifyMessageService notifyMessageService;

    // ========== 管理所有的站内信 ==========

    /**
     * 获得站内信
     *
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:notify-message:query')")
    public CommonResult<NotifyMessageRespVO> getNotifyMessage(@RequestParam("id") Long id) {
        NotifyMessageDO message = notifyMessageService.getNotifyMessage(id);
        return success(BeanUtils.toBean(message, NotifyMessageRespVO.class));
    }

    /**
     * 获得站内信分页
     *
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:notify-message:query')")
    public CommonResult<PageResult<NotifyMessageRespVO>> getNotifyMessagePage(@Valid NotifyMessagePageReqVO pageVO) {
        PageResult<NotifyMessageDO> pageResult = notifyMessageService.getNotifyMessagePage(pageVO);
        return success(BeanUtils.toBean(pageResult, NotifyMessageRespVO.class));
    }

    // ========== 查看自己的站内信 ==========

    /**
     * 获得我的站内信分页
     *
     * @param pageVO
     * @return
     */
    @GetMapping("/my-page")
    public CommonResult<PageResult<NotifyMessageRespVO>> getMyMyNotifyMessagePage(
            @Valid NotifyMessageMyPageReqVO pageVO) {
        PageResult<NotifyMessageDO> pageResult = notifyMessageService.getMyMyNotifyMessagePage(pageVO, getLoginUserId(),
                UserTypeEnum.ADMIN.getValue());
        return success(BeanUtils.toBean(pageResult, NotifyMessageRespVO.class));
    }

    /**
     * 标记站内信为已读
     *
     * @param ids 编号列表
     * @return
     */
    @PutMapping("/update-read")
    public CommonResult<Boolean> updateNotifyMessageRead(@RequestParam("ids") List<Long> ids) {
        notifyMessageService.updateNotifyMessageRead(ids, getLoginUserId(), UserTypeEnum.ADMIN.getValue());
        return success(Boolean.TRUE);
    }

    /**
     * 标记所有站内信为已读
     *
     * @return
     */
    @PutMapping("/update-all-read")
    public CommonResult<Boolean> updateAllNotifyMessageRead() {
        notifyMessageService.updateAllNotifyMessageRead(getLoginUserId(), UserTypeEnum.ADMIN.getValue());
        return success(Boolean.TRUE);
    }

    /**
     * 获取当前用户的最新站内信列表，默认 10 条
     *
     * @param size 长度
     * @return
     */
    @GetMapping("/get-unread-list")
    public CommonResult<List<NotifyMessageRespVO>> getUnreadNotifyMessageList(
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        List<NotifyMessageDO> list = notifyMessageService.getUnreadNotifyMessageList(getLoginUserId(),
                UserTypeEnum.ADMIN.getValue(), size);
        return success(BeanUtils.toBean(list, NotifyMessageRespVO.class));
    }

    /**
     * 获得当前用户的未读站内信数量
     *
     * @return
     */
    @GetMapping("/get-unread-count")
    public CommonResult<Long> getUnreadNotifyMessageCount() {
        return success(
                notifyMessageService.getUnreadNotifyMessageCount(getLoginUserId(), UserTypeEnum.ADMIN.getValue()));
    }

}
