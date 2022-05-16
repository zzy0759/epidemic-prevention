package com.example.epidemicprevention.module.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * User实体类
 *
 * @author zzy
 * @date 2022-01-05
 */
@Data
@AllArgsConstructor
@TableName("user")
public class User {


    public interface insert {
    }

    public interface update {
    }

    public static final int VALID_STATUS = 1;
    public static final int INVALID_STATUS = 0;
    public static final int NORMAL_NUMBER = 1;
    public static final int SUPER_NUMBER = 2;
    public static final String NORMAL_ROLE = "NORMAL";
    public static final String SUPER_ROLE = "SUPER";
    public static final String DEFAULT_PASSWORD = "abc123456";

    @ApiModelProperty(value = "用户表")
    @TableId(type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "passwordEncoder")
    private String password;

    @ApiModelProperty(value = "账号状态")
    private Integer status;

    @ApiModelProperty(value = "1普通2超级")
    private Integer state;

    public User() {
    }

    public User(String username, String encodedPassword) {
        this.username = username;
        this.password = encodedPassword;
    }
}
