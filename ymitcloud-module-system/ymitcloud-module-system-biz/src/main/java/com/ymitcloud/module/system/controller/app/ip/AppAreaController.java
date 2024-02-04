package com.ymitcloud.module.system.controller.app.ip;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.ip.core.Area;
import com.ymitcloud.framework.ip.core.utils.AreaUtils;
import com.ymitcloud.module.system.controller.app.ip.vo.AppAreaNodeRespVO;
import com.ymitcloud.module.system.convert.ip.AreaConvert;

import cn.hutool.core.lang.Assert;

/**
 * 用户 App - 地区
 */
@RestController
@RequestMapping("/system/area")
@Validated
public class AppAreaController {
    /**
     * 获得地区树
     * 
     * @return
     */
    @GetMapping("/tree")
    public CommonResult<List<AppAreaNodeRespVO>> getAreaTree() {
        Area area = AreaUtils.getArea(Area.ID_CHINA);
        Assert.notNull(area, "获取不到中国");
        return success(AreaConvert.INSTANCE.convertList3(area.getChildren()));
    }

}
