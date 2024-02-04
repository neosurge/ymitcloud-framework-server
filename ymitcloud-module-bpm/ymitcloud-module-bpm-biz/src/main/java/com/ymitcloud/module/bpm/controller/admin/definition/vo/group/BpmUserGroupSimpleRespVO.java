package com.ymitcloud.module.bpm.controller.admin.definition.vo.group;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 管理后台 - 用户组精简信息 Response VO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BpmUserGroupSimpleRespVO {


    /**
     * 用户组编号
     */
    private Long id;

    /**
     * 用户组名字
     */

    private String name;

}
