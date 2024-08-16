package com.ruoyi.module.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.EqualsAndHashCode;

/**
 * module对象 data
 *
 * @author fxb
 * @date 2024-08-13
 */
@EqualsAndHashCode(callSuper = true)
@lombok.Data
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
    private Long downLoadCount;

    /**
     * 封面
     */
    @Excel(name = "封面")
    private String coverImg;

    /**
     * 中文标题
     */
    @Excel(name = "中文标题")
    private String zhTitle;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;

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
     * 评分
     */
    @Excel(name = "评分")
    private String score;

    /**
     * 游戏下载地址
     */
    @Excel(name = "游戏下载地址")
    private String gameUrl;

    /**
     * 游戏大小
     */
    @Excel(name = "游戏大小")
    private String gameSize;

    /**
     * 游戏类型
     */
    @Excel(name = "游戏类型")
    private String gameType;

    /**
     * 发行商
     */
    @Excel(name = "发行商")
    private String publisher;

    /**
     * 标签列表
     */
    @Excel(name = "标签列表")
    private String tagList;

    /**
     * 文字大小kb
     */
    @Excel(name = "文字大小kb")
    private Double gameSizeKb;

    /**
     * 游戏ID
     */
    @Excel(name = "游戏ID")
    private Long gameId;
}
