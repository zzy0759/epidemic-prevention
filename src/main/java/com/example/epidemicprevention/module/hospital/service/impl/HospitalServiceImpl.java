package com.example.epidemicprevention.module.hospital.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.hospital.entity.Hospital;
import com.example.epidemicprevention.module.hospital.mapper.HospitalMapper;
import com.example.epidemicprevention.module.hospital.service.HospitalService;
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
 *  Hospital服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-01-17
 */
@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital> implements HospitalService {

    @Autowired
    private HospitalMapper hospitalMapper;

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param hospital：筛选条件
     * @return IPage<Hospital>
     */
    @Override
    public IPage<Hospital> getHospitalPage(Integer current,Integer size,Hospital hospital){
        QueryWrapper<Hospital> queryWrapper = WrapperUtils.queryWrapper(hospital);
        Page<Hospital> hospitalPage = new Page<>(current,size);
        IPage<Hospital> hospitalIPage = hospitalMapper.selectPage(hospitalPage, queryWrapper);
        return hospitalIPage;
    }

    /**
     * 查询所有
     * @param hospital：筛选条件
     * @return List<Hospital>
     */
    @Override
    public List<Hospital> getAll(Hospital hospital){
        QueryWrapper<Hospital> queryWrapper = WrapperUtils.queryWrapper(hospital);
        return hospitalMapper.selectList(queryWrapper);
    }

    /**
     * 通过id删除
     * @param id：hospitalId
     * @return Result<Object>
     */
    @Override
    public Result<Object> deleteById(String id){
        Hospital hospital=hospitalMapper.selectById(id);
        if(hospital==null){
            return Result.error(ResponseState.TARGET_NOT_EXIST.getValue(), ResponseState.TARGET_NOT_EXIST.getMessage());
        }
        hospitalMapper.deleteById(id);
        return Result.OK();
    }

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    @Override
    public Result<Object> batchDelete(List<String> ids){
        UpdateWrapper<Hospital> updateWrapper=new UpdateWrapper<>();
        updateWrapper.in("id",ids);
        hospitalMapper.delete(updateWrapper);
        return Result.OK();
    }
}