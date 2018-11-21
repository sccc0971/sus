package com.hikvision.sus.api.assist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * *********************************************************************
 * 全局异常处理<br/>
 * GlobalExceptionHandler.java <br/>
 * HIK所有，<br/>
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。<br/>
 *
 * @author shaochunchao <br/>
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 不支持HttpMethod异常
     *
     * @param e 异常对象
     * @return RespStatus
     * @author shaochunchao 2018-05-09 21:18:51
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error(e.getMessage(), e);
        return RespStatus.genStatus(RespStatusEnum.MethodNotAllowed);
    }

    /**
     * 缺少参数异常
     *
     * @param e 异常对象
     * @return RespStatus
     * @author shaochunchao 2018-05-09 21:19:25
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object handleMethodArgumentTypeMismatchException(MissingServletRequestParameterException e) {
        logger.error(e.getMessage(), e);
        return RespStatus.genStatus(RespStatusEnum.MessageParametersLack);
    }

    /**
     * 参数类型错误
     *
     * @param e 异常对象
     * @return RespStatus
     * @author shaochunchao 2018-05-09 21:22:14
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Object handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logger.error(e.getMessage(), e);
        return RespStatus.genStatus(RespStatusEnum.BadParameters);
    }

    /**
     * @param e 异常对象
     * @return RespStatus
     * @author shaochunchao 2018-05-09 21:22:14
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        RespStatus rs = RespStatus.genStatus(RespStatusEnum.BeyondARGSRangeLimit);
        if (!e.getBindingResult().getAllErrors().isEmpty() && e.getBindingResult().getAllErrors() != null) {
            rs.setErrorMsg(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        }
        return rs;
    }

    /**
     * 参数验证未通过
     *
     * @param e 异常对象
     * @return RespStatus
     * @author shaochunchao 2018-05-09 21:22:43
     */
    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException e) {
        logger.error(e.getMessage(), e);
        RespStatus rs = RespStatus.genStatus(RespStatusEnum.BeyondARGSRangeLimit);
        if (e.getAllErrors() != null && !e.getAllErrors().isEmpty()) {
            rs.setErrorMsg(e.getAllErrors().get(0).getDefaultMessage());
        }
        return rs;
    }

    /**
     * 通用异常
     *
     * @param e 异常对象
     * @return RespStatus
     * @author shaochunchao 2018-05-09 21:27:26
     */
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return RespStatus.genStatus(RespStatusEnum.UNKNOWN_ERROR);
    }

}
