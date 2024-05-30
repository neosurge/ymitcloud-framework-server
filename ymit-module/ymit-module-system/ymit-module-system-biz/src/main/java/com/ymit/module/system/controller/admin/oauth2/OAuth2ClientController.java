package com.ymit.module.system.controller.admin.oauth2;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.module.system.controller.admin.oauth2.vo.client.OAuth2ClientPageReqVO;
import com.ymit.module.system.controller.admin.oauth2.vo.client.OAuth2ClientRespVO;
import com.ymit.module.system.controller.admin.oauth2.vo.client.OAuth2ClientSaveReqVO;
import com.ymit.module.system.dal.dataobject.oauth2.OAuth2ClientDO;
import com.ymit.module.system.service.oauth2.OAuth2ClientService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ymit.framework.common.data.CommonResult.success;

/**
 * 管理后台 - OAuth2 客户端
 */
@RestController
@RequestMapping("/system/oauth2-client")
@Validated
public class OAuth2ClientController {

    @Resource
    private OAuth2ClientService oAuth2ClientService;

    /**
     * 创建 OAuth2 客户端
     *
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:oauth2-client:create')")
    public CommonResult<Long> createOAuth2Client(@Valid @RequestBody OAuth2ClientSaveReqVO createReqVO) {
        return success(this.oAuth2ClientService.createOAuth2Client(createReqVO));
    }

    /**
     * 更新 OAuth2 客户端
     *
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:oauth2-client:update')")
    public CommonResult<Boolean> updateOAuth2Client(@Valid @RequestBody OAuth2ClientSaveReqVO updateReqVO) {
        this.oAuth2ClientService.updateOAuth2Client(updateReqVO);
        return success(true);
    }

    /**
     * 删除 OAuth2 客户端
     *
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:oauth2-client:delete')")
    public CommonResult<Boolean> deleteOAuth2Client(@RequestParam("id") Long id) {
        this.oAuth2ClientService.deleteOAuth2Client(id);
        return success(true);
    }

    /**
     * 获得 OAuth2 客户端
     *
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:oauth2-client:query')")
    public CommonResult<OAuth2ClientRespVO> getOAuth2Client(@RequestParam("id") Long id) {
        OAuth2ClientDO client = this.oAuth2ClientService.getOAuth2Client(id);
        return success(BeanUtils.toBean(client, OAuth2ClientRespVO.class));
    }

    /**
     * 获得 OAuth2 客户端分页
     *
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:oauth2-client:query')")
    public CommonResult<PageResult<OAuth2ClientRespVO>> getOAuth2ClientPage(@Valid OAuth2ClientPageReqVO pageVO) {
        PageResult<OAuth2ClientDO> pageResult = this.oAuth2ClientService.getOAuth2ClientPage(pageVO);
        return success(BeanUtils.toBean(pageResult, OAuth2ClientRespVO.class));
    }

}
