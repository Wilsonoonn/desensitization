package ioscar.common.desensitization.pojo;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/15
 */
public class DynamicTestDesensitizedBaseDto implements IDesensitized {

    private Long accountId;
    private Long customerId;
    private Boolean isDesensitized = false;

    public DynamicTestDesensitizedBaseDto() {
    }

    public Boolean getDesensitized() {
        return isDesensitized;
    }

    public void setDesensitized(Boolean desensitized) {
        isDesensitized = desensitized;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long accountId) {

        this.accountId = accountId;
    }

    public Long getCustomerId() {

        return this.customerId;
    }

    public void setCustomerId(Long customerId) {

        this.customerId = customerId;

    }


}
