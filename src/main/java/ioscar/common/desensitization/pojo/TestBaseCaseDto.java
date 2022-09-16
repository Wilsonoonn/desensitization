package ioscar.common.desensitization.pojo;

import ioscar.common.desensitization.annotation.DesensitizedFieldAnnotation;
import ioscar.common.desensitization.sensitives.TestDesensitized;

/**
 * 案件脱敏基础信息Dto
 *
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/6
 */
public class TestBaseCaseDto extends TestDesensitizedBaseDto {

    /**
     * 卡号
     */
    @DesensitizedFieldAnnotation(value = "卡号", sensitive = TestDesensitized.class)
    private String entrustIdNumber;
    /**
     * 外包序号
     */
    @DesensitizedFieldAnnotation(value = "外包序号", sensitive = TestDesensitized.class)
    private String entrustNumber;

    public TestBaseCaseDto() {
    }

    public String getEntrustIdNumber() {
        return this.entrustIdNumber;
    }

    public void setEntrustIdNumber(String entrustIdNumber) {

        this.entrustIdNumber = entrustIdNumber;

    }

    public String getEntrustNumber() {
        return this.entrustNumber;
    }

    public void setEntrustNumber(String entrustNumber) {

        this.entrustNumber = entrustNumber;

    }


}
