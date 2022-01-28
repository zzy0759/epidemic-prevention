package com.example.epidemicprevention.module.vehicle.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.epidemicprevention.module.vehicle.mapper.VehicleMapper;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.vehicle.entity.Vehicle;
import com.example.epidemicprevention.module.vehicle.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Vehicle")
@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleMapper vehicleMapper;

    @ApiOperation("分页查询")
    @GetMapping("/page")
    @Deprecated
    public Result<IPage<Vehicle>> getVehiclePage(
            @RequestParam(name = "current", defaultValue = "1") Integer current,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            Vehicle vehicle) {
        IPage<Vehicle> vehiclePage = vehicleService.getVehiclePage(current, size, vehicle);
        return Result.OK(vehiclePage);
    }

    @ApiOperation("修改")
    @PutMapping
    @Deprecated
    public Result<Vehicle> update(@Validated(Vehicle.update.class) @RequestBody Vehicle vehicle) {
        vehicleService.updateById(vehicle);
        return Result.OK();
    }

    @ApiOperation("新增")
    @PostMapping
    @Deprecated
    public Result<Object> add(@Validated(Vehicle.insert.class) @RequestBody Vehicle vehicle) {
        vehicleService.save(vehicle);
        return Result.OK();
    }

    @ApiOperation("通过id查询")
    @GetMapping("/id")
    @Deprecated
    public Result<Vehicle> getById(@RequestParam String id) {
        Vehicle vehicle = vehicleService.getById(id);
        return Result.OK(vehicle);
    }

    @ApiOperation("通过时间查询,option1降序,其他升序")
    @GetMapping
    public Result<List<Vehicle>> getAll(@RequestParam String patientId, @RequestParam Integer option) {
        List<Vehicle> vehicleList;
        if (option == 1) {
            vehicleList = vehicleMapper.vehicleListDesc(patientId);
        } else {
            vehicleList = vehicleMapper.vehicleListAsc(patientId);
        }
        return Result.OK(vehicleList);
    }

    @ApiOperation("通过id删除")
    @DeleteMapping("/id")
    @Deprecated
    public Result<Object> deleteById(@RequestParam String id) {
        return vehicleService.deleteById(id);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    @Deprecated
    public Result<Object> batchDelete(@RequestParam List<String> ids) {
        return vehicleService.batchDelete(ids);
    }
}
