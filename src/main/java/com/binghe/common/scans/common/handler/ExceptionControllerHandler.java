package com.binghe.common.scans.common.handler;

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

import java.util.List;

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
    public ApiResponse<Void> handleException(Exception e) {
        log.info(">>>>> handleException", e);
        return ApiResponse.buildFail(ResponseCode.SYSTEM_ERROR);
    }

    /**
     * ApiException 处理
     *
     * @param e 异常对象
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public ApiResponse<Void> handleApiException(ApiException e) {
        log.info(">>>>> handleApiException", e);
        return ApiResponse.buildFail(e.getResponseCode());
    }

    /**
     * MethodArgumentNotValidException 处理
     *
     * @param e 异常对象
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ApiResponse<Void> handleMethodArgumentNotValidException(Exception e) {
        log.info(">>>>> handleMethodArgumentNotValidException", e);
        List<ObjectError> allErrors;
        if (e instanceof MethodArgumentNotValidException) {
            allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
        } else {
            allErrors = ((BindException) e).getBindingResult().getAllErrors();
        }
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
}
