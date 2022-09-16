package ioscar.common.desensitization.pojo;

import lombok.Data;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/22
 */
@Data
public class NormalDto {

    private Long accountId;

    private Long customerId;

    private String userName;

    private Integer age;

    private String sexType;

}
