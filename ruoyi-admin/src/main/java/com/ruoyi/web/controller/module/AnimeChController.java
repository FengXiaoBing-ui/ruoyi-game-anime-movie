package com.ruoyi.web.controller.module;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.module.domain.AnimeCh;
import com.ruoyi.module.service.IAnimeChService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * moduleController
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
@RestController
@RequestMapping("/module/chAnime")
public class AnimeChController extends BaseController
{
    @Autowired
    private IAnimeChService animeChService;

    /**
     * 查询module列表
     */
    @PreAuthorize("@ss.hasPermi('module:chAnime:list')")
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
    @PreAuthorize("@ss.hasPermi('module:chAnime:export')")
    @Log(title = "module", businessType = BusinessType.EXPORT)
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
    @PreAuthorize("@ss.hasPermi('module:chAnime:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(animeChService.selectAnimeChById(id));
    }

    /**
     * 新增module
     */
    @PreAuthorize("@ss.hasPermi('module:chAnime:add')")
    @Log(title = "module", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AnimeCh animeCh)
    {
        return toAjax(animeChService.insertAnimeCh(animeCh));
    }

    /**
     * 修改module
     */
    @PreAuthorize("@ss.hasPermi('module:chAnime:edit')")
    @Log(title = "module", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AnimeCh animeCh)
    {
        return toAjax(animeChService.updateAnimeCh(animeCh));
    }

    /**
     * 删除module
     */
    @PreAuthorize("@ss.hasPermi('module:chAnime:remove')")
    @Log(title = "module", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(animeChService.deleteAnimeChByIds(ids));
    }
}
