package com.example.epidemicprevention.module.patient.service;

import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.patient.entity.Patient;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Patient服务类
 * </p>
 *
 * @author zzy
 * @date 2022-01-17
 */
public interface PatientService extends IService<Patient> {

    /**
     * 分页查询
     *
     * @param current：页码
     * @param size：每页条数
     * @param patient：筛选条件
     * @return IPage<Patient>
     */
    IPage<Patient> getPatientPage(Integer current, Integer size, Patient patient);

    /**
     * 查询所有
     *
     * @param patient：筛选条件
     * @return List<Patient>
     */
    List<Patient> getAll(Patient patient);

    /**
     * 通过id删除
     *
     * @param id：patientId
     * @return Result<Object>
     */
    Result<Object> deleteById(String id);

    /**
     * 批量删除
     *
     * @param ids：id列表
     * @return Result<Object>
     */
    Result<Object> batchDelete(List<String> ids);
}
