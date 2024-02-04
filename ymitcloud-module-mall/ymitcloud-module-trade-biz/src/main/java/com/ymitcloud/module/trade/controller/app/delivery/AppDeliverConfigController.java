package com.ymitcloud.module.trade.controller.app.delivery;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.trade.controller.app.delivery.vo.config.AppDeliveryConfigRespVO;

/**
 * 用户 App - 配送配置
 */

@RestController
@RequestMapping("/trade/delivery/config")
@Validated
public class AppDeliverConfigController {


    /**
     * 获得配送配置
     * 
     * @return
     */
    // TODO @云码：这里后面干掉，合并到 AppTradeConfigController 中
    @GetMapping("/get")

    public CommonResult<AppDeliveryConfigRespVO> getDeliveryConfig() {
        return success(new AppDeliveryConfigRespVO().setPickUpEnable(true).setTencentLbsKey("123456"));
    }

}
