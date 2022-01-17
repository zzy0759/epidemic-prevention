package com.example.epidemicprevention.module.zeroPatient.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.zeroPatient.entity.ZeroPatient;
import com.example.epidemicprevention.module.zeroPatient.mapper.ZeroPatientMapper;
import com.example.epidemicprevention.module.zeroPatient.service.ZeroPatientService;
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
 *  ZeroPatient服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-01-17
 */
@Service
public class ZeroPatientServiceImpl extends ServiceImpl<ZeroPatientMapper, ZeroPatient> implements ZeroPatientService {

    @Autowired
    private ZeroPatientMapper zeroPatientMapper;

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param zeroPatient：筛选条件
     * @return IPage<ZeroPatient>
     */
    @Override
    public IPage<ZeroPatient> getZeroPatientPage(Integer current,Integer size,ZeroPatient zeroPatient){
        QueryWrapper<ZeroPatient> queryWrapper = WrapperUtils.queryWrapper(zeroPatient);
        Page<ZeroPatient> zeroPatientPage = new Page<>(current,size);
        IPage<ZeroPatient> zeroPatientIPage = zeroPatientMapper.selectPage(zeroPatientPage, queryWrapper);
        return zeroPatientIPage;
    }

    /**
     * 查询所有
     * @param zeroPatient：筛选条件
     * @return List<ZeroPatient>
     */
    @Override
    public List<ZeroPatient> getAll(ZeroPatient zeroPatient){
        QueryWrapper<ZeroPatient> queryWrapper = WrapperUtils.queryWrapper(zeroPatient);
        return zeroPatientMapper.selectList(queryWrapper);
    }

    /**
     * 通过id删除
     * @param id：zeroPatientId
     * @return Result<Object>
     */
    @Override
    public Result<Object> deleteById(String id){
        ZeroPatient zeroPatient=zeroPatientMapper.selectById(id);
        if(zeroPatient==null){
            return Result.error(ResponseState.TARGET_NOT_EXIST.getValue(), ResponseState.TARGET_NOT_EXIST.getMessage());
        }
        zeroPatientMapper.deleteById(id);
        return Result.OK();
    }

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    @Override
    public Result<Object> batchDelete(List<String> ids){
        UpdateWrapper<ZeroPatient> updateWrapper=new UpdateWrapper<>();
        updateWrapper.in("id",ids);
        zeroPatientMapper.delete(updateWrapper);
        return Result.OK();
    }
}