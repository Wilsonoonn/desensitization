package ioscar.common.desensitization.pojo;

import ioscar.common.desensitization.annotation.DesensitizedFieldAnnotation;
import ioscar.common.desensitization.sensitives.TestDesensitized;

/**
 * 联系资料脱敏基础信息Dto
 *
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/6
 */

public class TestBaseContactDto extends TestDesensitizedBaseDto {

    /**
     * 资料内容
     */
    @DesensitizedFieldAnnotation(value = "资料内容", sensitive = TestDesensitized.class)
    private String contact;
    /**
     * 资料类型(实际资料类型)
     */
    private String contactTypeName;
    /**
     * 原始资料类型
     */
    private String oriContactTypeName;
    /**
     * 与债务人关系
     */
    private String relationName;

    public TestBaseContactDto() {
    }

    public String getOriContactTypeName() {
        return oriContactTypeName;
    }

    public void setOriContactTypeName(String oriContactTypeName) {
        this.oriContactTypeName = oriContactTypeName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactTypeName() {
        return contactTypeName;
    }

    public void setContactTypeName(String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }


}
