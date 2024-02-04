package com.ymitcloud.module.promotion.controller.admin.seckill;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

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

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.promotion.controller.admin.seckill.vo.config.SeckillConfigCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.seckill.vo.config.SeckillConfigPageReqVO;
import com.ymitcloud.module.promotion.controller.admin.seckill.vo.config.SeckillConfigRespVO;
import com.ymitcloud.module.promotion.controller.admin.seckill.vo.config.SeckillConfigSimpleRespVO;
import com.ymitcloud.module.promotion.controller.admin.seckill.vo.config.SeckillConfigUpdateReqVO;
import com.ymitcloud.module.promotion.controller.admin.seckill.vo.config.SeckillConfigUpdateStatusReqVo;
import com.ymitcloud.module.promotion.convert.seckill.seckillconfig.SeckillConfigConvert;
import com.ymitcloud.module.promotion.dal.dataobject.seckill.SeckillConfigDO;
import com.ymitcloud.module.promotion.service.seckill.SeckillConfigService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 秒杀时段
 */

@RestController
@RequestMapping("/promotion/seckill-config")
@Validated
public class SeckillConfigController {

    @Resource
    private SeckillConfigService seckillConfigService;


    /**
     * 创建秒杀时段
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('promotion:seckill-config:create')")
    public CommonResult<Long> createSeckillConfig(@Valid @RequestBody SeckillConfigCreateReqVO createReqVO) {
        return success(seckillConfigService.createSeckillConfig(createReqVO));
    }


    /**
     * 更新秒杀时段
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('promotion:seckill-config:update')")
    public CommonResult<Boolean> updateSeckillConfig(@Valid @RequestBody SeckillConfigUpdateReqVO updateReqVO) {
        seckillConfigService.updateSeckillConfig(updateReqVO);
        return success(true);
    }


    /**
     * 修改时段配置状态
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update-status")

    @PreAuthorize("@ss.hasPermission('system:seckill-config:update')")
    public CommonResult<Boolean> updateSeckillConfigStatus(@Valid @RequestBody SeckillConfigUpdateStatusReqVo reqVO) {
        seckillConfigService.updateSeckillConfigStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }


    /**
     * 删除秒杀时段
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:seckill-config:delete')")
    public CommonResult<Boolean> deleteSeckillConfig(@RequestParam("id") Long id) {
        seckillConfigService.deleteSeckillConfig(id);
        return success(true);
    }


    /**
     * 获得秒杀时段
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('promotion:seckill-config:query')")
    public CommonResult<SeckillConfigRespVO> getSeckillConfig(@RequestParam("id") Long id) {
        SeckillConfigDO seckillConfig = seckillConfigService.getSeckillConfig(id);
        return success(SeckillConfigConvert.INSTANCE.convert(seckillConfig));
    }


    /**
     * 获得所有秒杀时段列表
     * 
     * @return
     */
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('promotion:seckill-config:query')")
    public CommonResult<List<SeckillConfigRespVO>> getSeckillConfigList() {
        List<SeckillConfigDO> list = seckillConfigService.getSeckillConfigList();
        return success(SeckillConfigConvert.INSTANCE.convertList(list));
    }


    /**
     * 获得所有开启状态的秒杀时段精简列表
     * 
     * @return
     */
    @GetMapping("/list-all-simple")
    public CommonResult<List<SeckillConfigSimpleRespVO>> getListAllSimple() {
        List<SeckillConfigDO> list = seckillConfigService
                .getSeckillConfigListByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(SeckillConfigConvert.INSTANCE.convertList1(list));
    }

    /**
     * 获得秒杀时间段分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('promotion:seckill-config:query')")
    public CommonResult<PageResult<SeckillConfigRespVO>> getSeckillActivityPage(@Valid SeckillConfigPageReqVO pageVO) {
        PageResult<SeckillConfigDO> pageResult = seckillConfigService.getSeckillConfigPage(pageVO);
        return success(SeckillConfigConvert.INSTANCE.convertPage(pageResult));
    }

}
