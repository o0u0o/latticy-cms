package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.banner.BannerDTO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 轮播业务实现类
 * @author o0u0o
 * @date 2021/8/17 2:44 下午
 */
public interface BannerService  {


   /**
    * 根据id更新轮播
    * @param dto
    * @param id
    */
   public void updateById(BannerDTO dto, Long id);


}
