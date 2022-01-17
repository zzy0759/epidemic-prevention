package com.example.epidemicprevention.module.medicalResource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.medicalResource.entity.MedicalResource;
import com.example.epidemicprevention.module.medicalResource.service.MedicalResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "MedicalResource")
@RestController
@RequestMapping("/medicalResource")
public class MedicalResourceController {
    @Autowired
    private MedicalResourceService medicalResourceService;

    @ApiOperation("分页查询")
    @GetMapping("/page")
    @Deprecated
    public Result<IPage<MedicalResource>> getMedicalResourcePage(
        @RequestParam(name = "current", defaultValue = "1") Integer current,
        @RequestParam(name = "size", defaultValue = "10") Integer size,
        MedicalResource medicalResource) {
        IPage<MedicalResource> medicalResourcePage=medicalResourceService.getMedicalResourcePage(current,size,medicalResource);
        return Result.OK(medicalResourcePage);
    }

    @ApiOperation("修改")
    @PutMapping
    @Deprecated
    public Result<MedicalResource> update(@Validated(MedicalResource.update.class) @RequestBody MedicalResource medicalResource) {
        medicalResourceService.updateById(medicalResource);
        return Result.OK();
    }

    @ApiOperation("新增")
    @PostMapping
    @Deprecated
    public Result<Object> add(@Validated(MedicalResource.insert.class) @RequestBody MedicalResource medicalResource) {
        medicalResourceService.save(medicalResource);
        return Result.OK();
    }

    @ApiOperation("通过id查询")
    @GetMapping("/id")
    @Deprecated
    public Result<MedicalResource> getById(@RequestParam String id){
        MedicalResource medicalResource=medicalResourceService.getById(id);
        return Result.OK(medicalResource);
    }

    @ApiOperation("查询所有")
    @GetMapping
    @Deprecated
    public Result<List<MedicalResource>> getAll(MedicalResource medicalResource){
        List<MedicalResource> medicalResourceList=medicalResourceService.getAll(medicalResource);
        return Result.OK(medicalResourceList);
    }

    @ApiOperation("通过id删除")
    @DeleteMapping("/id")
    @Deprecated
    public Result<Object> deleteById(@RequestParam String id){
        return medicalResourceService.deleteById(id);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    @Deprecated
    public Result<Object> batchDelete(@RequestParam List<String> ids){
        return medicalResourceService.batchDelete(ids);
    }
}
