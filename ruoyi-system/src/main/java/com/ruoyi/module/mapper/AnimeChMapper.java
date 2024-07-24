package com.ruoyi.module.mapper;

import com.ruoyi.module.domain.AnimeCh;

import java.util.List;

/**
 * moduleMapper接口
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
public interface AnimeChMapper 
{
    /**
     * 查询module
     * 
     * @param id module主键
     * @return module
     */
    public AnimeCh selectAnimeChById(Long id);

    /**
     * 查询module列表
     * 
     * @param animeCh module
     * @return module集合
     */
    public List<AnimeCh> selectAnimeChList(AnimeCh animeCh);

    /**
     * 新增module
     * 
     * @param animeCh module
     * @return 结果
     */
    public int insertAnimeCh(AnimeCh animeCh);

    /**
     * 修改module
     * 
     * @param animeCh module
     * @return 结果
     */
    public int updateAnimeCh(AnimeCh animeCh);

    /**
     * 删除module
     * 
     * @param id module主键
     * @return 结果
     */
    public int deleteAnimeChById(Long id);

    /**
     * 批量删除module
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnimeChByIds(Long[] ids);
}
