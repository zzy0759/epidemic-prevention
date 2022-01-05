package com.example.epidemicprevention.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author ZZY
 * @Date 2021/10/27
 */
@Component
public class UserUtil {

    public static String getLoginUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User principal = (User) authentication.getPrincipal();
            return principal.getUsername();
        } catch (Exception e) {
            return "unLogin";
        }
    }

    public static Collection<GrantedAuthority> getLoginUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        return principal.getAuthorities();
    }
}
