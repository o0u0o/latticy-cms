package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.bo.BannerWithItemsBO;
import io.github.talelin.latticy.dto.banner.BannerDTO;
import io.github.talelin.latticy.mapper.BannerItemMapper;
import io.github.talelin.latticy.mapper.BannerMapper;
import io.github.talelin.latticy.model.BannerDO;
import io.github.talelin.latticy.model.BannerItemDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 轮播业务实现类
 * @author o0u0o
 * @date 2021/8/17 2:44 下午
 */
@Service
public class BannerService extends ServiceImpl<BannerMapper, BannerDO> {


   @Autowired
   private BannerMapper bannerMapper;

   @Autowired
   private BannerItemMapper bannerItemMapper;

   /**
    * <h2>获取Items</h2>
    * @param id
    */
   public BannerWithItemsBO getWithItems(Long id){
      // 1、单表查询 先查询banner
      BannerDO banner = this.getById(id);
      if (banner == null){
         throw new NotFoundException(20000);
      }

      //2、再查询bannerItem 条件构造器 wrapper 构造查询条件
      QueryWrapper<BannerItemDO> wrapper = new QueryWrapper<>();
      wrapper.eq("banner_id", id);
      List<BannerItemDO> bannerItemDOList = bannerItemMapper.selectList(wrapper);

      //3、进行数据组装
      return new BannerWithItemsBO(banner, bannerItemDOList);
   }

   /**
    * <h2>删除轮播</h2>
    * @param id
    */
   public void delete(Integer id) {
      BannerDO banner = this.getById(id);
      if (banner == null){
         throw new NotFoundException(20000);
      }
      this.getBaseMapper().deleteById(id);
   }

   /**
    * <h2>更新轮播</h2>
    * @param dto
    * @param id
    */
   public void update(BannerDTO dto, Long id) {
      BannerDO bannerDO = this.getById(id);
      //判空
      if (ObjectUtils.isEmpty(bannerDO)){
         throw new NotFoundException(20000);
      }
      BeanUtils.copyProperties(dto, bannerDO);
      this.updateById(bannerDO);
   }

}
