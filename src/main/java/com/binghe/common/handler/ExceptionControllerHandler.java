package com.binghe.common.handler;

import com.binghe.common.domain.vo.ApiResponse;
import com.binghe.common.enums.ResponseCode;
import com.binghe.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * controller 统一异常处理
 *
 * @author binghe
 */
@RestControllerAdvice
@Slf4j
public class ExceptionControllerHandler {

    /**
     * Exception 处理
     *
     * @param e 异常对象
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleException(Exception e, HttpServletRequest request) {
        logError("handleException", request.getRequestURI(), e);
        return ApiResponse.buildFail(ResponseCode.SYSTEM_ERROR);
    }

    /**
     * ApiException 处理
     *
     * @param e 异常对象
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public ApiResponse<Void> handleApiException(ApiException e, HttpServletRequest request) {
        logError("handleApiException", request.getRequestURI(), e);
        return ApiResponse.buildFail(e.getResponseCode());
    }

    /**
     * MethodArgumentNotValidException 处理
     *
     * @param e 异常对象
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        logError("handleMethodArgumentNotValidException", request.getRequestURI(), e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        return handleArgNotValid(allErrors);
    }

    /**
     * BindException 处理
     *
     * @param e 异常对象
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ApiResponse<Void> handleBindException(BindException e, HttpServletRequest request) {
        logError("handleBindException", request.getRequestURI(), e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        return handleArgNotValid(allErrors);
    }

    /**
     * ConstraintViolationException 处理
     *
     * @param e 异常对象
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse<Void> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        logError("handleConstraintViolationException", request.getRequestURI(), e);
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return ApiResponse.buildFail(ResponseCode.ARG_NOT_VALID);
        }
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            sb.append(constraintViolation.getMessage()).append(",");
        }
        String msg = sb.toString();
        if (sb.length() > 0) {
            msg = sb.substring(0, sb.length() - 1);
        }
        return ApiResponse.buildFail(new ApiResponse(ResponseCode.ARG_NOT_VALID, msg));
    }

    /**
     * 处理参数不合法
     *
     * @param allErrors 错误信息
     * @return 返回统一响应数据体
     */
    private ApiResponse<Void> handleArgNotValid(List<ObjectError> allErrors) {
        if (CollectionUtils.isEmpty(allErrors)) {
            return ApiResponse.buildFail(ResponseCode.ARG_NOT_VALID);
        }
        StringBuilder sb = new StringBuilder();
        for (ObjectError objectError : allErrors) {
            sb.append(objectError.getDefaultMessage()).append(",");
        }
        String msg = sb.toString();
        if (sb.length() > 0) {
            msg = sb.substring(0, sb.length() - 1);
        }
        return ApiResponse.buildFail(new ApiResponse(ResponseCode.ARG_NOT_VALID, msg));
    }

    /**
     * 打印异常日志
     *
     * @param method 处理异常的方法名
     * @param uri    请求的uri
     * @param e      异常对象
     */
    private void logError(String method, String uri, Exception e) {
        log.info(method + " uri:{}", uri, e);
    }
}
