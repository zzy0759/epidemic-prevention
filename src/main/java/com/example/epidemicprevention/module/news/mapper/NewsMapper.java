package com.example.epidemicprevention.module.news.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.news.entity.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
    Page<News> newsPage(Page<News> page, @Param("params") Map<String, Object> params);
}
