//package com.zhang.myproject.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
///**
// * create by zhangbo on 2019/11/4 0004
// *
// * 配置权限验证
// */
//@Configuration
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
//public class SpringSecurityConfiguration_InMemory extends WebSecurityConfigurerAdapter {
//
//    /**
//     *
//     * @param auth
//     * @throws Exception
//     * 在内存中添加用户密码角色
//     */
//    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder()).withUser("user")
//                .password(new BCryptPasswordEncoder().encode("password")).roles("USER");
//
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder()).withUser("admin")
//                .password(new BCryptPasswordEncoder().encode("password")).roles("ADMIN");
//
//    }
//
//    /**
//     *
//     * @param http
//     * @throws Exception
//     * 配置REST API 访问的指定角色 并且不允许跨域
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.httpBasic()
//                .realmName("User Registration System")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/login/login.html","/template/home.html","/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//////                .antMatchers(HttpMethod.GET,"/api/user/")
//////                .hasRole("USER")
//////                .antMatchers(HttpMethod.POST,"/api/user/")
//////                .hasRole("USER")
//////                .antMatchers(HttpMethod.PUT,"/api/user/**")
//////                .hasRole("USER")
//////                .antMatchers(HttpMethod.DELETE,"/api/user/**")
//////                .hasRole("ADMIN")
//////                .and().csrf().disable();
////
//    }
//}
