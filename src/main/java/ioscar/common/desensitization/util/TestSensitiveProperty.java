package ioscar.common.desensitization.util;

import ioscar.common.desensitization.annotation.DesensitizedAnnotation;
import ioscar.common.desensitization.annotation.DesensitizedFieldAnnotation;
import ioscar.common.desensitization.enums.UsingRuleType;
import ioscar.common.desensitization.pojo.DynamicValue;
import ioscar.common.desensitization.pojo.IDesensitized;
import ioscar.common.desensitization.pojo.TestDesensitizedBaseDto;
import ioscar.common.desensitization.strategys.IStrategy;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/6/28
 */
@Slf4j
public class TestSensitiveProperty implements IBaseSensitiveProperty<TestDesensitizedBaseDto> {

    private final DesensitizedAnnotation annotation;
    private final Class<? extends IStrategy> strategyClass;
    private final UsingRuleType usingRuleType;
    private Object strategyRule;
    private TestDesensitizedBaseDto obj;
    private Field field;
    private String fieldName;
    private Long accountId;
    private Long caseId;
    private Long customerId;
    private Boolean icon;
    private Boolean isClearText = false;

    public TestSensitiveProperty(DesensitizedAnnotation annotation) {
        this.annotation = annotation;
        this.strategyClass = this.getStrategy();
        this.usingRuleType = this.getUsingRuleType();

        if (UsingRuleType.OVERALL.equals(this.usingRuleType)) {
            this.doStrategy();
        }
    }

    @Override
    public Class<? extends IStrategy> getStrategy() {

        return this.strategyClass;
    }

    @Override
    public UsingRuleType getUsingRuleType() {
        return this.usingRuleType;
    }

    @Override
    public TestDesensitizedBaseDto getObj() {
        return this.obj;
    }

    @Override
    public void setObj(TestDesensitizedBaseDto obj) {

        this.obj = obj;
        if (this.obj.getAccountId() != null && this.obj.getCustomerId() != null) {
            this.setAccountId();
            this.setCustomerId();
        }

        if (UsingRuleType.ROW.equals(this.usingRuleType)) {
            this.doStrategy();
        }

    }

    @Override
    public Object getStrategyRule() {
        return this.strategyRule;
    }

    @Override
    public void setStrategyRule(Object strategyRule) {
        this.strategyRule = strategyRule;
    }

    @Override
    public Object getValue() {
        try {
            return field != null ? field.get(this.obj) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
    }

    @Override
    public void setFieldName(String fieldName) {


        this.fieldName = fieldName;
    }

    @Override
    public Field getField() {
        return this.field;
    }

    @Override
    public void setField(Field field) {

        this.field = field;
        DesensitizedFieldAnnotation fieldAnnotation = this.getField().getDeclaredAnnotation(DesensitizedFieldAnnotation.class);
        this.setFieldName(fieldAnnotation.value());
        if (UsingRuleType.FIELD.equals(this.usingRuleType)) {
            this.doStrategy();
        }
    }

    @Override
    public Boolean doOrNot() {

        if (this.obj.getDesensitized()) {
            return false;
        } else {
            this.obj.setDesensitized(true);
            return true;
        }

    }

    @Override
    public Boolean doFieldBefore(Field field) {

        try {
            if (Objects.isNull(field.get(this.obj)) && this.obj instanceof IDesensitized) {
                field.set(this.obj, returnValue(field, null, null));
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Object returnValue(Field field, Object oriValue, Object value) {
        //是否有明文查看权限-案件详情特殊规则
        if (this.getClearText() && isBaseField(field)) {
            DynamicValue dv = new DynamicValue(oriValue, value, this.getIcon());
            return dv;
        }

        return value;
    }

    public Boolean getClearText() {
        return isClearText;
    }

    public void setClearText(Boolean clearText) {
        isClearText = clearText;
    }

    Boolean isBaseField(Field field) {

        return field.getType().getName().contains("java.lang.Object");

    }

    public Boolean getIcon() {
        return icon;
    }

    public void setIcon(Boolean icon) {
        this.icon = icon;
    }

    private void setAccountId() {
        this.accountId = this.obj.getAccountId();

    }

    private void setCustomerId() {
        this.customerId = this.obj.getCustomerId();


    }

    private void doStrategy() {
        IStrategy bean = ApplicationContextBean.getBean(this.strategyClass);
        bean.strategy(this.obj, this);
    }

    public TestSensitiveProperty(Class<? extends IStrategy> strategyClass, UsingRuleType usingRuleType) {
        this.annotation = null;
        this.strategyClass = strategyClass;
        this.usingRuleType = usingRuleType;

        if (UsingRuleType.OVERALL.equals(this.usingRuleType)) {
            this.doStrategy();
        }
    }

    public Long getCustomerId() {

        return this.customerId;
    }

    public Long getAccountId() {

        return this.accountId;
    }

}
