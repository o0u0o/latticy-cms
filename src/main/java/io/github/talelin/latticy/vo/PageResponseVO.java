package io.github.talelin.latticy.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页数据统一 view object
 *
 * @author pedro@TaleLin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponseVO<T> {

    private Long total;

    private List<T> items;

    private Long page;

    private Long count;
}
