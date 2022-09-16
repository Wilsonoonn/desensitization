package ioscar.common.desensitization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"cn.com.icbf", "ioscar.common.desensitization", "ioscar.common"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DesensitizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesensitizationApplication.class, args);
    }

}
