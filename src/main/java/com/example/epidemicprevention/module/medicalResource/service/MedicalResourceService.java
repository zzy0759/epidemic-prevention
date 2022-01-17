package com.example.epidemicprevention.module.medicalResource.service;

import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.medicalResource.entity.MedicalResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * MedicalResource服务类
 * </p>
 *
 * @author zzy
 * @date 2022-01-17
 */
public interface MedicalResourceService extends IService<MedicalResource> {

    /**
    * 分页查询
    * @param current：页码
    * @param size：每页条数
    * @param medicalResource：筛选条件
    * @return IPage<MedicalResource>
    */
    IPage<MedicalResource> getMedicalResourcePage(Integer current,Integer size,MedicalResource medicalResource);

    /**
    * 查询所有
    * @param medicalResource：筛选条件
    * @return List<MedicalResource>
    */
    List<MedicalResource> getAll(MedicalResource medicalResource);

    /**
    * 通过id删除
    * @param id：medicalResourceId
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
