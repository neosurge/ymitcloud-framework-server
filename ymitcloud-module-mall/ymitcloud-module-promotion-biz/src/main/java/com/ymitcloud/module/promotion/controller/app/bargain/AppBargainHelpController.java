package com.ymitcloud.module.promotion.controller.app.bargain;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.member.api.user.MemberUserApi;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;
import com.ymitcloud.module.promotion.controller.app.bargain.vo.help.AppBargainHelpCreateReqVO;
import com.ymitcloud.module.promotion.controller.app.bargain.vo.help.AppBargainHelpRespVO;
import com.ymitcloud.module.promotion.convert.bargain.BargainHelpConvert;
import com.ymitcloud.module.promotion.dal.dataobject.bargain.BargainHelpDO;
import com.ymitcloud.module.promotion.service.bargain.BargainHelpService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;

/**
 * 用户 App - 砍价助力
 */

@RestController
@RequestMapping("/promotion/bargain-help")
@Validated
public class AppBargainHelpController {

    @Resource
    private BargainHelpService bargainHelpService;

    @Resource
    private MemberUserApi memberUserApi;


    /**
     * 创建砍价助力
     * 
     * @param reqVO 给拼团记录砍一刀
     * @return
     */
    @PostMapping("/create")

    public CommonResult<Integer> createBargainHelp(@RequestBody AppBargainHelpCreateReqVO reqVO) {
        BargainHelpDO help = bargainHelpService.createBargainHelp(getLoginUserId(), reqVO);
        return success(help.getReducePrice());
    }


    /**
     * 获得砍价助力列表
     * 
     * @param recordId 砍价记录编号
     * @return
     */
    @GetMapping("/list")

    public CommonResult<List<AppBargainHelpRespVO>> getBargainHelpList(@RequestParam("recordId") Long recordId) {
        List<BargainHelpDO> helps = bargainHelpService.getBargainHelpListByRecordId(recordId);
        if (CollUtil.isEmpty(helps)) {
            return success(Collections.emptyList());
        }
        helps.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime())); // 倒序展示

        // 拼接数据

        Map<Long, MemberUserRespDTO> userMap = memberUserApi.getUserMap(convertSet(helps, BargainHelpDO::getUserId));

        return success(BargainHelpConvert.INSTANCE.convertList(helps, userMap));
    }

}
