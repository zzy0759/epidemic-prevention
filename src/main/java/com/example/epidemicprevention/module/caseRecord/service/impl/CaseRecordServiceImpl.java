package com.example.epidemicprevention.module.caseRecord.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.caseRecord.entity.CaseRecord;
import com.example.epidemicprevention.module.caseRecord.entity.DateRecord;
import com.example.epidemicprevention.module.caseRecord.mapper.CaseRecordMapper;
import com.example.epidemicprevention.module.caseRecord.service.CaseRecordService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  CaseRecord服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-01-26
 */
@Service
public class CaseRecordServiceImpl extends ServiceImpl<CaseRecordMapper, CaseRecord> implements CaseRecordService {

    @Autowired
    private CaseRecordMapper caseRecordMapper;

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param caseRecord：筛选条件
     * @return IPage<CaseRecord>
     */
    @Override
    public IPage<CaseRecord> getCaseRecordPage(Integer current, Integer size,CaseRecord caseRecord) {
        QueryWrapper<CaseRecord> queryWrapper = WrapperUtils.queryWrapper(caseRecord);
        Page<CaseRecord> caseRecordPage = new Page<>(current, size);
        IPage<CaseRecord> caseRecordIPage = caseRecordMapper.selectPage(caseRecordPage, queryWrapper);
        return caseRecordIPage;
    }

    /**
     * 查询所有
     * @param caseRecord：筛选条件
     * @return List<CaseRecord>
     */
    @Override
    public List<CaseRecord> getAll(CaseRecord caseRecord) {
        QueryWrapper<CaseRecord> queryWrapper = WrapperUtils.queryWrapper(caseRecord);
        return caseRecordMapper.selectList(queryWrapper);
    }

    /**
     * 通过id删除
     * @param id：caseRecordId
     * @return Result<Object>
     */
    @Override
    public Result<Object> deleteById(String id) {
        CaseRecord caseRecord=caseRecordMapper.selectById(id);
        if (caseRecord==null){
            return Result.error(ResponseState.TARGET_NOT_EXIST.getValue(), ResponseState.TARGET_NOT_EXIST.getMessage());
        }
            caseRecordMapper.deleteById(id);
        return Result.OK();
    }

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    @Override
    public Result<Object> batchDelete(List<String> ids) {
        UpdateWrapper<CaseRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids);
            caseRecordMapper.delete(updateWrapper);
        return Result.OK();
    }

    /**
     * 添加
     */
    @Override
    public void add(CaseRecord caseRecord) {
        CaseRecord lastRecord=caseRecordMapper.getLastRecord(caseRecord);
        if(lastRecord==null){
            caseRecord.setNowNumber(caseRecord.getNewNumber()-caseRecord.getDieNumber());
            caseRecord.setTotalNumber(caseRecord.getNewNumber());
            caseRecordMapper.insert(caseRecord);
        }else{
            caseRecord.setNowNumber(lastRecord.getNowNumber()+caseRecord.getNewNumber()-caseRecord.getDieNumber());
            caseRecord.setTotalNumber(lastRecord.getTotalNumber()+caseRecord.getNewNumber());
            if(lastRecord.getDate().equals(caseRecord.getDate())){
                caseRecord.setNewNumber(caseRecord.getNewNumber()+lastRecord.getNowNumber());
                caseRecord.setCureNumber(caseRecord.getCureNumber()+lastRecord.getCureNumber());
                caseRecord.setDieNumber(caseRecord.getDieNumber()+lastRecord.getDieNumber());
                caseRecord.setId(lastRecord.getId());
                caseRecordMapper.updateById(caseRecord);
            }else{
                caseRecordMapper.insert(caseRecord);
            }
        }

    }

    /**
     * 日期折线图
     * @param dateRecord
     * @return
     */
    @Override
    public Result<Object> getByDate(DateRecord dateRecord) {
        QueryWrapper<CaseRecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.between("date",dateRecord.getStart(),dateRecord.getEnd());
        queryWrapper.orderByAsc("date");
        queryWrapper.eq("epidemic_id",dateRecord.getEpidemicId());
        queryWrapper.eq(dateRecord.getProvince()!=null,"province",dateRecord.getProvince());
        queryWrapper.eq(dateRecord.getCity()!=null,"city",dateRecord.getCity());
        queryWrapper.eq(dateRecord.getDistrict()!=null,"district",dateRecord.getDistrict());
        List<CaseRecord> caseRecords=caseRecordMapper.selectList(queryWrapper);
        Map<String,List<Object>> result=new HashMap<>();
        List<Object> date=new ArrayList<>();
        List<Object> totalNumber=new ArrayList<>();
        List<Object> newNumber=new ArrayList<>();
        if(caseRecords!=null&&caseRecords.size()>0){
            for(CaseRecord caseRecord:caseRecords){
                date.add(caseRecord.getDate());
                totalNumber.add(caseRecord.getTotalNumber());
                newNumber.add(caseRecord.getNewNumber());
            }
        }
        result.put("date",date);
        result.put("totalNumber",totalNumber);
        result.put("newNumber",newNumber);
        return Result.OK(result);

    }


}