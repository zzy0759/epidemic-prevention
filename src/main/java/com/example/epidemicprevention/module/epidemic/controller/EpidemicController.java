package com.example.epidemicprevention.module.epidemic.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.epidemicprevention.module.epidemic.vo.EpidemicAddVO;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.epidemic.entity.Epidemic;
import com.example.epidemicprevention.module.epidemic.service.EpidemicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @Author ZZY
 * @Date 2022/1/17
 */
@Api(tags = "Epidemic")
@RestController
@RequestMapping("/epidemic")
public class EpidemicController {
    @Autowired
    private EpidemicService epidemicService;

    @PostMapping("/add")
    @ApiOperation("添加疫情")
    public Result<Object> addEpidemic(@RequestBody @Validated EpidemicAddVO epidemicAddVo) {
        return epidemicService.insertEpidemic(epidemicAddVo);
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    @Deprecated
    public Result<IPage<Epidemic>> getEpidemicPage(
            @RequestParam(name = "current", defaultValue = "1") Integer current,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            Epidemic epidemic) {
        IPage<Epidemic> epidemicPage = epidemicService.getEpidemicPage(current, size, epidemic);
        return Result.OK(epidemicPage);
    }

    @ApiOperation("修改")
    @PutMapping
    @Deprecated
    public Result<Epidemic> update(@Validated(Epidemic.update.class) @RequestBody Epidemic epidemic) {
        epidemicService.updateById(epidemic);
        return Result.OK();
    }

    @ApiOperation("新增")
    @PostMapping
    @Deprecated
    public Result<Object> add(@Validated(Epidemic.insert.class) @RequestBody Epidemic epidemic) {
        epidemicService.save(epidemic);
        return Result.OK();
    }

    @ApiOperation("通过id查询")
    @GetMapping("/id")
    @Deprecated
    public Result<Epidemic> getById(@RequestParam String id) {
        Epidemic epidemic = epidemicService.getById(id);
        return Result.OK(epidemic);
    }

    @ApiOperation("查询所有")
    @GetMapping
    @Deprecated
    public Result<List<Epidemic>> getAll(Epidemic epidemic) {
        List<Epidemic> epidemicList = epidemicService.getAll(epidemic);
        return Result.OK(epidemicList);
    }

    @ApiOperation("通过id删除")
    @DeleteMapping("/id")
    @Deprecated
    public Result<Object> deleteById(@RequestParam String id) {
        return epidemicService.deleteById(id);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    @Deprecated
    public Result<Object> batchDelete(@RequestParam List<String> ids) {
        return epidemicService.batchDelete(ids);
    }
}
