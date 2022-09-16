package ioscar.common.desensitization.util;

import ioscar.common.desensitization.enums.UsingRuleType;
import ioscar.common.desensitization.strategys.IStrategy;

import java.lang.reflect.Field;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/19
 */
public interface IBaseSensitiveProperty<T> {

    Class<? extends IStrategy> getStrategy();

    UsingRuleType getUsingRuleType();

    T getObj();

    void setObj(T obj);

    Object getStrategyRule();

    void setStrategyRule(Object strategyRule);

    Object getValue() throws IllegalAccessException;

    String getFieldName();

    void setFieldName(String fieldName);

    Field getField();

    void setField(Field field);

    Boolean doOrNot();

    Boolean doFieldBefore(Field field);

}
