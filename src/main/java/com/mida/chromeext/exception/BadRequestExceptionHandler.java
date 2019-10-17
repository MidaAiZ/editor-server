package com.mida.chromeext.exception;

import com.mida.chromeext.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;


/**
 * 创建统一处理异常的类
 *
 * @author lihaoyu
 * @date 2019/9/28 15:50
 */
@RestControllerAdvice
public class BadRequestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BadRequestExceptionHandler.class);

    /**
     * 校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validationBodyException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                logger.error("Data check failure : object{" + fieldError.getObjectName() + "},field{" + fieldError.getField() +
                        "},errorMessage{" + fieldError.getDefaultMessage() + "}");

            });
        }
        return Result.error(result.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result validationBodyException(ConstraintViolationException exception) {
        String result = exception.getMessage();
        return Result.error(result);
    }

    @ExceptionHandler(BindException.class)
    public Result validationParameterException(BindException exception) {
        return Result.error(exception.getFieldError().getDefaultMessage());
    }
    /**
     * 参数类型转换错误
     *
     * @param exception 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public Result parameterTypeException(HttpMessageConversionException exception) {
        //logger.error(exception.getCause().getLocalizedMessage());
        return Result.error("类型转换错误");
    }


//    @ExceptionHandler(java.lang.Exception.class)
//    public Result handleException(java.lang.Exception e){
//        logger.error(e.getMessage(), e);
//        return Result.error("未知错误");
//    }

    @ExceptionHandler(BaseException.class)
    public Result handleException(BaseException e) {
        logger.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(MyException.class)
    public Result handleException(MyException e) {
        logger.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }
}

