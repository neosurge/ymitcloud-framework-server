package com.ymitcloud.module.report.controller.admin.goview;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.report.controller.admin.goview.vo.data.GoViewDataGetBySqlReqVO;
import com.ymitcloud.module.report.controller.admin.goview.vo.data.GoViewDataRespVO;
import com.ymitcloud.module.report.service.goview.GoViewDataService;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - GoView 数据
 */

@RestController
@RequestMapping("/report/go-view/data")
@Validated
public class GoViewDataController {

    @Resource
    private GoViewDataService goViewDataService;


    /**
     * 使用 SQL 查询数据
     * 
     * @param reqVO
     * @return
     */
    @RequestMapping("/get-by-sql")

    @PreAuthorize("@ss.hasPermission('report:go-view-data:get-by-sql')")
    @OperateLog(enable = false) // 不记录操作日志，因为不需要
    public CommonResult<GoViewDataRespVO> getDataBySQL(@Valid @RequestBody GoViewDataGetBySqlReqVO reqVO) {
        return success(goViewDataService.getDataBySQL(reqVO.getSql()));
    }


    /**
     * 使用 HTTP 查询数据
     * 
     * @param params
     * @param body
     * @return
     */
    @RequestMapping("/get-by-http")
    @PreAuthorize("@ss.hasPermission('report:go-view-data:get-by-http')")
    @OperateLog(enable = false) // 不记录操作日志，因为不需要
    public CommonResult<GoViewDataRespVO> getDataByHttp(@RequestParam(required = false) Map<String, String> params,

            @RequestBody(required = false) String body) { // params、body 按照需要去接收，这里仅仅是示例
        GoViewDataRespVO respVO = new GoViewDataRespVO();
        // 1. 数据维度
        respVO.setDimensions(Arrays.asList("日期", "PV", "UV")); // PV 是每天访问次数；UV 是每天访问人数
        // 2. 明细数据列表
        // 目前通过随机的方式生成。一般来说，这里你可以写逻辑来实现数据的返回
        respVO.setSource(new LinkedList<>());
        for (int i = 1; i <= 12; i++) {
            String date = "2021-" + (i < 10 ? "0" + i : i);
            Integer pv = RandomUtil.randomInt(1000, 10000);
            Integer uv = RandomUtil.randomInt(100, 1000);

            respVO.getSource()
                    .add(MapUtil.<String, Object>builder().put("日期", date).put("PV", pv).put("UV", uv).build());

        }
        return success(respVO);
    }

}
