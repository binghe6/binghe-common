package com.binghe.common.enums;


import com.binghe.common.anno.ResponseCodeAnnotation;

/**
 * 公共返回码
 *
 * @author binghe
 */
@ResponseCodeAnnotation(type = ResponseCodeRegion.COMMON)
public enum ResponseCode implements IResponseCode {
    /**
     * 成功
     */
    SUCCESS(2000, "成功", "成功"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR(5000, "系统异常", "系统异常"),
    /**
     * 其它异常
     */
    ARG_NOT_VALID(1001, "参数校验错误", "参数校验错误");

    private final int code;
    private final String outerMsg;
    private final String innerMsg;

    ResponseCode(int code, String outerMsg, String innerMsg) {
        this.code = code;
        this.outerMsg = outerMsg;
        this.innerMsg = innerMsg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getOuterMsg() {
        return this.outerMsg;
    }

    @Override
    public String getInnerMsg() {
        return this.innerMsg;
    }
}
