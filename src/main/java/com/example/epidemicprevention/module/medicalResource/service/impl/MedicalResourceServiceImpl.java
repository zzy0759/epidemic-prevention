package com.example.epidemicprevention.module.medicalResource.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.medicalResource.entity.MedicalResource;
import com.example.epidemicprevention.module.medicalResource.mapper.MedicalResourceMapper;
import com.example.epidemicprevention.module.medicalResource.service.MedicalResourceService;
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
 *  MedicalResource服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-01-17
 */
@Service
public class MedicalResourceServiceImpl extends ServiceImpl<MedicalResourceMapper, MedicalResource> implements MedicalResourceService {

    @Autowired
    private MedicalResourceMapper medicalResourceMapper;

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param medicalResource：筛选条件
     * @return IPage<MedicalResource>
     */
    @Override
    public IPage<MedicalResource> getMedicalResourcePage(Integer current,Integer size,MedicalResource medicalResource){
        QueryWrapper<MedicalResource> queryWrapper = WrapperUtils.queryWrapper(medicalResource);
        Page<MedicalResource> medicalResourcePage = new Page<>(current,size);
        IPage<MedicalResource> medicalResourceIPage = medicalResourceMapper.selectPage(medicalResourcePage, queryWrapper);
        return medicalResourceIPage;
    }

    /**
     * 查询所有
     * @param medicalResource：筛选条件
     * @return List<MedicalResource>
     */
    @Override
    public List<MedicalResource> getAll(MedicalResource medicalResource){
        QueryWrapper<MedicalResource> queryWrapper = WrapperUtils.queryWrapper(medicalResource);
        return medicalResourceMapper.selectList(queryWrapper);
    }

    /**
     * 通过id删除
     * @param id：medicalResourceId
     * @return Result<Object>
     */
    @Override
    public Result<Object> deleteById(String id){
        MedicalResource medicalResource=medicalResourceMapper.selectById(id);
        if(medicalResource==null){
            return Result.error(ResponseState.TARGET_NOT_EXIST.getValue(), ResponseState.TARGET_NOT_EXIST.getMessage());
        }
        medicalResourceMapper.deleteById(id);
        return Result.OK();
    }

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    @Override
    public Result<Object> batchDelete(List<String> ids){
        UpdateWrapper<MedicalResource> updateWrapper=new UpdateWrapper<>();
        updateWrapper.in("id",ids);
        medicalResourceMapper.delete(updateWrapper);
        return Result.OK();
    }
}