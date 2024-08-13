package com.ruoyi.web.controller.module;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.module.domain.DataMovie;
import com.ruoyi.module.service.IDataMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * data_movieController
 *
 * @author ruoyi
 * @date 2024-08-08
 */
@Anonymous
@RestController
@RequestMapping("/module/data_movie")
public class DataMovieController extends BaseController {
    @Autowired
    private IDataMovieService dataMovieService;

    /**
     * 查询data_movie列表
     */
    @GetMapping("/list")
    public TableDataInfo list(DataMovie dataMovie) {
        startPage();
        List<DataMovie> list = dataMovieService.selectDataMovieList(dataMovie);
        return getDataTable(list);
    }

    /**
     * 导出data_movie列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, DataMovie dataMovie) {
        List<DataMovie> list = dataMovieService.selectDataMovieList(dataMovie);
        ExcelUtil<DataMovie> util = new ExcelUtil<DataMovie>(DataMovie.class);
        util.exportExcel(response, list, "data_movie数据");
    }

    /**
     * 获取data_movie详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) throws Exception {
        return success(dataMovieService.selectDataMovieById(id));
    }

    /**
     * 新增data_movie
     */
    @PostMapping
    public AjaxResult add(@RequestBody DataMovie dataMovie) {
        return toAjax(dataMovieService.insertDataMovie(dataMovie));
    }

    /**
     * 修改data_movie
     */
    @PutMapping
    public AjaxResult edit(@RequestBody DataMovie dataMovie) {
        return toAjax(dataMovieService.updateDataMovie(dataMovie));
    }

    /**
     * 删除data_movie
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(dataMovieService.deleteDataMovieByIds(ids));
    }
}
