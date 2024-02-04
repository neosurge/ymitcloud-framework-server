package com.ymitcloud.module.promotion.controller.admin.bargain;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.member.api.user.MemberUserApi;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;
import com.ymitcloud.module.promotion.controller.admin.bargain.vo.help.BargainHelpPageReqVO;
import com.ymitcloud.module.promotion.controller.admin.bargain.vo.help.BargainHelpRespVO;
import com.ymitcloud.module.promotion.convert.bargain.BargainHelpConvert;
import com.ymitcloud.module.promotion.dal.dataobject.bargain.BargainHelpDO;
import com.ymitcloud.module.promotion.service.bargain.BargainHelpService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 砍价助力
 */

@RestController
@RequestMapping("/promotion/bargain-help")
@Validated
public class BargainHelpController {

    @Resource
    private BargainHelpService bargainHelpService;

    @Resource
    private MemberUserApi memberUserApi;


    /**
     * 获得砍价助力分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('promotion:bargain-help:query')")
    public CommonResult<PageResult<BargainHelpRespVO>> getBargainHelpPage(@Valid BargainHelpPageReqVO pageVO) {
        PageResult<BargainHelpDO> pageResult = bargainHelpService.getBargainHelpPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }

        // 拼接数据

        Map<Long, MemberUserRespDTO> userMap = memberUserApi
                .getUserMap(convertSet(pageResult.getList(), BargainHelpDO::getUserId));

        return success(BargainHelpConvert.INSTANCE.convertPage(pageResult, userMap));
    }

}
