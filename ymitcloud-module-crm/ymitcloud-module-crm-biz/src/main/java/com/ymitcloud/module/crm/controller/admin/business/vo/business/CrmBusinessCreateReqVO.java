package com.ymitcloud.module.crm.controller.admin.business.vo.business;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 管理后台 - 商机创建 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmBusinessCreateReqVO extends CrmBusinessBaseVO {

    // TODO @ljileo：新建的时候，应该可以传递添加的产品；

}
