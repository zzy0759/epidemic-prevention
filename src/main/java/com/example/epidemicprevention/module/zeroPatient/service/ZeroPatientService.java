package com.example.epidemicprevention.module.zeroPatient.service;

import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.zeroPatient.entity.ZeroPatient;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * ZeroPatient服务类
 * </p>
 *
 * @author zzy
 * @date 2022-01-17
 */
public interface ZeroPatientService extends IService<ZeroPatient> {

    /**
    * 分页查询
    * @param current：页码
    * @param size：每页条数
    * @param zeroPatient：筛选条件
    * @return IPage<ZeroPatient>
    */
    IPage<ZeroPatient> getZeroPatientPage(Integer current,Integer size,ZeroPatient zeroPatient);

    /**
    * 查询所有
    * @param zeroPatient：筛选条件
    * @return List<ZeroPatient>
    */
    List<ZeroPatient> getAll(ZeroPatient zeroPatient);

    /**
    * 通过id删除
    * @param id：zeroPatientId
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
