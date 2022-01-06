package com.example.epidemicprevention.module.user.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author ZZY
 * @Date 2022/1/6
 */
@Data
public class UserLoginVo {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
