package com.example.epidemicprevention.module.track.service;

import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.track.entity.Track;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Track服务类
 * </p>
 *
 * @author zzy
 * @date 2022-01-28
 */
public interface TrackService extends IService<Track> {

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param track：筛选条件
     * @return IPage<Track>
     */
    IPage<Track> getTrackPage(Integer current, Integer size,Track track);

    /**
     * 查询所有
     * @param track：筛选条件
     * @return List<Track>
     */
    List<Track> getAll(Track track);

    /**
     * 通过id删除
     * @param id：trackId
     * @return Result<Object>
     */
    Result<Object> deleteById(String id);

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    Result<Object> batchDelete(List<String> ids);
}
