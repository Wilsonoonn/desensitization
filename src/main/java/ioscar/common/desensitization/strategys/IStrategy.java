package ioscar.common.desensitization.strategys;

import ioscar.common.desensitization.util.IBaseSensitiveProperty;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/4
 */
public interface IStrategy<T> {

    void strategy(T context, IBaseSensitiveProperty property);

}
