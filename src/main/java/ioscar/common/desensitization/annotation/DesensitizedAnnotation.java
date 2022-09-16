package ioscar.common.desensitization.annotation;

import ioscar.common.desensitization.enums.UsingRuleType;
import ioscar.common.desensitization.strategys.IStrategy;
import ioscar.common.desensitization.util.IBaseSensitiveProperty;

import java.lang.annotation.*;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/6/28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DesensitizedAnnotation {

    Class<? extends IStrategy> strategy();

    UsingRuleType using() default UsingRuleType.ROW;

    Class<? extends IBaseSensitiveProperty> property();

}
