package com.binghe.common.exception;

import com.binghe.common.enums.IResponseCode;
import lombok.Getter;

/**
 * api自定义异常
 *
 * @author binghe
 */
@Getter
public class ApiException extends RuntimeException {

    private IResponseCode responseCode;

    public ApiException(IResponseCode responseCode) {
        super(responseCode.getCode() + ", " + responseCode.getOuterMsg() + ", " + responseCode.getInnerMsg());
        this.responseCode = responseCode;
    }
}