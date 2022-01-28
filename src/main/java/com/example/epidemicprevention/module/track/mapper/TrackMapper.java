package com.example.epidemicprevention.module.track.mapper;

import com.example.epidemicprevention.module.track.entity.Track;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * TrackMapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-01-28
 */
@Mapper
public interface TrackMapper extends BaseMapper<Track> {
    List<Track> trackListDesc(String patientId);
}
