package com.example.epidemicprevention.interceptor;

import com.alibaba.fastjson.JSON;

import com.example.epidemicprevention.annotation.UnLogin;
import com.example.epidemicprevention.response.ResponseState;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.util.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author ZZY
 * @Date 2021/10/26
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final Logger logger;
    private final JwtUtils jwtUtils;

    @Autowired
    LoginInterceptor(Logger logger, JwtUtils jwtUtils) {
        this.logger = logger;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf8");
        response.setContentType("text/json;charset = UTF-8");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(UnLogin.class)) {
            UnLogin unLogin = method.getAnnotation(UnLogin.class);
            if (unLogin.value()) {
                return true;
            }
        }
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            String result = JSON.toJSONString(Result.error(ResponseState.TOKEN_NOT_PROVIDE.getValue(), ResponseState.TOKEN_NOT_PROVIDE.getMessage()));
            response.getWriter().append(result);
            return false;
        }
        String[] bearer = authorization.split(" ");
        if (!bearer[0].equals("Bearer")) {
            String result = JSON.toJSONString(Result.error(ResponseState.TOKEN_IS_ERROR.getMessage(), ResponseState.TOKEN_IS_ERROR.getValue()));
            response.getWriter().append(result);
            return false;
        }
        String token = bearer[1];
        try {
            UserDetails userDetails = jwtUtils.getUserDetails(token, request);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                    request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (ExpiredJwtException e) {
            logger.warn("token已过期:{}", (Object) e.getStackTrace());
            String Json = JSON.toJSONString(Result.error(ResponseState.TOKEN_IS_EXPIRED.getValue(), ResponseState.TOKEN_IS_EXPIRED.getMessage()));
            response.getWriter().append(Json);
            return false;
        } catch (Exception e) {
            logger.error("发生错误:{}", (Object) e.getStackTrace());
            e.getStackTrace();
            String Json = JSON.toJSONString(Result.error(ResponseState.TOKEN_IS_ERROR.getMessage(), ResponseState.TOKEN_IS_ERROR.getValue()));
            response.getWriter().append(Json);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
