package com.ruoyi.module.service.impl;

import com.ruoyi.module.domain.Anime;
import com.ruoyi.module.mapper.AnimeMapper;
import com.ruoyi.module.service.IAnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * moduleService业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-19
 */
@Service
public class AnimeServiceImpl implements IAnimeService 
{
    @Autowired
    private AnimeMapper animeMapper;

    /**
     * 查询module
     * 
     * @param id module主键
     * @return module
     */
    @Override
    public Anime selectAnimeById(Long id)
    {
        return animeMapper.selectAnimeById(id);
    }

    /**
     * 查询module列表
     * 
     * @param anime module
     * @return module
     */
    @Override
    public List<Anime> selectAnimeList(Anime anime)
    {
        return animeMapper.selectAnimeList(anime);
    }

    /**
     * 新增module
     * 
     * @param anime module
     * @return 结果
     */
    @Override
    public int insertAnime(Anime anime)
    {
        return animeMapper.insertAnime(anime);
    }

    /**
     * 修改module
     * 
     * @param anime module
     * @return 结果
     */
    @Override
    public int updateAnime(Anime anime)
    {
        return animeMapper.updateAnime(anime);
    }

    /**
     * 批量删除module
     * 
     * @param ids 需要删除的module主键
     * @return 结果
     */
    @Override
    public int deleteAnimeByIds(Long[] ids)
    {
        return animeMapper.deleteAnimeByIds(ids);
    }

    /**
     * 删除module信息
     * 
     * @param id module主键
     * @return 结果
     */
    @Override
    public int deleteAnimeById(Long id)
    {
        return animeMapper.deleteAnimeById(id);
    }

    @Override
    public List<Anime> selectDataByTitle(String title) {
        return animeMapper.selectDataByTitle(title);
    }

}
