package com.example.epidemicprevention.module.epidemic.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.epidemic.entity.Epidemic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * EpidemicMapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-01-17
 */
@Mapper
public interface EpidemicMapper extends BaseMapper<Epidemic> {
    /**
     * 查询疫情和对应病例的数量
     *
     * @param page
     * @param params
     * @return
     */
    Page<Epidemic> selectEpidemicWithPatientCount(Page<Epidemic> page, @Param("params") Map<String, Object> params);
}
