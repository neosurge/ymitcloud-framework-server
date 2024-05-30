package com.ymit.module.system.controller.admin.area.vo;

import com.ymit.framework.common.data.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collection;

import static com.ymit.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 管理后台 - 行政区划 Request VO
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AreaQueryReqVO extends PageParam {

    @Serial
    private static final long serialVersionUID = -57417961475852785L;
    /**
     * id集合
     */
    private Collection<Long> ids;

    /**
     * 区域编码
     */
    private String code;

    /**
     * 区域编码 集合
     */
    private Collection<String> codes;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 区域等级：1 : 省/直辖市、2 : 市、3 : 区/县、4 : 乡镇/街道、5 : 社区/村委会
     */
    private Integer level;

    /**
     * 父级区域编码
     */
    private String parentCode;

    /**
     * 父级区域编码 集合
     */
    private Collection<String> parentCodes;

    /**
     * 父级区域ID
     */
    private Long parentId;

    /**
     * 层级
     */
    private Integer layer;

    /**
     * 层级包含的id集合
     */
    private String layerList;
    /**
     * 子节点数量
     */
    private Integer childrenCount;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
