package com.ruoyi.web.controller.module;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.module.domain.AnimeMovie;
import com.ruoyi.module.service.IAnimeMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * anime_movieController
 *
 * @author ruoyi
 * @date 2024-07-31
 */
@Anonymous
@RestController
@RequestMapping("/module/movie")
public class AnimeMovieController extends BaseController {
    @Autowired
    private IAnimeMovieService animeMovieService;

    /**
     * 查询anime_movie列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AnimeMovie animeMovie) {
        startPage();
        List<AnimeMovie> list = animeMovieService.selectAnimeMovieList(animeMovie);
        return getDataTable(list);
    }

    /**
     * 导出anime_movie列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, AnimeMovie animeMovie) {
        List<AnimeMovie> list = animeMovieService.selectAnimeMovieList(animeMovie);
        ExcelUtil<AnimeMovie> util = new ExcelUtil<AnimeMovie>(AnimeMovie.class);
        util.exportExcel(response, list, "anime_movie数据");
    }

    /**
     * 获取anime_movie详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(animeMovieService.selectAnimeMovieById(id));
    }

    /**
     * 新增anime_movie
     */
    @PostMapping
    public AjaxResult add(@RequestBody AnimeMovie animeMovie) {
        return toAjax(animeMovieService.insertAnimeMovie(animeMovie));
    }

    /**
     * 修改anime_movie
     */
    @PutMapping
    public AjaxResult edit(@RequestBody AnimeMovie animeMovie) {
        return toAjax(animeMovieService.updateAnimeMovie(animeMovie));
    }

    /**
     * 删除anime_movie
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(animeMovieService.deleteAnimeMovieByIds(ids));
    }
}
