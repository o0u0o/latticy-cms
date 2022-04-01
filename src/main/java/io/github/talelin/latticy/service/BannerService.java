package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.dto.banner.BannerDTO;
import io.github.talelin.latticy.mapper.BannerMapper;
import io.github.talelin.latticy.model.BannerDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 轮播业务实现类
 * @author o0u0o
 * @date 2021/8/17 2:44 下午
 */
@Service
public class BannerService extends ServiceImpl<BannerMapper, BannerDO> {


   @Autowired
   private BannerMapper bannerMapper;

   public void getWithItems(Long id){
      // 单表查询 先查询banner 再查询bannerItem
      BannerDO banner = this.getById(id);
      if (banner == null){
         throw new NotFoundException(20000);
      }


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
