package com.example.epidemicprevention.module.patient.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.patient.entity.Patient;
import com.example.epidemicprevention.module.patient.mapper.PatientMapper;
import com.example.epidemicprevention.module.patient.service.PatientService;
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
 *  Patient服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-01-17
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param patient：筛选条件
     * @return IPage<Patient>
     */
    @Override
    public IPage<Patient> getPatientPage(Integer current,Integer size,Patient patient){
        QueryWrapper<Patient> queryWrapper = WrapperUtils.queryWrapper(patient);
        Page<Patient> patientPage = new Page<>(current,size);
        IPage<Patient> patientIPage = patientMapper.selectPage(patientPage, queryWrapper);
        return patientIPage;
    }

    /**
     * 查询所有
     * @param patient：筛选条件
     * @return List<Patient>
     */
    @Override
    public List<Patient> getAll(Patient patient){
        QueryWrapper<Patient> queryWrapper = WrapperUtils.queryWrapper(patient);
        return patientMapper.selectList(queryWrapper);
    }

    /**
     * 通过id删除
     * @param id：patientId
     * @return Result<Object>
     */
    @Override
    public Result<Object> deleteById(String id){
        Patient patient=patientMapper.selectById(id);
        if(patient==null){
            return Result.error(ResponseState.TARGET_NOT_EXIST.getValue(), ResponseState.TARGET_NOT_EXIST.getMessage());
        }
        patientMapper.deleteById(id);
        return Result.OK();
    }

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    @Override
    public Result<Object> batchDelete(List<String> ids){
        UpdateWrapper<Patient> updateWrapper=new UpdateWrapper<>();
        updateWrapper.in("id",ids);
        patientMapper.delete(updateWrapper);
        return Result.OK();
    }
}