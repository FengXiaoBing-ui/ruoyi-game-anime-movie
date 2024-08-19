package com.ruoyi.web.controller.module;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.module.domain.Anime;
import com.ruoyi.module.service.IAnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * moduleController
 *
 * @author ruoyi
 * @date 2024-07-19
 */
@Anonymous
@RestController
@RequestMapping("/module/anime")
public class AnimeController extends BaseController {
    @Autowired
    private IAnimeService animeService;

    /**
     * 查询module列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Anime anime) {
        System.out.println(anime);
        startPage();
        List<Anime> list = animeService.selectAnimeList(anime);
        return getDataTable(list);
    }

    /**
     * 导出module列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, Anime anime) {
        List<Anime> list = animeService.selectAnimeList(anime);
        ExcelUtil<Anime> util = new ExcelUtil<Anime>(Anime.class);
        util.exportExcel(response, list, "module数据");
    }

    /**
     * 获取module详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(animeService.selectAnimeById(id));
    }

    /**
     * 新增module
     */
    @PostMapping
    public AjaxResult add(@RequestBody Anime anime) {
        return toAjax(animeService.insertAnime(anime));
    }

    /**
     * 修改module
     */
    @PutMapping
    public AjaxResult edit(@RequestBody Anime anime) {
        return toAjax(animeService.updateAnime(anime));
    }

    /**
     * 删除module
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(animeService.deleteAnimeByIds(ids));
    }
}
