package com.ruoyi.module.service.impl;

import com.ruoyi.module.domain.DataChild;
import com.ruoyi.module.mapper.DataChildMapper;
import com.ruoyi.module.service.IDataChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * data_childService业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
@Service
public class DataChildServiceImpl implements IDataChildService 
{
    @Autowired
    private DataChildMapper dataChildMapper;

    /**
     * 查询data_child
     * 
     * @param id data_child主键
     * @return data_child
     */
    @Override
    public DataChild selectDataChildById(Long id)
    {
        return dataChildMapper.selectDataChildById(id);
    }

    /**
     * 查询data_child列表
     * 
     * @param dataChild data_child
     * @return data_child
     */
    @Override
    public List<DataChild> selectDataChildList(DataChild dataChild)
    {
        return dataChildMapper.selectDataChildList(dataChild);
    }

    /**
     * 新增data_child
     * 
     * @param dataChild data_child
     * @return 结果
     */
    @Override
    public int insertDataChild(DataChild dataChild)
    {
        return dataChildMapper.insertDataChild(dataChild);
    }

    /**
     * 修改data_child
     * 
     * @param dataChild data_child
     * @return 结果
     */
    @Override
    public int updateDataChild(DataChild dataChild)
    {
        return dataChildMapper.updateDataChild(dataChild);
    }

    /**
     * 批量删除data_child
     * 
     * @param ids 需要删除的data_child主键
     * @return 结果
     */
    @Override
    public int deleteDataChildByIds(Long[] ids)
    {
        return dataChildMapper.deleteDataChildByIds(ids);
    }

    /**
     * 删除data_child信息
     * 
     * @param id data_child主键
     * @return 结果
     */
    @Override
    public int deleteDataChildById(Long id)
    {
        return dataChildMapper.deleteDataChildById(id);
    }
}
