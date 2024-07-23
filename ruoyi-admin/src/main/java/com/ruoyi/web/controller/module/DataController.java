package com.ruoyi.web.controller.module;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.module.domain.Data;
import com.ruoyi.module.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * moduleController
 *
 * @author fxb
 * @date 2024-07-17
 */
@Anonymous
@RestController
@RequestMapping("/module/data")
public class DataController extends BaseController {
    @Autowired
    private IDataService dataService;

    /**
     * 查询module列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Data data) {
        startPage();
        List<Data> list = dataService.selectDataList(data);
        return getDataTable(list);
    }

    /**
     * 导出module列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, Data data) {
        List<Data> list = dataService.selectDataList(data);
        ExcelUtil<Data> util = new ExcelUtil<Data>(Data.class);
        util.exportExcel(response, list, "module数据");
    }

    /**
     * 获取module详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(dataService.selectDataById(id));
    }

    /**
     * 新增module
     */

    @PostMapping
    public AjaxResult add(@RequestBody Data data) {
        return toAjax(dataService.insertData(data));
    }

    /**
     * 修改module
     */

    @PutMapping
    public AjaxResult edit(@RequestBody Data data) {
        return toAjax(dataService.updateData(data));
    }

    /**
     * 删除module
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(dataService.deleteDataByIds(ids));
    }
}
