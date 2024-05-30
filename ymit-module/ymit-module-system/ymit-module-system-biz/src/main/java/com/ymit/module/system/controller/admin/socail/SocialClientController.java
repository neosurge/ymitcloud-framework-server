package com.ymit.module.system.controller.admin.socail;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.system.controller.admin.socail.vo.client.SocialClientPageReqVO;
import com.ymit.module.system.controller.admin.socail.vo.client.SocialClientRespVO;
import com.ymit.module.system.controller.admin.socail.vo.client.SocialClientSaveReqVO;
import com.ymit.module.system.dal.dataobject.social.SocialClientDO;
import com.ymit.module.system.service.social.SocialClientService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ymit.framework.common.data.CommonResult.success;

/**
 * 管理后台 - 社交客户端
 */
@RestController
@RequestMapping("/system/social-client")
@Validated
public class SocialClientController {

    @Resource
    private SocialClientService socialClientService;

    /**
     * 创建社交客户端
     *
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:social-client:create')")
    public CommonResult<Long> createSocialClient(@Valid @RequestBody SocialClientSaveReqVO createReqVO) {
        return success(this.socialClientService.createSocialClient(createReqVO));
    }

    /**
     * 更新社交客户端
     *
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:social-client:update')")
    public CommonResult<Boolean> updateSocialClient(@Valid @RequestBody SocialClientSaveReqVO updateReqVO) {
        this.socialClientService.updateSocialClient(updateReqVO);
        return success(true);
    }

    /**
     * 删除社交客户端
     *
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:social-client:delete')")
    public CommonResult<Boolean> deleteSocialClient(@RequestParam("id") Long id) {
        this.socialClientService.deleteSocialClient(id);
        return success(true);
    }

    /**
     * 获得社交客户端
     *
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:social-client:query')")
    public CommonResult<SocialClientRespVO> getSocialClient(@RequestParam("id") Long id) {
        SocialClientDO client = this.socialClientService.getSocialClient(id);
        return success(BeanUtils.toBean(client, SocialClientRespVO.class));
    }

    /**
     * 获得社交客户端分页
     *
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:social-client:query')")
    public CommonResult<PageResult<SocialClientRespVO>> getSocialClientPage(@Valid SocialClientPageReqVO pageVO) {
        PageResult<SocialClientDO> pageResult = this.socialClientService.getSocialClientPage(pageVO);
        return success(BeanUtils.toBean(pageResult, SocialClientRespVO.class));
    }

}
