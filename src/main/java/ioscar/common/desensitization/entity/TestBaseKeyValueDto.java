package ioscar.common.desensitization.entity;

import ioscar.common.desensitization.annotation.DesensitizedFieldAnnotation;
import ioscar.common.desensitization.pojo.TestDesensitizedBaseDto;
import ioscar.common.desensitization.sensitives.TestKeyValueDesensitized;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/19
 */
public class TestBaseKeyValueDto extends TestDesensitizedBaseDto {

    private String keyName;


    @DesensitizedFieldAnnotation(sensitive = TestKeyValueDesensitized.class)
    private Object keyValue;

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public Object getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(Object keyValue) {
        this.keyValue = keyValue;
    }


}
