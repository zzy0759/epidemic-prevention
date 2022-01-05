package com.example.epidemicprevention.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回数据格式
 */
@Data
@ApiModel(value = "接口返回对象", description = "接口返回对象")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志")
    private boolean success = true;

    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String message = "操作成功！";

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private Integer status = 0;

    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "返回数据对象")
    private T result;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

    public Result() {

    }

    public Result<T> success(String message) {
        this.message = message;
        this.status = ResponseState.SUCCESS.getValue();
        this.success = true;
        return this;
    }

    @Deprecated
    public static <T> Result<T> ok() {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setStatus(ResponseState.SUCCESS.getValue());
        r.setMessage(ResponseState.SUCCESS.getMessage());
        return r;
    }

    @Deprecated
    public static <T> Result<T> ok(String msg) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setStatus(ResponseState.SUCCESS.getValue());
        r.setMessage(msg);
        return r;
    }

    @Deprecated
    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setStatus(ResponseState.SUCCESS.getValue());
        r.setMessage(ResponseState.SUCCESS.getMessage());
        r.setResult(data);
        return r;
    }

    public static <T> Result<T> OK() {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setStatus(ResponseState.SUCCESS.getValue());
        r.setMessage(ResponseState.SUCCESS.getMessage());
        return r;
    }

    public static <T> Result<T> OK(T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setStatus(ResponseState.SUCCESS.getValue());
        r.setMessage(ResponseState.SUCCESS.getMessage());
        r.setResult(data);
        return r;
    }

    public static <T> Result<T> OK(String msg, T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setStatus(ResponseState.SUCCESS.getValue());
        r.setMessage(msg);
        r.setResult(data);
        return r;
    }


    public static <T> Result<T> error(String msg, T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(false);
        r.setStatus(ResponseState.ERROR.getValue());
        r.setMessage(msg);
        r.setResult(data);
        return r;
    }

    public static <T> Result<T> error() {
        return error(ResponseState.ERROR.getValue(), ResponseState.ERROR.getMessage());
    }

    public static <T> Result<T> error(ResponseState responseState) {
        return error(responseState.getValue(), responseState.getMessage());
    }

    public static <T> Result<T> error(String msg) {
        return error(ResponseState.ERROR.getValue(), msg);
    }

    public static <T> Result<T> error(int status, String msg) {
        Result<T> r = new Result<>();
        r.setStatus(status);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }


    public Result<T> error500(String message) {
        this.message = message;
        this.status = ResponseState.ERROR.getValue();
        this.success = false;
        return this;
    }


    public void error(Integer status, String message) {
        this.message = message;
        this.status = status;
    }


    /**
     * 无权限访问返回结果
     */
    public static <T> Result<T> noAuth() {
        return error(ResponseState.WITHOUT_PERMISSION.getValue(), ResponseState.WITHOUT_PERMISSION.getMessage());
    }

    /**
     * 参数错误返回结果
     */
    public static <T> Result<T> paramError() {
        return error(ResponseState.PARAM_IS_ERROR.getValue(), ResponseState.PARAM_IS_ERROR.getMessage());
    }

    public static <T> Result<T> paramError(String message) {
        return error(ResponseState.PARAM_IS_ERROR.getValue(), message);
    }


    @JsonIgnore
    private String onlTable;

}