package ioscar.common.desensitization.aspect;

import cn.hutool.core.date.TimeInterval;
import ioscar.common.desensitization.util.DesensitizedUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/6/28
 */
@Aspect
@Slf4j
public class DesensitizedAspect {


    final TimeInterval timer = new TimeInterval();

    @Pointcut("@annotation(ioscar.common.desensitization.annotation.DesensitizedAnnotation)")
    public void sensitiveCut() {

    }

    @AfterReturning(pointcut = "sensitiveCut()", returning = "data")
    public void doAfterReturning(JoinPoint joinpoint, Object data) throws Exception {

        if (data == null) {
            return;
        }
        timer.start();

        MethodSignature targetMethod = (MethodSignature) joinpoint.getSignature();

        DesensitizedUtil.doDesensitized(targetMethod.getMethod(), data);
        log.info("doAfterReturning {} ms", timer.intervalMs());

    }


}
