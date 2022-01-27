package com.example.epidemicprevention.module.track.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.track.entity.Track;
import com.example.epidemicprevention.module.track.mapper.TrackMapper;
import com.example.epidemicprevention.module.track.service.TrackService;
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
 *  Track服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-01-28
 */
@Service
public class TrackServiceImpl extends ServiceImpl<TrackMapper, Track> implements TrackService {

    @Autowired
    private TrackMapper trackMapper;

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param track：筛选条件
     * @return IPage<Track>
     */
    @Override
    public IPage<Track> getTrackPage(Integer current, Integer size,Track track) {
        QueryWrapper<Track> queryWrapper = WrapperUtils.queryWrapper(track);
        Page<Track> trackPage = new Page<>(current, size);
        IPage<Track> trackIPage = trackMapper.selectPage(trackPage, queryWrapper);
        return trackIPage;
    }

    /**
     * 查询所有
     * @param track：筛选条件
     * @return List<Track>
     */
    @Override
    public List<Track> getAll(Track track) {
        QueryWrapper<Track> queryWrapper = WrapperUtils.queryWrapper(track);
        return trackMapper.selectList(queryWrapper);
    }

    /**
     * 通过id删除
     * @param id：trackId
     * @return Result<Object>
     */
    @Override
    public Result<Object> deleteById(String id) {
        Track track=trackMapper.selectById(id);
        if (track==null){
            return Result.error(ResponseState.TARGET_NOT_EXIST.getValue(), ResponseState.TARGET_NOT_EXIST.getMessage());
        }
            trackMapper.deleteById(id);
        return Result.OK();
    }

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    @Override
    public Result<Object> batchDelete(List<String> ids) {
        UpdateWrapper<Track> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids);
            trackMapper.delete(updateWrapper);
        return Result.OK();
    }
}