package ioscar.common.desensitization.enums;

import java.util.concurrent.TimeUnit;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/15
 */
public enum DesensitizedRedisKeyEnum {

    /**
     * DESENSITIZED:案件ID
     */
    DESENSITIZED_ACCOUNT_ID_SET("DESENSITIZED:ACCOUNT:ID:SET.", TimeUnit.MINUTES, 99999L, "DESENSITIZED:案件ID"),

    /**
     * DESENSITIZED:用户ID:案件ID
     */
    DESENSITIZED_USERID_ACCOUNT_ID_HASH("DESENSITIZED:USERID:ACCOUNT:ID:HASH.", TimeUnit.MINUTES, 99999L, "DESENSITIZED:用户ID:案件ID"),


    /**
     * DESENSITIZED:用户ID:案件ID:明文申请ID
     */
    DESENSITIZED_USERID_ACCOUNT_ID_PLAINTEXT_ID_STR("DESENSITIZED:USERID:ACCOUNT:ID:PLAINTEXT:ID:STR.", TimeUnit.MINUTES, 99999L, "DESENSITIZED:用户ID:案件ID:明文申请ID");


    /**
     * redis key
     */
    private final String key;
    /**
     * 过期时间
     */
    private final Long timeout;
    /**
     * 时间单位
     */
    private final TimeUnit timeUnit;
    /**
     * 描述
     */
    private final String desc;


    DesensitizedRedisKeyEnum(String key, TimeUnit timeUnit, Long timeout, String desc) {
        this.timeUnit = timeUnit;
        this.timeout = timeout;
        this.desc = desc;
        this.key = key;
    }


    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public Long getTimeout() {
        return timeout;
    }

}
