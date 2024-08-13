package com.ruoyi.web.controller.module;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.module.domain.Data;
import com.ruoyi.module.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * moduleController
 * 
 * @author fxb
 * @date 2024-08-13
 */
@RestController
@RequestMapping("/module/dataGame")
public class DataController extends BaseController
{
    @Autowired
    private IDataService dataService;

    /**
     * 查询module列表
     */
    @PreAuthorize("@ss.hasPermi('module:dataGame:list')")
    @GetMapping("/list")
    public TableDataInfo list(Data data)
    {
        startPage();
        List<Data> list = dataService.selectDataList(data);
        return getDataTable(list);
    }

    /**
     * 导出module列表
     */
    @PreAuthorize("@ss.hasPermi('module:dataGame:export')")
    @Log(title = "module", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Data data)
    {
        List<Data> list = dataService.selectDataList(data);
        ExcelUtil<Data> util = new ExcelUtil<Data>(Data.class);
        util.exportExcel(response, list, "module数据");
    }

    /**
     * 获取module详细信息
     */
    @PreAuthorize("@ss.hasPermi('module:dataGame:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dataService.selectDataById(id));
    }

    /**
     * 新增module
     */
    @PreAuthorize("@ss.hasPermi('module:dataGame:add')")
    @Log(title = "module", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Data data)
    {
        return toAjax(dataService.insertData(data));
    }

    /**
     * 修改module
     */
    @PreAuthorize("@ss.hasPermi('module:dataGame:edit')")
    @Log(title = "module", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Data data)
    {
        return toAjax(dataService.updateData(data));
    }

    /**
     * 删除module
     */
    @PreAuthorize("@ss.hasPermi('module:dataGame:remove')")
    @Log(title = "module", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dataService.deleteDataByIds(ids));
    }
}
