package com.ruoyi.module.service;

import com.ruoyi.module.domain.Data;

import java.util.List;

/**
 * moduleService接口
 * 
 * @author fxb
 * @date 2024-08-13
 */
public interface IDataService 
{
    /**
     * 查询module
     * 
     * @param id module主键
     * @return module
     */
    public Data selectDataById(Long id);

    /**
     * 查询module列表
     * 
     * @param data module
     * @return module集合
     */
    public List<Data> selectDataListLimit(Data data);

    /**
     * 新增module
     * 
     * @param data module
     * @return 结果
     */
    public int insertData(Data data);

    /**
     * 修改module
     * 
     * @param data module
     * @return 结果
     */
    public int updateData(Data data);

    /**
     * 批量删除module
     * 
     * @param ids 需要删除的module主键集合
     * @return 结果
     */
    public int deleteDataByIds(Long[] ids);

    /**
     * 删除module信息
     * 
     * @param id module主键
     * @return 结果
     */
    public int deleteDataById(Long id);

    List<Data> selectDataByTitle(Long gameId);
}
