package com.ymitcloud.module.bpm.controller.admin.oa.vo;

import jakarta.validation.constraints.AssertTrue;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 请假申请创建 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmOALeaveCreateReqVO extends BpmOALeaveBaseVO {

    @AssertTrue(message = "结束时间，需要在开始时间之后")
    public boolean isEndTimeValid() {
        return !getEndTime().isBefore(getStartTime());
    }

}
