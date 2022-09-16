package ioscar.common.desensitization.sensitives;

import ioscar.common.desensitization.util.IBaseSensitiveProperty;
import org.springframework.stereotype.Component;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/14
 */
@Component
public class TestContactDesensitized extends TestAbstractDesensitized {

    @Override
    public Object desensitizedProvider(String value, IBaseSensitiveProperty property) {

        if (value == null) {
            return value;
        }

        return value.replaceAll("(\\d{3})\\d*(\\d{4})", "$1*********$2");
    }


}
