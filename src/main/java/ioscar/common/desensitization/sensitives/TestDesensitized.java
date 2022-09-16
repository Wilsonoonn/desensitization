package ioscar.common.desensitization.sensitives;

import ioscar.common.desensitization.entity.TestCaseDto;
import ioscar.common.desensitization.util.IBaseSensitiveProperty;
import ioscar.common.desensitization.util.TestSensitiveProperty;
import org.springframework.stereotype.Component;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/19
 */
@Component
public class TestDesensitized extends TestAbstractDesensitized {

    @Override
    public Object desensitizedProvider(String value, IBaseSensitiveProperty property) {
        if (value == null) {
            return value;
        }
        System.out.println("TestDesensitized..");

        TestCaseDto obj = (TestCaseDto) property.getObj();

        TestSensitiveProperty property1 = (TestSensitiveProperty) property;

        //if ("张三".equals(obj.getUserName())) {
        return property1.returnValue(property1.getField(), property1.getValue(), value.replaceAll("(\\d{3})\\d*(\\d{4})", "$1*********$2"));
//        }
//
//        return value;
    }
}
