package ioscar.common.desensitization.sensitives;

import ioscar.common.desensitization.util.IBaseSensitiveProperty;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/6/28
 */
public interface ISensitive<T> {

    Object desensitizedProvider(T value, IBaseSensitiveProperty property);

}
