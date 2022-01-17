package com.example.epidemicprevention.module.hospital.service;

import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.hospital.entity.Hospital;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Hospital服务类
 * </p>
 *
 * @author zzy
 * @date 2022-01-17
 */
public interface HospitalService extends IService<Hospital> {

    /**
    * 分页查询
    * @param current：页码
    * @param size：每页条数
    * @param hospital：筛选条件
    * @return IPage<Hospital>
    */
    IPage<Hospital> getHospitalPage(Integer current,Integer size,Hospital hospital);

    /**
    * 查询所有
    * @param hospital：筛选条件
    * @return List<Hospital>
    */
    List<Hospital> getAll(Hospital hospital);

    /**
    * 通过id删除
    * @param id：hospitalId
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
