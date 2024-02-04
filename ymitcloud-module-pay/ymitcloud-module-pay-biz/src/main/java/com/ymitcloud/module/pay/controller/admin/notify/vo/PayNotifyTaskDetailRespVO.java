
package com.ymitcloud.module.pay.controller.admin.notify.vo;




import java.time.LocalDateTime;
import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 管理后台 - 回调通知的明细 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayNotifyTaskDetailRespVO extends PayNotifyTaskBaseVO {


    /** 任务编号*/
    private Long id;

    /** 创建时间*/
    private LocalDateTime createTime;

    /** 更新时间*/
    private LocalDateTime updateTime;

    /** 应用名称*/
    private String appName;

    /** 
     * 回调日志列表
     */
    private List<Log> logs;

    /** 
     * 管理后台 - 回调日志
     */
    @Data
    public static class Log {

        /** 日志编号*/
        private Long id;

        /** 通知状态*/
        private Byte status;

        /** 当前通知次数*/
        private Byte notifyTimes;

        /** HTTP 响应结果*/
        private String response;

        /** 创建时间*/

        private LocalDateTime createTime;

    }

}
