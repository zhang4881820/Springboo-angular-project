package com.zhang.myproject.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * create by zhangbo on 2019/11/1 0001
 * @author zhangbo
 * 处理异常使用@ControllerAdvice注解。这个注解被用于定义一个全局异常处理器。
 * 这个处理器针对于那些被@ExceptionHandler注解标注的异常处理方法.
 * MethodArgumentNotValidException 这个异常就是hibernate的验证没通过异常
 * 一个类被@controllerAdvice标注那么将会应用到这个应用程序的所有控制层中
 * 所以在这个应用程序中，任何Controller类抛出的任何异常都将会被@ControllerAdvice这个
 * 注解的类中的@ExceptionHandler注解的方法处理，这个方法仅仅处理Controller类抛出的异常和
 * ExceptionHandler指定的异常类型相匹配的异常
 */
@ControllerAdvice
public class RestValidationHandler {

    private MessageSource messageSource;

    @Autowired
    public RestValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<FieldValidationErrorDetails> handleValidationError(MethodArgumentNotValidException mNotValidException, HttpServletRequest request) {

        FieldValidationErrorDetails fErrorDetails = new FieldValidationErrorDetails();

        fErrorDetails.setError_timeStamp(System.currentTimeMillis());

        fErrorDetails.setError_status(HttpStatus.BAD_REQUEST.value());

        fErrorDetails.setError_title("字段验证错误.");

        fErrorDetails.setError_detail("字段验证未通过");

        fErrorDetails.setError_developer_Message(mNotValidException.getClass().getName());

        fErrorDetails.setError_path(request.getRequestURI());

        BindingResult result = mNotValidException.getBindingResult();

        List<FieldError> fieldErrors = result.getFieldErrors();

        for (FieldError error : fieldErrors) {

            FieldValidationError fError = processFieldError(error);

            List<FieldValidationError> fValidationErrorsList = fErrorDetails.getErrors().get(error.getField());

            if (fValidationErrorsList == null) {

                fValidationErrorsList = new ArrayList<FieldValidationError>();

            }

            fValidationErrorsList.add(fError);

            fErrorDetails.getErrors().put(error.getField(), fValidationErrorsList);

        }
        return new ResponseEntity<FieldValidationErrorDetails>(fErrorDetails, HttpStatus.BAD_REQUEST);
    }

    private FieldValidationError processFieldError(final FieldError error) {

        FieldValidationError fieldValidationError = new FieldValidationError();
        if (error != null) {

//            加载本地定义的错误信息
            Locale currentlocale = LocaleContextHolder.getLocale();
            String message = messageSource.getMessage(error.getDefaultMessage(), null, currentlocale);

            fieldValidationError.setFiled(error.getField());

            fieldValidationError.setType(TrayIcon.MessageType.ERROR);

            fieldValidationError.setMessage(message);

        }
        return fieldValidationError;
    }
}

/**
 *
 *
 *
 *
 */