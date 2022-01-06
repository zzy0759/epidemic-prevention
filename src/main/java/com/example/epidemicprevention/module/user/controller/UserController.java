package com.example.epidemicprevention.module.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.epidemicprevention.annotation.UnLogin;
import com.example.epidemicprevention.module.user.vo.AddUserVo;
import com.example.epidemicprevention.module.user.vo.ChangeUserInfoVo;
import com.example.epidemicprevention.module.user.vo.UserChangePasswordVo;
import com.example.epidemicprevention.module.user.vo.UserLoginVo;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.user.entity.User;
import com.example.epidemicprevention.module.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param userLoginVo
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    @UnLogin
    Result<Object> login(UserLoginVo userLoginVo){
        return userService.login(userLoginVo);
    }

    @ApiOperation("修改密码")
    @PutMapping("/changePassword")
    @UnLogin
    Result<Object> changePassword(@Validated @RequestBody UserChangePasswordVo userChangePasswordVo){
        return userService.changePassword(userChangePasswordVo);
    }

    @ApiOperation("添加用户")
    @PreAuthorize("hasRole('SUPER')")
    @PostMapping("/addUser")
    Result<Object> addUser(@Validated @RequestBody AddUserVo addUserVo){
        return userService.addUser(addUserVo);
    }

    @ApiOperation("修改用户信息")
    @PreAuthorize("hasRole('SUPER')")
    @PutMapping("/changeUserInfo")
    Result<Object> changeUserInfo(@Validated @RequestBody ChangeUserInfoVo changeUserInfoVo){
        return userService.changeUserInfo(changeUserInfoVo);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/deleteUser")
    Result<Object> deleteUser(@RequestParam String id){
        return userService.deleteUser(id);
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    @Deprecated
    public Result<IPage<User>> getUserPage(
        @RequestParam(name = "current", defaultValue = "1") Integer current,
        @RequestParam(name = "size", defaultValue = "10") Integer size,
        User user) {
        IPage<User> userPage=userService.getUserPage(current,size,user);
        return Result.OK(userPage);
    }

    @ApiOperation("修改")
    @PutMapping
    @Deprecated
    public Result<User> update(@Validated(User.update.class) @RequestBody User user) {
        userService.updateById(user);
        return Result.OK();
    }

    @ApiOperation("新增")
    @PostMapping
    @Deprecated
    public Result<Object> add(@Validated(User.insert.class) @RequestBody User user) {
        userService.save(user);
        return Result.OK();
    }

    @ApiOperation("通过id查询")
    @GetMapping("/id")
    @Deprecated
    public Result<User> getById(@RequestParam String id){
        User user=userService.getById(id);
        return Result.OK(user);
    }

    @ApiOperation("查询所有")
    @GetMapping
    @Deprecated
    public Result<List<User>> getAll(User user){
        List<User> userList=userService.getAll(user);
        return Result.OK(userList);
    }

    @ApiOperation("通过id删除")
    @DeleteMapping("/id")
    @Deprecated
    public Result<Object> deleteById(@RequestParam String id){
        return userService.deleteById(id);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    @Deprecated
    public Result<Object> batchDelete(@RequestParam List<String> ids){
        return userService.batchDelete(ids);
    }
}
