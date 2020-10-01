package com.binghe.common.handler;

import com.binghe.common.anno.IgnoreApiResponseAnnotation;
import com.binghe.common.domain.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * controller 全局处理响应数据
 *
 * @author binghe
 */
@RestControllerAdvice
@Slf4j
public class ResponseControllerHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // 标注IgnoreApiResponseAnnotation注解的接口不封装
        // 已经封装为ApiResponse的接口不重复封装
        return !methodParameter.hasMethodAnnotation(IgnoreApiResponseAnnotation.class) &&
                !ApiResponse.class.equals(methodParameter.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return ApiResponse.buildSuccess(o);
    }
}
