package com.binghe.common.enums;

import lombok.Getter;

/**
 * 各项目返回码区间定义
 *
 * @author binghe
 */
@Getter
public enum ResponseCodeRegion {

    /**
     * common
     */
    COMMON(1001, 10000),
    TEST(10001, 11000);

    /**
     * 响应码的最小值（包含）
     */
    private final int min;
    /**
     * 响应码的最大值（包含）
     */
    private final int max;

    ResponseCodeRegion(int min, int max) {
        this.min = min;
        this.max = max;
    }
}
