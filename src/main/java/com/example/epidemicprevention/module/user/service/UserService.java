package com.example.epidemicprevention.module.user.service;

import com.example.epidemicprevention.module.user.vo.AddUserVO;
import com.example.epidemicprevention.module.user.vo.ChangeUserInfoVO;
import com.example.epidemicprevention.module.user.vo.UserChangePasswordVO;
import com.example.epidemicprevention.module.user.vo.UserLoginVO;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.user.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * User服务类
 * </p>
 *
 * @author zzy
 * @date 2022-01-05
 */
public interface UserService extends IService<User> {
    /**
     * 登录
     *
     * @param userLoginVo
     * @return
     */
    Result<Object> login(UserLoginVO userLoginVo);

    /**
     * 修改密码
     *
     * @param userChangePasswordVo
     * @return
     */
    Result<Object> changePassword(UserChangePasswordVO userChangePasswordVo);

    /**
     * 添加用户
     *
     * @param addUserVo
     * @return
     */
    Result<Object> addUser(AddUserVO addUserVo);

    /**
     * 修改用户信息
     *
     * @param changeUserInfoVo
     * @return
     */
    Result<Object> changeUserInfo(ChangeUserInfoVO changeUserInfoVo);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    Result<Object> deleteUser(String id);

    /**
     * 分页查询
     *
     * @param current：页码
     * @param size：每页条数
     * @param user：筛选条件
     * @return IPage<User>
     */
    IPage<User> getUserPage(Integer current, Integer size, User user);

    /**
     * 查询所有
     *
     * @param user：筛选条件
     * @return List<User>
     */
    List<User> getAll(User user);

    /**
     * 通过id删除
     *
     * @param id：userId
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
