package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.spu.SpuDTO;
import io.github.talelin.latticy.model.SpuDO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.model.SpuDetailDO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2022-04-14
 */
public interface SpuService extends IService<SpuDO> {

    public SpuDetailDO getDetail(Long id);

    public void create(SpuDTO dto);
}
