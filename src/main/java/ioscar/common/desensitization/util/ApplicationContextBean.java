package ioscar.common.desensitization.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/15
 */
public class ApplicationContextBean<T> implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext != null ? applicationContext.getBean(clazz) : null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        ApplicationContextBean.applicationContext = applicationContext;

    }


}
