package com.ymitcloud.module.member.controller.app.level;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.member.controller.app.level.vo.level.AppMemberLevelRespVO;
import com.ymitcloud.module.member.convert.level.MemberLevelConvert;
import com.ymitcloud.module.member.dal.dataobject.level.MemberLevelDO;
import com.ymitcloud.module.member.service.level.MemberLevelService;


import jakarta.annotation.Resource;

/**
 * 用户 App - 会员等级
 */

@RestController
@RequestMapping("/member/level")
@Validated
public class AppMemberLevelController {

    @Resource
    private MemberLevelService levelService;


    /**
     * 获得会员等级列表
     * 
     * @return
     */
    @GetMapping("/list")

    public CommonResult<List<AppMemberLevelRespVO>> getLevelList() {
        List<MemberLevelDO> result = levelService.getEnableLevelList();
        return success(MemberLevelConvert.INSTANCE.convertList02(result));
    }

}
