package com.ruoyi.module.mapper;

import com.ruoyi.module.domain.DataMovie;

import java.util.List;

/**
 * data_movieMapper接口
 * 
 * @author ruoyi
 * @date 2024-08-08
 */
public interface DataMovieMapper 
{
    /**
     * 查询data_movie
     * 
     * @param id data_movie主键
     * @return data_movie
     */
    public DataMovie selectDataMovieById(Long id);

    /**
     * 查询data_movie列表
     * 
     * @param dataMovie data_movie
     * @return data_movie集合
     */
    public List<DataMovie> selectDataMovieList(DataMovie dataMovie);

    /**
     * 新增data_movie
     * 
     * @param dataMovie data_movie
     * @return 结果
     */
    public int insertDataMovie(DataMovie dataMovie);

    /**
     * 修改data_movie
     * 
     * @param dataMovie data_movie
     * @return 结果
     */
    public int updateDataMovie(DataMovie dataMovie);

    public int updateMovieUrl(DataMovie dataMovie);

    /**
     * 删除data_movie
     * 
     * @param id data_movie主键
     * @return 结果
     */
    public int deleteDataMovieById(Long id);

    /**
     * 批量删除data_movie
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDataMovieByIds(Long[] ids);

    List<DataMovie> selectDataByTitle(String title);

    DataMovie selectMovieUrl1(long id);

    DataMovie selectMovieUrl2(long id);

    DataMovie selectMovieUrl3(long id);
}
