package ioscar.common.desensitization.entity;

import ioscar.common.desensitization.annotation.DesensitizedFieldAnnotation;
import ioscar.common.desensitization.pojo.DynamicTestBaseCaseDto;
import ioscar.common.desensitization.sensitives.TestDesensitized;
import ioscar.common.desensitization.sensitives.TestFieldDesensitized;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/6/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseDto extends DynamicTestBaseCaseDto {

    @DesensitizedFieldAnnotation(value = "手机", sensitive = TestDesensitized.class)
    private Object phone;

    private List<TestBaseKeyValueDto> estBaseKeyValueDtos;

    @DesensitizedFieldAnnotation(value = "证件号", sensitive = TestDesensitized.class)
    private Object idCard;
    @DesensitizedFieldAnnotation(value = "地址", sensitive = TestDesensitized.class)
    private Object address;
    @DesensitizedFieldAnnotation(value = "姓名", sensitive = TestFieldDesensitized.class, field = "idCard")
    private Object userName;
    private Object age;

    private TestCaseDto entity;

    private List<TestCaseDto> entityList;


}
