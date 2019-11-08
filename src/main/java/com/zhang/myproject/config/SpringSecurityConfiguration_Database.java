package com.zhang.myproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * create by zhangbo on 2019/11/4 0004
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration_Database extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userInfoDetailService;

//    @Autowired
//    private MyCorsConfiguration myCorsConfiguration;


    /**
     * spring security5x 的特性 需要一个加密对象
     * @param auth
     * @throws Exception
     *
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userInfoDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        MyCorsConfiguration myCorsConfiguration = new MyCorsConfiguration();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .realmName("User Registration System")
                .and()
//        对监控端点取消安全验证
//                .requestMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeRequests()
                .antMatchers("/api/user/**","/css/**", "/js/**", "/images/**", "/webjars/**","/login/login.html","/template/home.html","/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable()
                .cors()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .configurationSource(myCorsConfiguration.corsConfigurationSource());
    }
}
