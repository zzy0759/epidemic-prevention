package com.example.epidemicprevention.module.vehicle.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.vehicle.entity.Vehicle;
import com.example.epidemicprevention.module.vehicle.mapper.VehicleMapper;
import com.example.epidemicprevention.module.vehicle.service.VehicleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.epidemicprevention.response.ResponseState;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.util.WrapperUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  Vehicle服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-01-28
 */
@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle> implements VehicleService {

    @Autowired
    private VehicleMapper vehicleMapper;

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param vehicle：筛选条件
     * @return IPage<Vehicle>
     */
    @Override
    public IPage<Vehicle> getVehiclePage(Integer current, Integer size,Vehicle vehicle) {
        QueryWrapper<Vehicle> queryWrapper = WrapperUtils.queryWrapper(vehicle);
        Page<Vehicle> vehiclePage = new Page<>(current, size);
        IPage<Vehicle> vehicleIPage = vehicleMapper.selectPage(vehiclePage, queryWrapper);
        return vehicleIPage;
    }

    /**
     * 查询所有
     * @param vehicle：筛选条件
     * @return List<Vehicle>
     */
    @Override
    public List<Vehicle> getAll(Vehicle vehicle) {
        QueryWrapper<Vehicle> queryWrapper = WrapperUtils.queryWrapper(vehicle);
        return vehicleMapper.selectList(queryWrapper);
    }

    /**
     * 通过id删除
     * @param id：vehicleId
     * @return Result<Object>
     */
    @Override
    public Result<Object> deleteById(String id) {
        Vehicle vehicle=vehicleMapper.selectById(id);
        if (vehicle==null){
            return Result.error(ResponseState.TARGET_NOT_EXIST.getValue(), ResponseState.TARGET_NOT_EXIST.getMessage());
        }
            vehicleMapper.deleteById(id);
        return Result.OK();
    }

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    @Override
    public Result<Object> batchDelete(List<String> ids) {
        UpdateWrapper<Vehicle> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids);
            vehicleMapper.delete(updateWrapper);
        return Result.OK();
    }
}