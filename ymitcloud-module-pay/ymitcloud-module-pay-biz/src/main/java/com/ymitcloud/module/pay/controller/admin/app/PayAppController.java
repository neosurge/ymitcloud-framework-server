package com.ymitcloud.module.pay.controller.admin.app;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;

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
import com.ymitcloud.module.pay.controller.admin.app.vo.PayAppCreateReqVO;
import com.ymitcloud.module.pay.controller.admin.app.vo.PayAppPageItemRespVO;
import com.ymitcloud.module.pay.controller.admin.app.vo.PayAppPageReqVO;
import com.ymitcloud.module.pay.controller.admin.app.vo.PayAppRespVO;
import com.ymitcloud.module.pay.controller.admin.app.vo.PayAppUpdateReqVO;
import com.ymitcloud.module.pay.controller.admin.app.vo.PayAppUpdateStatusReqVO;

import com.ymitcloud.module.pay.convert.app.PayAppConvert;
import com.ymitcloud.module.pay.dal.dataobject.app.PayAppDO;
import com.ymitcloud.module.pay.dal.dataobject.channel.PayChannelDO;
import com.ymitcloud.module.pay.service.app.PayAppService;
import com.ymitcloud.module.pay.service.channel.PayChannelService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台 - 支付应用信息
 */
@Slf4j

@RestController
@RequestMapping("/pay/app")
@Validated
public class PayAppController {

    @Resource
    private PayAppService appService;
    @Resource
    private PayChannelService channelService;


    /**
     * 创建支付应用信息
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('pay:app:create')")
    public CommonResult<Long> createApp(@Valid @RequestBody PayAppCreateReqVO createReqVO) {
        return success(appService.createApp(createReqVO));
    }


    /**
     * 更新支付应用信息
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('pay:app:update')")
    public CommonResult<Boolean> updateApp(@Valid @RequestBody PayAppUpdateReqVO updateReqVO) {
        appService.updateApp(updateReqVO);
        return success(true);
    }


    /**
     * 更新支付应用状态
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update-status")

    @PreAuthorize("@ss.hasPermission('pay:app:update')")
    public CommonResult<Boolean> updateAppStatus(@Valid @RequestBody PayAppUpdateStatusReqVO updateReqVO) {
        appService.updateAppStatus(updateReqVO.getId(), updateReqVO.getStatus());
        return success(true);
    }


    /**
     * 删除支付应用信息
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('pay:app:delete')")
    public CommonResult<Boolean> deleteApp(@RequestParam("id") Long id) {
        appService.deleteApp(id);
        return success(true);
    }


    /**
     * 获得支付应用信息
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('pay:app:query')")
    public CommonResult<PayAppRespVO> getApp(@RequestParam("id") Long id) {
        PayAppDO app = appService.getApp(id);
        return success(PayAppConvert.INSTANCE.convert(app));
    }


    /**
     * 获得支付应用信息分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('pay:app:query')")
    public CommonResult<PageResult<PayAppPageItemRespVO>> getAppPage(@Valid PayAppPageReqVO pageVO) {
        // 得到应用分页列表
        PageResult<PayAppDO> pageResult = appService.getAppPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty());
        }

        // 得到所有的应用编号，查出所有的渠道
        Collection<Long> appIds = convertList(pageResult.getList(), PayAppDO::getId);
        List<PayChannelDO> channels = channelService.getChannelListByAppIds(appIds);

        // 拼接后返回
        return success(PayAppConvert.INSTANCE.convertPage(pageResult, channels));
    }


    /**
     * 获得应用列表
     * 
     * @return
     */
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('pay:merchant:query')")
    public CommonResult<List<PayAppRespVO>> getAppList() {
        List<PayAppDO> appListDO = appService.getAppList();
        return success(PayAppConvert.INSTANCE.convertList(appListDO));
    }

}
