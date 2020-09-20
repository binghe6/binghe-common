package com.binghe.common.domain.vo;

import com.binghe.common.enums.IResponseCode;
import com.binghe.common.enums.ResponseCode;
import lombok.Getter;
import lombok.ToString;

/**
 * api响应体
 *
 * @author binghe
 */
@Getter
@ToString
public class ApiResponse<T> {

    /**
     * 状态码
     */
    private int code;
    /**
     * 展示用信息
     */
    private String outerMsg;
    /**
     * 内部用信息
     */
    private String innerMsg;
    /**
     * 数据
     */
    private T data;

    public ApiResponse(int code, String outerMsg, String innerMsg, T data) {
        this.code = code;
        this.outerMsg = outerMsg;
        this.innerMsg = innerMsg;
        this.data = data;
    }

    public ApiResponse(IResponseCode responseCode, String outerMsg, T data) {
        this.code = responseCode.getCode();
        this.outerMsg = outerMsg;
        this.innerMsg = responseCode.getInnerMsg();
        this.data = data;
    }

    public ApiResponse(IResponseCode responseCode, String outerMsg) {
        this.code = responseCode.getCode();
        this.outerMsg = outerMsg;
        this.innerMsg = responseCode.getInnerMsg();
        this.data = null;
    }

    public static <T> ApiResponse<T> build(IResponseCode responseCode, T data) {
        return new ApiResponse<>(responseCode.getCode(), responseCode.getOuterMsg(), responseCode.getInnerMsg(), data);
    }

    public static <T> ApiResponse<T> buildSuccess(T data) {
        return build(ResponseCode.SUCCESS, data);
    }

    public static <T> ApiResponse<T> buildSuccess() {
        return build(ResponseCode.SUCCESS, null);
    }

    public static <T> ApiResponse<T> buildFail(IResponseCode responseCode) {
        return new ApiResponse<>(responseCode.getCode(), responseCode.getOuterMsg(), responseCode.getInnerMsg(), null);
    }

    public static <T> ApiResponse<T> buildFail(ApiResponse apiResponse) {
        return new ApiResponse<>(apiResponse.getCode(), apiResponse.getOuterMsg(), apiResponse.getInnerMsg(), null);
    }
}
