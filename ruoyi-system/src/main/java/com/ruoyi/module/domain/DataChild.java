package com.ruoyi.module.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * data_child对象 data_child
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
public class DataChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 最低配置 */
    @Excel(name = "最低配置")
    private String minSpec;

    /** 推荐配置 */
    @Excel(name = "推荐配置")
    private String recommendSpec;

    /** 最低配置2 */
    @Excel(name = "最低配置2")
    private String minSpec2;

    /** 游戏简介 */
    @Excel(name = "游戏简介")
    private String gameDesc;

    /** 游戏介绍 */
    @Excel(name = "游戏介绍")
    private String gameIntroduce;

    /** steam最近评价 */
    @Excel(name = "steam最近评价")
    private String steamLatelyMixed;

    /** steam最近评价详情 */
    @Excel(name = "steam最近评价详情")
    private String steamLatelyReviewDesc;

    /** steam全部评价 */
    @Excel(name = "steam全部评价")
    private String steamAllPositive;

    /** steam全部评价详情 */
    @Excel(name = "steam全部评价详情")
    private String steamAllReviewDesc;

    /** 视频播放地址webm */
    @Excel(name = "视频播放地址webm")
    private String webmSource;

    /** 视频高清播放地址webm */
    @Excel(name = "视频高清播放地址webm")
    private String webmHdSource;

    /** 视频播放地址mp4 */
    @Excel(name = "视频播放地址mp4")
    private String mp4Source;

    /** 视频高清播放地址mp4 */
    @Excel(name = "视频高清播放地址mp4")
    private String mp4HdSource;

    /** 视频封面 */
    @Excel(name = "视频封面")
    private String poster;

    /** 详情图片 */
    @Excel(name = "详情图片")
    private String image;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMinSpec(String minSpec) 
    {
        this.minSpec = minSpec;
    }

    public String getMinSpec() 
    {
        return minSpec;
    }
    public void setRecommendSpec(String recommendSpec) 
    {
        this.recommendSpec = recommendSpec;
    }

    public String getRecommendSpec() 
    {
        return recommendSpec;
    }
    public void setMinSpec2(String minSpec2) 
    {
        this.minSpec2 = minSpec2;
    }

    public String getMinSpec2() 
    {
        return minSpec2;
    }
    public void setGameDesc(String gameDesc) 
    {
        this.gameDesc = gameDesc;
    }

    public String getGameDesc() 
    {
        return gameDesc;
    }
    public void setGameIntroduce(String gameIntroduce) 
    {
        this.gameIntroduce = gameIntroduce;
    }

    public String getGameIntroduce() 
    {
        return gameIntroduce;
    }
    public void setSteamLatelyMixed(String steamLatelyMixed) 
    {
        this.steamLatelyMixed = steamLatelyMixed;
    }

    public String getSteamLatelyMixed() 
    {
        return steamLatelyMixed;
    }
    public void setSteamLatelyReviewDesc(String steamLatelyReviewDesc) 
    {
        this.steamLatelyReviewDesc = steamLatelyReviewDesc;
    }

    public String getSteamLatelyReviewDesc() 
    {
        return steamLatelyReviewDesc;
    }
    public void setSteamAllPositive(String steamAllPositive) 
    {
        this.steamAllPositive = steamAllPositive;
    }

    public String getSteamAllPositive() 
    {
        return steamAllPositive;
    }
    public void setSteamAllReviewDesc(String steamAllReviewDesc) 
    {
        this.steamAllReviewDesc = steamAllReviewDesc;
    }

    public String getSteamAllReviewDesc() 
    {
        return steamAllReviewDesc;
    }
    public void setWebmSource(String webmSource) 
    {
        this.webmSource = webmSource;
    }

    public String getWebmSource() 
    {
        return webmSource;
    }
    public void setWebmHdSource(String webmHdSource) 
    {
        this.webmHdSource = webmHdSource;
    }

    public String getWebmHdSource() 
    {
        return webmHdSource;
    }
    public void setMp4Source(String mp4Source) 
    {
        this.mp4Source = mp4Source;
    }

    public String getMp4Source() 
    {
        return mp4Source;
    }
    public void setMp4HdSource(String mp4HdSource) 
    {
        this.mp4HdSource = mp4HdSource;
    }

    public String getMp4HdSource() 
    {
        return mp4HdSource;
    }
    public void setPoster(String poster) 
    {
        this.poster = poster;
    }

    public String getPoster() 
    {
        return poster;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("minSpec", getMinSpec())
            .append("recommendSpec", getRecommendSpec())
            .append("minSpec2", getMinSpec2())
            .append("gameDesc", getGameDesc())
            .append("gameIntroduce", getGameIntroduce())
            .append("steamLatelyMixed", getSteamLatelyMixed())
            .append("steamLatelyReviewDesc", getSteamLatelyReviewDesc())
            .append("steamAllPositive", getSteamAllPositive())
            .append("steamAllReviewDesc", getSteamAllReviewDesc())
            .append("webmSource", getWebmSource())
            .append("webmHdSource", getWebmHdSource())
            .append("mp4Source", getMp4Source())
            .append("mp4HdSource", getMp4HdSource())
            .append("poster", getPoster())
            .append("image", getImage())
            .toString();
    }
}
