package com.zhang.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @SpringBootApplication 这个注解表明是springboot启动类。由springboot 1.2.0添加的注解还增加了如下注解
 *                        1.@configuration 一个类使用这个注解可以被spring容器当作一个定义的bean资源使用，
 *                                          并不是特定于springboot的。这种被这个注解标记的类可以通过带有@bean注解的方法
 *                                          包含一个或者多个bean的声明
 *                        2.@EnableAutoConfiguration 该注解是spring boot项目的一部分，该注解告诉spring boot使用类路径
 *                                                   定义或者设置去添加bean
 *                                                   自动配置可以智能地猜测和配置您可能与应用程序一起运行的bean，
 *                                                   从而简化了开发人员的工作。
 *                                                   例如，假设您在类路径上具有tomcat-embedded.jar，
 *                                                   然后希望使用TomcatEmbeddedServletContainerFactory bean去配置Tomcat服务器。
 *                                                   因此，将自动搜索和配置此文件，而无需任何手动XML配置。
 *
 *                                                   通常，对于Spring MVC应用程序，您可以添加@EnableWebMvc批注，
 *                                                   该批注将应用程序标记为Web应用程序并激活诸如设置DispatcherServlet的关键功能，
 *                                                   但是当Spring Boot在服务器上看到spring-webmvc时，它将自动添加此注解在类路径
 *                                                   同样，将自动添加@EnableTransactionManagement批注，这将启用声明式事务管理
 *                        3.@ComponentScan 这个注释告诉Spring寻找特定的软件包来扫描带注释的组件，配置和服务。
 */

@SpringBootApplication
@RestController
public class HellospringbootApplication {

    public static void main(String[] args) {

        SpringApplication.run(HellospringbootApplication.class, args);
    }

    @RequestMapping("/hello")
    public String greeting() {

        return "hello,world";
    }
}

