package com.ruoyi.module.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * module对象 data
 *
 * @author fxb
 * @date 2024-07-17
 */
public class Data extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 下载数量
     */
    @Excel(name = "下载数量")
    private Integer downLoadCount;

    /**
     * 封面
     */
    @Excel(name = "封面")
    private String img;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;

    /**
     * 版本
     */
    @Excel(name = "版本")
    private String subhnamever;

    /**
     * 版本标签
     */
    @Excel(name = "版本标签")
    private String subhname;

    /**
     * 发售日期
     */
    @Excel(name = "发售日期")
    private String releaseDate;

    /**
     * 开发商
     */
    @Excel(name = "开发商")
    private String developer;

    /**
     * 游戏类型
     */
    @Excel(name = "游戏类型")
    private String gameType;

    /**
     * 评分
     */
    @Excel(name = "评分")
    private String score;

    /**
     * 更新时间
     */
    @Excel(name = "更新时间")
    private Long update;

    /**
     * 描述图片高清
     */
    @Excel(name = "描述图片高清")
    private String descHeightImg;

    /**
     * 描述图片
     */
    @Excel(name = "描述图片")
    private String descImg;

    /**
     * 视频
     */
    @Excel(name = "视频")
    private String descVideo;

    /**
     * 视频完整地址
     */
    @Excel(name = "视频完整地址")
    private String movieMax;

    /**
     * 游戏下载地址
     */
    @Excel(name = "游戏下载地址")
    private String gameUrl;

    /**
     * 最低配置
     */
    @Excel(name = "最低配置")
    private String minSpec;

    /**
     * 游戏介绍
     */
    @Excel(name = "最低配置")
    private String desc;

    /**
     * 中文标题
     */
    @Excel(name = "中文标题")
    private String chTitle;

    /**
     * 文件大小
     */
    @Excel(name = "文件大小")
    private String gameSize;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDownLoadCount(Integer downLoadCount) {
        this.downLoadCount = downLoadCount;
    }

    public Integer getDownLoadCount() {
        return downLoadCount;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSubhnamever(String subhnamever) {
        this.subhnamever = subhnamever;
    }

    public String getSubhnamever() {
        return subhnamever;
    }

    public void setSubhname(String subhname) {
        this.subhname = subhname;
    }

    public String getSubhname() {
        return subhname;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameType() {
        return gameType;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setUpdate(Long update) {
        this.update = update;
    }

    public Long getUpdate() {
        return update;
    }

    public void setDescHeightImg(String descHeightImg) {
        this.descHeightImg = descHeightImg;
    }

    public String getDescHeightImg() {
        return descHeightImg;
    }

    public void setDescImg(String descImg) {
        this.descImg = descImg;
    }

    public String getDescImg() {
        return descImg;
    }

    public void setDescVideo(String descVideo) {
        this.descVideo = descVideo;
    }

    public String getDescVideo() {
        return descVideo;
    }

    public void setMovieMax(String movieMax) {
        this.movieMax = movieMax;
    }

    public String getMovieMax() {
        return movieMax;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setMinSpec(String minSpec) {
        this.minSpec = minSpec;
    }

    public String getMinSpec() {
        return minSpec;
    }

    public void setChTitle(String chTitle) {
        this.chTitle = chTitle;
    }

    public String getChTitle() {
        return chTitle;
    }

    public void setGameSize(String gameSize) {
        this.gameSize = gameSize;
    }

    public String getGameSize() {
        return gameSize;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Data(String title) {
        this.title = title;
    }

    public Data() {
    }

    public Data(Long id, Integer downLoadCount, String img, String title, String subhnamever, String subhname, String releaseDate, String developer, String gameType, String score, Long update, String descHeightImg, String descImg, String descVideo,String movieMax, String gameUrl, String minSpec, String desc, String chTitle,String gameSize) {
        this.id = id;
        this.downLoadCount = downLoadCount;
        this.img = img;
        this.title = title;
        this.subhnamever = subhnamever;
        this.subhname = subhname;
        this.releaseDate = releaseDate;
        this.developer = developer;
        this.gameType = gameType;
        this.score = score;
        this.update = update;
        this.descHeightImg = descHeightImg;
        this.descImg = descImg;
        this.descVideo = descVideo;
        this.movieMax = movieMax;
        this.gameUrl = gameUrl;
        this.minSpec = minSpec;
        this.desc = desc;
        this.chTitle = chTitle;
        this.gameSize = gameSize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("downLoadCount", getDownLoadCount())
                .append("img", getImg())
                .append("title", getTitle())
                .append("subhnamever", getSubhnamever())
                .append("subhname", getSubhname())
                .append("releaseDate", getReleaseDate())
                .append("developer", getDeveloper())
                .append("gameType", getGameType())
                .append("score", getScore())
                .append("update", getUpdate())
                .append("descHeightImg", getDescHeightImg())
                .append("descImg", getDescImg())
                .append("descVideo", getDescVideo())
                .append("movieMax", getMovieMax())
                .append("gameUrl", getGameUrl())
                .append("minSpec", getMinSpec())
                .append("desc", getDesc())
                .append("chTitle", getChTitle())
                .append("gameSize", getGameSize())
                .toString();
    }
}
