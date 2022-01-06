package com.example.epidemicprevention.module.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.user.entity.User;
import com.example.epidemicprevention.module.user.mapper.UserMapper;
import com.example.epidemicprevention.module.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.epidemicprevention.module.user.vo.AddUserVo;
import com.example.epidemicprevention.module.user.vo.ChangeUserInfoVo;
import com.example.epidemicprevention.module.user.vo.UserChangePasswordVo;
import com.example.epidemicprevention.module.user.vo.UserLoginVo;
import com.example.epidemicprevention.response.ResponseState;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.util.JwtUtils;
import com.example.epidemicprevention.util.WrapperUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.val;
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
 * User服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-01-05
 */
@Validated
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Result<Object> login(UserLoginVo userLoginVo) {
        final String username = userLoginVo.getUsername();
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);
        final List<User> userList = userMapper.selectList(lambdaQueryWrapper);
        if (userList.isEmpty()) {
            return Result.error(ResponseState.USER_NOT_EXIST);
        }
        final User user = userList.get(0);
        if (user.getStatus() != User.INVALID_STATUS) {
            return Result.error(ResponseState.USER_STATUS_INVALID);
        }
        if (!passwordEncoder.matches(userLoginVo.getPassword(), user.getPassword())) {
            return Result.error(ResponseState.PASSWORD_IS_ERROR);
        }
        final String accessToken = jwtUtils.getAccessToken(user.getId(), user.getState() == User.NORMAL_NUMBER ? User.NORMAL_ROLE : User.SUPER_ROLE);
        return Result.OK(accessToken);
    }

    @Override
    public Result<Object> changePassword(UserChangePasswordVo userChangePasswordVo) {
        //fixme java还能这样写?奇怪的知识又增加了
        val id = userChangePasswordVo.getId();
        val user = userMapper.selectById(id);
        if (!passwordEncoder.matches(userChangePasswordVo.getOldPassword(), user.getPassword())) {
            return Result.error(ResponseState.PASSWORD_IS_ERROR);
        }
        user.setPassword(passwordEncoder.encode(userChangePasswordVo.getNewPassword()));
        userMapper.updateById(user);
        return Result.OK();
    }

    @Override
    public Result<Object> addUser(AddUserVo addUserVo) {
        final String username = addUserVo.getUsername();
        final Integer count = userMapper.selectCount(WrapperUtils.getLambdaQueryWrapper(User::getUsername, username));
        if (count != 0) {
            return Result.error(ResponseState.USERNAME_IS_EXIST);
        }
        String password;
        if (addUserVo.getPassword() == null) {
            password = passwordEncoder.encode(User.DEFAULT_PASSWORD);
        } else {
            password = passwordEncoder.encode(addUserVo.getPassword());
        }
        User user = new User(username, password);
        userMapper.insert(user);
        return Result.OK();
    }

    @Override
    public Result<Object> changeUserInfo(ChangeUserInfoVo changeUserInfoVo) {
        User user = new User();
        user.setId(changeUserInfoVo.getId());
        user.setPassword(passwordEncoder.encode(changeUserInfoVo.getPassword()));
        userMapper.updateById(user);
        return Result.OK();
    }

    @Override
    public Result<Object> deleteUser(String id) {
        userMapper.deleteById(id);
        return Result.OK();
    }

    /**
     * 分页查询
     *
     * @param current：页码
     * @param size：每页条数
     * @param user：筛选条件
     * @return IPage<User>
     */
    @Override
    public IPage<User> getUserPage(Integer current, Integer size, User user) {
        QueryWrapper<User> queryWrapper = WrapperUtils.queryWrapper(user);
        Page<User> userPage = new Page<>(current, size);
        IPage<User> userIPage = userMapper.selectPage(userPage, queryWrapper);
        return userIPage;
    }

    /**
     * 查询所有
     *
     * @param user：筛选条件
     * @return List<User>
     */
    @Override
    public List<User> getAll(User user) {
        QueryWrapper<User> queryWrapper = WrapperUtils.queryWrapper(user);
        return userMapper.selectList(queryWrapper);
    }

    /**
     * 通过id删除
     *
     * @param id：userId
     * @return Result<Object>
     */
    @Override
    public Result<Object> deleteById(String id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.error(ResponseState.TARGET_NOT_EXIST.getValue(), ResponseState.TARGET_NOT_EXIST.getMessage());
        }
        userMapper.deleteById(id);
        return Result.OK();
    }

    /**
     * 批量删除
     *
     * @param ids：id列表
     * @return Result<Object>
     */
    @Override
    public Result<Object> batchDelete(List<String> ids) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids);
        userMapper.delete(updateWrapper);
        return Result.OK();
    }
}