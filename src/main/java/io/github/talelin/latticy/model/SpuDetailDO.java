package io.github.talelin.latticy.model;

import lombok.Data;

import java.util.List;

/**
 * <h1>SpuDetail数据对象</h1>
 * <p>从数据库查询出来的对象</p>
 * @author o0u0o
 * @date 2022/4/20 10:31 AM
 */
@Data
public class SpuDetailDO extends SpuDO {

    /** 分类名 */
    private String categoryName;

    /** 可视规格名 */
    private String sketchSpecKeyName;

    private String defaultSkuTitle;

    private List<String> spuImgList;

    private List<String> spuDetailList;

}
