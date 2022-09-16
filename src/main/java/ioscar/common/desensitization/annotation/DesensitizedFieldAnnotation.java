package ioscar.common.desensitization.annotation;

import ioscar.common.desensitization.sensitives.ISensitive;

import java.lang.annotation.*;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/6/28
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DesensitizedFieldAnnotation {

    String value() default "";

    Class<? extends ISensitive> sensitive();

    String field() default "";
}
