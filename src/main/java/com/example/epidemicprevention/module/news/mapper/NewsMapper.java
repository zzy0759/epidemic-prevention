package com.example.epidemicprevention.module.news.mapper;

import com.example.epidemicprevention.module.news.entity.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * NewsMapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-01-26
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {

}
