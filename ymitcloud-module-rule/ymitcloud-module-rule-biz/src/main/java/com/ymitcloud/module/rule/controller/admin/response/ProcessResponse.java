package com.ymitcloud.module.rule.controller.admin.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.alibaba.fastjson2.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessResponse implements Serializable {
   /**
    * 编号
    */
    private String id;
    /**
     * 流程名称
     */
    private String processName;
    /**
     * 流程数据
     */
    private JSONObject processData;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
   /**
    * 更新时间
    */
    private LocalDateTime updateTime;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 更新者
     */
    private String updater;
}
