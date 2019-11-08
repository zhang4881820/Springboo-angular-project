package com.zhang.myproject.exception;

import lombok.Data;

import java.awt.TrayIcon;

/**
 * create by zhangbo on 2019/11/1 0001
 * FieldValidationError 和FieldValidationErrorDetail就是处理前置验证错误信息的
 * 就是hibernate的注解验证的返回信息自定义
 * TrayIcon.MessageType 就是包含可能的信息类型的枚举
 */
@Data
public class FieldValidationError {


    private String filed;
    private String message;
//  SUCCESS, INFO, WARNING, ERROR
    private TrayIcon.MessageType type;
}
