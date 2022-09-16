package ioscar.common.desensitization.sensitives;

import ioscar.common.desensitization.annotation.DesensitizedFieldAnnotation;
import ioscar.common.desensitization.util.IBaseSensitiveProperty;
import ioscar.common.desensitization.util.TestSensitiveProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/28
 */
@Component
public class TestFieldDesensitized extends TestAbstractDesensitized {
    @Override
    public Object desensitizedProvider(String value, IBaseSensitiveProperty property) {

        TestSensitiveProperty p = (TestSensitiveProperty) property;

        try {

            DesensitizedFieldAnnotation fieldAnnotation = p.getField().getAnnotation(DesensitizedFieldAnnotation.class);
            if (StringUtils.isNotEmpty(fieldAnnotation.field())) {
                Field declaredField = p.getObj().getClass().getDeclaredField(fieldAnnotation.field());
                declaredField.setAccessible(true);
                System.out.println(declaredField.get(p.getObj()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
