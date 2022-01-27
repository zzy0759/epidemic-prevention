package com.example.epidemicprevention.module.news.service;

import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.news.entity.News;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * News服务类
 * </p>
 *
 * @author zzy
 * @date 2022-01-26
 */
public interface NewsService extends IService<News> {
    /**
     * 新闻分页,根据疫情种类分页查询
     *
     * @param epidemicName
     * @param current
     * @param size
     * @return
     */
    Result<Object> newsPage(String epidemicName, Integer current, Integer size);

    /**
     * 分页查询
     *
     * @param current：页码
     * @param size：每页条数
     * @param news：筛选条件
     * @return IPage<News>
     */
    IPage<News> getNewsPage(Integer current, Integer size, News news);

    /**
     * 查询所有
     *
     * @param news：筛选条件
     * @return List<News>
     */
    List<News> getAll(News news);

    /**
     * 通过id删除
     *
     * @param id：newsId
     * @return Result<Object>
     */
    Result<Object> deleteById(String id);

    /**
     * 批量删除
     *
     * @param ids：id列表
     * @return Result<Object>
     */
    Result<Object> batchDelete(List<String> ids);
}
