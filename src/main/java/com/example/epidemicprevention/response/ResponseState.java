package com.example.epidemicprevention.response;

/**
 * @Author ZZY
 * @Date 2021/10/26
 */
public enum ResponseState {
    SUCCESS("操作成功", 200),
    TOKEN_NOT_PROVIDE("未传入token", 101),
    TOKEN_IS_ERROR("token错误", 102),
    TOKEN_IS_EXPIRED("token已过期", 103),
    REFRESH_TOKEN_IS_ERROR("refreshToken错误", 104),
    REFRESH_TOKEN_IS_EXPIRED("refreshToken已过期", 105),
    USER_NOT_EXIST("用户不存在", 106),
    PASSWORD_IS_ERROR("密码错误", 107),
    PHONE_IS_EXIST("该手机号已被绑定", 108),
    CODE_NOT_EXIST("验证码未获取或已过期", 109),
    CODE_IS_ERROR("验证码错误", 110),
    PARAM_IS_ERROR("参数错误", 111),
    FILE_FORMAT_ERROR("文件格式错误", 112),
    FILE_STREAM_ERROR("获取文件流失败", 113),
    ACCOUNT_IS_ILLEGAL("该账号涉嫌违规", 114),
    OPERATION_HAI_FINISH("该操作已经完成，请勿重复操作", 116),
    WITHOUT_PERMISSION("该用户无此权限", 117),
    SOME_INFORMATION_INSERT_ERROR("部分信息插入失败", 118),
    EXCEL_IS_ERROR("该EXCEL里没有数据", 119),
    USER_IS_NORMAL("该用户状态正常，未被封禁", 120),
    APPLY_NOT_EXIST("未提交申请", 122),
    USER_IS_EXIST("该用户已存在", 123),
    TARGET_NOT_EXIST("该目标不存在", 124),
    MAIL_IS_EXIST("该邮箱已注册", 125),
    PARENT_NOT_EXIST("父节点不存在", 126),
    HAS_CHILDREN("存在子节点，无法删除", 127),
    DATA_IS_EXIST("该数据已存在", 128),
    CODE_INVALID("微信CODE无效", 129),
    SESSION_KEY_EXPIRED("session_key过期,请重新登录", 130),
    SIGNATURE_CHECKED_FAILURE("签名验证失败", 131),
    UNIT_IS_EXIST("该机构下已存在此单位,无法添加", 132),
    GROUP_IS_EXIST("该单位下已存在此用户组,无法添加", 133),
    USER_STATUS_INVALID("用户状态异常", 134),
    USERNAME_IS_EXIST("该用户名已存在", 135),

    ERROR("操作失败", 500);

    private final String message;
    private final int value;

    ResponseState(String message, int value) {
        this.message = message;
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public int getValue() {
        return value;
    }
}
