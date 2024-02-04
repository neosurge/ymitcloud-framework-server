package com.ymitcloud.module.promotion.controller.app.bargain.vo.help;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户 App - 砍价助力的创建 Request VO
 */
@Data
public class AppBargainHelpCreateReqVO {

    /**
     * 砍价记录编号
     */

    @NotNull(message = "砍价记录编号不能为空")
    private Long recordId;

}
