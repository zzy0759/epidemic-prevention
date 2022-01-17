package com.example.epidemicprevention.module.resourceType.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.resourceType.entity.ResourceType;
import com.example.epidemicprevention.module.resourceType.service.ResourceTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "ResourceType")
@RestController
@RequestMapping("/resourceType")
public class ResourceTypeController {
    @Autowired
    private ResourceTypeService resourceTypeService;

    @ApiOperation("分页查询")
    @GetMapping("/page")
    @Deprecated
    public Result<IPage<ResourceType>> getResourceTypePage(
        @RequestParam(name = "current", defaultValue = "1") Integer current,
        @RequestParam(name = "size", defaultValue = "10") Integer size,
        ResourceType resourceType) {
        IPage<ResourceType> resourceTypePage=resourceTypeService.getResourceTypePage(current,size,resourceType);
        return Result.OK(resourceTypePage);
    }

    @ApiOperation("修改")
    @PutMapping
    @Deprecated
    public Result<ResourceType> update(@Validated(ResourceType.update.class) @RequestBody ResourceType resourceType) {
        resourceTypeService.updateById(resourceType);
        return Result.OK();
    }

    @ApiOperation("新增")
    @PostMapping
    @Deprecated
    public Result<Object> add(@Validated(ResourceType.insert.class) @RequestBody ResourceType resourceType) {
        resourceTypeService.save(resourceType);
        return Result.OK();
    }

    @ApiOperation("通过id查询")
    @GetMapping("/id")
    @Deprecated
    public Result<ResourceType> getById(@RequestParam String id){
        ResourceType resourceType=resourceTypeService.getById(id);
        return Result.OK(resourceType);
    }

    @ApiOperation("查询所有")
    @GetMapping
    @Deprecated
    public Result<List<ResourceType>> getAll(ResourceType resourceType){
        List<ResourceType> resourceTypeList=resourceTypeService.getAll(resourceType);
        return Result.OK(resourceTypeList);
    }

    @ApiOperation("通过id删除")
    @DeleteMapping("/id")
    @Deprecated
    public Result<Object> deleteById(@RequestParam String id){
        return resourceTypeService.deleteById(id);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    @Deprecated
    public Result<Object> batchDelete(@RequestParam List<String> ids){
        return resourceTypeService.batchDelete(ids);
    }
}
