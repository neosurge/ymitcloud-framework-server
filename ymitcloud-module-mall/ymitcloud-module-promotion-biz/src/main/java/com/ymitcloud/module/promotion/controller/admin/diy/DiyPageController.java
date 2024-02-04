package com.ymitcloud.module.promotion.controller.admin.diy;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

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
import com.ymitcloud.module.promotion.controller.admin.diy.vo.page.DiyPageCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.diy.vo.page.DiyPagePageReqVO;
import com.ymitcloud.module.promotion.controller.admin.diy.vo.page.DiyPagePropertyRespVO;
import com.ymitcloud.module.promotion.controller.admin.diy.vo.page.DiyPagePropertyUpdateRequestVO;
import com.ymitcloud.module.promotion.controller.admin.diy.vo.page.DiyPageRespVO;
import com.ymitcloud.module.promotion.controller.admin.diy.vo.page.DiyPageUpdateReqVO;
import com.ymitcloud.module.promotion.convert.diy.DiyPageConvert;
import com.ymitcloud.module.promotion.dal.dataobject.diy.DiyPageDO;
import com.ymitcloud.module.promotion.service.diy.DiyPageService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 装修页面
 */

@RestController
@RequestMapping("/promotion/diy-page")
@Validated
public class DiyPageController {

    @Resource
    private DiyPageService diyPageService;


    /**
     * 创建装修页面
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('promotion:diy-page:create')")
    public CommonResult<Long> createDiyPage(@Valid @RequestBody DiyPageCreateReqVO createReqVO) {
        return success(diyPageService.createDiyPage(createReqVO));
    }


    /**
     * 更新装修页面
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('promotion:diy-page:update')")
    public CommonResult<Boolean> updateDiyPage(@Valid @RequestBody DiyPageUpdateReqVO updateReqVO) {
        diyPageService.updateDiyPage(updateReqVO);
        return success(true);
    }


    /**
     * 删除装修页面
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:diy-page:delete')")
    public CommonResult<Boolean> deleteDiyPage(@RequestParam("id") Long id) {
        diyPageService.deleteDiyPage(id);
        return success(true);
    }


    /**
     * 获得装修页面
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('promotion:diy-page:query')")
    public CommonResult<DiyPageRespVO> getDiyPage(@RequestParam("id") Long id) {
        DiyPageDO diyPage = diyPageService.getDiyPage(id);
        return success(DiyPageConvert.INSTANCE.convert(diyPage));
    }


    /**
     * 获得装修页面列表
     * 
     * @param ids 编号列表
     * @return
     */
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('promotion:diy-page:query')")
    public CommonResult<List<DiyPageRespVO>> getDiyPageList(@RequestParam("ids") Collection<Long> ids) {
        List<DiyPageDO> list = diyPageService.getDiyPageList(ids);
        return success(DiyPageConvert.INSTANCE.convertList(list));
    }


    /**
     * 获得装修页面分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('promotion:diy-page:query')")
    public CommonResult<PageResult<DiyPageRespVO>> getDiyPagePage(@Valid DiyPagePageReqVO pageVO) {
        PageResult<DiyPageDO> pageResult = diyPageService.getDiyPagePage(pageVO);
        return success(DiyPageConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 获得装修页面属性
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get-property")

    @PreAuthorize("@ss.hasPermission('promotion:diy-page:query')")
    public CommonResult<DiyPagePropertyRespVO> getDiyPageProperty(@RequestParam("id") Long id) {
        DiyPageDO diyPage = diyPageService.getDiyPage(id);
        return success(DiyPageConvert.INSTANCE.convertPropertyVo(diyPage));
    }


    /**
     * 更新装修页面属性
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update-property")

    @PreAuthorize("@ss.hasPermission('promotion:diy-page:update')")
    public CommonResult<Boolean> updateDiyPageProperty(@Valid @RequestBody DiyPagePropertyUpdateRequestVO updateReqVO) {
        diyPageService.updateDiyPageProperty(updateReqVO);
        return success(true);
    }

}
