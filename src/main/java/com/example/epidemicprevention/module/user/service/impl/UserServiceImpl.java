package com.example.epidemicprevention.module.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.user.entity.User;
import com.example.epidemicprevention.module.user.mapper.UserMapper;
import com.example.epidemicprevention.module.user.service.UserService;
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
 *  User服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-01-05
 */
@Validated
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询
     * @param current：页码
     * @param size：每页条数
     * @param user：筛选条件
     * @return IPage<User>
     */
    @Override
    public IPage<User> getUserPage(Integer current,Integer size,User user){
        QueryWrapper<User> queryWrapper = WrapperUtils.queryWrapper(user);
        Page<User> userPage = new Page<>(current,size);
        IPage<User> userIPage = userMapper.selectPage(userPage, queryWrapper);
        return userIPage;
    }

    /**
     * 查询所有
     * @param user：筛选条件
     * @return List<User>
     */
    @Override
    public List<User> getAll(User user){
        QueryWrapper<User> queryWrapper = WrapperUtils.queryWrapper(user);
        return userMapper.selectList(queryWrapper);
    }

    /**
     * 通过id删除
     * @param id：userId
     * @return Result<Object>
     */
    @Override
    public Result<Object> deleteById(String id){
        User user=userMapper.selectById(id);
        if(user==null){
            return Result.error(ResponseState.TARGET_NOT_EXIST.getValue(), ResponseState.TARGET_NOT_EXIST.getMessage());
        }
        userMapper.deleteById(id);
        return Result.OK();
    }

    /**
    * 批量删除
    * @param ids：id列表
    * @return Result<Object>
    */
    @Override
    public Result<Object> batchDelete(List<String> ids){
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
        updateWrapper.in("id",ids);
        userMapper.delete(updateWrapper);
        return Result.OK();
    }
}