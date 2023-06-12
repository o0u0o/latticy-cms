package io.github.talelin.latticy.controller.v1;


import io.github.talelin.latticy.dto.spu.SpuDTO;
import io.github.talelin.latticy.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.SkuDO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import java.util.List;

/**
 * <h1>SKU视图层</h1>
* @author o0u0o
* @since 2022-04-14
*/
@RestController
@RequestMapping("/v1/sku")
@Validated
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     * <h2>新增SKU</h2>
     * @return
     */
    @PostMapping("")
    public CreatedVO create(@RequestBody @Validated SpuDTO dto) {
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    public SkuDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        return null;
    }

    /**
     * <h2>即时搜索SKU</h2>
     * @param spuId
     */
    @GetMapping("/by/spu/{id}")
    public List<SkuDO> getBySpuId(@PathVariable(value = "id") @Positive Long spuId){
        return this.skuService.lambdaQuery().eq(SkuDO::getSpuId, spuId).list();
    }

    @GetMapping("/page")
    public PageResponseVO<SkuDO> page(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count
    ) {
        return null;
    }

}
