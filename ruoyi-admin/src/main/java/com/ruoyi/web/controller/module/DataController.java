package com.ruoyi.web.controller.module;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.module.domain.Data;
import com.ruoyi.module.mapper.DataMapper;
import com.ruoyi.module.service.IDataChildService;
import com.ruoyi.module.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruoyi.module.domain.Data;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * moduleController
 *
 * @author fxb
 * @date 2024-08-13
 */
@Anonymous
@RestController
@RequestMapping("/module/dataGame")
public class DataController extends BaseController {
    @Autowired
    private IDataService dataService;
    @Autowired
    private IDataChildService dataChildService;
    @Autowired
    private DataMapper dataMapper;

    /**
     * 查询module列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Data data) {
        startPage();
        List<Data> list = dataService.selectDataListLimit(data);
        return getDataTable(list);
    }


    /**
     * 导出module列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, Data data) {
        List<Data> list = dataService.selectDataListLimit(data);
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

    @Anonymous
    @GetMapping(value = "dataChild/{id}")
    public AjaxResult dataChild(@PathVariable("id") Long id) {
        return success(dataChildService.selectDataChildById(id));
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
