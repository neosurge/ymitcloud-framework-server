package com.ymitcloud.module.system.controller.admin.oauth2;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

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
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.system.controller.admin.oauth2.vo.client.OAuth2ClientPageReqVO;
import com.ymitcloud.module.system.controller.admin.oauth2.vo.client.OAuth2ClientRespVO;
import com.ymitcloud.module.system.controller.admin.oauth2.vo.client.OAuth2ClientSaveReqVO;
import com.ymitcloud.module.system.dal.dataobject.oauth2.OAuth2ClientDO;
import com.ymitcloud.module.system.service.oauth2.OAuth2ClientService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

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
        return success(oAuth2ClientService.createOAuth2Client(createReqVO));
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
        oAuth2ClientService.updateOAuth2Client(updateReqVO);
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
        oAuth2ClientService.deleteOAuth2Client(id);
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
        OAuth2ClientDO client = oAuth2ClientService.getOAuth2Client(id);
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
        PageResult<OAuth2ClientDO> pageResult = oAuth2ClientService.getOAuth2ClientPage(pageVO);
        return success(BeanUtils.toBean(pageResult, OAuth2ClientRespVO.class));
    }

}
