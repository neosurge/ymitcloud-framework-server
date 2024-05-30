package com.ymit.module.system.controller.app.ip;

import cn.hutool.core.lang.Assert;
import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.ip.core.Area;
import com.ymit.framework.ip.core.utils.AreaUtils;
import com.ymit.module.system.controller.app.ip.vo.AppAreaNodeRespVO;
import com.ymit.module.system.convert.ip.AreaNodeConvert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;

/**
 * 用户 App - 地区
 */
@RestController
@RequestMapping("/system/area")
@Validated
public class AppAreaNodeController {
    /**
     * 获得地区树
     *
     * @return
     */
    @GetMapping("/tree")
    public CommonResult<List<AppAreaNodeRespVO>> getAreaTree() {
        Area area = AreaUtils.getArea(Area.ID_CHINA);
        Assert.notNull(area, "获取不到中国");
        return success(AreaNodeConvert.convertList3(area.getChildren()));
    }

}
