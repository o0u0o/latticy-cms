package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.core.annotation.*;
import io.github.talelin.latticy.bo.BannerWithItemsBO;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.banner.BannerDTO;
import io.github.talelin.latticy.model.BannerDO;
import io.github.talelin.latticy.service.BannerService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

/**
 * 轮播控制器
 * query-200 create-201 delete-201 put-201
 * @author o0u0o
 * @date 2021/8/17 12:05 下午
 */
@RequestMapping("/v1/banner")
@RestController
@Validated
@PermissionModule(value = "轮播(Banner)")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * <h2>新增轮播</h2>
     *
     * @param dto
     * @return CreatedVO 创建成功视图对象
     */
    @PostMapping
    @PermissionMeta(value = "创建Banner")
    public CreatedVO create(@RequestBody @Validated BannerDTO dto){
        BannerDO bannerDO = new BannerDO();
        BeanUtils.copyProperties(dto, bannerDO);
        this.bannerService.save(bannerDO);
        return new CreatedVO();
    }

//    lim-cms 权限有关注解
//    @LoginRequired
//    @GroupRequired
//    @AdminRequired 需要登录 且 用户分组为管理员
//    @PermissionMeta
//    @PermissionModule

    @PutMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "更新Banner")
    public UpdatedVO update(@RequestBody @Validated BannerDTO dto,
                       @PathVariable @Positive Long id){
        this.bannerService.update(dto, id);
        return new UpdatedVO(3001);
    }

    /**
     * <h2>删除Banner</h2>
     * 注解解释：
     * 1、@PermissionMeta 指定权限的元数据信息(value 和 module) 但是不会进去权限保护
     * 2、@GroupRequired 分组权限-权限保护
     * 之所以设计为2个不同注解，是为了增加起灵活性
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除Banner")
    public DeletedVO delete(@PathVariable @Positive Integer id) {
        bannerService.delete(id);
        //级联删除
        //1、删除主表的数据
        //2、删除从表的数据 查询banner_item_id
        return new DeletedVO();
    }

    /**
     * <h2>获取所有注解</h2>
     * 注解@LoginRequired - 需要登录方可访问
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @LoginRequired
    public BannerWithItemsBO getWithItems(@PathVariable @Positive Long id){
        return bannerService.getWithItems(id);
    }

    /**
     * 获取轮播
     * 注解解释：
     * 1、@Logger 记录行为日志（记录到数据库表里）
     * @param page 第几页
     * @param count 每页的数据条数
     */
    @GetMapping("/page")
    @LoginRequired
    @PermissionMeta(value = "查询Banner")
    @Logger(template = "{user.username}查询了Banner数据")
    public PageResponseVO<BannerDO> getBanners(@RequestParam(required = false, defaultValue = "0")
                               @Min(value = 0, message = "") Integer page,
                           @RequestParam(required = false, defaultValue = "10")
                               @Min(value = 0) @Max(value = 30) Integer count){
        Page<BannerDO> pager = new Page<>(page, count);
        IPage<BannerDO> paging = bannerService.getBaseMapper().selectPage(pager, null);
        return new PageResponseVO<>(paging.getTotal(), paging.getRecords(), paging.getCurrent(), paging.getSize());
    }
}
