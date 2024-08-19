package com.ruoyi.module.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * data_movie对象 data_movie
 *
 * @author ruoyi
 * @date 2024-08-08
 */
public class DataMovie extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String movieTitle;

    /**
     * 类型
     */
    @Excel(name = "类型")
    private String movieType;

    /**
     * 地区
     */
    @Excel(name = "地区")
    private String movieArea;

    /**
     * 年份
     */
    @Excel(name = "年份")
    private String movieYear;

    /**
     * 评分
     */
    @Excel(name = "评分")
    private String movieScore;

    /**
     * 语言
     */
    @Excel(name = "语言")
    private String movieLanguage;

    /**
     * 演员
     */
    @Excel(name = "演员")
    private String performer;

    /**
     * 导演
     */
    @Excel(name = "导演")
    private String director;

    /**
     * 版本
     */
    @Excel(name = "版本")
    private String movieEdition;

    /**
     * 更新
     */
    @Excel(name = "更新")
    private String updateMovie;

    /**
     * 简介1
     */
    @Excel(name = "简介1")
    private String movieDesc;

    /**
     * 简介2
     */
    @Excel(name = "简介2")
    private String movieDesc2;

    /**
     * 链接名1
     */
    @Excel(name = "链接名1")
    private String hrefName1;

    /**
     * 链接文字1
     */
    @Excel(name = "链接文字1")
    private String hrefText1;

    /**
     * 链接地址1
     */
    @Excel(name = "链接地址1")
    private String linkHref1;

    /**
     * 视频地址1
     */
    @Excel(name = "视频地址1")
    private String movieUrl1;

    /**
     * 链接名2
     */
    @Excel(name = "链接名2")
    private String hrefName2;

    /**
     * 链接文字2
     */
    @Excel(name = "链接文字2")
    private String hrefText2;

    /**
     * 链接地址2
     */
    @Excel(name = "链接地址2")
    private String linkHref2;

    /**
     * 视频地址2
     */
    @Excel(name = "视频地址2")
    private String movieUrl2;

    /**
     * 链接名3
     */
    @Excel(name = "链接名3")
    private String hrefName3;

    /**
     * 链接文字3
     */
    @Excel(name = "链接文字3")
    private String hrefText3;

    /**
     * 链接地址3
     */
    @Excel(name = "链接地址3")
    private String linkHref3;

    /**
     * 视频地址3
     */
    @Excel(name = "视频地址3")
    private String movieUrl3;

    /**
     * 封面
     */
    @Excel(name = "封面")
    private String image;

    /**
     * 分类
     */
    @Excel(name = "分类")
    private String category;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieArea(String movieArea) {
        this.movieArea = movieArea;
    }

    public String getMovieArea() {
        return movieArea;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieScore(String movieScore) {
        this.movieScore = movieScore;
    }

    public String getMovieScore() {
        return movieScore;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getPerformer() {
        return performer;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public void setMovieEdition(String movieEdition) {
        this.movieEdition = movieEdition;
    }

    public String getMovieEdition() {
        return movieEdition;
    }

    public void setUpdateMovie(String updateMovie) {
        this.updateMovie = updateMovie;
    }

    public String getUpdateMovie() {
        return updateMovie;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc2(String movieDesc2) {
        this.movieDesc2 = movieDesc2;
    }

    public String getMovieDesc2() {
        return movieDesc2;
    }

    public void setHrefName1(String hrefName1) {
        this.hrefName1 = hrefName1;
    }

    public String getHrefName1() {
        return hrefName1;
    }

    public void setHrefText1(String hrefText1) {
        this.hrefText1 = hrefText1;
    }

    public String getHrefText1() {
        return hrefText1;
    }

    public void setLinkHref1(String linkHref1) {
        this.linkHref1 = linkHref1;
    }

    public String getLinkHref1() {
        return linkHref1;
    }

    public void setMovieUrl1(String movieUrl1) {
        this.movieUrl1 = movieUrl1;
    }

    public String getMovieUrl1() {
        return movieUrl1;
    }

    public void setHrefName2(String hrefName2) {
        this.hrefName2 = hrefName2;
    }

    public String getHrefName2() {
        return hrefName2;
    }

    public void setHrefText2(String hrefText2) {
        this.hrefText2 = hrefText2;
    }

    public String getHrefText2() {
        return hrefText2;
    }

    public void setLinkHref2(String linkHref2) {
        this.linkHref2 = linkHref2;
    }

    public String getLinkHref2() {
        return linkHref2;
    }

    public void setMovieUrl2(String movieUrl2) {
        this.movieUrl2 = movieUrl2;
    }

    public String getMovieUrl2() {
        return movieUrl2;
    }

    public void setHrefName3(String hrefName3) {
        this.hrefName3 = hrefName3;
    }

    public String getHrefName3() {
        return hrefName3;
    }

    public void setHrefText3(String hrefText3) {
        this.hrefText3 = hrefText3;
    }

    public String getHrefText3() {
        return hrefText3;
    }

    public void setLinkHref3(String linkHref3) {
        this.linkHref3 = linkHref3;
    }

    public String getLinkHref3() {
        return linkHref3;
    }

    public void setMovieUrl3(String movieUrl3) {
        this.movieUrl3 = movieUrl3;
    }

    public String getMovieUrl3() {
        return movieUrl3;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("movieTitle", getMovieTitle())
                .append("movieType", getMovieType())
                .append("movieArea", getMovieArea())
                .append("movieYear", getMovieYear())
                .append("movieScore", getMovieScore())
                .append("movieLanguage", getMovieLanguage())
                .append("performer", getPerformer())
                .append("director", getDirector())
                .append("movieEdition", getMovieEdition())
                .append("updateMovie", getUpdateMovie())
                .append("createTime", getCreateTime())
                .append("movieDesc", getMovieDesc())
                .append("movieDesc2", getMovieDesc2())
                .append("hrefName1", getHrefName1())
                .append("hrefText1", getHrefText1())
                .append("linkHref1", getLinkHref1())
                .append("movieUrl1", getMovieUrl1())
                .append("hrefName2", getHrefName2())
                .append("hrefText2", getHrefText2())
                .append("linkHref2", getLinkHref2())
                .append("movieUrl2", getMovieUrl2())
                .append("hrefName3", getHrefName3())
                .append("hrefText3", getHrefText3())
                .append("linkHref3", getLinkHref3())
                .append("movieUrl3", getMovieUrl3())
                .append("image", getImage())
                .append("category", getCategory())
                .toString();
    }
}
