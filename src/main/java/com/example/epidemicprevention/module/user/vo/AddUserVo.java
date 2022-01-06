package com.example.epidemicprevention.module.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author ZZY
 * @Date 2022/1/6
 */
@Data
public class AddUserVo {
    private static final String PASSWORD_FORMAT_ERROR = "密码8-16个字符,不包含空格,必须包含数字,字母或字符至少两种";
    @NotBlank
    private String username;
    @ApiModelProperty(value = "默认是abc123456")
    @Pattern(regexp = "(?!.*\\s)(?!^[\\u4e00-\\u9fa5]+$)(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{8,16}$",
            message = PASSWORD_FORMAT_ERROR)
    private String password;
}
