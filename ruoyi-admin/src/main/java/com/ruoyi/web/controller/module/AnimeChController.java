package com.ruoyi.web.controller.module;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.module.domain.AnimeCh;
import com.ruoyi.module.service.IAnimeChService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * moduleController
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
@Anonymous
@RestController
@RequestMapping("/module/chAnime")
public class AnimeChController extends BaseController
{
    @Autowired
    private IAnimeChService animeChService;

    /**
     * 查询module列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AnimeCh animeCh)
    {
        startPage();
        List<AnimeCh> list = animeChService.selectAnimeChList(animeCh);
        return getDataTable(list);
    }

    /**
     * 导出module列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, AnimeCh animeCh)
    {
        List<AnimeCh> list = animeChService.selectAnimeChList(animeCh);
        ExcelUtil<AnimeCh> util = new ExcelUtil<AnimeCh>(AnimeCh.class);
        util.exportExcel(response, list, "module数据");
    }

    /**
     * 获取module详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(animeChService.selectAnimeChById(id));
    }

    /**
     * 新增module
     */
    @PostMapping
    public AjaxResult add(@RequestBody AnimeCh animeCh)
    {
        return toAjax(animeChService.insertAnimeCh(animeCh));
    }

    /**
     * 修改module
     */
    @PutMapping
    public AjaxResult edit(@RequestBody AnimeCh animeCh)
    {
        return toAjax(animeChService.updateAnimeCh(animeCh));
    }

    /**
     * 删除module
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(animeChService.deleteAnimeChByIds(ids));
    }
}
