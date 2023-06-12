package io.github.talelin.latticy.dto.spu;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

/**
 * <h1>SPU数据传输对象</h1>
 * 并对传输进来的数据进行基础验证
 * @author o0u0o
 * @date 2022/4/22 5:09 PM
 */
@Data
public class SpuDTO {

    @NotBlank
    @Length(min = 1, max = 128)
    private String title;

    @NotBlank
    @Length(min = 1, max = 255)
    private String subtitle;

    @NotBlank
    @Length(min = 1, max = 255)
    private String img;

    @Length(min = 1, max = 255)
    private String forThemeImg;

    @Positive
    @NotNull
    private Integer categoryId;

    @Max(1)
    @Min(0)
    private Integer online;

    @Positive
    private Integer sketchSpecId;

    @Positive
    private Integer defaultSkuId;

    @NotBlank
    @Length(min = 1, max = 20)
    private String price;

    @Length(min = 1, max = 20)
    private String discountPrice;

    @Length(min = 1, max = 255)
    private String description;

    @Length(min = 1, max = 255)
    private String tags;

    private List<Integer> specKeyIdList;

    private List<String> spuImgList;

    private List<String> detailImgList;
}
