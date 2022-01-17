package com.example.epidemicprevention.module.zeroPatient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.zeroPatient.entity.ZeroPatient;
import com.example.epidemicprevention.module.zeroPatient.service.ZeroPatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "ZeroPatient")
@RestController
@RequestMapping("/zeroPatient")
public class ZeroPatientController {
    @Autowired
    private ZeroPatientService zeroPatientService;

    @ApiOperation("分页查询")
    @GetMapping("/page")
    @Deprecated
    public Result<IPage<ZeroPatient>> getZeroPatientPage(
        @RequestParam(name = "current", defaultValue = "1") Integer current,
        @RequestParam(name = "size", defaultValue = "10") Integer size,
        ZeroPatient zeroPatient) {
        IPage<ZeroPatient> zeroPatientPage=zeroPatientService.getZeroPatientPage(current,size,zeroPatient);
        return Result.OK(zeroPatientPage);
    }

    @ApiOperation("修改")
    @PutMapping
    @Deprecated
    public Result<ZeroPatient> update(@Validated(ZeroPatient.update.class) @RequestBody ZeroPatient zeroPatient) {
        zeroPatientService.updateById(zeroPatient);
        return Result.OK();
    }

    @ApiOperation("新增")
    @PostMapping
    @Deprecated
    public Result<Object> add(@Validated(ZeroPatient.insert.class) @RequestBody ZeroPatient zeroPatient) {
        zeroPatientService.save(zeroPatient);
        return Result.OK();
    }

    @ApiOperation("通过id查询")
    @GetMapping("/id")
    @Deprecated
    public Result<ZeroPatient> getById(@RequestParam String id){
        ZeroPatient zeroPatient=zeroPatientService.getById(id);
        return Result.OK(zeroPatient);
    }

    @ApiOperation("查询所有")
    @GetMapping
    @Deprecated
    public Result<List<ZeroPatient>> getAll(ZeroPatient zeroPatient){
        List<ZeroPatient> zeroPatientList=zeroPatientService.getAll(zeroPatient);
        return Result.OK(zeroPatientList);
    }

    @ApiOperation("通过id删除")
    @DeleteMapping("/id")
    @Deprecated
    public Result<Object> deleteById(@RequestParam String id){
        return zeroPatientService.deleteById(id);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    @Deprecated
    public Result<Object> batchDelete(@RequestParam List<String> ids){
        return zeroPatientService.batchDelete(ids);
    }
}
