package com.zhang.myproject.component;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * create by zhangbo on 2019/11/8 0008
 *
 * 自定义健康端点的指针消息(就是要展示的消息)
 */
@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {

        int errorCode = check();
        if (errorCode == 0) {
            return Health.up()
                         .withDetail("Status"," UP")
                         .withDetail("Error Code",errorCode)
                         .withDetail("Description","自定义健康指针指向UP")
                         .build();
        }

        return null;
    }

    public int check() {
        return 0;
    }
}
