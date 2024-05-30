package com.ymit.module.system.convert.ip;

import com.ymit.framework.ip.core.Area;
import com.ymit.module.system.controller.admin.ip.vo.AreaNodeRespVO;
import com.ymit.module.system.controller.app.ip.vo.AppAreaNodeRespVO;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class AreaNodeConvert {

    public static List<AreaNodeRespVO> convertList(List<Area> list) {
        List<AreaNodeRespVO> datas = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Area item : list) {
                AreaNodeRespVO data = new AreaNodeRespVO();
                data.setId(item.getId());
                data.setName(item.getName());
                data.setChildren(convertList(item.getChildren()));
            }
        }
        return datas;
    }

    public static List<AppAreaNodeRespVO> convertList3(List<Area> list) {
        List<AppAreaNodeRespVO> datas = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Area item : list) {
                AppAreaNodeRespVO data = new AppAreaNodeRespVO();
                data.setId(item.getId());
                data.setName(item.getName());
                data.setChildren(convertList3(item.getChildren()));
            }
        }
        return datas;
    }

}
