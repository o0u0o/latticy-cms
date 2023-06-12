package io.github.talelin.latticy.service;

import io.github.talelin.latticy.model.CategoryDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author o0u0o
 * @since 2022-04-22
 */
public interface CategoryService extends IService<CategoryDO> {

    public CategoryDO getCategoryById(Integer id);

}
