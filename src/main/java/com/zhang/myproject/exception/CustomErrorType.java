package com.zhang.myproject.exception;

import com.zhang.myproject.dto.UserDTO;
import lombok.Getter;

/**
 * create by zhangbo on 2019/11/1 0001
 * 自定义UserDTO异常
 */
@Getter
public class CustomErrorType extends UserDTO {

    private String errorMessage;

    public CustomErrorType(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
