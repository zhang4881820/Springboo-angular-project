package com.zhang.myproject.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * create by zhangbo on 2019/11/5 0005
 */
@RestController
public class ServiceAuthenticate {

    @RequestMapping("/user")
    public Principal user(Principal user) {

        return user;
    }

}
