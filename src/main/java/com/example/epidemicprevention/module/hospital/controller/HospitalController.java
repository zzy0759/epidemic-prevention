package com.example.epidemicprevention.module.hospital.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.hospital.entity.Hospital;
import com.example.epidemicprevention.module.hospital.service.HospitalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Hospital")
@RestController
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @ApiOperation("分页查询")
    @GetMapping("/page")
    @Deprecated
    public Result<IPage<Hospital>> getHospitalPage(
        @RequestParam(name = "current", defaultValue = "1") Integer current,
        @RequestParam(name = "size", defaultValue = "10") Integer size,
        Hospital hospital) {
        IPage<Hospital> hospitalPage=hospitalService.getHospitalPage(current,size,hospital);
        return Result.OK(hospitalPage);
    }

    @ApiOperation("修改")
    @PutMapping
    @Deprecated
    public Result<Hospital> update(@Validated(Hospital.update.class) @RequestBody Hospital hospital) {
        hospitalService.updateById(hospital);
        return Result.OK();
    }

    @ApiOperation("新增")
    @PostMapping
    @Deprecated
    public Result<Object> add(@Validated(Hospital.insert.class) @RequestBody Hospital hospital) {
        hospitalService.save(hospital);
        return Result.OK();
    }

    @ApiOperation("通过id查询")
    @GetMapping("/id")
    @Deprecated
    public Result<Hospital> getById(@RequestParam String id){
        Hospital hospital=hospitalService.getById(id);
        return Result.OK(hospital);
    }

    @ApiOperation("查询所有")
    @GetMapping
    @Deprecated
    public Result<List<Hospital>> getAll(Hospital hospital){
        List<Hospital> hospitalList=hospitalService.getAll(hospital);
        return Result.OK(hospitalList);
    }

    @ApiOperation("通过id删除")
    @DeleteMapping("/id")
    @Deprecated
    public Result<Object> deleteById(@RequestParam String id){
        return hospitalService.deleteById(id);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    @Deprecated
    public Result<Object> batchDelete(@RequestParam List<String> ids){
        return hospitalService.batchDelete(ids);
    }
}
