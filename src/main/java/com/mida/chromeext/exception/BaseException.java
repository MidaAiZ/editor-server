package com.mida.chromeext.exception;

/**
 * @author lihaoyu
 * @date 2019/9/19 13:56
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -13345478420L;

    protected String code;

    protected String message;

    public BaseException(ExceptionEnum exceptionEnum){
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

}
