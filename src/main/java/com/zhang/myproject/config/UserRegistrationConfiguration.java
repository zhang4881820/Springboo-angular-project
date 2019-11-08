package com.zhang.myproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * create by zhangbo on 2019/11/1 0001
 * @Configuration 这是个元注解指定了这个类有一个或多个bean方法通过spring容器去生成
 *                bean 的定义管理
 *                这个bean允许你修改一个message的属性文件，不需要重启应用程序
 * @Bean(name = "messageSource") 定义了一个名字为messageSource作为messageSource
 *
 * ReloadableResourceBundleMessageSource 配置这个类可以属性文件的信息，这个类是一种
 *                                       MessageSource，可以加载一个message属性文件
 *                                       从属性文件中解析信息的keys.
 */
@Configuration
public class UserRegistrationConfiguration {

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
//        这个方法的参数就是属性文件的路径。spring将会搜索一个在messages文件夹下的messages属性文件
        messageBundle.setBasename("classpath:messages/messages");
//        这是设置默认的属性文件里的信息资源的编码方式
        messageBundle.setDefaultEncoding("UTF-8");
        return messageBundle;
    }

}
