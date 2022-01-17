package com.example.epidemicprevention.module.epidemic.mapper;

import com.example.epidemicprevention.module.epidemic.entity.Epidemic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

}
