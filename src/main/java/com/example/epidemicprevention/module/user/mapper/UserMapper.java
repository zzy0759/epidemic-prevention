package com.example.epidemicprevention.module.user.mapper;

import com.example.epidemicprevention.module.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * UserMapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-01-05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
