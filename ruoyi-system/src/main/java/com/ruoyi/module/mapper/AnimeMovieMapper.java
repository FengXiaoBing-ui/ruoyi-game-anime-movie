package com.ruoyi.module.mapper;

import com.ruoyi.module.domain.AnimeMovie;

import java.util.List;

/**
 * anime_movieMapper接口
 * 
 * @author ruoyi
 * @date 2024-07-31
 */
public interface AnimeMovieMapper 
{
    /**
     * 查询anime_movie
     * 
     * @param id anime_movie主键
     * @return anime_movie
     */
    public AnimeMovie selectAnimeMovieById(Long id);

    /**
     * 查询anime_movie列表
     * 
     * @param animeMovie anime_movie
     * @return anime_movie集合
     */
    public List<AnimeMovie> selectAnimeMovieList(AnimeMovie animeMovie);

    /**
     * 新增anime_movie
     * 
     * @param animeMovie anime_movie
     * @return 结果
     */
    public int insertAnimeMovie(AnimeMovie animeMovie);

    /**
     * 修改anime_movie
     * 
     * @param animeMovie anime_movie
     * @return 结果
     */
    public int updateAnimeMovie(AnimeMovie animeMovie);

    /**
     * 删除anime_movie
     * 
     * @param id anime_movie主键
     * @return 结果
     */
    public int deleteAnimeMovieById(Long id);

    /**
     * 批量删除anime_movie
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnimeMovieByIds(Long[] ids);

    List<AnimeMovie> selectDataByTitle(String title);
}
