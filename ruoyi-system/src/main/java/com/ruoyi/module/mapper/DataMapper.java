package com.ruoyi.module.mapper;

import com.ruoyi.module.domain.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * moduleMapper接口
 * 
 * @author fxb
 * @date 2024-08-13
 */
public interface DataMapper 
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
     * 删除module
     * 
     * @param id module主键
     * @return 结果
     */
    public int deleteDataById(Long id);

    /**
     * 批量删除module
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDataByIds(Long[] ids);

    List<Data> selectDataByTitle(Long gameId);

    List<Data> selectDataTitle(@Param("title") String title);


}
