package io.github.talelin.latticy.dto.banner;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 轮播数据传输对象
 * @author o0u0o
 * @date 2021/8/23 2:47 下午
 */
@Getter
@Setter
public class BannerDTO {

    @NotBlank
    @Length(min = 2, max = 20, message = "Banner的name长度为2~20")
    private String name;

    @Length(min = 2, max = 30)
    private String title;

    @Length(min = 2, max = 256)
    private String img;

    @Length(min = 2, max = 256)
    private String description;
}
