package com.zhang.myproject.exception;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by zhangbo on 2019/11/1 0001
 * <p>
 * 生成json格式的错误响应
 */
@Data
public class FieldValidationErrorDetails {

    private String error_title;
    private int error_status;
    private String error_detail;
    private long error_timeStamp;
    private String error_path;
    private String error_developer_Message;
    private Map<String, List<FieldValidationError>> errors = new HashMap<String, List<FieldValidationError>>();

}
