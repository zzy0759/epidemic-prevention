package com.example.epidemicprevention.module.news.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.news.entity.News;
import com.example.epidemicprevention.module.news.mapper.NewsMapper;
import com.example.epidemicprevention.module.news.service.NewsService;
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
 *  News服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-01-26
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param news：筛选条件
     * @return IPage<News>
     */
    @Override
    public IPage<News> getNewsPage(Integer current, Integer size,News news) {
        QueryWrapper<News> queryWrapper = WrapperUtils.queryWrapper(news);
        Page<News> newsPage = new Page<>(current, size);
        IPage<News> newsIPage = newsMapper.selectPage(newsPage, queryWrapper);
        return newsIPage;
    }

    /**
     * 查询所有
     * @param news：筛选条件
     * @return List<News>
     */
    @Override
    public List<News> getAll(News news) {
        QueryWrapper<News> queryWrapper = WrapperUtils.queryWrapper(news);
        return newsMapper.selectList(queryWrapper);
    }

    /**
     * 通过id删除
     * @param id：newsId
     * @return Result<Object>
     */
    @Override
    public Result<Object> deleteById(String id) {
        News news=newsMapper.selectById(id);
        if (news==null){
            return Result.error(ResponseState.TARGET_NOT_EXIST.getValue(), ResponseState.TARGET_NOT_EXIST.getMessage());
        }
            newsMapper.deleteById(id);
        return Result.OK();
    }

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    @Override
    public Result<Object> batchDelete(List<String> ids) {
        UpdateWrapper<News> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids);
            newsMapper.delete(updateWrapper);
        return Result.OK();
    }
}