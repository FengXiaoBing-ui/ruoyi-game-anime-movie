package com.ruoyi.module.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.module.domain.AnimeMovie;
import com.ruoyi.module.mapper.AnimeMovieMapper;
import com.ruoyi.module.service.IAnimeMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * anime_movieService业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-31
 */
@Service
public class AnimeMovieServiceImpl implements IAnimeMovieService 
{
    @Autowired
    private AnimeMovieMapper animeMovieMapper;

    /**
     * 查询anime_movie
     * 
     * @param id anime_movie主键
     * @return anime_movie
     */
    @Override
    public AnimeMovie selectAnimeMovieById(Long id)
    {
        return animeMovieMapper.selectAnimeMovieById(id);
    }

    /**
     * 查询anime_movie列表
     * 
     * @param animeMovie anime_movie
     * @return anime_movie
     */
    @Override
    public List<AnimeMovie> selectAnimeMovieList(AnimeMovie animeMovie)
    {
        return animeMovieMapper.selectAnimeMovieList(animeMovie);
    }

    /**
     * 新增anime_movie
     * 
     * @param animeMovie anime_movie
     * @return 结果
     */
    @Override
    public int insertAnimeMovie(AnimeMovie animeMovie)
    {
        animeMovie.setCreateTime(DateUtils.getNowDate());
        return animeMovieMapper.insertAnimeMovie(animeMovie);
    }

    /**
     * 修改anime_movie
     * 
     * @param animeMovie anime_movie
     * @return 结果
     */
    @Override
    public int updateAnimeMovie(AnimeMovie animeMovie)
    {
        return animeMovieMapper.updateAnimeMovie(animeMovie);
    }

    /**
     * 批量删除anime_movie
     * 
     * @param ids 需要删除的anime_movie主键
     * @return 结果
     */
    @Override
    public int deleteAnimeMovieByIds(Long[] ids)
    {
        return animeMovieMapper.deleteAnimeMovieByIds(ids);
    }

    /**
     * 删除anime_movie信息
     * 
     * @param id anime_movie主键
     * @return 结果
     */
    @Override
    public int deleteAnimeMovieById(Long id)
    {
        return animeMovieMapper.deleteAnimeMovieById(id);
    }

    @Override
    public List<AnimeMovie> selectDataByTitle(String title) {
        return animeMovieMapper.selectDataByTitle(title);
    }
}
