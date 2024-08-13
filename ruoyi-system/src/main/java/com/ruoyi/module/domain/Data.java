package com.ruoyi.module.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * module对象 data
 * 
 * @author fxb
 * @date 2024-08-13
 */
public class Data extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 下载数量 */
    @Excel(name = "下载数量")
    private Long downLoadCount;

    /** 封面 */
    @Excel(name = "封面")
    private String coverImg;

    /** 中文标题 */
    @Excel(name = "中文标题")
    private String zhTitle;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 发售日期 */
    @Excel(name = "发售日期")
    private String releaseDate;

    /** 开发商 */
    @Excel(name = "开发商")
    private String developer;

    /** 评分 */
    @Excel(name = "评分")
    private String score;

    /** 游戏下载地址 */
    @Excel(name = "游戏下载地址")
    private String gameUrl;

    /** 最低配置 */
    @Excel(name = "最低配置")
    private String minSpec;

    /** 推荐配置 */
    @Excel(name = "推荐配置")
    private String recommendSpec;

    /** 最低配置2 */
    @Excel(name = "最低配置2")
    private String minSpec2;

    /** 游戏大小 */
    @Excel(name = "游戏大小")
    private String gameSize;

    /** 游戏简介 */
    @Excel(name = "游戏简介")
    private String gameDesc;

    /** 游戏类型 */
    @Excel(name = "游戏类型")
    private String gameType;

    /** 游戏介绍 */
    @Excel(name = "游戏介绍")
    private String gameIntroduce;

    /** 发行商 */
    @Excel(name = "发行商")
    private String publisher;

    /** 标签列表 */
    @Excel(name = "标签列表")
    private String tagList;

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
    public void setDownLoadCount(Long downLoadCount) 
    {
        this.downLoadCount = downLoadCount;
    }

    public Long getDownLoadCount() 
    {
        return downLoadCount;
    }
    public void setCoverImg(String coverImg) 
    {
        this.coverImg = coverImg;
    }

    public String getCoverImg() 
    {
        return coverImg;
    }
    public void setZhTitle(String zhTitle) 
    {
        this.zhTitle = zhTitle;
    }

    public String getZhTitle() 
    {
        return zhTitle;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setReleaseDate(String releaseDate) 
    {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() 
    {
        return releaseDate;
    }
    public void setDeveloper(String developer) 
    {
        this.developer = developer;
    }

    public String getDeveloper() 
    {
        return developer;
    }
    public void setScore(String score) 
    {
        this.score = score;
    }

    public String getScore() 
    {
        return score;
    }
    public void setGameUrl(String gameUrl) 
    {
        this.gameUrl = gameUrl;
    }

    public String getGameUrl() 
    {
        return gameUrl;
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
    public void setGameSize(String gameSize) 
    {
        this.gameSize = gameSize;
    }

    public String getGameSize() 
    {
        return gameSize;
    }
    public void setGameDesc(String gameDesc) 
    {
        this.gameDesc = gameDesc;
    }

    public String getGameDesc() 
    {
        return gameDesc;
    }
    public void setGameType(String gameType) 
    {
        this.gameType = gameType;
    }

    public String getGameType() 
    {
        return gameType;
    }
    public void setGameIntroduce(String gameIntroduce) 
    {
        this.gameIntroduce = gameIntroduce;
    }

    public String getGameIntroduce() 
    {
        return gameIntroduce;
    }
    public void setPublisher(String publisher) 
    {
        this.publisher = publisher;
    }

    public String getPublisher() 
    {
        return publisher;
    }
    public void setTagList(String tagList) 
    {
        this.tagList = tagList;
    }

    public String getTagList() 
    {
        return tagList;
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
            .append("downLoadCount", getDownLoadCount())
            .append("coverImg", getCoverImg())
            .append("zhTitle", getZhTitle())
            .append("title", getTitle())
            .append("releaseDate", getReleaseDate())
            .append("developer", getDeveloper())
            .append("score", getScore())
            .append("gameUrl", getGameUrl())
            .append("minSpec", getMinSpec())
            .append("recommendSpec", getRecommendSpec())
            .append("minSpec2", getMinSpec2())
            .append("gameSize", getGameSize())
            .append("gameDesc", getGameDesc())
            .append("gameType", getGameType())
            .append("gameIntroduce", getGameIntroduce())
            .append("publisher", getPublisher())
            .append("tagList", getTagList())
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
