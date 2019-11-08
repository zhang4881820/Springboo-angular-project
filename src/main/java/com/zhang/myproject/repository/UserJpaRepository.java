package com.zhang.myproject.repository;

import com.zhang.myproject.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * create by zhangbo on 2019/10/31 0031.
 * 数据访问层 继承JpaRepository 也就继承里面的数据库操作方法·
 * JpaRepository 填入两个泛型参数UserDTO，Long 相当于T,ID
 * 如果要重新定义方法那么自己写就行 而且不用实现 查询方法。
 * jpa在运行时候自动实现
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserDTO,Long> {

    UserDTO findByName(String name);
}
