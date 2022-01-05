package com.example.epidemicprevention.globle;


import com.example.epidemicprevention.response.ResponseState;
import com.example.epidemicprevention.response.Result;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionController {
    @Autowired
    private Logger logger;

    /**
     * 处理请求参数格式错误的返回信息,form-data格式时
     *
     * @param e：BindException异常
     * @return 111
     */
    @ExceptionHandler
    public Result<Object> BindExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return Result.paramError(message);
    }

    /**
     * 处理请求参数格式错误的返回信息，json格式时
     *
     * @param e：MethodArgumentNotValidException异常
     * @return 111
     */
    @ExceptionHandler
    public Result<Object> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = null;
        for (ObjectError error : errors) {
            if (message != null) {
                message = message + ";" + error.getDefaultMessage();
            } else {
                message = error.getDefaultMessage();
            }
        }
        logger.error(message);
        return Result.paramError(message);
    }

    /**
     * 处理请求参数格式错误的返回信息，param格式时
     *
     * @param e：MethodArgumentNotValidException异常
     * @return 111
     */
    @ExceptionHandler
    public Result<Object> constraintViolationException(ConstraintViolationException e) {
        String message = e.getMessage();
        if(message.contains(":")){
            message=message.split(": ")[1];
        }
        return Result.paramError(message);
    }

    /**
     * 请求参数错误
     *
     * @param e：请求参数错误
     * @return 111
     */
    @ExceptionHandler
    public Result<Object> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error(ResponseState.PARAM_IS_ERROR.getMessage());
        logger.error(e.getLocalizedMessage());
        return Result.paramError();
    }


}
