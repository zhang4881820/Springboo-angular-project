package com.zhang.myproject.service;

import com.zhang.myproject.dto.UserInfo;
import com.zhang.myproject.repository.UserInfoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * create by zhangbo on 2019/11/4 0004
 *
 */
@Service
public class UserInfoDetailService implements UserDetailsService {

    @Autowired
    private UserInfoJpaRepository userInfoJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = userInfoJpaRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名找不到"+username);
        }
        return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),getAuthorities(user));
    }

    private Collection<GrantedAuthority> getAuthorities(UserInfo user) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities = AuthorityUtils.createAuthorityList(user.getRole());
        return authorities;
    }
}
