package com.example.epidemicprevention.module.news.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.epidemicprevention.module.news.mapper.NewsMapper;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.news.entity.News;
import com.example.epidemicprevention.module.news.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "News")
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsMapper newsMapper;

    @ApiOperation("获取最新新闻")
    @GetMapping("/getLastNew")
    public Result<Object> getLastNew(){
        return Result.OK(newsMapper.lastNew());
    }

    @ApiOperation("分页查询,根据疫情分页")
    @GetMapping("/newsPage")
    public Result<Object> newsPage(
            @RequestParam(required = false) String epidemicName,
            @RequestParam(name = "current", defaultValue = "1") Integer current,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return newsService.newsPage(epidemicName, current, size);
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    @Deprecated
    public Result<IPage<News>> getNewsPage(
            @RequestParam(name = "current", defaultValue = "1") Integer current,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            News news) {
        IPage<News> newsPage = newsService.getNewsPage(current, size, news);
        return Result.OK(newsPage);
    }

    @ApiOperation("修改")
    @PutMapping
    @Deprecated
    public Result<News> update(@Validated(News.update.class) @RequestBody News news) {
        newsService.updateById(news);
        return Result.OK();
    }

    //    @ApiOperation("新增")
//    @PostMapping
//    @Deprecated
//    public Result<Object> add(@Validated(News.insert.class) @RequestBody News news) {
//            newsService.save(news);
//        return Result.OK();
//    }
//
//    @ApiOperation("通过id查询")
//    @GetMapping("/id")
//    @Deprecated
//    public Result<News> getById(@RequestParam String id) {
//        News news=newsService.getById(id);
//        return Result.OK(news);
//    }
//
//    @ApiOperation("查询所有")
//    @GetMapping
//    @Deprecated
//    public Result<List<News>> getAll(News news) {
//        List<News> newsList = newsService.getAll(news);
//        return Result.OK(newsList);
//    }
//
    @ApiOperation("通过id删除")
    @DeleteMapping("/id")
    @Deprecated
    public Result<Object> deleteById(@RequestParam String id) {
        return newsService.deleteById(id);
    }
//
//    @ApiOperation("批量删除")
//    @DeleteMapping("/batch")
//    @Deprecated
//    public Result<Object> batchDelete(@RequestParam List<String> ids) {
//        return newsService.batchDelete(ids);
//    }
}
