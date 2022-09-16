package ioscar.common.desensitization.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestContactDto extends TestBaseContactDto {

    private Integer userId;

    private Object obj;

}
