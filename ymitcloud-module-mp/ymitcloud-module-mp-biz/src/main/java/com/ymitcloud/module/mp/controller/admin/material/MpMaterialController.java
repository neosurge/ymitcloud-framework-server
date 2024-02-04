package com.ymitcloud.module.mp.controller.admin.material;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.io.IOException;

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
import com.ymitcloud.module.mp.controller.admin.material.vo.MpMaterialPageReqVO;
import com.ymitcloud.module.mp.controller.admin.material.vo.MpMaterialRespVO;
import com.ymitcloud.module.mp.controller.admin.material.vo.MpMaterialUploadNewsImageReqVO;
import com.ymitcloud.module.mp.controller.admin.material.vo.MpMaterialUploadPermanentReqVO;
import com.ymitcloud.module.mp.controller.admin.material.vo.MpMaterialUploadRespVO;
import com.ymitcloud.module.mp.controller.admin.material.vo.MpMaterialUploadTemporaryReqVO;
import com.ymitcloud.module.mp.convert.material.MpMaterialConvert;
import com.ymitcloud.module.mp.dal.dataobject.material.MpMaterialDO;
import com.ymitcloud.module.mp.service.material.MpMaterialService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 公众号素材
 */

@RestController
@RequestMapping("/mp/material")
@Validated
public class MpMaterialController {

    @Resource
    private MpMaterialService mpMaterialService;


    /**
     * 上传临时素材
     * 
     * @param reqVO
     * @return
     * @throws IOException
     */
    @PostMapping("/upload-temporary")
    @PreAuthorize("@ss.hasPermission('mp:material:upload-temporary')")
    public CommonResult<MpMaterialUploadRespVO> uploadTemporaryMaterial(@Valid MpMaterialUploadTemporaryReqVO reqVO)
            throws IOException {

        MpMaterialDO material = mpMaterialService.uploadTemporaryMaterial(reqVO);
        return success(MpMaterialConvert.INSTANCE.convert(material));
    }


    /**
     * 上传永久素材
     * 
     * @param reqVO
     * @return
     * @throws IOException
     */
    @PostMapping("/upload-permanent")
    @PreAuthorize("@ss.hasPermission('mp:material:upload-permanent')")
    public CommonResult<MpMaterialUploadRespVO> uploadPermanentMaterial(@Valid MpMaterialUploadPermanentReqVO reqVO)
            throws IOException {

        MpMaterialDO material = mpMaterialService.uploadPermanentMaterial(reqVO);
        return success(MpMaterialConvert.INSTANCE.convert(material));
    }


    /**
     * 删除素材
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete-permanent")

    @PreAuthorize("@ss.hasPermission('mp:material:delete')")
    public CommonResult<Boolean> deleteMaterial(@RequestParam("id") Long id) {
        mpMaterialService.deleteMaterial(id);
        return success(true);
    }


    /**
     * 上传图文内容中的图片
     * 
     * @param reqVO
     * @return
     * @throws IOException
     */
    @PostMapping("/upload-news-image")
    @PreAuthorize("@ss.hasPermission('mp:material:upload-news-image')")
    public CommonResult<String> uploadNewsImage(@Valid MpMaterialUploadNewsImageReqVO reqVO) throws IOException {
        return success(mpMaterialService.uploadNewsImage(reqVO));
    }

    /**
     * 获得素材分页
     * 
     * @param pageReqVO
     * @return
     */

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('mp:material:query')")
    public CommonResult<PageResult<MpMaterialRespVO>> getMaterialPage(@Valid MpMaterialPageReqVO pageReqVO) {
        PageResult<MpMaterialDO> pageResult = mpMaterialService.getMaterialPage(pageReqVO);
        return success(MpMaterialConvert.INSTANCE.convertPage(pageResult));
    }

}
