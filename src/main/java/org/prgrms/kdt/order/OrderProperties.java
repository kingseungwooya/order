package org.prgrms.kdt.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "kdt")
public class OrderProperties implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(OrderProperties.class);

    private String version;

    private String minimumOrderAmount;

    private List<String> supportVendors;

    private String description;


    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info(" [OrderProperties]  -> {0} ", version);
        logger.info(" [OrderProperties]  -> {0} ", minimumOrderAmount);
        logger.info(" [OrderProperties]  -> {0} ", supportVendors);
        logger.info(" [OrderProperties]  -> {0} ", description);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMinimumOrderAmount() {
        return minimumOrderAmount;
    }

    public void setMinimumOrderAmount(String minimumOrderAmount) {
        this.minimumOrderAmount = minimumOrderAmount;
    }

    public List<String> getSupportVendors() {
        return supportVendors;
    }

    public void setSupportVendors(List<String> supportVendors) {
        this.supportVendors = supportVendors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
