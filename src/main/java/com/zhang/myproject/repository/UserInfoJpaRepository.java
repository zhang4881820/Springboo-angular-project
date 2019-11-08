package com.zhang.myproject.repository;

import com.zhang.myproject.dto.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * create by zhangbo on 2019/11/4 0004
 */
public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {

    public UserInfo findByUsername(String username);
}
