package com.ymitcloud.module.rule.controller.admin.response;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessExecuteResponse implements Serializable {
    /**
     * 此组件是否执行成功
     */
    private Boolean success;
   /**
    * 组件Id
    */
    private String nodeId;
    /**
     * 组件名称
     */
    private String nodeName;
    /**
     * 组件标签值
     */
    private String tag;
    /**
     * 组件的耗时，单位为毫秒
     */
    private Long timeSpent;
   /**
    * 组件开始执行的时间
    */
    private Date startTime;
    /**
     * 件结束执行的时间
     */
    private Date endTime;
    /**
     * 组件抛出的异常,注意：有exception，success一定为false，但是success为false，不一定有exception，因为有可能没执行到，或者没执行结束
     */
    private Exception exception;
}
