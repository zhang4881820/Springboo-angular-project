package com.zhang.myproject.component;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * create by zhangbo on 2019/11/8 0008
 * 自定义新端点
 */
@Endpoint(id = "mycustomEndpoint")
@Component
public class MyCustomEndpoint {

    @ReadOperation
    public String getMessage(@Selector String name) {
        return "my name is"+name;
    }
    @ReadOperation
    public String getMessage() {
        return "hello,我是自定义的端点";
    }
    @WriteOperation
    public String showMessage() {
        return "hello,我是自定义的端点";
    }
}
