package com.example.epidemicprevention.module.resourceType.service;

import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.resourceType.entity.ResourceType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * ResourceType服务类
 * </p>
 *
 * @author zzy
 * @date 2022-01-17
 */
public interface ResourceTypeService extends IService<ResourceType> {

    /**
    * 分页查询
    * @param current：页码
    * @param size：每页条数
    * @param resourceType：筛选条件
    * @return IPage<ResourceType>
    */
    IPage<ResourceType> getResourceTypePage(Integer current,Integer size,ResourceType resourceType);

    /**
    * 查询所有
    * @param resourceType：筛选条件
    * @return List<ResourceType>
    */
    List<ResourceType> getAll(ResourceType resourceType);

    /**
    * 通过id删除
    * @param id：resourceTypeId
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
