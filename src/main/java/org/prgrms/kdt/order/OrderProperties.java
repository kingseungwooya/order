package org.prgrms.kdt.order;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

@Component
public class OrderProperties implements InitializingBean {

    @Value("${kdt.version}")
    private String version;

    @Value("${kdt.minimum-order-amount}")
    private String minimumOrderMount;

    @Value("${kdt.support-vendors} ")
    private List<String> supportVenders;

    @Value("${JAVA_HOME}")
    private String javaHome;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(MessageFormat.format(" [OrderProperties]  -> {0} ", version));
        System.out.println(MessageFormat.format(" [OrderProperties]  -> {0} ", minimumOrderMount));
        System.out.println(MessageFormat.format(" [OrderProperties]  -> {0} ", supportVenders));
        System.out.println(MessageFormat.format(" [OrderProperties]  -> {0} ", javaHome));
    }
}
