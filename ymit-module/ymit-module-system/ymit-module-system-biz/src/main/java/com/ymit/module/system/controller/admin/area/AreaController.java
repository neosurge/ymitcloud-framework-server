package com.ymit.module.system.controller.admin.area;

import cn.hutool.core.util.NumberUtil;
import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.excel.core.util.ExcelUtils;
import com.ymit.framework.operatelog.core.annotations.OperateLog;
import com.ymit.module.system.controller.admin.area.vo.*;
import com.ymit.module.system.dal.dataobject.area.AreaDO;
import com.ymit.module.system.service.area.AreaService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;


/**
 * 管理后台 - 行政区划
 */

@RestController
@RequestMapping("/system/area")
@Validated
public class AreaController {

    @Resource
    private AreaService areaService;


    /**
     * 创建行政区划
     *
     * @param createReqVO 创建请求实体
     * @return 创建后的实体主键
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:area:create')")
    public CommonResult<Long> createArea(@Valid @RequestBody AreaSaveReqVO createReqVO) {
        return success(this.areaService.createArea(createReqVO));
    }


    /**
     * 更新行政区划
     *
     * @param updateReqVO 修改请求实体
     * @return 成功与否
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:area:update')")
    public CommonResult<Boolean> updateArea(@Valid @RequestBody AreaSaveReqVO updateReqVO) {
        this.areaService.updateArea(updateReqVO);
        return success(true);
    }


    /**
     * 删除行政区划
     *
     * @param id 主键集合，使用英文逗号分割
     * @return 成功与否
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:area:delete')")
    public CommonResult<Boolean> deleteArea(@RequestParam("id") String id) {
        String[] arr = StringUtils.split(id, ",");
        List<Long> ids = Arrays.stream(arr).filter(NumberUtil::isLong).map(Long::valueOf).toList();
        this.areaService.deleteArea(ids);
        return success(true);
    }

    /**
     * 获得行政区划
     *
     * @param id 主键
     * @return 行政区划
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:area:query')")
    public CommonResult<AreaRespTreeVO> getArea(@RequestParam("id") Long id) {
        AreaDO area = this.areaService.getArea(id);
        AreaRespTreeVO data = BeanUtils.toBean(area, AreaRespTreeVO.class);
        return success(this.areaService.setParents(data));
    }

    /**
     * 获取回填数据，从顶层到当前节点，包含其链路中兄弟节点
     *
     * @param id 当前节点
     */
    @GetMapping("/get/backfill")
    public CommonResult<List<AreaRespTreeVO>> getAreaBackfill(@RequestParam("id") Long id) {
        return success(this.areaService.getAreaBackfill(id));
    }

    /**
     * 获得行政区划列表
     *
     * @param queryReqVO 查询参数
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('system:area:query')")
    public CommonResult<List<AreaRespVO>> getAreaList(@Valid AreaQueryReqVO queryReqVO) {
        List<AreaDO> list = this.areaService.getAreaList(queryReqVO);
        return success(BeanUtils.toBean(list, AreaRespVO.class));
    }

    /**
     * 获得行政区划列表(Simple 无权限校验)
     *
     * @param queryReqVO 查询参数
     */
    @GetMapping("/list/opt")
    public CommonResult<List<AreaRespSimpleVO>> getAreaListOpt(@Valid AreaQueryReqVO queryReqVO) {
        List<AreaDO> list = this.areaService.getAreaList(queryReqVO);
        return success(BeanUtils.toBean(list, AreaRespSimpleVO.class));
    }

    /**
     * 获得行政区划列表(包含parent相关信息)
     *
     * @param queryReqVO 查询参数
     */
    @GetMapping("/list/tree")
    @PreAuthorize("@ss.hasPermission('system:area:query')")
    public CommonResult<List<AreaRespTreeVO>> getAreaListTree(@Valid AreaQueryReqVO queryReqVO) {
        if (queryReqVO.getParentId() == null) {
            queryReqVO.setParentId(0L);
        }
        List<AreaDO> list = this.areaService.getAreaList(queryReqVO);
        List<AreaRespTreeVO> datas = this.areaService.setParents(BeanUtils.toBean(list, AreaRespTreeVO.class));
        List<Long> pids = list.stream().map(AreaDO::getId).toList();
        List<AreaDO> children = this.areaService.getListBytParentIds(pids);
        if (CollectionUtils.isNotEmpty(datas)) {
            for (var item : datas) {
                List<AreaDO> selfChildren = children.stream().filter(c -> c.getParentId().equals(item.getId())).toList();
                item.setChildren(BeanUtils.toBean(selfChildren, AreaRespTreeVO.class));
            }
        }
        return success(datas);
    }

    /**
     * 获得行政区划列表(包含parent相关信息 Simple无权限校验)
     *
     * @param queryReqVO 查询参数
     */
    @GetMapping("/list/tree/opt")
    public CommonResult<List<AreaRespOptVO>> getAreaListTreeOpt(@Valid AreaQueryReqVO queryReqVO) {
        List<AreaDO> list = this.areaService.getAreaList(queryReqVO);
        List<AreaRespTreeVO> datas = this.areaService.setParents(BeanUtils.toBean(list, AreaRespTreeVO.class));
        List<AreaRespOptVO> results = BeanUtils.toBean(datas, AreaRespOptVO.class);
        return success(results);
    }

    /**
     * 导出行政区划 Excel
     *
     * @param pageReqVO 请求实体
     * @throws IOException 异常
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('system:area:export')")
    @OperateLog(type = EXPORT)
    public void exportAreaExcel(@Valid AreaQueryReqVO pageReqVO, HttpServletResponse response) throws IOException {
        List<AreaDO> list = this.areaService.getAreaList(pageReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "行政区划.xls", "数据", AreaRespVO.class, BeanUtils.toBean(list, AreaRespVO.class));
    }

}