package com.example.epidemicprevention.module.caseRecord.service;

import com.example.epidemicprevention.module.caseRecord.entity.DateRecord;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.caseRecord.entity.CaseRecord;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * CaseRecord服务类
 * </p>
 *
 * @author zzy
 * @date 2022-01-26
 */
public interface CaseRecordService extends IService<CaseRecord> {

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param caseRecord：筛选条件
     * @return IPage<CaseRecord>
     */
    IPage<CaseRecord> getCaseRecordPage(Integer current, Integer size,CaseRecord caseRecord);

    /**
     * 查询所有
     * @param caseRecord：筛选条件
     * @return List<CaseRecord>
     */
    List<CaseRecord> getAll(CaseRecord caseRecord);

    /**
     * 通过id删除
     * @param id：caseRecordId
     * @return Result<Object>
     */
    Result<Object> deleteById(String id);

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    Result<Object> batchDelete(List<String> ids);

    /**
     * 添加
     * @param caseRecord
     */
    void add(CaseRecord caseRecord);

    /**
     * 日期折线图
     * @param dateRecord
     * @return
     */
    Result<Object> getByDate(DateRecord dateRecord);
}
