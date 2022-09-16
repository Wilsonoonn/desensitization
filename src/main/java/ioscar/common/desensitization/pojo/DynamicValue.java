package ioscar.common.desensitization.pojo;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/25
 */
public class DynamicValue {

    private Object originalValue;
    private Object desensitizedValue;
    private Boolean isIcon;

    public DynamicValue() {
    }

    public DynamicValue(Object originalValue, Object desensitizedValue, Boolean isIcon) {
        this.originalValue = originalValue;
        this.desensitizedValue = desensitizedValue;
        this.isIcon = isIcon;
    }

    public Object getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(String originalValue) {
        originalValue = originalValue;
    }

    public Object getDesensitizedValue() {
        return desensitizedValue;
    }

    public void setDesensitizedValue(String desensitizedValue) {
        this.desensitizedValue = desensitizedValue;
    }

    public Boolean getIcon() {
        return isIcon;
    }

    public void setIcon(Boolean icon) {
        isIcon = icon;
    }
}
