package com.example.epidemicprevention.module.caseRecord.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.epidemicprevention.annotation.UnLogin;
import com.example.epidemicprevention.module.caseRecord.entity.DateRecord;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.caseRecord.entity.CaseRecord;
import com.example.epidemicprevention.module.caseRecord.service.CaseRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "CaseRecord")
@RestController
@RequestMapping("/caseRecord")
public class CaseRecordController {
    @Autowired
    private CaseRecordService caseRecordService;

    @ApiOperation("分页查询")
    @GetMapping("/page")
    @Deprecated
    public Result<IPage<CaseRecord>> getCaseRecordPage(
            @RequestParam(name = "current", defaultValue = "1") Integer current,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
        CaseRecord caseRecord) {
        IPage<CaseRecord> caseRecordPage = caseRecordService.getCaseRecordPage(current, size,caseRecord);
        return Result.OK(caseRecordPage);
    }

    @ApiOperation("修改")
    @PutMapping
    @Deprecated
    public Result<CaseRecord> update(@Validated(CaseRecord.update.class) @RequestBody CaseRecord caseRecord) {
            caseRecordService.updateById(caseRecord);
        return Result.OK();
    }

    @ApiOperation("新增")
    @PostMapping
    @Deprecated
    public Result<Object> add(@Validated(CaseRecord.insert.class) @RequestBody CaseRecord caseRecord) {
        caseRecordService.add(caseRecord);
        return Result.OK();
    }

    @ApiOperation("通过id查询")
    @GetMapping("/id")
    @Deprecated
    public Result<CaseRecord> getById(@RequestParam String id) {
        CaseRecord caseRecord=caseRecordService.getById(id);
        return Result.OK(caseRecord);
    }

    @ApiOperation("查询所有")
    @GetMapping
    @Deprecated
    public Result<List<CaseRecord>> getAll(CaseRecord caseRecord) {
        List<CaseRecord> caseRecordList = caseRecordService.getAll(caseRecord);
        return Result.OK(caseRecordList);
    }

    @ApiOperation("通过id删除")
    @DeleteMapping("/id")
    @Deprecated
    public Result<Object> deleteById(@RequestParam String id) {
        return caseRecordService.deleteById(id);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    @Deprecated
    public Result<Object> batchDelete(@RequestParam List<String> ids) {
        return caseRecordService.batchDelete(ids);
    }

    @UnLogin
    @ApiOperation("日期折线图")
    @GetMapping("/date")
    public Result<Object> getByDate(DateRecord dateRecord){
        return caseRecordService.getByDate(dateRecord);
    }

}
