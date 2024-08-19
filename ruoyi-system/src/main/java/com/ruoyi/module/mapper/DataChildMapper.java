package com.ruoyi.module.mapper;

import com.ruoyi.module.domain.DataChild;

import java.util.List;

/**
 * data_childMapper接口
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
public interface DataChildMapper 
{
    /**
     * 查询data_child
     * 
     * @param id data_child主键
     * @return data_child
     */
    public DataChild selectDataChildById(Long id);

    /**
     * 查询data_child列表
     * 
     * @param dataChild data_child
     * @return data_child集合
     */
    public List<DataChild> selectDataChildList(DataChild dataChild);

    /**
     * 新增data_child
     * 
     * @param dataChild data_child
     * @return 结果
     */
    public int insertDataChild(DataChild dataChild);

    /**
     * 修改data_child
     * 
     * @param dataChild data_child
     * @return 结果
     */
    public int updateDataChild(DataChild dataChild);

    /**
     * 删除data_child
     * 
     * @param id data_child主键
     * @return 结果
     */
    public int deleteDataChildById(Long id);

    /**
     * 批量删除data_child
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDataChildByIds(Long[] ids);
}
