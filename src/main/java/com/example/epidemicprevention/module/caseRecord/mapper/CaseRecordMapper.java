package com.example.epidemicprevention.module.caseRecord.mapper;

import com.example.epidemicprevention.module.caseRecord.entity.CaseRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * CaseRecordMapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-01-26
 */
@Mapper
public interface CaseRecordMapper extends BaseMapper<CaseRecord> {

    /**
     * 查询上一条记录
     */
    @Select("select * from case_record where epidemic_id=#{epidemicId} and date = (select ifnull(max(date),0) from case_record)")
    CaseRecord getLastRecord(CaseRecord caseRecord);
}
