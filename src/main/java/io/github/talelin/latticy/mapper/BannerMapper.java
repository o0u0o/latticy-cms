package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.BannerDO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 轮播映射器
 * @author o0u0o
 * @date 2021/8/17 2:46 下午
 */
@Repository
public interface BannerMapper extends BaseMapper<BannerDO> {

    /**
     * <h2>获取所以轮播</h2>
     * @return
     */
    List<BannerDO> getAllBanners();

    @Select("SELECT * FROM banner")
    List<BannerDO> getAllBanners1();

    long insertBanner(BannerDO bannerDO);

}
