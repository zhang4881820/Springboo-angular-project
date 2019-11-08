package com.zhang.myproject.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * create by zhangbo on 2019/11/4 0004
 */
@Entity
@Table(name = "users1")
@Data
public class UserInfo {

    @Id
    @GeneratedValue
    @Column(name = "userid")
    private Long id;

    @Column(name = "username")
    @NotEmpty
    private String username;

    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "enabled")
    private boolean isEnabled;

    @Column(name = "role")
    private String role;
}
