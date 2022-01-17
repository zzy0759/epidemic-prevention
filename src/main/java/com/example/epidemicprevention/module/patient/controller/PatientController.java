package com.example.epidemicprevention.module.patient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.patient.entity.Patient;
import com.example.epidemicprevention.module.patient.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @Author ZZY
 * @Date 2022/1/17
 */
@Api(tags = "Patient")
@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @ApiOperation("分页查询")
    @GetMapping("/page")
    @Deprecated
    public Result<IPage<Patient>> getPatientPage(
        @RequestParam(name = "current", defaultValue = "1") Integer current,
        @RequestParam(name = "size", defaultValue = "10") Integer size,
        Patient patient) {
        IPage<Patient> patientPage=patientService.getPatientPage(current,size,patient);
        return Result.OK(patientPage);
    }

    @ApiOperation("修改")
    @PutMapping
    @Deprecated
    public Result<Patient> update(@Validated(Patient.update.class) @RequestBody Patient patient) {
        patientService.updateById(patient);
        return Result.OK();
    }

    @ApiOperation("新增")
    @PostMapping
    @Deprecated
    public Result<Object> add(@Validated(Patient.insert.class) @RequestBody Patient patient) {
        patientService.save(patient);
        return Result.OK();
    }

    @ApiOperation("通过id查询")
    @GetMapping("/id")
    @Deprecated
    public Result<Patient> getById(@RequestParam String id){
        Patient patient=patientService.getById(id);
        return Result.OK(patient);
    }

    @ApiOperation("查询所有")
    @GetMapping
    @Deprecated
    public Result<List<Patient>> getAll(Patient patient){
        List<Patient> patientList=patientService.getAll(patient);
        return Result.OK(patientList);
    }

    @ApiOperation("通过id删除")
    @DeleteMapping("/id")
    @Deprecated
    public Result<Object> deleteById(@RequestParam String id){
        return patientService.deleteById(id);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    @Deprecated
    public Result<Object> batchDelete(@RequestParam List<String> ids){
        return patientService.batchDelete(ids);
    }
}
