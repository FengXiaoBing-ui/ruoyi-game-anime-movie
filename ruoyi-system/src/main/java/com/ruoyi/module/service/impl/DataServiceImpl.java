package com.ruoyi.module.service.impl;

import com.ruoyi.module.domain.Data;
import com.ruoyi.module.mapper.DataMapper;
import com.ruoyi.module.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * moduleService业务层处理
 * 
 * @author fxb
 * @date 2024-08-13
 */
@Service
public class DataServiceImpl implements IDataService 
{
    @Autowired
    private DataMapper dataMapper;

    /**
     * 查询module
     * 
     * @param id module主键
     * @return module
     */
    @Override
    public Data selectDataById(Long id)
    {
        return dataMapper.selectDataById(id);
    }

    /**
     * 查询module列表
     * 
     * @param data module
     * @return module
     */
    @Override
    public List<Data> selectDataListLimit(Data data)
    {
        return dataMapper.selectDataListLimit(data);
    }

    /**
     * 新增module
     * 
     * @param data module
     * @return 结果
     */
    @Override
    public int insertData(Data data)
    {
        return dataMapper.insertData(data);
    }

    /**
     * 修改module
     * 
     * @param data module
     * @return 结果
     */
    @Override
    public int updateData(Data data)
    {
        return dataMapper.updateData(data);
    }

    /**
     * 批量删除module
     * 
     * @param ids 需要删除的module主键
     * @return 结果
     */
    @Override
    public int deleteDataByIds(Long[] ids)
    {
        return dataMapper.deleteDataByIds(ids);
    }

    /**
     * 删除module信息
     * 
     * @param id module主键
     * @return 结果
     */
    @Override
    public int deleteDataById(Long id)
    {
        return dataMapper.deleteDataById(id);
    }

    @Override
    public List<Data> selectDataByTitle(Long gameId) {
        return dataMapper.selectDataByTitle(gameId);
    }
}
