package com.example.epidemicprevention.module.vehicle.service;

import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.vehicle.entity.Vehicle;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Vehicle服务类
 * </p>
 *
 * @author zzy
 * @date 2022-01-28
 */
public interface VehicleService extends IService<Vehicle> {

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param vehicle：筛选条件
     * @return IPage<Vehicle>
     */
    IPage<Vehicle> getVehiclePage(Integer current, Integer size,Vehicle vehicle);

    /**
     * 查询所有
     * @param vehicle：筛选条件
     * @return List<Vehicle>
     */
    List<Vehicle> getAll(Vehicle vehicle);

    /**
     * 通过id删除
     * @param id：vehicleId
     * @return Result<Object>
     */
    Result<Object> deleteById(String id);

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    Result<Object> batchDelete(List<String> ids);
}
