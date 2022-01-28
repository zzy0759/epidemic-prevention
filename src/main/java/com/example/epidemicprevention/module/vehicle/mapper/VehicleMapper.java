package com.example.epidemicprevention.module.vehicle.mapper;

import com.example.epidemicprevention.module.vehicle.entity.Vehicle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * VehicleMapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-01-28
 */
@Mapper
public interface VehicleMapper extends BaseMapper<Vehicle> {
    List<Vehicle> vehicleListDesc(String patientId);
    List<Vehicle> vehicleListAsc(String patientId);

}
