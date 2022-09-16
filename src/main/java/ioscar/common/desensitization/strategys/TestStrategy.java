package ioscar.common.desensitization.strategys;

import ioscar.common.desensitization.util.IBaseSensitiveProperty;
import ioscar.common.desensitization.util.TestSensitiveProperty;
import org.springframework.stereotype.Component;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/16
 */
@Component
public class TestStrategy implements IStrategy {

    @Override
    public void strategy(Object context, IBaseSensitiveProperty property) {

        TestSensitiveProperty p = (TestSensitiveProperty) property;
        System.out.println("TestStrategy..");
    }
}
