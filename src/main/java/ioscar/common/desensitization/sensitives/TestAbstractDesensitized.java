package ioscar.common.desensitization.sensitives;

import com.alibaba.fastjson.JSON;
import ioscar.common.desensitization.util.IBaseSensitiveProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/16
 */
public abstract class TestAbstractDesensitized implements ISensitive<String> {


    /**
     * 脱敏或不脱敏，规则在这里使用
     *
     * @param property
     * @return
     */
    protected Boolean doOrNot(IBaseSensitiveProperty property) {

        if (StringUtils.isEmpty(property.getStrategyRule().toString())) {
            return true;
        }
        List<String> strategyRule = JSON.parseArray(JSON.toJSONString(property.getStrategyRule()), String.class);

        return !strategyRule.contains(property.getFieldName());
    }


}
