package com.example.epidemicprevention.module.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("user")
public class User {

public interface insert {
}

public interface update {
}

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


}
