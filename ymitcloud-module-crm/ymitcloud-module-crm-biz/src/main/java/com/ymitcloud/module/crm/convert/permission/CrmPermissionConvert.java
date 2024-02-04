package com.ymitcloud.module.crm.convert.permission;

import com.ymitcloud.framework.common.util.collection.CollectionUtils;
import com.ymitcloud.framework.common.util.collection.MapUtils;
import com.ymitcloud.module.crm.controller.admin.permission.vo.CrmPermissionCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.permission.vo.CrmPermissionRespVO;
import com.ymitcloud.module.crm.controller.admin.permission.vo.CrmPermissionUpdateReqVO;
import com.ymitcloud.module.crm.dal.dataobject.permission.CrmPermissionDO;
import com.ymitcloud.module.crm.service.permission.bo.CrmPermissionCreateReqBO;
import com.ymitcloud.module.crm.service.permission.bo.CrmPermissionUpdateReqBO;
import com.ymitcloud.module.system.api.dept.dto.DeptRespDTO;
import com.ymitcloud.module.system.api.dept.dto.PostRespDTO;
import com.ymitcloud.module.system.api.user.dto.AdminUserRespDTO;
import com.google.common.collect.Multimaps;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

import static com.ymitcloud.framework.common.util.collection.MapUtils.findAndThen;

/**
 * Crm 数据权限 Convert
 *
 * @author HUIHUI
 */
@Mapper
public interface CrmPermissionConvert {

    CrmPermissionConvert INSTANCE = Mappers.getMapper(CrmPermissionConvert.class);

    CrmPermissionDO convert(CrmPermissionCreateReqBO createBO);

    CrmPermissionDO convert(CrmPermissionUpdateReqBO updateBO);

    CrmPermissionCreateReqBO convert(CrmPermissionCreateReqVO reqVO);

    CrmPermissionUpdateReqBO convert(CrmPermissionUpdateReqVO updateReqVO);

    List<CrmPermissionRespVO> convert(List<CrmPermissionDO> permission);

    default List<CrmPermissionRespVO> convert(List<CrmPermissionDO> permission, List<AdminUserRespDTO> userList,
                                              Map<Long, DeptRespDTO> deptMap, Map<Long, PostRespDTO> postMap) {
        Map<Long, AdminUserRespDTO> userMap = CollectionUtils.convertMap(userList, AdminUserRespDTO::getId);
        return CollectionUtils.convertList(convert(permission), item -> {
            findAndThen(userMap, item.getUserId(), user -> {
                item.setNickname(user.getNickname());
                findAndThen(deptMap, user.getDeptId(), deptRespDTO -> {
                    item.setDeptName(deptRespDTO.getName());
                });
                List<PostRespDTO> postRespList = MapUtils.getList(Multimaps.forMap(postMap), user.getPostIds());
                item.setPostNames(CollectionUtils.convertSet(postRespList, PostRespDTO::getName));
            });
            return item;
        });
    }

    default List<CrmPermissionDO> convertList(CrmPermissionUpdateReqVO updateReqVO) {
        return CollectionUtils.convertList(updateReqVO.getIds(),
                id -> new CrmPermissionDO().setId(id).setLevel(updateReqVO.getLevel()));
    }

    List<CrmPermissionDO> convertList(List<CrmPermissionCreateReqBO> list);

}
