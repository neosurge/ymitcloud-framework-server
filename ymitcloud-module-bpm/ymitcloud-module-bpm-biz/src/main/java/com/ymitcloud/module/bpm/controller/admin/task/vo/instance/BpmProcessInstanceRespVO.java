package com.ymitcloud.module.bpm.controller.admin.task.vo.instance;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


import com.ymitcloud.module.bpm.enums.definition.BpmModelFormTypeEnum;

import lombok.Data;

/**
 * 管理后台 - 流程实例的 Response VO
 */
@Data
public class BpmProcessInstanceRespVO {
    /**
     * 流程实例的编号
     */
    private String id;
    /**
     * 流程名称
     */
    private String name;
    /**
     * 流程分类-参见 bpm_model_category 数据字典
     */
    private String category;
    /**
     * 流程实例的状态-参见 bpm_process_instance_status
     */
    private Integer status;
    /**
     * 流程实例的结果-参见 bpm_process_instance_result
     */
    private Integer result;
    /**
     * 提交时间
     */
    private LocalDateTime createTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 提交的表单值
     */
    private Map<String, Object> formVariables;
    /**
     * 业务的唯一标识-例如说，请假申请的编号
     */

    private String businessKey;

    /**
     * 发起流程的用户
     */
    private User startUser;

    /**
     * 流程定义
     */
    private ProcessDefinition processDefinition;


    /**
     * 用户信息
     */
    @Data
    public static class User {
        /**
         * 用户编号
         */
        private Long id;
        /**
         * 用户昵称
         */
        private String nickname;
        /**
         * 部门编号
         */
        private Long deptId;
        /**
         * 部门名称
         */

        private String deptName;

    }


    /**
     * 流程定义信息
     */
    @Data
    public static class ProcessDefinition {
        /**
         * 编号
         */
        private String id;
        /**
         * 表单类型-参见 bpm_model_form_type 数据字典
         */
        private Integer formType;
        /**
         * 表单编号-在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时，必须非空
         */
        private Long formId;
        /**
         * 表单的配置-JSON 字符串。在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时，必须非空
         */
        private String formConf;
        /**
         * 表单项的数组-JSON 字符串的数组。在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时，必须非空
         */
        private List<String> formFields;
        /**
         * 自定义表单的提交路径，使用 Vue 的路由地址-在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时，必须非空
         */
        private String formCustomCreatePath;
        /**
         * 自定义表单的查看路径，使用 Vue 的路由地址-在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时，必须非空
         */
        private String formCustomViewPath;
        /**
         * BPMN XML
         */

        private String bpmnXml;

    }

}
