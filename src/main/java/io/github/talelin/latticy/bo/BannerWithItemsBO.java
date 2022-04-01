package io.github.talelin.latticy.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.github.talelin.latticy.model.BannerDO;
import io.github.talelin.latticy.model.BannerItemDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * <h1>BannerWithItems业务对象</h1>
 * @author o0u0o
 * @date 2022/4/1 12:12 PM
 */
@Data
@NoArgsConstructor
public class BannerWithItemsBO {

    private Integer id;

    private String name;

    private String title;

    private String img;

    private String description;

    List<BannerItemDO> items;

    /**
     * 使用构造函数进行属性拷贝和设置
     * @param banner
     * @param items
     */
    public BannerWithItemsBO(BannerDO banner, List<BannerItemDO> items) {
        BeanUtils.copyProperties(banner, this);
        this.setItems(items);
    }
}
