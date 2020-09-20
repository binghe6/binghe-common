package com.binghe.common.anno;


import com.binghe.common.enums.ResponseCodeRegion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * response code 注解类
 *
 * @author binghe
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseCodeAnnotation {

    ResponseCodeRegion type();
}
