package com.ruoyi.module.service;

import com.ruoyi.module.domain.Anime;

import java.util.List;

/**
 * moduleService接口
 * 
 * @author ruoyi
 * @date 2024-07-19
 */
public interface IAnimeService 
{
    /**
     * 查询module
     * 
     * @param id module主键
     * @return module
     */
    public Anime selectAnimeById(Long id);

    /**
     * 查询module列表
     * 
     * @param anime module
     * @return module集合
     */
    public List<Anime> selectAnimeList(Anime anime);

    /**
     * 新增module
     * 
     * @param anime module
     * @return 结果
     */
    public int insertAnime(Anime anime);

    /**
     * 修改module
     * 
     * @param anime module
     * @return 结果
     */
    public int updateAnime(Anime anime);

    /**
     * 批量删除module
     * 
     * @param ids 需要删除的module主键集合
     * @return 结果
     */
    public int deleteAnimeByIds(Long[] ids);

    /**
     * 删除module信息
     * 
     * @param id module主键
     * @return 结果
     */
    public int deleteAnimeById(Long id);

    List<Anime> selectDataByTitle(String title);
}
