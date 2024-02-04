package com.ymitcloud.module.promotion.service.banner;

import com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.promotion.controller.admin.banner.vo.BannerCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.banner.vo.BannerPageReqVO;
import com.ymitcloud.module.promotion.controller.admin.banner.vo.BannerUpdateReqVO;
import com.ymitcloud.module.promotion.convert.banner.BannerConvert;
import com.ymitcloud.module.promotion.dal.dataobject.banner.BannerDO;
import com.ymitcloud.module.promotion.dal.mysql.banner.BannerMapper;
import com.ymitcloud.module.promotion.enums.ErrorCodeConstants;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 首页 banner 实现类
 *
 * @author xia
 */
@Service
@Validated
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerMapper bannerMapper;

    @Override
    public Long createBanner(BannerCreateReqVO createReqVO) {
        // 插入
        BannerDO banner = BannerConvert.INSTANCE.convert(createReqVO);
        bannerMapper.insert(banner);
        // 返回
        return banner.getId();
    }

    @Override
    public void updateBanner(BannerUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateBannerExists(updateReqVO.getId());
        // 更新
        BannerDO updateObj = BannerConvert.INSTANCE.convert(updateReqVO);
        bannerMapper.updateById(updateObj);
    }

    @Override
    public void deleteBanner(Long id) {
        // 校验存在
        this.validateBannerExists(id);
        // 删除
        bannerMapper.deleteById(id);
    }

    private void validateBannerExists(Long id) {
        if (bannerMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.BANNER_NOT_EXISTS);
        }
    }

    @Override
    public BannerDO getBanner(Long id) {
        return bannerMapper.selectById(id);
    }

    @Override
    public PageResult<BannerDO> getBannerPage(BannerPageReqVO pageReqVO) {
        return bannerMapper.selectPage(pageReqVO);
    }

    @Override
    public void addBannerBrowseCount(Long id) {
        // 校验 Banner 是否存在
        validateBannerExists(id);
        // 增加点击次数
        bannerMapper.updateBrowseCount(id);
    }

    @Override
    public List<BannerDO> getBannerListByPosition(Integer position) {
        return bannerMapper.selectBannerListByPosition(position);
    }

}
