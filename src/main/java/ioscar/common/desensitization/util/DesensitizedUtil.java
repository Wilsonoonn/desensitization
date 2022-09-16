package ioscar.common.desensitization.util;

import cn.hutool.core.date.TimeInterval;
import ioscar.common.desensitization.annotation.DesensitizedAnnotation;
import ioscar.common.desensitization.annotation.DesensitizedFieldAnnotation;
import ioscar.common.desensitization.pojo.IDesensitized;
import ioscar.common.desensitization.sensitives.ISensitive;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/6/28
 */
@Slf4j
public class DesensitizedUtil {

    static final TimeInterval timer = new TimeInterval();
    /**
     * 递归的最大深度
     */
    private static final Integer MAX_DEPTH = 10;
    /**
     * 递归深度计数器：防止循环依赖
     */
    private static final ThreadLocal<AtomicInteger> THREAD_LOCAL_COUNTER = new ThreadLocal<>();

    public static void doDesensitized(Method method, Object data) throws Exception {

        initData();
        DesensitizedAnnotation methodAnnotation = getMethodAnnotation(method);

        Constructor<? extends IBaseSensitiveProperty> declaredConstructor
                = methodAnnotation.property().getDeclaredConstructor(DesensitizedAnnotation.class);

        IBaseSensitiveProperty sp = declaredConstructor.newInstance(methodAnnotation);

        handler(data, sp);

        clearThreadLocal();
    }

    public static void doDesensitized(IBaseSensitiveProperty property, Object data) {

        initData();

        handler(data, property);

        clearThreadLocal();
    }

    private static void initData() {

        // 控制递归的深度：防止循环遍历
        THREAD_LOCAL_COUNTER.set(new AtomicInteger(MAX_DEPTH));
    }

    private static void handler(Object obj, IBaseSensitiveProperty sp) {

        if (Objects.isNull(obj) || isBeYouMaxDepth(THREAD_LOCAL_COUNTER.get())) {
            return;
        }
        Object bean = null;
        if (obj instanceof Collection<?>) {

            Boolean isRecursion = false;
            //对集合类型的字段进行递归过滤
            Iterator<?> it = ((Collection<?>) obj).iterator();
            while (it.hasNext()) {
                bean = it.next();
                if (Objects.nonNull(bean) && isNotGeneralType(bean.getClass())) {
                    isRecursion = true;
                    desensitizedOne(bean, sp);
                }
            }
            if (isRecursion) {
                THREAD_LOCAL_COUNTER.get().getAndDecrement();
            }

        } else {
            if (isNotGeneralType(obj.getClass())) {
                THREAD_LOCAL_COUNTER.get().getAndDecrement();
                desensitizedOne(obj, sp);
            }
        }
    }


    private static Boolean isBeYouMaxDepth(AtomicInteger atomicInteger) {
        if (Objects.isNull(atomicInteger)) {
            return Boolean.FALSE;
        }
        if (atomicInteger.get() <= 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private static List<Field> getAllFields(Object obj) {

        Class<?> clazz = obj.getClass();

        if (obj instanceof IDesensitized) {

            List<Field> fieldList = new ArrayList<>();

            while (clazz != null) {
                fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
                clazz = clazz.getSuperclass();
            }
            return fieldList;

        } else {
            Field[] declaredFields = clazz.getDeclaredFields();
            return Arrays.asList(declaredFields);
        }
    }

    public static void desensitizedOne(Object obj, IBaseSensitiveProperty sp) {
        try {

            List<Field> fields = getAllFields(obj);

            if (CollectionUtils.isEmpty(fields)) {
                return;
            }
            if (obj instanceof IDesensitized) {
                sp.setObj(obj);
                //判断是否符合继续执行的逻辑
                if (!sp.doOrNot()) {
                    return;
                }
            }

            timer.start("1");
            Object value;
            for (Field field : fields) {
                field.setAccessible(true);
                value = field.get(obj);

                if (obj instanceof IDesensitized) {
                    if (!sp.doFieldBefore(field)) {
                        continue;
                    }
                }
                if (Objects.isNull(value)) {
                    continue;
                }
                //不是基本数据类型
                if (isNotGeneralType(value.getClass())) {
                    handler(value, sp);

                    if (obj instanceof IDesensitized) {
                        sp.setObj(obj);
                    }
                } else if (field.isAnnotationPresent(DesensitizedFieldAnnotation.class)) {
                    sp.setField(field);
                    timer.start("2");
                    //脱敏操作
                    desensitizedField(field, obj, sp);
                    log.info("desensitizedOne-Field {} ms", timer.intervalMs("2"));
                }
            }

            log.info("desensitized-obj {} ms", timer.intervalMs("1"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 排除基础类型、枚举类型、扩展库类型的字段
     *
     * @param clazz
     * @return
     */
    private static boolean isNotGeneralType(Class<?> clazz) {
        return !clazz.isPrimitive()
                && !clazz.isEnum()
                && Objects.nonNull(clazz.getPackage())
                && !StringUtils.startsWith(clazz.getPackage().getName(), "javax.")
                && !StringUtils.startsWith(clazz.getPackage().getName(), "java.math")
                && !StringUtils.startsWith(clazz.getPackage().getName(), "java.lang")
                && !StringUtils.startsWith(clazz.getPackage().getName(), "java.time")
                && !StringUtils.startsWith(clazz.getName(), "java.util.Date")
                && (!StringUtils.startsWith(clazz.getSuperclass().getName(), "java.util.Date"));

    }

    private static void desensitizedField(Field field, Object obj, IBaseSensitiveProperty sp) {
        try {

            DesensitizedFieldAnnotation fieldAnnotation = field.getAnnotation(DesensitizedFieldAnnotation.class);

            ISensitive bean = ApplicationContextBean.getBean(fieldAnnotation.sensitive());
            assert bean != null;

            Object originalValue = field.get(obj);

            Object value = bean.desensitizedProvider(originalValue, sp);

            field.set(obj, value);
        } catch (Exception e) {
            log.error("DesensitizedUtil->desensitizedField", e);
        }
    }

    public static DesensitizedAnnotation getMethodAnnotation(Method method) {

        return method.getDeclaredAnnotation(DesensitizedAnnotation.class);

    }

    private static void clearThreadLocal() {

        THREAD_LOCAL_COUNTER.remove();
    }


}
