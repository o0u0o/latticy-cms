package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <h1>BannerItemDO</h1>
 * @author o0u0o
 * @date 2022/4/1 11:51 AM
 */
@Getter
@Setter
@TableName("banner_item")
public class BannerItemDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String img;

    private String keyword;

    private Integer type;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;

    /**
     * 删除时间
     * 注解@TableLogic 标记为逻辑删除
     */
    @JsonIgnore
    @TableLogic
    private Date deleteTime;

    /** 轮播ID */
    private Long bannerId;
}
