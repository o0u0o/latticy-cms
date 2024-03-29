package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 轮播数据对象
 * @author o0u0o
 * @date 2021/8/17 2:55 下午
 */
@Getter
@Setter
@TableName("banner")
public class BannerDO {

    //细粒度权限控制 针对字段进行权限控制
    //解决方案 1、动态SQL实现
    private Integer id;

    private String name;

    private String title;

    private String description;

    private String img;

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
}
