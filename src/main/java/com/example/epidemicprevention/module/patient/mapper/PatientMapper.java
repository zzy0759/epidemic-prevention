package com.example.epidemicprevention.module.patient.mapper;

import com.example.epidemicprevention.module.patient.entity.Patient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * PatientMapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-01-17
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {

}
