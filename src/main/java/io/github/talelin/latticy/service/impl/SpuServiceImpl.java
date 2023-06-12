package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.latticy.dto.spu.SpuDTO;
import io.github.talelin.latticy.model.*;
import io.github.talelin.latticy.mapper.SpuMapper;
import io.github.talelin.latticy.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author o0u0o
 * @since 2022-04-14
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, SpuDO> implements SpuService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpuImgService spuImgService;

    @Autowired
    private SpuDetailImgService spuDetailImgService;

    @Autowired
    private SpuKeyService spuKeyService;

    @Override
    public SpuDetailDO getDetail(Long id){
        return this.getBaseMapper().getDetail(id);
    }

    @Transactional
    @Override
    public void create(SpuDTO dto) {
        SpuDO spuDO = new SpuDO();
        BeanUtils.copyProperties(dto, spuDO);

        //1、查询并设置父分类ID
        CategoryDO category = categoryService.getCategoryById(dto.getCategoryId());
        spuDO.setRootCategoryId(category.getParentId());
        this.save(spuDO);

        //2、设置spu图片
        List<String> spuImgList = new ArrayList<>();
        if (dto.getSpuImgList() == null){
            spuImgList.add(dto.getImg());
        }
        else {
            spuImgList = dto.getSpuImgList();
        }

        //3、插入SpuImg
        this.insertSpuImgList(spuImgList, spuDO.getId());

        //4、插入规格
        if (dto.getSpecKeyIdList() != null) {
            this.insertSpuKeyList(dto.getSpecKeyIdList(), spuDO.getId());
        }
    }

    /**
     * 插入SpuImg
     * @param spuImgList
     */
    private void insertSpuImgList(List<String> spuImgList, Integer spuId){
        List<SpuImgDO> spuImgDOList = spuImgList.stream().map(s -> {
            SpuImgDO spuImgDO = new SpuImgDO();
            spuImgDO.setImg(s);
            spuImgDO.setSpuId(spuId);
            return spuImgDO;
        }).collect(Collectors.toList());

        //批量插入图片
        spuImgService.saveBatch(spuImgDOList);
    }

    /**
     * <h2>插入Spu详情图片</h2>
     * @param spuDetailImgList
     * @param spuId
     */
    private void insertSpuDetailImgList(List<String> spuDetailImgList, Long spuId){
        List<SpuDetailImgDO> spuDetailImgDOList = new ArrayList<>();
        for (int i = 0; i < spuDetailImgList.size(); i++) {
            SpuDetailImgDO spuDetailImgDO = new SpuDetailImgDO();
            spuDetailImgDO.setImg(spuDetailImgList.get(i))
                    .setSpuId(spuId.intValue())
                    //排序序号，根据前端传入顺序保存序号
                    .setIndex(i);
            spuDetailImgDOList.add(spuDetailImgDO);
        }
        this.spuDetailImgService.saveBatch(spuDetailImgDOList);
    }

    /**
     * <h2>插入规格Key</h2>
     * @param spuKeyIdList
     */
    private void insertSpuKeyList(List<Integer> spuKeyIdList, Integer spuId){
        List<SpuKeyDO> spuKeyDOList = spuKeyIdList.stream().map(s -> {
            SpuKeyDO spuKeyDO = new SpuKeyDO();
            spuKeyDO.setSpuId(spuId)
                    .setSpecKeyId(s);
            return spuKeyDO;
        }).collect(Collectors.toList());

        this.spuKeyService.saveBatch(spuKeyDOList);
    }

    /**
     * <h2>更新spu_key表</h2>
     * @param spuId spu id
     * @param newSpecKeyIdList 前端传递过来的 spu_key id列表
     */
    private void updateSpuKey(Integer spuId, List<Integer> newSpecKeyIdList){
        QueryWrapper<SpuKeyDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SpuKeyDO::getSpuId, spuId);

        List<SpuKeyDO> exists = spuKeyService.getBaseMapper().selectList(wrapper);
        List<Integer> existsIds = new ArrayList<>();
        List<SpuKeyDO> newSpuKeyList = new ArrayList<>();
        for (SpuKeyDO exist : exists){
            existsIds.add(exist.getId());
        }

        for (Integer  specKeyId : newSpecKeyIdList){
            SpuKeyDO spuKeyDO = new SpuKeyDO();
            spuKeyDO.setSpecKeyId(specKeyId);
            spuKeyDO.setSpuId(spuId);
            newSpuKeyList.add(spuKeyDO);
        }

        spuKeyService.removeByIds(existsIds);
        spuKeyService.saveBatch(newSpuKeyList);
    }
}
