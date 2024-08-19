package com.ruoyi.module.service.impl;

import com.ruoyi.module.domain.AnimeCh;
import com.ruoyi.module.mapper.AnimeChMapper;
import com.ruoyi.module.service.IAnimeChService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * moduleService业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
@Service
public class AnimeChServiceImpl implements IAnimeChService 
{
    @Autowired
    private AnimeChMapper animeChMapper;

    /**
     * 查询module
     * 
     * @param id module主键
     * @return module
     */
    @Override
    public AnimeCh selectAnimeChById(Long id)
    {
        return animeChMapper.selectAnimeChById(id);
    }

    /**
     * 查询module列表
     * 
     * @param animeCh module
     * @return module
     */
    @Override
    public List<AnimeCh> selectAnimeChList(AnimeCh animeCh)
    {
        return animeChMapper.selectAnimeChList(animeCh);
    }

    /**
     * 新增module
     * 
     * @param animeCh module
     * @return 结果
     */
    @Override
    public int insertAnimeCh(AnimeCh animeCh)
    {
        return animeChMapper.insertAnimeCh(animeCh);
    }

    /**
     * 修改module
     * 
     * @param animeCh module
     * @return 结果
     */
    @Override
    public int updateAnimeCh(AnimeCh animeCh)
    {
        return animeChMapper.updateAnimeCh(animeCh);
    }

    /**
     * 批量删除module
     * 
     * @param ids 需要删除的module主键
     * @return 结果
     */
    @Override
    public int deleteAnimeChByIds(Long[] ids)
    {
        return animeChMapper.deleteAnimeChByIds(ids);
    }

    /**
     * 删除module信息
     * 
     * @param id module主键
     * @return 结果
     */
    @Override
    public int deleteAnimeChById(Long id)
    {
        return animeChMapper.deleteAnimeChById(id);
    }

    @Override
    public List<AnimeCh> selectDataByTitle(String title) {
        return animeChMapper.selectDataByTitle(title);
    }
}
