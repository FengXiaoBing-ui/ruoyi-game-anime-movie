package com.ruoyi.module.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.module.domain.DataMovie;
import com.ruoyi.module.mapper.DataMovieMapper;
import com.ruoyi.module.service.IDataMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * data_movieService业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-08
 */
@Service
public class DataMovieServiceImpl implements IDataMovieService
{
    @Autowired
    private DataMovieMapper dataMovieMapper;

    /**
     * 查询data_movie
     * 
     * @param id data_movie主键
     * @return data_movie
     */
    @Override
    public DataMovie selectDataMovieById(Long id)
    {
        return dataMovieMapper.selectDataMovieById(id);
    }

    /**
     * 查询data_movie列表
     * 
     * @param dataMovie data_movie
     * @return data_movie
     */
    @Override
    public List<DataMovie> selectDataMovieList(DataMovie dataMovie)
    {
        return dataMovieMapper.selectDataMovieList(dataMovie);
    }

    /**
     * 新增data_movie
     * 
     * @param dataMovie data_movie
     * @return 结果
     */
    @Override
    public int insertDataMovie(DataMovie dataMovie)
    {
        dataMovie.setCreateTime(DateUtils.getNowDate());
        return dataMovieMapper.insertDataMovie(dataMovie);
    }

    /**
     * 修改data_movie
     * 
     * @param dataMovie data_movie
     * @return 结果
     */
    @Override
    public int updateDataMovie(DataMovie dataMovie)
    {
        return dataMovieMapper.updateDataMovie(dataMovie);
    }

    @Override
    public int updateMovieUrl(DataMovie dataMovie)
    {
        return dataMovieMapper.updateDataMovie(dataMovie);
    }

    /**
     * 批量删除data_movie
     * 
     * @param ids 需要删除的data_movie主键
     * @return 结果
     */
    @Override
    public int deleteDataMovieByIds(Long[] ids)
    {
        return dataMovieMapper.deleteDataMovieByIds(ids);
    }

    /**
     * 删除data_movie信息
     * 
     * @param id data_movie主键
     * @return 结果
     */
    @Override
    public int deleteDataMovieById(Long id)
    {
        return dataMovieMapper.deleteDataMovieById(id);
    }

    @Override
    public List<DataMovie> selectDataByTitle(String title) {
        return dataMovieMapper.selectDataByTitle(title);
    }

    @Override
    public DataMovie selectMovieUrl1(Long id) {
        return dataMovieMapper.selectMovieUrl1(id);
    }

    @Override
    public DataMovie selectMovieUrl2(Long id) {
        return dataMovieMapper.selectMovieUrl2(id);
    }

    @Override
    public DataMovie selectMovieUrl3(Long id) {
        return dataMovieMapper.selectMovieUrl3(id);
    }
}
