package com.ruoyi.module.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * module对象 anime_ch
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
public class AnimeCh extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 封面图 */
    @Excel(name = "封面图")
    private String coverImg;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 别名 */
    @Excel(name = "别名")
    private String alias;

    /** 动漫类型 */
    @Excel(name = "动漫类型")
    private String animeType;

    /** 动漫分类 */
    @Excel(name = "动漫分类")
    private String animeCategory;

    /** 地区 */
    @Excel(name = "地区")
    private String animeArea;

    /** 年份 */
    @Excel(name = "年份")
    private String animeYear;

    /** 更新状态 */
    @Excel(name = "更新状态")
    private String updateState;

    /** 简介 */
    @Excel(name = "简介")
    private String animeDesc;

    /** 视频地址集合 */
    @Excel(name = "视频地址集合")
    private String animeVideo;

    /** 备用视频地址集合 */
    @Excel(name = "备用视频地址集合")
    private String standbyVideo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCoverImg(String coverImg) 
    {
        this.coverImg = coverImg;
    }

    public String getCoverImg() 
    {
        return coverImg;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setAlias(String alias) 
    {
        this.alias = alias;
    }

    public String getAlias() 
    {
        return alias;
    }
    public void setAnimeType(String animeType) 
    {
        this.animeType = animeType;
    }

    public String getAnimeType() 
    {
        return animeType;
    }
    public void setAnimeCategory(String animeCategory) 
    {
        this.animeCategory = animeCategory;
    }

    public String getAnimeCategory() 
    {
        return animeCategory;
    }
    public void setAnimeArea(String animeArea) 
    {
        this.animeArea = animeArea;
    }

    public String getAnimeArea() 
    {
        return animeArea;
    }
    public void setAnimeYear(String animeYear) 
    {
        this.animeYear = animeYear;
    }

    public String getAnimeYear() 
    {
        return animeYear;
    }
    public void setUpdateState(String updateState) 
    {
        this.updateState = updateState;
    }

    public String getUpdateState() 
    {
        return updateState;
    }
    public void setAnimeDesc(String animeDesc) 
    {
        this.animeDesc = animeDesc;
    }

    public String getAnimeDesc() 
    {
        return animeDesc;
    }
    public void setAnimeVideo(String animeVideo) 
    {
        this.animeVideo = animeVideo;
    }

    public String getAnimeVideo() 
    {
        return animeVideo;
    }
    public void setStandbyVideo(String standbyVideo) 
    {
        this.standbyVideo = standbyVideo;
    }

    public String getStandbyVideo() 
    {
        return standbyVideo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("coverImg", getCoverImg())
            .append("title", getTitle())
            .append("alias", getAlias())
            .append("animeType", getAnimeType())
            .append("animeCategory", getAnimeCategory())
            .append("animeArea", getAnimeArea())
            .append("animeYear", getAnimeYear())
            .append("updateState", getUpdateState())
            .append("animeDesc", getAnimeDesc())
            .append("animeVideo", getAnimeVideo())
            .append("standbyVideo", getStandbyVideo())
            .toString();
    }
}
