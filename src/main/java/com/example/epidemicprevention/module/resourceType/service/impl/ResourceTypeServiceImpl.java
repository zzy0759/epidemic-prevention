package com.example.epidemicprevention.module.resourceType.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.resourceType.entity.ResourceType;
import com.example.epidemicprevention.module.resourceType.mapper.ResourceTypeMapper;
import com.example.epidemicprevention.module.resourceType.service.ResourceTypeService;
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
 *  ResourceType服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-01-17
 */
@Service
public class ResourceTypeServiceImpl extends ServiceImpl<ResourceTypeMapper, ResourceType> implements ResourceTypeService {

    @Autowired
    private ResourceTypeMapper resourceTypeMapper;

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param resourceType：筛选条件
     * @return IPage<ResourceType>
     */
    @Override
    public IPage<ResourceType> getResourceTypePage(Integer current,Integer size,ResourceType resourceType){
        QueryWrapper<ResourceType> queryWrapper = WrapperUtils.queryWrapper(resourceType);
        Page<ResourceType> resourceTypePage = new Page<>(current,size);
        IPage<ResourceType> resourceTypeIPage = resourceTypeMapper.selectPage(resourceTypePage, queryWrapper);
        return resourceTypeIPage;
    }

    /**
     * 查询所有
     * @param resourceType：筛选条件
     * @return List<ResourceType>
     */
    @Override
    public List<ResourceType> getAll(ResourceType resourceType){
        QueryWrapper<ResourceType> queryWrapper = WrapperUtils.queryWrapper(resourceType);
        return resourceTypeMapper.selectList(queryWrapper);
    }

    /**
     * 通过id删除
     * @param id：resourceTypeId
     * @return Result<Object>
     */
    @Override
    public Result<Object> deleteById(String id){
        ResourceType resourceType=resourceTypeMapper.selectById(id);
        if(resourceType==null){
            return Result.error(ResponseState.TARGET_NOT_EXIST.getValue(), ResponseState.TARGET_NOT_EXIST.getMessage());
        }
        resourceTypeMapper.deleteById(id);
        return Result.OK();
    }

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    @Override
    public Result<Object> batchDelete(List<String> ids){
        UpdateWrapper<ResourceType> updateWrapper=new UpdateWrapper<>();
        updateWrapper.in("id",ids);
        resourceTypeMapper.delete(updateWrapper);
        return Result.OK();
    }
}