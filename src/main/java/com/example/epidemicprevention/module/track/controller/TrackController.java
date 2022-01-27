package com.example.epidemicprevention.module.track.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.track.entity.Track;
import com.example.epidemicprevention.module.track.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Track")
@RestController
@RequestMapping("/track")
public class TrackController {
    @Autowired
    private TrackService trackService;

    @ApiOperation("分页查询")
    @GetMapping("/page")
    @Deprecated
    public Result<IPage<Track>> getTrackPage(
            @RequestParam(name = "current", defaultValue = "1") Integer current,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
        Track track) {
        IPage<Track> trackPage = trackService.getTrackPage(current, size,track);
        return Result.OK(trackPage);
    }

    @ApiOperation("修改")
    @PutMapping
    @Deprecated
    public Result<Track> update(@Validated(Track.update.class) @RequestBody Track track) {
            trackService.updateById(track);
        return Result.OK();
    }

    @ApiOperation("新增")
    @PostMapping
    @Deprecated
    public Result<Object> add(@Validated(Track.insert.class) @RequestBody Track track) {
            trackService.save(track);
        return Result.OK();
    }

    @ApiOperation("通过id查询")
    @GetMapping("/id")
    @Deprecated
    public Result<Track> getById(@RequestParam String id) {
        Track track=trackService.getById(id);
        return Result.OK(track);
    }

    @ApiOperation("查询所有")
    @GetMapping
    @Deprecated
    public Result<List<Track>> getAll(Track track) {
        List<Track> trackList = trackService.getAll(track);
        return Result.OK(trackList);
    }

    @ApiOperation("通过id删除")
    @DeleteMapping("/id")
    @Deprecated
    public Result<Object> deleteById(@RequestParam String id) {
        return trackService.deleteById(id);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    @Deprecated
    public Result<Object> batchDelete(@RequestParam List<String> ids) {
        return trackService.batchDelete(ids);
    }
}
