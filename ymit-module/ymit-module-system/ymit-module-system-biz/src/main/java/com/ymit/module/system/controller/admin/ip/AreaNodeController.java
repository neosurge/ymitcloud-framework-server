package com.ymit.module.system.controller.admin.ip;

import cn.hutool.core.lang.Assert;
import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.ip.core.Area;
import com.ymit.framework.ip.core.utils.AreaUtils;
import com.ymit.framework.ip.core.utils.IPUtils;
import com.ymit.module.system.controller.admin.ip.vo.AreaNodeRespVO;
import com.ymit.module.system.convert.ip.AreaNodeConvert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;

/**
 * 管理后台 - 地区
 */
@RestController
@RequestMapping("/system/ip/area-node")
@Validated
public class AreaNodeController {
    /**
     * 获得地区树
     *
     * @return
     */
    @GetMapping("/tree")
    public CommonResult<List<AreaNodeRespVO>> getAreaTree() {
        Area area = AreaUtils.getArea(Area.ID_CHINA);
        Assert.notNull(area, "获取不到中国");
        return success(AreaNodeConvert.convertList(area.getChildren()));
    }

    /**
     * 获得 IP 对应的地区名
     *
     * @param ip IP
     * @return
     */
    @GetMapping("/get-by-ip")
    public CommonResult<String> getAreaByIp(@RequestParam("ip") String ip) {
        // 获得城市
        Area area = IPUtils.getArea(ip);
        if (area == null) {
            return success("未知");
        }
        // 格式化返回
        return success(AreaUtils.format(area.getId()));
    }

}
