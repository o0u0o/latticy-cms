package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.banner.BannerDTO;
import io.github.talelin.latticy.model.BannerDO;
import io.github.talelin.latticy.service.impl.BannerServiceImpl;
import io.github.talelin.latticy.vo.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

/**
 * 轮播控制器
 * @author o0u0o
 * @date 2021/8/17 12:05 下午
 */
@RequestMapping("/v1/banner")
@RestController
@Validated
public class BannerController {

    @Autowired
    private BannerServiceImpl bannerServiceImpl;


    @PutMapping("/{id}")
    public void update(@RequestBody @Validated BannerDTO dto,
                       @PathVariable @Positive Long id){
        this.bannerServiceImpl.getById()
    }

    /**
     * 获取轮播
     * @param page 第几页
     * @param count 每页的数据条数
     */
    @GetMapping("/page")
    public PageResponseVO<BannerDO> getBanners(@RequestParam(required = false, defaultValue = "0")
                               @Min(value = 0, message = "") Integer page,
                           @RequestParam(required = false, defaultValue = "10")
                               @Min(value = 0) @Max(value = 30) Integer count){
        Page<BannerDO> pager = new Page<>(page, count);
        IPage<BannerDO> paging = bannerServiceImpl.getBaseMapper().selectPage(pager, null);
        return new PageResponseVO<>(paging.getTotal(), paging.getRecords(), paging.getCurrent(), paging.getSize());
    }
}
