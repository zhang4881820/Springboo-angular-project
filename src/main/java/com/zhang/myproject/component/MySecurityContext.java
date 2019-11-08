package com.zhang.myproject.component;

import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * create by zhangbo on 2019/11/8 0008
 */
@Component
public class MySecurityContext implements SecurityContext {

    @Override
    public Principal getPrincipal() {

//        获取取得认证的人的principal
        Principal principal = (Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;

    }

    @Override
    public boolean isUserInRole(String role) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String authorities = userDetails.getAuthorities().toString();
        if (authorities.contains(role)) {
            return true;
        }
        return false;
    }
}
